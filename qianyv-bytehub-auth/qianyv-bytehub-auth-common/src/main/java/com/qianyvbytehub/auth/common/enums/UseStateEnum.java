package com.qianyvbytehub.auth.common.enums;

import lombok.Getter;

@Getter
public enum UseStateEnum {
    ENABLE(0,"禁用"),
    DISABLE(1,"启用");

    private int code;
    private String desc;

    private UseStateEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static UseStateEnum getByCode(int codeVal) {
        for (UseStateEnum resultCodeEnum : UseStateEnum.values()) {
            if (resultCodeEnum.code == codeVal) {
                return resultCodeEnum;
            }
        }
        return null;
    }
}
