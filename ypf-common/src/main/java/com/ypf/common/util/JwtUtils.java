package com.ypf.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ypf.common.TokenException;
import com.ypf.common.dto.TokenResultDTO;
import com.ypf.common.enums.ExceptionCodeEnum;
import com.ypf.common.enums.IdentityTypeEnum;
import com.ypf.common.enums.TokenTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author ypf
 * @date 2023/05/15 14:33
 */
@Slf4j
public class JwtUtils {
    // 盐
    private static final String SIGN = "CPFmsb!@#$$";
    private static final String JWT_KEY_PHONE = "phone";
    private static final String JWT_KEY_IDENTITY = "identity";
    private static final String JWT_TOKEN_TYPE = "tokenType";
    private static final String JWT_TOKEN_TIME = "tokenTime";

    /**
     * 生成Token
     */
    public static String generatorToken(String phone, Integer identityTypeCode, Integer tokenTypeCode) {
        IdentityTypeEnum identityTypeEnum = IdentityTypeEnum.getIdentityTypeEnumByCode(identityTypeCode);
        TokenTypeEnum tokenTypeEnum = TokenTypeEnum.getTokenTypeEnumByCode(tokenTypeCode);
        if (Objects.isNull(identityTypeEnum) || Objects.isNull(tokenTypeEnum)) {
            log.info("生成token的参数不正确:identityTypeCode={},tokenTypeCode={}", identityTypeCode, tokenTypeCode);
            throw new TokenException(ExceptionCodeEnum.ERROR_PARAM.getCode(), "生成token的参数不正确");
        }
        String identityTypeName = identityTypeEnum.getName();
        String tokenTypeName = tokenTypeEnum.getName();
        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE, phone);
        map.put(JWT_KEY_IDENTITY, identityTypeName);
        map.put(JWT_TOKEN_TYPE, tokenTypeName);
        map.put(JWT_TOKEN_TIME, new DateFormatUtil("yyyy-mm-dd").format(new Date()));
        JWTCreator.Builder builder = JWT.create();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.withClaim(entry.getKey(), entry.getValue());
        }
        String sign = builder.sign(Algorithm.HMAC256(SIGN));
        return sign;
    }

    /**
     * 解析token
     */
    public static TokenResultDTO parseToken(String token) throws RuntimeException {
        try {
            DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
            String identityTypeName = verify.getClaim(JWT_KEY_IDENTITY).asString();
            IdentityTypeEnum identityTypeEnum = IdentityTypeEnum.getIdentityTypeEnumByName(identityTypeName);
            if (Objects.isNull(identityTypeEnum)) {
                throw new TokenException(ExceptionCodeEnum.ERROR_PARAM.getCode(), "token错误");
            }
            Integer identityTypeEnumCode = identityTypeEnum.getCode();
            String phone = verify.getClaim(JWT_KEY_PHONE).asString();
            TokenResultDTO tokenResultDTO = new TokenResultDTO();
            tokenResultDTO.setIdentityTypeCode(identityTypeEnumCode);
            tokenResultDTO.setPhone(phone);
            return tokenResultDTO;
        } catch (TokenException e) {
            log.info("token解析程序异常:token = {}", token, e);
            throw new TokenException(e.getErrorCode(), e.getErrorMsg());
        } catch (JWTDecodeException e) {
            log.info("token不合法:token = {}", token, e);
            throw new TokenException(ExceptionCodeEnum.ERROR_PARAM, "token不合法");
        } catch (Exception e) {
            log.error("token解析系统异常:token = {}", token, e);
            throw new RuntimeException("程序异常");
        }
    }

    public static void main(String[] args) {
        String s = generatorToken("19945561120", 1, 1);
        System.out.println("生成的token："+s);
        System.out.println("解析-----------------");
        TokenResultDTO tokenResult = parseToken("1yJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwaG9uZSI6IjE5OTQ1NTYxMTIwIiwiaWRlbnRpdHkiOiJwYXNzZW5nZXJfaWRlbnRpdHkiLCJ0b2tlblRpbWUiOiIyMDIzLTEzLTE1IiwidG9rZW5UeXBlIjoiYWNjZXNzVG9rZW4ifQ.DQFzbv0EUbfcORiQ9KUdiboiQwpPcZsAM-AekaNlIfo");
        System.out.println("手机号："+tokenResult.getPhone());
        System.out.println("身份：" + tokenResult.getIdentityTypeCode());
    }
}
