package com.qianyvbytehub.subject.common.enums;

import lombok.Getter;

/**
 * 题目类型枚举
 * 1、单选radio  2、多选multiple
 * 3、判断judge  4、简答brief
 */
@Getter
public enum SubjectTypeEnum {
    RADIO(1,"单选"),
    MULTIPLE(1,"多选"),
    JUDGE(1,"判断"),
    BRIEF(1,"简答");

    private int code;
    private String desc;

    private SubjectTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SubjectTypeEnum getByCode(int code){
        for (SubjectTypeEnum value : SubjectTypeEnum.values()) {
            if (value.code == code){
                return value;
            }
        }
        return null;
    }

}
