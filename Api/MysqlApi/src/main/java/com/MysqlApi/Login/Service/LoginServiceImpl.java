package com.MysqlApi.Login.Service;

import com.MysqlApi.Login.Dao.LoginDao;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    private LoginDao loginDao;
    @Override
    public boolean login(UserData userData) {
        String hashPassword = loginDao.getHashPassword(userData);
        try {
            return BCrypt.checkpw(userData.getPassword(), hashPassword);
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public UserData getUserData(String username) {
        return loginDao.getUserData(username);
    }
}
