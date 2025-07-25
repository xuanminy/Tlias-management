package com.itxuan.pojo;

import lombok.Data;

//统一响应结果的实体类
@Data
public class Result {

    private Integer code;//自定义状态码：1成功，0失败
    private String msg;//错误信息
    private Object data;//数据

    public static Result success() {
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        return result;
    }
    public static Result success(Object data) {
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        result.data = data;
        return result;
    }
    public static Result error(String msg) {
        Result result = new Result();
        result.code = 0;
        result.msg = msg;
        return result;
    }
}
