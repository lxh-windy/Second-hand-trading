package com.MysqlApi.ShopCenter.Dao;

import com.MysqlApi.ShopCenter.Service.GoodMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ShopCenterDao {
    @Select("select * from is_saling")
    public List<GoodMessage> getAllGoods();

    @Insert("insert into goods(gid,gname,price,uid) values (#{gid},#{gname},#{price},#{username})")
    public void addGoods1(GoodMessage goodMessage);

    @Insert("insert into salerecode(gid,status,time) values (#{gid},0,#{time})")
    public void addGoods2(GoodMessage goodMessage);

    @Select("select price from is_saling where gid=#{gid}")
    public Integer getPrice(@RequestParam("gid") String gid);

    @Select("select uid from goods where gid=#{gid}")
    public String getSalerName(@RequestParam("gid") String gid);

    @Update("update salerecode set time=#{time},status='1',uid=#{username} where gid=#{gid}")
    public void shopping(@RequestParam("gid") String gid, @RequestParam("username") String username, @RequestParam("time")LocalDateTime time);

    @Update("update user set money=#{NewMoney} where username= #{username}")
    public void sendmoney(@RequestParam("username") String username, @RequestParam("NewMoney") Integer NewMoney);
}
