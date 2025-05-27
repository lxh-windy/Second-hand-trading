package com.MysqlApi.Register.Dao;

import com.MysqlApi.Login.Service.UserData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterDao {
    public int checkUser(String username);
    public int register(UserData userData);
}
