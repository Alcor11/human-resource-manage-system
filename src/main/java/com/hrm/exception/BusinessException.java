package com.hrm.exception;

import com.hrm.common.ResCodeEnum;

/**
 * @author guchun
 * @description TODO
 * @date 2022/4/29 17:53
 */
public class BusinessException extends RuntimeException{


    private final int code;

    private final String description;

    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(ResCodeEnum errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BusinessException(ResCodeEnum errorCode, String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
