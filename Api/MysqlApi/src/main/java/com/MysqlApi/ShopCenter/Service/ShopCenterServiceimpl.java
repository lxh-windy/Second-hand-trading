package com.MysqlApi.ShopCenter.Service;

import com.MysqlApi.Recharge.Dao.RechargeDao;
import com.MysqlApi.ShopCenter.Dao.ShopCenterDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ShopCenterServiceimpl implements ShopCenterService{
    @Autowired
    private ShopCenterDao shopCenterDao;
    @Autowired
    private RechargeDao rechargeDao;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addgood(GoodMessage goodMessage) {
        String gid = UUID.randomUUID().toString();
        LocalDateTime time = LocalDateTime.now();
        goodMessage.setGid(gid);
        goodMessage.setTime(time);
        shopCenterDao.addGoods1(goodMessage);
        shopCenterDao.addGoods2(goodMessage);
    }

    @Override
    public PageInfo<GoodMessage> getsaleGoods(int PageNum) {
        PageHelper.startPage(PageNum,5);
        return new PageInfo<>(shopCenterDao.getAllGoods());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void shopping(String gid, String username) {
        Integer nowMoney = rechargeDao.getNowMoney(username);
        Integer price = shopCenterDao.getPrice(gid);
        if(nowMoney==null || price == null || nowMoney < price)
            throw new RuntimeException();
        Integer newMoney = nowMoney - price;
        shopCenterDao.shopping(gid,username,LocalDateTime.now());
        shopCenterDao.sendmoney(username,newMoney);
        String SalerName=shopCenterDao.getSalerName(gid);
        nowMoney=rechargeDao.getNowMoney(SalerName);
        newMoney = nowMoney + price;
        shopCenterDao.sendmoney(SalerName,newMoney);

    }
}
