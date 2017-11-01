package com.zqsign.client.util;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 *  响应数据包装
 * @author zzk
 * 2017年7月13日下午1:08:01
 */
public class AuthenResult implements Serializable{
    
    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    public static AuthenResult build(Integer status, String msg, Object data) {    
    	System.out.println("=========status"+status+"===msg:"+msg);
        return new AuthenResult(status, msg, data);
    }
   
    public AuthenResult() {

    }
    public static AuthenResult build(Integer status, String msg) {
        return new AuthenResult(status, msg, null);
    }
    public String toJson() {
    	return JSONObject.toJSONString(this);
    }

    public AuthenResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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
