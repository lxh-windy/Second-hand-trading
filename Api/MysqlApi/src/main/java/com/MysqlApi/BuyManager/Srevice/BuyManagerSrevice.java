package com.MysqlApi.BuyManager.Srevice;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

public interface BuyManagerSrevice {
   public PageInfo<BuyMessage> getAllBuyMessage(String username,int pageNum);
}
