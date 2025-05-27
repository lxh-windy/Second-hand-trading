package com.MysqlApi.BuyManager.Srevice;

import com.MysqlApi.BuyManager.Dao.BuyManagerDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BuyManagerSreviceimpl implements BuyManagerSrevice{
    @Autowired
    private BuyManagerDao buyManagerDao;
    @Override
    public PageInfo<BuyMessage> getAllBuyMessage(String username, int pageNum) {
        PageHelper.startPage(pageNum,15);
        List<BuyMessage> list=buyManagerDao.getAllBuyMessage(username);
        return new PageInfo<>(list);
    }
}
