package com.MysqlApi.ShopCenter.Service;

import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ShopCenterService {
    public void addgood(GoodMessage goodMessage);
    public PageInfo<GoodMessage> getsaleGoods(int PageNum);
    public void shopping(String gid, String username);
}
