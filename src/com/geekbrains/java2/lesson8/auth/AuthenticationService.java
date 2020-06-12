package com.geekbrains.java2.lesson8.auth;

public interface AuthenticationService {
    void start();
    void stop();
    String getNickByLoginAndPwd(String login, String password);
}
