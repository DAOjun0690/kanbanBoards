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

@WebServlet("/userInformation")
@MultipartConfig
public class userInformationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(!AuthCheck.isAuth(request, 0) && !AuthCheck.isAuth(request, 1) && !AuthCheck.isAuth(request, 2)) {
            response.sendRedirect("login");
            return;
        }
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        ImageDao imageDao = new ImageDaoImpl();
        String imagePath = "\\head_images\\" + account.getIdNumber() + "\\";
        String imageFileName = "";
        try {
            imageFileName = imageDao.getImage(account.getIdNumber());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(imageFileName != null && imageFileName.length() > 0){
            request.setAttribute("imagePath", imagePath + imageFileName);
        }
        else request.setAttribute("imagePath", "");
        request.getRequestDispatcher("/WEB-INF/jsp/userInformation.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        String checknumber = request.getParameter("checknumber");
        if(checknumber.equals("0"))
        {
            try {
                Part file = request.getPart("image");
                Account account = (Account) session.getAttribute("account");
                Upload uploadImage = new Upload();
                String rootPath = this.getServletContext().getRealPath("/head_images");
                String fileName = uploadImage.uploadFile(account, file, "image", rootPath);
                ImageDao imageDao = new ImageDaoImpl();
                imageDao.uploadImage(account.getIdNumber(), fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(checknumber.equals("1"))
        {
            String regNumber = request.getParameter("regNumber");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String english_name = request.getParameter("english_name");
            String chinese_name = request.getParameter("chinese_name");
            String nickname = request.getParameter("nickname");

            try {
                /*圖片修改*/
                Part file = request.getPart("image1");
                Account account = (Account) session.getAttribute("account");
                Upload uploadImage = new Upload();
                String rootPath = this.getServletContext().getRealPath("/head_images");
                String fileName = uploadImage.uploadFile(account, file, "image", rootPath);

                ImageDao imageDao = new ImageDaoImpl();
                imageDao.uploadImage(account.getIdNumber(), fileName);


                /* 表格資料修改*/
                AccountDao accountDao = new AccountDaoImpl();
                accountDao.editToDB(regNumber, email, phone, english_name, chinese_name, nickname);

                String inputID = regNumber;
                Account account1 = accountDao.getAccountByRegNumber(inputID);
                session.setAttribute("account", account1);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("userInformation");
    }

}
