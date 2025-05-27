package com.MysqlApi.Login.Service;

public interface LoginService {
    boolean login(UserData userData);
    UserData getUserData(String username);
}
