package com.tangzq.response;

import java.io.Serializable;

/**
 * 请求响应结果
 */
public class JsonObject implements Serializable{
    /**
     * 请求成功与否
     */
    private boolean status = true;
    /**
     * 消息提示
     */
    private String msg;
    /**
     * 响应结果数据
     */
    private Object data;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
