package com.zqsign.common;

import java.io.Serializable;

/**
 * 远程调用返回结果接收的基类
 * @author zzk
 * 2017年5月4日下午6:30:15
 */
public class BaseResultEntity implements Serializable{
	
	
	private String code;
	private String msg;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public BaseResultEntity() {
		super();
	}
	public BaseResultEntity(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "BaseRpcResult [code=" + code + ", msg=" + msg + "]";
	}
}
