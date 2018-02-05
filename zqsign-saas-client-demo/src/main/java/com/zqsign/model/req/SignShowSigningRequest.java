package com.zqsign.model.req;

import java.util.Map;

import com.zqsign.common.BaseEntity;

/**
 * 显示签署
 * @author zzk
 * 2017年10月10日上午10:45:41
 */
public class SignShowSigningRequest extends BaseEntity{

	private String zqid;//商户的zqid,该值需要与private_key对应
	private String no;//已存在的合同编号
	private String user_code;//签署人的user_code
	private String sign_type;//签章验证签署
	private String return_url;//同步回调地址:签署成功后,服务器会主动调用该地址,并将请求结果一并返回，若关闭该页面，请求通知结果也会关闭。
	private String notify_url;//异步回调地址:若用户关闭同步通知调用该页面，后台每三分钟向后台发送请求直到用户拿到success结果。
	
	@Override
	public Map<String, Object> toMap() {
		put("zqid", zqid);//商户的zqid,该值需要与private_key对应
		put("no", no);//已存在的合同编号
		put("user_code", user_code);//签署人的user_code
		put("sign_type", sign_type);//签章验证签署
		put("return_url", return_url);//同步回调地址:签署成功后,服务器会主动调用该地址,并将请求结果一并返回，若关闭该页面，请求通知结果也会关闭。
		put("notify_url", notify_url);//异步回调地址:若用户关闭同步通知调用该页面，后台每三分钟向后台发送请求直到用户拿到success结果。
		return super.get();
	}

	public String getZqid() {
		return zqid;
	}

	public void setZqid(String zqid) {
		this.zqid = zqid;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getReturn_url() {
		return return_url;
	}

	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

}
