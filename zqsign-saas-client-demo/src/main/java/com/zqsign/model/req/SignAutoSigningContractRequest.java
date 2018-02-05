package com.zqsign.model.req;

import java.util.Map;

import com.zqsign.common.BaseEntity;

/**
 * 自动签署
 * @author zzk
 * 2017年10月10日下午12:04:14
 */
public class SignAutoSigningContractRequest extends BaseEntity{

	private String signers;//签署人user_code,多个用逗号分隔
	private String no;//已存在的合同编号
	
	@Override
	public Map<String, Object> toMap() {
		put("no", no);//已存在的合同编号
		put("signers", signers);//签署人user_code
		return super.get();
	}

	public String getSigners() {
		return signers;
	}

	public void setSigners(String signers) {
		this.signers = signers;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

}
