package servlet;

import dao.AccountDao;
import dao.impl.AccountDaoImpl;
import model.Account;
import org.apache.commons.codec.digest.DigestUtils;
import util.AuthCheck;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/accountManage")
public class accountManageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(!AuthCheck.isAuth(request, 0)) {
            response.sendRedirect("login");
            return;
        }
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        AccountDao accountDao = new AccountDaoImpl();
        try {
            List<Account> accounts = accountDao.getAccountList(account.getRoleCode());
            session.setAttribute("memberList", accounts);
            int latestAccountId = accountDao.getLatestAccountId(account.getRoleCode());
            session.setAttribute("latestId", latestAccountId+1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/WEB-INF/jsp/accountManage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String regNumber = request.getParameter("account");
        String chinese_name = request.getParameter("chinese_name");
        String english_name = request.getParameter("english_name");
        String role_code = request.getParameter("role_code");
//        System.out.println(regNumber);
//        System.out.println(chinese_name);
//        System.out.println(english_name);
//        System.out.println(role_code);
        String role_name = "";
        switch(Integer.parseInt(role_code)){
            case 1:
                role_name = "產品負責人";
                break;
            case 2:
                role_name = "開發者";
                break;
        }
        try {
            Account account = new Account(Integer.parseInt(regNumber), "", regNumber, "", chinese_name, english_name, "", Integer.parseInt(role_code), role_name, "", "");
            AccountDao accountDao = new AccountDaoImpl();
            accountDao.insertToDB(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("accountManage");
    }
}
