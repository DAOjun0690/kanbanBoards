package dao;

import model.Project;

import java.sql.SQLException;
import java.util.List;

public interface ProjectDao {
    List<Project> getProjectList(int id);
    void insertProject(String project_name, String po_id, String start_time, String end_time, String developer) throws SQLException;
}
