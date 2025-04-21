package com.qianyvbytehub.gateway.entity;


import com.qianyvbytehub.gateway.enums.ResultCodeEnum;
import lombok.Data;

@Data
public class Result<T> {
    private Boolean success;
    private Integer code;
    private String msg;
    private T data;

    //成功的响应结果：
    public static Result ok(){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());    //响应状态码，用枚举的形式
        result.setMsg(ResultCodeEnum.SUCCESS.getDesc());    //响应信息，枚举
        return result;
    }
    public static <T> Result ok(T data){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());    //响应状态码，用枚举的形式
        result.setMsg(ResultCodeEnum.SUCCESS.getDesc());    //响应信息，枚举
        result.setData(data);
        return result;
    }

    //失败的响应结果
    public static Result fail(){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCodeEnum.FAIL.getCode());    //响应状态码，用枚举的形式
        result.setMsg(ResultCodeEnum.FAIL.getDesc());    //响应信息，枚举
        return result;
    }
    public static <T> Result fail(T data){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCodeEnum.FAIL.getCode());    //响应状态码，用枚举的形式
        result.setMsg(ResultCodeEnum.FAIL.getDesc());    //响应信息，枚举
        result.setData(data);
        return result;
    }
    public static Result fail(Integer code, String msg){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(code);    //响应状态码，用枚举的形式
        result.setMsg(msg);    //响应信息，枚举
        return result;
    }
}
