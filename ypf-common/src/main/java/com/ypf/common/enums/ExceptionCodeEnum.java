package com.ypf.common.enums;

/**
 * @author 作者
 * @date 2023/05/04 17:20
 */
public enum ExceptionCodeEnum {
    ERROR(500, "错误"),
    VERIFICATION_CODE_ERROR(1099, "验证码不正确"),
    ERROR_PARAM(501, "参数错误"),
    ;
    private Integer code;
    private String msg;

    ExceptionCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
