package com.MysqlApi.BuyManager.Dao;

import com.MysqlApi.BuyManager.Srevice.BuyMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface BuyManagerDao {
    @Select("select gid,price,gname,time from is_bought where username=#{username} order by time desc")
    public List<BuyMessage> getAllBuyMessage(@RequestParam("username") String username);
}
