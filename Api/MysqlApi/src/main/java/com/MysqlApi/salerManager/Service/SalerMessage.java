package com.MysqlApi.salerManager.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalerMessage {
    private String gid;
    private String gname;
    private Integer price;
    private String status;
    private LocalDateTime time;
}
