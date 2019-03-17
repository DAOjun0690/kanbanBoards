package servlet;

import dao.AccountDao;
import dao.ImageDao;
import dao.impl.AccountDaoImpl;
import dao.impl.ImageDaoImpl;
import model.Account;
import util.AuthCheck;
import util.Upload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.rmi.ServerException;
import java.sql.SQLException;

@WebServlet("/addTaskCard")
public class addTaskCardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        AccountDao accountDao = new AccountDaoImpl();

        String taskID = request.getParameter("taskID");
        int taskIDint = Integer.parseInt(taskID);
        session.setAttribute("taskIDjsp", taskIDint);


        request.getRequestDispatcher("/WEB-INF/jsp/addTaskCard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

    }


}
