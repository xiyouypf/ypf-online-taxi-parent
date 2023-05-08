package com.ypf.common.lock;

import lombok.AllArgsConstructor;
import lombok.Getter;



/**
 * @author 杨朋飞
 * @date 2023/05/04 20:14
 */
@Getter
@AllArgsConstructor
public enum RedisKeyEnum {
    VERIFICATION_NUMBER(RedisKeyConstant.KEY_PREFIX + "numberCode_", "1", 60 * 1, "验证码"),
    ;

    private final String key;
    private final String defaultValue;
    private final int timeOut;
    private final String desc;

    public String format(String... keys) {
        StringBuilder sb = new StringBuilder(key);
        for (String s : keys) {
            sb.append(s);
            sb.append("_");
        }
        return sb.toString();
    }
}
