package com.MysqlApi.Login;

import com.MysqlApi.JWT.Jwtservice;
import com.MysqlApi.Login.Service.LoginService;
import com.MysqlApi.Login.Service.UserData;
import com.MysqlApi.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginControl {
    @Autowired
    private LoginService loginService;
    @Autowired
    private Jwtservice jwtservice;
    @PostMapping("/match")
    public Result  match(@RequestBody UserData userData){
        boolean match = loginService.login(userData);
        if(match){
            return Result.success("登录成功",jwtservice.createJWT(userData.getUsername()));
        }else{
            return Result.error("登录失败",null);
        }
    }
    @GetMapping("/getUser")
    public Result getUser(@RequestParam("username") String username){
        UserData userData = loginService.getUserData(username);
        if(userData != null){
            return Result.success("获取用户成功",userData);
        }else{
            return Result.error("获取用户失败",null);
        }
    }
}
