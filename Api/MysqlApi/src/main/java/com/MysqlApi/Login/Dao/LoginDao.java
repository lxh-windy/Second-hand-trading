package com.MysqlApi.Login.Dao;

import com.MysqlApi.Login.Service.UserData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginDao {
    public String getHashPassword(UserData userData);
    public UserData  getUserData(String username);
}
