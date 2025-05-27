package com.MysqlApi.Recharge;

import com.MysqlApi.Recharge.Service.RechargeMessage;
import com.MysqlApi.Recharge.Service.RechargeService;
import com.MysqlApi.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RechargeControl {
    @Autowired
    private RechargeService rechargeService;
    @PostMapping("/recharge")
    public Result recharge(@RequestBody RechargeMessage rechargeMessage){
        try{
            rechargeService.recharge(rechargeMessage);
            return Result.success("充值成功",null);
        }
        catch (Exception e){
            return Result.error("充值失败",null);
        }
    }
}
