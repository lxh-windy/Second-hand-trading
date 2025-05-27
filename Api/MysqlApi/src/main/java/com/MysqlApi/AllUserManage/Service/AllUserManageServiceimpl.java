package com.MysqlApi.AllUserManage.Service;

import com.MysqlApi.AllUserManage.Dao.AllUserManageDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AllUserManageServiceimpl implements AllUserManageService {
    @Autowired
    private AllUserManageDao userManageDao;
    @Override
    public PageInfo<UserMessage> getAllUser(int pageNum,  int pageSize) {
        try{
            PageHelper.startPage(pageNum,pageSize);
            List<UserMessage> list= userManageDao.getAllUser();
            return new PageInfo<>(list);
        }finally {
            PageHelper.clearPage();
        }
    }

    @Override
    public void updatePass(UserMessage userMessage) {
        userManageDao.updatePass(userMessage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(String username) {
        userManageDao.deleteUser(username);
        userManageDao.deleterUserSaleRecode(username);
    }
}
