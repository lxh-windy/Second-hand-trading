package com.MysqlApi.Login.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    private String username;
    private String password;
    private int money;
    private int pass;
}
