package com.MysqlApi.AllUserManage.Service;

import com.github.pagehelper.PageInfo;

public interface AllUserManageService {
    public PageInfo<UserMessage> getAllUser(int pageNum,  int pageSize);
    public void updatePass(UserMessage userMessage);
    public void deleteUser(String username);
}
