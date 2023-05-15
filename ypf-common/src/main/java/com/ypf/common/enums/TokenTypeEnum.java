package com.ypf.common.enums;

import java.util.Objects;

/**
 * @author 作者
 * @date 2023/05/15 14:39
 */
public enum TokenTypeEnum {
    ACCESS_TOKEN_TYPE(1, "accessToken", "访问token"),
    REFRESH_TOKEN_TYPE(2, "refreshToken", "刷新token"),
    ;

    public static TokenTypeEnum getTokenTypeEnumByCode(Integer code) {
        TokenTypeEnum[] tokenTypeEnums = TokenTypeEnum.values();
        for (TokenTypeEnum tokenTypeEnum : tokenTypeEnums) {
            if (Objects.equals(tokenTypeEnum.getCode(), code)) {
                return tokenTypeEnum;
            }
        }
        return null;
    }

    public static TokenTypeEnum getTokenTypeEnumByName(String name) {
        TokenTypeEnum[] tokenTypeEnums = TokenTypeEnum.values();
        for (TokenTypeEnum tokenTypeEnum : tokenTypeEnums) {
            if (Objects.equals(tokenTypeEnum.getName(), name)) {
                return tokenTypeEnum;
            }
        }
        return null;
    }

    private Integer code;
    private String name;
    private String desc;

    TokenTypeEnum(Integer code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
