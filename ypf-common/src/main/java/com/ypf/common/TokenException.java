package com.ypf.common;

import com.ypf.common.enums.ExceptionCodeEnum;

/**
 * @author 作者
 * @date 2023/05/15 15:22
 */
public class TokenException extends RuntimeException {
    private int errorCode;
    private String errorMsg;

    public TokenException(int errorCode, String errorMsg) {
        super("{\"errorCode\":"+ errorCode + ",\"errorMsg\":\"" + errorMsg +"\"}");
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public TokenException() {
    }

    public TokenException(Throwable cause) {
        super(cause);
    }

    public TokenException(ExceptionCodeEnum exceptionCodeEnum) {
        this(exceptionCodeEnum.getCode(), exceptionCodeEnum.getMsg());
    }

    public TokenException(ExceptionCodeEnum exceptionCodeEnum, String errorMsg) {
        this(exceptionCodeEnum.getCode(), errorMsg);
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
