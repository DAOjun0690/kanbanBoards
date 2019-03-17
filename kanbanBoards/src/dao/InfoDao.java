package dao;

import model.Info;

import java.sql.SQLException;
import java.util.List;

public interface InfoDao {
    List<Info> getInfoList(int id_no) throws SQLException;
}
