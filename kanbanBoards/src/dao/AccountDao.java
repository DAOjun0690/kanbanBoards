package dao;

import model.Account;

import java.sql.SQLException;
import java.util.List;

public interface AccountDao {
    Account getAccountByRegNumber(String regNumber) throws SQLException;
    List<Account> getAccountList(int role_code) throws SQLException;
    List<Account> getDeveloperList()throws  SQLException;
    int getLatestAccountId(int role_code) throws SQLException;
    void insertToDB(Account account) throws SQLException;
    void editToDB(String regNumber, String email, String phone, String english_name, String chinese_name, String nickname) throws SQLException;
}
