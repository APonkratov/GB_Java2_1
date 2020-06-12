package com.geekbrains.java2.lesson8.auth;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAuthService implements AuthenticationService {
    Connection conn = null;

    private class User {
        private String login;
        private String passwd;
        private String nick;

        public User(String login, String passwd, String nick) {
            this.login = login;
            this.passwd = passwd;
            this.nick = nick;
        }
    }

    private List<User> userList;

    public DatabaseAuthService() {
        userList = new ArrayList<>();

        try {
            conn = DriverManager.
                    getConnection("jdbc:h2:~/test", "sa", "");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM USERS");
            while (rs.next()) {
                String login = rs.getString("LOGIN");
                String passwd = rs.getString("PASSWORD");
                String nick = rs.getString("NICK");
                userList.add(new User(login, passwd, nick));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void start() {
        System.out.println("Authentication service started");
    }

    public void stop() {
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Authentication service stopped");
    }

    public String getNickByLoginAndPwd(String login, String passwd) {
        for(User user: userList) {
            if (user.login.equals(login) && user.passwd.equals(passwd)) {
                return user.nick;
            }
        }
        return null;
    }
}
