package com.MysqlApi.AllBuyManager.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllSalerMessage {
    private String gid;
    private String gname;
    private String uid;
    private Integer price;
    private String status;
    private LocalDateTime time;
}
