package com.ypf.common.response;

/**
 * @author 作者
 * @date 2023/05/04 17:04
 */
public abstract class AbstractServiceResponse<T> {
    public abstract Integer getCode();

    public abstract String getMsg();

    public abstract void setMsg(String msg);

    public abstract T getData();
}
