package com.MysqlApi.BuyManager;

import com.MysqlApi.BuyManager.Srevice.BuyManagerSrevice;
import com.MysqlApi.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuyManagerControl {
    @Autowired
    private BuyManagerSrevice buyManagerSrevice;

    @RequestMapping("/getAllBuy")
    public Result  getAllBuy(String username,int pageNum){
        try{
            return Result.success("获取成功",buyManagerSrevice.getAllBuyMessage(username,pageNum));
        }
        catch (Exception e){
            return Result.error("获取失败",null);
        }
    }
}
