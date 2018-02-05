package com.zqsign.model.req;

import java.util.Map;

/**
 * 通过上传PDF文件创建合同
 * @author zzk
 * 2017年10月10日上午11:18:29
 */
public class SignCreateContractByUploadRequest extends CreateContract{

	private String contract; //合同文件的base64
	
	@Override
	public Map<String, Object> toMap() {
		return super.get();
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		put("contract", contract);
		this.contract = contract;
	}

}
