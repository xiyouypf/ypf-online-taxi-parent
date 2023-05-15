package com.ypf.common.util;

import com.ypf.common.lock.RedisKeyEnum;

/**
 * @author 作者
 * @date 2023/05/15 18:01
 */
public class RedisPrefixUtils {
    // token存储的前缀
    public static String tokenPrefix = "token_";

    public static String generatorRedisKey(String phone, Integer identityType) {
        return RedisKeyEnum.VERIFICATION_NUMBER.format(phone, String.valueOf(identityType));
    }
    /**
     * 根据手机号和身份标识，生成token
     * @param phone
     * @param identity
     * @return
     */
    public static String generatorTokenKey(String phone , Integer identity , Integer tokenType){
        return tokenPrefix + phone + "-" + identity + "-" + tokenType;
    }
}
