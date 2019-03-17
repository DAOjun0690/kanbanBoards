package dao;

import java.sql.SQLException;

public interface ImageDao {
    void uploadImage(int id_no, String imagePath) throws SQLException;
    String getImage(int id_no) throws SQLException;
}
