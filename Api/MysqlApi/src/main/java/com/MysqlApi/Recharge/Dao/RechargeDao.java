package com.MysqlApi.Recharge.Dao;

import com.MysqlApi.Recharge.Service.RechargeMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
public interface RechargeDao {
    @Select("select money from user where username=#{username}")
    public Integer getNowMoney(@RequestParam("username") String username);

    @Update("update user set money=#{money}  where username=#{username}")
    public void updateMoney(RechargeMessage rechargeMessage);
}
