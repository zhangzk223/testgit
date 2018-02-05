package com.zqsign.model.req;

import java.util.Map;

import com.zqsign.common.BaseEntity;

/**
 * 合同下载、或者合同图片下载
 * @author zzk
 * 2017年10月10日上午11:06:59
 */
public class SignDownloadRequest extends BaseEntity{

	private String no;//合同编号
	
	@Override
	public Map<String, Object> toMap() {
		put("no", no);
		return super.get();
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

}
