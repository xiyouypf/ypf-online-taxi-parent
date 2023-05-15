package com.ypf.common.interceptor;

import com.ypf.common.dto.TokenResultDTO;
import com.ypf.common.enums.ExceptionCodeEnum;
import com.ypf.common.enums.TokenTypeEnum;
import com.ypf.common.response.ServiceResponse;
import com.ypf.common.util.JsonUtils;
import com.ypf.common.util.JwtUtils;
import com.ypf.common.util.RedisPrefixUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * @author 作者
 * @date 2023/05/15 17:51
 */
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = true;
        String resutltString = "";
        try {
            String token = request.getHeader("Authorization");
            TokenResultDTO tokenResultDTO = JwtUtils.parseToken(token);
            if (Objects.isNull(tokenResultDTO)) {
                resutltString = "access token invalid";
                result = false;
            } else {
                String phone = tokenResultDTO.getPhone();
                Integer identityTypeCode = tokenResultDTO.getIdentityTypeCode();
                String tokenKey = RedisPrefixUtils.generatorTokenKey(phone, identityTypeCode, TokenTypeEnum.ACCESS_TOKEN_TYPE.getCode());
                String tokenRedis = stringRedisTemplate.opsForValue().get(tokenKey);
                if (Objects.nonNull(token) && Objects.nonNull(tokenRedis) && Objects.equals(tokenRedis, token)) {
                    resutltString = "access token invalid";
                    result = false;
                }
            }
        } catch (Exception e) {
            result = false;
            resutltString = "token解析失败";
        }
        if (!result) {
            PrintWriter writer = response.getWriter();
            writer.print(JsonUtils.toJson(ServiceResponse.buildErrorResponse(ExceptionCodeEnum.ERROR.getCode(), resutltString)));
        }
        return result;
    }
}
