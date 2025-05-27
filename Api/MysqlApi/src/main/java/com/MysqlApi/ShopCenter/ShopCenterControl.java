package com.MysqlApi.ShopCenter;

import com.MysqlApi.Result;
import com.MysqlApi.ShopCenter.Service.ShopCenterService;
import com.MysqlApi.ShopCenter.Service.GoodMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShopCenterControl {
    @Autowired
    ShopCenterService  shopCenterService;

    @RequestMapping("/getAllGoods")
    public Result getAllGoods(@RequestParam int PageNum){
        try{
            return Result.success("获取成功",shopCenterService.getsaleGoods(PageNum));
        }catch (Exception e){
            return Result.error("获取失败",null);
        }
    }

    @PostMapping("/addgood")
    public Result addgood(@RequestBody GoodMessage goodMessage){
        try{
            shopCenterService.addgood(goodMessage);
            return Result.success("添加成功",null);
        }catch (Exception e){
            return Result.error("添加失败",null);
        }
    }

    @RequestMapping("/shopping")
    public Result shopping(@RequestParam("gid") String gid,@RequestParam("username") String username){
        try{
            shopCenterService.shopping(gid,username);
            return Result.success("购买成功",null);
        }catch (Exception e){
            return Result.error("购买失败",null);
        }
    }
}
