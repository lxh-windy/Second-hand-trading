package com.MysqlApi.UserManage;

import com.MysqlApi.Result;
import com.MysqlApi.UserManage.Service.UserManageService;
import com.MysqlApi.UserManage.Service.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserManageControl {
    @Autowired
    private UserManageService userManageService;

    @PostMapping("/updatePassword")
    public Result updatepassword(@RequestBody UserMessage userMessage){
        try{
            userManageService.updatePassword(userMessage);
            return Result.success("修改成功",null);
        }
        catch (Exception e){
            return Result.error("修改失败",null);
        }
    }

    @PostMapping("/updateUsername")
    public Result updateUsername(@RequestBody UserMessage userMessage){
        try{
            userManageService.updateUsername(userMessage);
            return Result.success("修改成功",null);
        }
        catch (Exception e){
            return Result.error("修改失败",null);
        }
    }
}
