package com.MysqlApi.salerManager.Service;

import com.github.pagehelper.PageInfo;

public interface salerManagerService {
    PageInfo<SalerMessage> getAllrecord(String username, Integer PageNum);
    void updatePrice(String gid,Integer NewMoney);
    void deleterGood(String gid);
}
