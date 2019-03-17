package servlet;

import dao.InfoDao;
import dao.ProjectDao;
import dao.impl.InfoDaoImpl;
import dao.impl.ProjectDaoImpl;
import model.Account;
import util.AuthCheck;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/projectDetail")
public class projectDetailServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (!AuthCheck.isAuth(request, 1)) {
            response.sendRedirect("login");
            return;
        }
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        response.addHeader("Access-Control-Allow-Origin", "*");
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.addHeader("Access-Control-Allow-Credentials", "true");
        }
        request.getRequestDispatcher("/WEB-INF/jsp/projectDetail.jsp").forward(request, response);
    }
}
