package com.MysqlApi.AllUserManage.Dao;

import com.MysqlApi.AllUserManage.Service.UserMessage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AllUserManageDao {
    @Select("select username,pass from user order by pass desc")
    public List<UserMessage> getAllUser();
    @Update("update user set pass=#{pass} where username=#{username}")
    public void updatePass(UserMessage userMessage);
    @Delete("delete from user where username=#{username}")
    public void deleteUser(String username);
    public void deleterUserSaleRecode(String username);
}
