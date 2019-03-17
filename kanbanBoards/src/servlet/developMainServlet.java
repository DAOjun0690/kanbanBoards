package servlet;

import dao.InfoDao;
import dao.impl.InfoDaoImpl;
import model.Account;
import model.Info;
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

@WebServlet("/developMain")
public class developMainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(!AuthCheck.isAuth(request, 2)) {
            response.sendRedirect("login");
            return;
        }
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        InfoDao infoDao = new InfoDaoImpl();
        try {
            List<Info> infoList = infoDao.getInfoList(account.getIdNumber());
            request.setAttribute("infoList", infoList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/WEB-INF/jsp/developMain.jsp").forward(request, response);
    }
}
