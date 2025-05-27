package com.MysqlApi.salerManager.Dao;

import com.MysqlApi.salerManager.Service.SalerMessage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface SalerManagerDao {
    @Select("select gid,gname,price,status,time from salemanager where uid=#{username} order by time desc")
    public List<SalerMessage> getAllrecord(@RequestParam("username") String username);

    @Select("select status from salemanager where gid=#{gid}")
    public String getStatus(@RequestParam("gid") String gid);

    @Update("update goods set price=#{NewMoney} where gid=#{gid}")
    public void updateMoney(@RequestParam("gid") String gid,@RequestParam("NewMoney") Integer NewMoney);

    @Delete("delete from goods where gid=#{gid}")
    public void deletergood(@RequestParam("gid") String gid);
}
