package com.zqsign.model.req;

import java.util.Map;

/**
 * 通过PDF模板创建合同
 * @author zzk
 * 2017年10月10日上午11:18:29
 */
public class SignCreateContractByTemplateRequest extends CreateContract{

	private String t_no; //模板编号
	private String contract_val; //模板里填充的数据（json字符串格式）
	
	@Override
	public Map<String, Object> toMap() {
		put("t_no", t_no);
		put("contract_val", contract_val);
		return super.get();
	}

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
