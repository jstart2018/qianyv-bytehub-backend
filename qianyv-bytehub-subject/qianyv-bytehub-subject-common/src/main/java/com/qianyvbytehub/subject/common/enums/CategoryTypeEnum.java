package com.qianyvbytehub.subject.common.enums;

import lombok.Getter;


/**
 * 分类类型枚举
 */
@Getter
public enum CategoryTypeEnum {
    PRIMARY(1,"大类"),
    SON(2,"小类");

    private int code;
    private String desc;

    private CategoryTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CategoryTypeEnum getByCode(int codeVal) {
        for (CategoryTypeEnum resultCodeEnum : CategoryTypeEnum.values()) {
            if (resultCodeEnum.code == codeVal) {
                return resultCodeEnum;
            }
        }
        return null;
    }
}
