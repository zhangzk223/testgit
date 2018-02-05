package com.zqsign.model.req;

import com.zqsign.common.BaseEntity;

public abstract class CreateContract extends BaseEntity{

	private String name;//合同名称
	private String no;//合同编号
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		put("name", name);
		this.name = name;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		put("no", no);
		this.no = no;
	}
	
}
