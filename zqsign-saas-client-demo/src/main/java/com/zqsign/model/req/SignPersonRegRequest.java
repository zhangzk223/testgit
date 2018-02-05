package com.zqsign.model.req;

import java.util.Map;

import com.zqsign.common.BaseEntity;

/**
 * 个人用户信息  推送、更新
 * @author zzk
 * 2017年10月10日上午10:05:48
 */
public class SignPersonRegRequest extends BaseEntity {

	private String user_code;// 用户唯一标示，该值不能重复
	private String name; // 平台方用户姓名
	private String id_card_no;// 身份证号
	private String mobile; // 联系手机号码

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

	public String getId_card_no() {
		return id_card_no;
	}

	public void setId_card_no(String id_card_no) {
		this.id_card_no = id_card_no;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public Map<String, Object> toMap() {
		put("user_code", user_code);
		put("name", name);
		put("id_card_no", id_card_no);
		put("mobile", mobile);
		return super.get();
	}

}
