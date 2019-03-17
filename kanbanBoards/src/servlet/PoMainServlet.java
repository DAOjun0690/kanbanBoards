package servlet;

import dao.AccountDao;
import dao.ImageDao;
import dao.InfoDao;
import dao.ProjectDao;
import dao.impl.AccountDaoImpl;
import dao.impl.ImageDaoImpl;
import dao.impl.InfoDaoImpl;
import dao.impl.ProjectDaoImpl;
import model.Account;
import model.Info;
import model.Project;
import util.AuthCheck;
import util.Upload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/PoMain")
public class PoMainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(!AuthCheck.isAuth(request, 1)) {
            response.sendRedirect("login");
            return;
        }
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        InfoDao infoDao = new InfoDaoImpl();
        ProjectDao projectDao = new ProjectDaoImpl();
        AccountDao accountDao = new AccountDaoImpl();
        try {
            List<Info> infoList = infoDao.getInfoList(account.getIdNumber());
            request.setAttribute("infoList", infoList);
            List<Project> projectList = projectDao.getProjectList(account.getIdNumber());
            request.setAttribute("projectList", projectList);
            List<Account> developerList = accountDao.getDeveloperList();
            request.setAttribute("developerList", developerList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/WEB-INF/jsp/PoMain.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String project_name = request.getParameter("project_name");
        String po_id = request.getParameter("po_id");
        String start_date = request.getParameter("start_date");
        String end_date = request.getParameter("end_date");
        String[] developer = request.getParameterValues("developer");
        String developers = "";
        for(int i = 0; i < developer.length; i++){
            if(i != 0) developers += ",";
            developers += developer[i];
        }
        try{
            ProjectDao projectDao = new ProjectDaoImpl();
            projectDao.insertProject(project_name, po_id, start_date, end_date, developers);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("PoMain");
    }
}
