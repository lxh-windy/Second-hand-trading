package com.MysqlApi.AllUserManage.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMessage {
    private String username;
    private String pass;
}
