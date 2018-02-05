package com.zqsign.model.res;

import com.zqsign.common.BaseResultEntity;

/**
 * 创建模板的响应
 * @author zzk
 * 2017年10月10日上午11:45:46
 */
public class SignCreateTemplateResponse extends BaseResultEntity{

	private String t_no;
	private String contract_val;
	
	public String getT_no() {
		return t_no;
	}
	public void setT_no(String t_no) {
		this.t_no = t_no;
	}
	public String getContract_val() {
		return contract_val;
	}
	public void setContract_val(String contract_val) {
		this.contract_val = contract_val;
	}
	
}
