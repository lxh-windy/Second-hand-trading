package com.MysqlApi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer status;
    private String message;
    private Object data;

    public static Result success(String message,Object data) {
        return new Result(200, message, data);
    }

    public static Result error(String message,Object data) {
        return new Result(400, message, data);
    }
}
