package com.MysqlApi.UserManage.Service;

import com.MysqlApi.UserManage.Dao.UserManageDao;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserManageServiceimpl implements UserManageService{
    @Autowired
    private UserManageDao userManageDao;
    @Override
    public void updatePassword(UserMessage userMessage) {
        String Hashpassword = BCrypt.hashpw(userMessage.getNewData(), BCrypt.gensalt());
        userMessage.setNewData(Hashpassword);
        userManageDao.updatePassword(userMessage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUsername(UserMessage userMessage) {
        userManageDao.updateUsername1(userMessage);
        userManageDao.updateUsername2(userMessage);
        userManageDao.updateUsername3(userMessage);
    }
}
