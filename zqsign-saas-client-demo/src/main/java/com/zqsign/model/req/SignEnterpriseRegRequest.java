package com.zqsign.model.req;

import java.util.Map;

import com.zqsign.common.BaseEntity;

/**
 * 企业用户信息  推送、更新
 * @author zzk
 * 2017年10月10日上午10:24:46
 */
public class SignEnterpriseRegRequest extends BaseEntity{

	private String user_code;// 用户唯一标示，该值不能重复
	private String name; // 平台方企业姓名
	private String certificate; //营业执照号或社会统一代码
	private String address; //企业注册地址
	private String contact; //联系人
	private String mobile; // 联系手机号码

	@Override
	public Map<String, Object> toMap() {
		put("user_code", user_code);
		put("name", name);
		put("mobile", mobile);
		put("certificate", certificate);
		put("address", address);
		put("contact", contact);
		return super.get();
	}
	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}


}
