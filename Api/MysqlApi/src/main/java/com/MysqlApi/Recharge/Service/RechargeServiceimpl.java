package com.MysqlApi.Recharge.Service;

import com.MysqlApi.Recharge.Dao.RechargeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RechargeServiceimpl implements RechargeService{
    @Autowired
    private RechargeDao rechargeDao;
    @Override
    public void recharge(RechargeMessage rechargeMessage) {
        Integer NowMoney=rechargeDao.getNowMoney(rechargeMessage.getUsername());
        rechargeMessage.setMoney(NowMoney+rechargeMessage.getMoney());
        rechargeDao.updateMoney(rechargeMessage);
    }
}
