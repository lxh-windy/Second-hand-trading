package com.MysqlApi.BuyManager.Srevice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyMessage {
    private String gid;
    private String gname;
    private LocalDateTime time;
    private Integer  price;
}
