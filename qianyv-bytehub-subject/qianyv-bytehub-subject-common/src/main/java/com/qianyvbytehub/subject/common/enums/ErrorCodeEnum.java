package com.qianyvbytehub.subject.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

    NO_PERMISSION(401,"无权限"),
    PARAMETER_ERROR(402,"参数错误"),
    SYSTEM_ERROR(403,"系统内部异常"),
    OPERATION_FAILED(500,"操作失败");


    private final Integer code;
    private final String msg;

    public static ErrorCodeEnum getByCode(Integer code) {
        for (ErrorCodeEnum e : ErrorCodeEnum.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

}
