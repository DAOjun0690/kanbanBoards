package dao.impl;

import dao.AccountDao;
import dao.InfoDao;
import dbconn.DbConn;
import model.Account;
import model.Info;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InfoDaoImpl implements InfoDao {

    private final Connection pcon = DbConn.getConnection(DbConn.KANBAN);

    public InfoDaoImpl(){

    }
    @Override
    public List<Info> getInfoList(int id_no) throws SQLException {
        String sql = "SELECT * FROM kanban.account WHERE id_number = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Info> infoList = new ArrayList<Info>();
        int i = 1;
        try{
            preparedStatement = pcon.prepareStatement(sql);
            preparedStatement.setInt(1, id_no);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                if(resultSet.getString("password").compareTo(DigestUtils.sha512Hex(Integer.toString(id_no))) == 0) {
                    infoList.add(new Info(i++, "<a href = 'userInformation'>密碼尚未更改</a>"));
                }
                if(resultSet.getString("head_image") == null || resultSet.getString("head_image").length() == 0){
                    infoList.add(new Info(i++, "<a href = 'userInformation'>大頭貼尚未上傳</a>"));
                }
                if(resultSet.getString("nickname") == null || resultSet.getString("nickname").length() == 0){
                    infoList.add(new Info(i++, "<a href = 'userInformation'>暱稱尚未設定</a>"));
                }
                if(resultSet.getString("email") == null || resultSet.getString("email").length() == 0){
                    infoList.add(new Info(i++, "<a href = 'userInformation'>電子信箱尚未設定</a>"));
                }
                if(resultSet.getString("phone") == null || resultSet.getString("phone").length() == 0){
                    infoList.add(new Info(i++, "<a href = 'userInformation'>電話尚未設定</a>"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return infoList;
    }
}
