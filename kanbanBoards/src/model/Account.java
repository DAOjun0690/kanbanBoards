package model;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Account implements Authenticate{
    private boolean exist;
    private int idNumber;
    private String image;
    private String regNumber;
    private String password;
    private String chinese_name;
    private String english_name;
    private String nickname;
    private int role_code;
    private String role_name;
    private String email;
    private String phone;

    public Account(int idNumber, String image, String regNumber, String password , String chinese_name, String english_name, String nickname, int role_code, String role_name, String email, String phone) throws SQLException {
        if (regNumber == null) {
            throw new IllegalArgumentException("帳號為null");
        }
        this.idNumber = idNumber;
        this.image = image;
        this.regNumber = regNumber;
        this.password = password;
        this.chinese_name = chinese_name;
        this.english_name = english_name;
        this.nickname = nickname;
        this.role_code = role_code;
        this.role_name = role_name;
        this.email = email;
        this.phone = phone;
        this.exist = true;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public boolean isExist() {
        return exist;
    }

    public int getIdNumber(){return idNumber;}

    public String getImage(){return image;}

    public String getRegNumber(){return regNumber;}

    public String getChineseName(){return chinese_name;}

    public String getEnglishName(){return english_name;}

    public String getNickname(){return nickname;}

    public String getEmail(){return email;}

    public int getRoleCode(){return role_code;}

    public String getRoleName(){return role_name;}

    public String getPhone(){return phone;}
}
