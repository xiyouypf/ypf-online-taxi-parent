package com.ypf.common.response;

import com.ypf.common.enums.ServiceResponseEnum;
import lombok.Data;

/**
 * @author 杨朋飞
 * @date 2023/05/04 17:03
 * 统一返回参数
 */
@Data
public class ServiceResponse<T> extends AbstractServiceResponse<T> {
    private Integer code;
    private Long ts;
    private String msg;
    T data;

    private ServiceResponse() {
    }

    public static <S> ServiceResponse<S> buildSuccessResponse(S result) {
        return buildResponse(ServiceResponseEnum.SUCCESS.getCode(), ServiceResponseEnum.SUCCESS.getMsg(), result);
    }

    public static <S>ServiceResponse<S> buildErrorResponse(int errCode, String errMsg) {
        return buildResponse(errCode, errMsg, null);
    }

    private static <S> ServiceResponse<S> buildResponse(int code, String msg, S data) {
        ServiceResponse<S> resp = new ServiceResponse<>();
        resp.setCode(code);
        resp.setMsg(msg);
        resp.setTs(System.currentTimeMillis()/1000);
        resp.setData(data);
        return resp;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }
}
