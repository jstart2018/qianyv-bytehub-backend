package com.qianyvbytehub.subject.common.enums;

import lombok.Getter;

@Getter
public enum DeletedStateEnum {
    UNDELETED(0,"未删除"),
    DELETED(1,"已删除");

    private int code;
    private String desc;

    private DeletedStateEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static DeletedStateEnum getByCode(int codeVal) {
        for (DeletedStateEnum resultCodeEnum : DeletedStateEnum.values()) {
            if (resultCodeEnum.code == codeVal) {
                return resultCodeEnum;
            }
        }
        return null;
    }
}
