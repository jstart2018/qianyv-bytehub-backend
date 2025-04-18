package com.qianyvbytehub.subject.common.exception;

import com.qianyvbytehub.subject.common.enums.ErrorCodeEnum;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private Integer code;

    public BusinessException() {}

    public BusinessException(Integer code,String message) {
        super(message);
        this.code = code;
    }

}
