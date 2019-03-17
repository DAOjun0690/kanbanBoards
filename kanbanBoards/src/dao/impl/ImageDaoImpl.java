package dao.impl;

import dao.ImageDao;
import dbconn.DbConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageDaoImpl implements ImageDao {
    private final Connection pcon = DbConn.getConnection(DbConn.KANBAN);
    public ImageDaoImpl(){

    }
    @Override
    public void uploadImage(int id_no, String imagePath) throws SQLException {
        String sql = "UPDATE kanban.account SET head_image = ? WHERE id_number = ?";
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = pcon.prepareStatement(sql);
            preparedStatement.setString(1, imagePath);
            preparedStatement.setInt(2, id_no);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
        }

    }

    @Override
    public String getImage(int id_no) throws SQLException {
        String sql = "SELECT head_image FROM kanban.account WHERE id_number = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String image = "";
        try{
            preparedStatement = pcon.prepareStatement(sql);
            preparedStatement.setInt(1, id_no);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                image = resultSet.getString("head_image");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
        }
        return image;
    }
}
