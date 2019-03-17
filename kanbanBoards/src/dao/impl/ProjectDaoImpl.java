package dao.impl;

import dao.ProjectDao;
import dbconn.DbConn;
import model.Project;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl implements ProjectDao {

    private final Connection pcon = DbConn.getConnection(DbConn.KANBAN);

    public ProjectDaoImpl(){

    }
    @Override
    public List<Project> getProjectList(int id) {
        String sql = "SELECT id, project_name, po_id, start_date, estimate_end_date, DATEDIFF(estimate_end_date, start_date) AS days, DATEDIFF(estimate_end_date, CURDATE()) AS remain_day, join_member FROM project WHERE po_id = ? ORDER BY id";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Project> projects = new ArrayList<Project>();
        try {
            preparedStatement = pcon.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Project project = new Project(
                    resultSet.getInt("id"),
                    resultSet.getString("project_name"),
                    resultSet.getInt("po_id"),
                    resultSet.getString("start_date"),
                    resultSet.getString("estimate_end_date"),
                    resultSet.getInt("days"),
                    resultSet.getInt("remain_day"),
                    resultSet.getString("join_member")
                );
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    @Override
    public void insertProject(String project_name, String po_id, String start_time, String end_time, String developer) throws SQLException {
        String sql = "INSERT INTO project(project_name, po_id, start_date, estimate_end_date, join_member) VALUES ( ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = pcon.prepareStatement(sql);
            int i = 1;
            preparedStatement.setString(i++, project_name);
            preparedStatement.setInt(i++, Integer.parseInt(po_id));
            preparedStatement.setString(i++, start_time);
            preparedStatement.setString(i++, end_time);
            preparedStatement.setString(i++, developer);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
        }
    }
}
