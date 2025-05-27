package com.MysqlApi.ShopCenter.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodMessage {
    private String gid;
    private String gname;
    private Integer price;
    private String username;
    LocalDateTime time;
}
