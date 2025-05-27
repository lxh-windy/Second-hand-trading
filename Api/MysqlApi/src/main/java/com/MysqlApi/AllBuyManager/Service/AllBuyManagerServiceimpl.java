package com.MysqlApi.AllBuyManager.Service;

import com.MysqlApi.AllBuyManager.Dao.AllBuyManagerDao;
import com.MysqlApi.salerManager.Service.SalerMessage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllBuyManagerServiceimpl implements AllBuyManagerService{
    @Autowired
    AllBuyManagerDao allBuyManagerDao;
    @Override
    public PageInfo<AllSalerMessage> getAllBuyMessage(int pageNum) {
        PageHelper.startPage(pageNum,11);
        List<AllSalerMessage> list = allBuyManagerDao.getAllBuyMessage();
        return new PageInfo<>(list);
    }
}
