package com.MysqlApi.AllBuyManager;

import com.MysqlApi.AllBuyManager.Service.AllBuyManagerService;
import com.MysqlApi.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AllBuyManagerControl {
    @Autowired
    AllBuyManagerService allBuyManagerService;
    @GetMapping("/getAllBuyMessage")
    public Result getAllBuyMessage(@RequestParam("pageNum") int pageNum){
        try{
            return Result.success("获取成功",allBuyManagerService.getAllBuyMessage(pageNum));
        }
        catch(Exception e){
            return Result.error("获取失败",null);
        }
    }
}
