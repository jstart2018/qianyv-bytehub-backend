package com.qianyvbytehub.subject.common.util;

import com.qianyvbytehub.subject.common.enums.ErrorCodeEnum;
import com.qianyvbytehub.subject.common.exception.BusinessException;

public class ThrowUtils {

    public static void ThrowIf(boolean condition, Integer code, String message) {
        if (condition) {
            throw new BusinessException(code,message);
        }
    }

    public static void ThrowIf(boolean condition, Integer code) {
        if (condition) {
            ErrorCodeEnum errorEnum = ErrorCodeEnum.getByCode(code);
            throw new BusinessException(code,errorEnum.getMsg());
        }
    }

}
