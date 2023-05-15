package com.ypf.common.response;

import java.io.Serializable;

/**
 * @author 作者
 * @date 2023/05/04 17:04
 */
public abstract class AbstractServiceResponse<T> implements Serializable {
    public abstract Integer getCode();

    public abstract String getMsg();

    public abstract void setMsg(String msg);

    public abstract T getData();
}
