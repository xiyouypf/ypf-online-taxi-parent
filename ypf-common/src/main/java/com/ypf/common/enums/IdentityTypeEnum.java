package com.ypf.common.enums;

import java.util.Objects;

/**
 * @author ypf
 * @date 2023/05/04 23:25
 */
public enum IdentityTypeEnum {
    PASSENGER_IDENTITY(1, "passenger_identity", "乘客身份"),
    DRIVER_IDENTITY(2, "driver_identity", "司机身份"),
    ;

    public static IdentityTypeEnum getIdentityTypeEnumByCode(Integer code) {
        IdentityTypeEnum[] identityTypeEnums = IdentityTypeEnum.values();
        for (IdentityTypeEnum identityTypeEnum : identityTypeEnums) {
            if (Objects.equals(identityTypeEnum.getCode(), code)) {
                return identityTypeEnum;
            }
        }
        return null;
    }

    public static IdentityTypeEnum getIdentityTypeEnumByName(String name) {
        IdentityTypeEnum[] identityTypeEnums = IdentityTypeEnum.values();
        for (IdentityTypeEnum identityTypeEnum : identityTypeEnums) {
            if (Objects.equals(identityTypeEnum.getName(), name)) {
                return identityTypeEnum;
            }
        }
        return null;
    }
    private Integer code;
    private String name;
    private String desc;

    IdentityTypeEnum(Integer code, String name, String desc) {
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
