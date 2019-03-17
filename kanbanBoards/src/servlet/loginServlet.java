package servlet;

import dao.AccountDao;
import dao.impl.AccountDaoImpl;
import model.Account;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String inputID = request.getParameter("inputID");
        String inputPassword = DigestUtils.sha512Hex(request.getParameter("inputPassword"));
        AccountDao accountDao = new AccountDaoImpl();
        try{
            Account account = accountDao.getAccountByRegNumber(inputID);
            if(account == null || !account.isExist()){
                request.setAttribute("error", "帳號不存在");
                request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
                return;
            }
            else if(account.authenticate(inputPassword)){
                session.setAttribute("account", account);
                int roleCode = account.getRoleCode();
                switch(roleCode) {
                    case 0:
                        //request.getRequestDispatcher("/WEB-INF/jsp/adminMain.jsp").forward(request, response);
                        response.sendRedirect("adminMain");
                        break;
                    case 1:
                        response.sendRedirect("PoMain");
                        break;
                    case 2:
                        //request.getRequestDispatcher("/WEB-INF/jsp/developMain.jsp").forward(request, response);
                        response.sendRedirect("developMain");
                        break;
                }
                return;
            }
        }catch (SQLException e){
            request.setAttribute("error", "資料庫錯誤，無法關閉連線");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            return;
        }
        response.sendRedirect("login");
    }
}