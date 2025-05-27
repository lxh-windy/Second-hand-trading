package com.MysqlApi.UserManage.Dao;

import com.MysqlApi.UserManage.Service.UserMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserManageDao {
    @Update("update user set password=#{newData} where username=#{oldData}")
    public void updatePassword(UserMessage userMessage);

    @Update("update user set username=#{newData} where username=#{oldData}")
    public void updateUsername1(UserMessage userMessage);

    @Update("update goods set uid=#{newData} where uid=#{oldData}")
    public void updateUsername2(UserMessage userMessage);

    @Update("update salerecode set uid=#{newData} where uid=#{oldData}")
    public void updateUsername3(UserMessage userMessage);
}
