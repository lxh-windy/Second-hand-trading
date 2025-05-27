package com.MysqlApi.AllBuyManager.Dao;

import com.MysqlApi.AllBuyManager.Service.AllSalerMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AllBuyManagerDao {
    @Select("select * from salemanager order by time desc")
    public List<AllSalerMessage> getAllBuyMessage();
}
