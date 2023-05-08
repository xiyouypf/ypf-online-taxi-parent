package com.ypf.common.enums;

/**
 * @author ypf
 * @date 2023/05/04 23:25
 */
public enum UserTypeEnum {
    PASSENGER_IDENTITY(1, "乘客身份"),
    DRIVER_IDENTITY(2, "司机身份"),
    ;

    UserTypeEnum(Integer identityType, String identityName) {
        this.identityType = identityType;
        this.identityName = identityName;
    }

    private Integer identityType;
    private String identityName;

    public Integer getIdentityType() {
        return identityType;
    }

    public void setIdentityType(Integer identityType) {
        this.identityType = identityType;
    }

    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(String identityName) {
        this.identityName = identityName;
    }
}
