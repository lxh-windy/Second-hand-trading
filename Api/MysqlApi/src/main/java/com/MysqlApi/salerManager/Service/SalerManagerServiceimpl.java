package com.MysqlApi.salerManager.Service;

import com.MysqlApi.salerManager.Dao.SalerManagerDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalerManagerServiceimpl implements salerManagerService{
    @Autowired
    private SalerManagerDao salerManagerDao;
    @Override
    public PageInfo<SalerMessage> getAllrecord(String username, Integer PageNum) {
        PageHelper.startPage(PageNum,15);
        List<SalerMessage> list = salerManagerDao.getAllrecord(username);
        return new PageInfo<>(list);
    }

    @Override
    public void updatePrice(String gid, Integer NewMoney) {
        String status = salerManagerDao.getStatus(gid);
        if(status.equals("0")){
            salerManagerDao.updateMoney(gid,NewMoney);
        }
        else{
            throw new RuntimeException("商品状态为已售出，无法修改价格");
        }
    }

    @Override
    public void deleterGood(String gid) {
        String status = salerManagerDao.getStatus(gid);
        if(status.equals("0")){
            salerManagerDao.deletergood(gid);
        }
        else{
            throw new RuntimeException("商品状态为已售出，无法删除");
        }
    }
}
