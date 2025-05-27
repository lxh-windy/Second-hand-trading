package com.MysqlApi.Register.Service;

import com.MysqlApi.Login.Service.UserData;
import com.MysqlApi.Register.Dao.RegisterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceimpl implements RegisterService{

    @Autowired
    private RegisterDao registerDao;
    private boolean checkUser(String username) {
        return registerDao.checkUser(username) <= 0;
    }

    @Override
    public boolean register(UserData userData) {
        try{
            if(checkUser(userData.getUsername())){
                registerDao.register(userData);
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e) {
            return false;
        }
    }
}
