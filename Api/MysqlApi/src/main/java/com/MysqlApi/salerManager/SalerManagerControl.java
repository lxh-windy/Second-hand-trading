package com.MysqlApi.salerManager;

import com.MysqlApi.Result;
import com.MysqlApi.salerManager.Service.salerManagerService;
import com.MysqlApi.salerManager.Service.SalerMessage;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalerManagerControl {
    @Autowired
    private salerManagerService salerManagerService;

    @RequestMapping("/getAllrecord")
    public Result getAllrecord(@RequestParam("username") String username, @RequestParam("pageNum") int pageNum) {
        try{
            PageInfo<SalerMessage> list = salerManagerService.getAllrecord(username,pageNum);
            return Result.success("获取成功",list);
        }
        catch (Exception e){
            return Result.error("获取失败",null);
        }
   }

   @RequestMapping("/updatePrice")
    public Result updatePrice(@RequestParam("gid") String gid,@RequestParam("NewMoney") Integer NewMoney) {
        try{
            salerManagerService.updatePrice(gid,NewMoney);
            return Result.success("修改成功",null);
        }
        catch (Exception e){
            return Result.error("修改失败",null);
        }
   }

   @DeleteMapping("/deletegood")
    public Result deletegood(@RequestParam("gid") String gid) {
        try{
            salerManagerService.deleterGood(gid);
            return Result.success("删除成功",null);
        }
        catch (Exception e){
            return Result.error("删除失败",null);
        }
   }
}
