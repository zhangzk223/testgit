package com.zqsign.model.req;

import java.util.Map;

import com.zqsign.common.BaseEntity;

/**
 * 创建合同（PDF）模板
 * @author zzk
 * 2017年10月10日上午11:35:45
 */
public class SignCreateTemplateRequest extends BaseEntity{

	private String contract;//合同文件的base64
	private String t_name;// ---------需要用户修改 --模板名称
	
	@Override
	public Map<String, Object> toMap() {
		put("contract", contract);//合同文件的base64
		put("t_name", t_name);// ---------需要用户修改 --模板名称
		return super.get();
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}

}
