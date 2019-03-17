package dao.impl;

import dao.AccountDao;
import dbconn.DbConn;
import model.Account;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {

    private final Connection pcon = DbConn.getConnection(DbConn.KANBAN);

    public AccountDaoImpl(){}

    @Override
    public Account getAccountByRegNumber(String regNumber) throws SQLException {
        String sql = "SELECT * FROM kanban.account WHERE register_number = ?";
        return getAccountByOneParameter(sql, regNumber);
    }

    @Override
    public List<Account> getAccountList(int role_code) throws SQLException {
        if(role_code != 0)
            return null;
        else {
            String sql = "SELECT * FROM kanban.account WHERE role_code != ? ORDER BY id_number";
            List<Account> accounts = new ArrayList<Account>();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                preparedStatement = pcon.prepareStatement(sql);
                preparedStatement.setInt(1, 0);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Account account = new Account(
                            resultSet.getInt("id_number"),
                            resultSet.getString("head_image"),
                            resultSet.getString("register_number"),
                            resultSet.getString("password"),
                            resultSet.getString("chinese_name"),
                            resultSet.getString("english_name"),
                            resultSet.getString("nickname"),
                            resultSet.getInt("role_code"),
                            resultSet.getString("role_name"),
                            resultSet.getString("email"),
                            resultSet.getString("phone")
                    );
                    accounts.add(account);
                }
                return accounts;
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
            return null;
        }
    }

    @Override
    public List<Account> getDeveloperList() throws SQLException {
        String sql = "SELECT * FROM kanban.account WHERE role_code = ? ORDER BY id_number";
        List<Account> accounts = new ArrayList<Account>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = pcon.prepareStatement(sql);
            preparedStatement.setInt(1, 2);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Account account = new Account(
                        resultSet.getInt("id_number"),
                        resultSet.getString("head_image"),
                        resultSet.getString("register_number"),
                        resultSet.getString("password"),
                        resultSet.getString("chinese_name"),
                        resultSet.getString("english_name"),
                        resultSet.getString("nickname"),
                        resultSet.getInt("role_code"),
                        resultSet.getString("role_name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone")
                );
                accounts.add(account);
            }
            return accounts;
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
        return null;
    }

    @Override
    public int getLatestAccountId(int role_code) throws SQLException {
        if(role_code != 0)
            return -1;
        else {
            String sql = "SELECT id_number FROM kanban.account ORDER BY id_number DESC LIMIT 0 , 1";
            int latestAccountId = -1;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                preparedStatement = pcon.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    latestAccountId = resultSet.getInt("id_number");
                }
                return latestAccountId;
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
            return -1;
        }
    }

    private Account getAccountByOneParameter(String sql, String parameter) throws SQLException {
        Account account = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            preparedStatement = pcon.prepareStatement(sql);
            preparedStatement.setString(1, parameter);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                account = new Account(
                        resultSet.getInt("id_number"),
                        resultSet.getString("head_image"),
                        resultSet.getString("register_number"),
                        resultSet.getString("password"),
                        resultSet.getString("chinese_name"),
                        resultSet.getString("english_name"),
                        resultSet.getString("nickname"),
                        resultSet.getInt("role_code"),
                        resultSet.getString("role_name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone")
                );
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return account;
    }

    @Override
    public void insertToDB(Account account) throws SQLException {
        String sql = "INSERT INTO kanban.account(id_number, register_number, password, chinese_name, english_name, role_code, role_name) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = pcon.prepareStatement(sql);
            int i = 1;
            preparedStatement.setInt(i++, account.getIdNumber());
            preparedStatement.setString(i++, account.getRegNumber());
            preparedStatement.setString(i++, DigestUtils.sha512Hex(Integer.toString(account.getIdNumber())));
            preparedStatement.setString(i++, account.getChineseName());
            preparedStatement.setString(i++, account.getEnglishName());
            preparedStatement.setInt(i++, account.getRoleCode());
            preparedStatement.setString(i++, account.getRoleName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
        }
    }

    @Override
    public void editToDB(String regNumber, String email, String phone, String english_name, String chinese_name, String nickname) throws SQLException {
        String sql = "UPDATE kanban.account SET email = ?, phone = ?, english_name = ?, chinese_name = ?, nickname = ? WHERE register_number = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = pcon.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, english_name);
            preparedStatement.setString(4, chinese_name);
            preparedStatement.setString(5, nickname);
            preparedStatement.setString(6, regNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
        }
    }
}
