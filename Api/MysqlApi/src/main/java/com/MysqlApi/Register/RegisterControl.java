package com.MysqlApi.Register;

import com.MysqlApi.Login.Service.UserData;
import com.MysqlApi.Register.Service.RegisterService;
import com.MysqlApi.Result;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterControl {
    @Autowired
    private RegisterService registerService;

    @RequestMapping("/register")
    public Result register(@RequestBody UserData userData) {
        userData.setMoney(0);
        userData.setPass(0);
        String password = userData.getPassword();
        String Hashpassword = BCrypt.hashpw(password, BCrypt.gensalt());
        userData.setPassword(Hashpassword);
        if (registerService.register(userData)) {
            return Result.success("注册成功", null);
        } else {
            return Result.error("注册失败", null);
        }
    }
}
