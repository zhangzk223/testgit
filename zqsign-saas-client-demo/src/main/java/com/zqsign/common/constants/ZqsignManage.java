package com.zqsign.common.constants;

import java.util.Properties;

import com.zqsign.common.utils.PropertiesUtil;

public class ZqsignManage {
	//开发环境zqid
//	public static final String ZQID = "ZQ26AE180566FF4CF3BD1D2FBC3C61B592";
//	public static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKud4/ndRM4dJs5ycvTCcTDs9EA0TrBN/pYm4NSq0BCMzThwAHn/DuDMecwExp8/KcaKjJY9VYwGiAxauc1Fp55emwlLH42NxZjVu45ix7woDIO/p4s0zJEEVVJBl0CdGedZ6BFrL+ZI67todfia7khpke0OOcPvrbeLhignMjTXAgMBAAECgYA79Wj+FjmAzZyOSlwxeXG0h0T63YuUe1t0OFca1iblcVwN2MvB08Pt9GKlThHa92VwEV3266zkxC9qKZR6zFg4INg3ZyoUXDQ7/0m5CyACqG2grg5rcQtMVXYb8r72XGzxSB/IUYiDEmW/9cRXmrD66jwZ+lQlTQ6xUrtw8alcGQJBAN6vqm+7YFIgSB5em4+LHLKBq4VwBmOMHOOhMqoToXZKLgCkoVPxbBoDBxidPHOwjwffUwy0iv/C7UlaEsZn1VMCQQDFSmQQFz+7ykDg+uRNqahXvj1+JgQJACw0zRt06Vc5HEqYB7IKgugIXy4eIn0VbI1wmeENoKQiMimC+h8XwY3tAkEAn/7Y1yRqpTcaee+Bl1XdDYhLIzmE7Zsq2oXkL/ygwquzd7TRUM2h3OKWA6JdX8XYTlCvi+5f/DkiYdR8rt7FjwJAYHI7dHpJF96J1PIRxb2YpDxGB3NCKbxB4BYcxAyGLU8dUDEC4oyYLe+JtgWaLG0Cv/cZw/WyB12KWZM4u/5NCQJBAMrid+bzm6toseBy9zCjoDb9qSLyZSyTWcXLOqt6jg8XpsxSQyeTUfxiIR0x99RqXE1nqLIgRv/8Ve03Q1YT2wc=";
//	public static final String REQUEST_URL = "http://192.168.1.221/";
	
	//测试环境zqid
	public static String ZQID = "ZQABA206A379B342FB987B8DCCBA679549";
	public static String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANIwqMKRiZMTMerWYJsp54AoMUcIbgZsdB4FjtGAzabh/NYH9ptNgNBfBo78yShPCP5c0wB0MVqg3wv5ExQRcCA5uj1ajO+FuHy5ESxmDDftxOzQlpHlMdvxCLZwJjy0+Il2AsZcbcSy3HMDN8HGhOG01A9rllbx6JnyC8hFdd+7AgMBAAECgYBztZHRuqjPrGt4ahe4k3L73CR0hDF9m8q4lDqxHoUX76RudufNSvc0vnsvz/01EX1T+em2gECDMbhYMP/NtmPQegoVIsojSGSSF8Q+q7JOCQlDi9JXiRMkoj+uSMeSqa4EbqOdoFAj+F8BlzYJCUCdfdcJRR4Zb8seFNlpUfDToQJBAPMGQt8dWfFGDGlo9Tnif5GIlz09Of7odn/NOyFb6c+fca0ufrg816GWGgLBl0qnj8bO/93P+EY0MWsVF8RytRkCQQDdaZtWGm9YImGT+PKdKapQvt0C5RAfi2OAnRndqCs8bA1K1kPII8hg/t2QFPshx48pqayJ7ve5/dmeig1y0eHzAkAKWnHu32k9hiZxNy97T9LveEo5KaqW2YBy4WNrgGbtmXVWU2zCnJTzJVnmVCkF3S2a4qaz5HBHTWHtlfB1Rg3BAkEA0cpr3fTkRX0mOf/rWhENiL6gSUrjsQ/w8v9ob8cVWIYFPkCxLuUAyy8Snp/SqFofA1n62yMrZPbriTXDsmS+EwJBAOFhYJS/x04TKX3H4iGDXLKLTSaQWoDyHBIZG61HSLVI8UTTre/Efc8jrs6GnYXkXAA0KeAcUQDxdeF0YRFhc2g=";
	public static String REQUEST_URL = "http://test.sign.zqsign.com:8081/";
	
	private static Properties properties= null;
	static {
		try {
			properties = PropertiesUtil.getPropertiesValue("config.properties");
		} catch (Exception e) {
			System.out.println("没有找到classpath下的config.properties");
		}
		if(properties!=null){
			ZQID = properties.getProperty("ZQID");
			PRIVATE_KEY = properties.getProperty("PRIVATE_KEY");
			REQUEST_URL = properties.getProperty("REQUEST_URL");
		}
	}
	
	public class SignType{
		//签章不验证
		public static final String SIGNATURE = "SIGNATURE"; 
		//签章验证
		public static final String SIGNATURECODE = "SIGNATURECODE";
		//签字不验证
		public static final String WRITTEN = "WRITTEN";
		//签字验证
		public static final String WRITTENCODE = "WRITTENCODE";
		//短信验证
		public static final String code = "CODE";
	}

	public class Result{

		//请求成功
		public static final String SUCCESS = "操作成功";
		public static final String REQUESR_SUCCESS = "请求成功";

		//用户唯一标识格式不正确
		public static final String USERCODE_IDINCORRECT = "用户ID格式不正确";
		//用户唯一标识已经存在
		public static final String USERCODE_EXIST = "用户已存在";
		//用户唯一标识不存在
		public static final String USERCODE_NOEXIST = "用户不存在";
		//用户唯一标识不能为空
		public static final String USERCODE_NOTNULL = "用户ID不能为空";
		//用户唯一标识不能为空
		public static final String USERCODE_NOSTYLE = "用户ID与需要更新的用户类型不匹配";
		//用户姓名格式不正确
		public static final String NAME_INCORRECT = "用户姓名格式不正确";
		//用户姓名不能为空
		public static final String NAME_NOTNULL = "用户姓名不能为空";
		//企业名称不能为空
		public static final String ENTERPRISENAME_NOTNULL = "企业名称不能为空";
		//用户身份证号格式不正确
		public static final String IDCARDNO_INCORRECT = "身份证号码格式不正确";
		//用户身份证号不能为空
		public static final String IDCARDNO_NOTNULL = "身份证号码不能为空";
		//用户电话号码格式不正确
		public static final String MOBILE_INCORRECT = "手机号码格式不正确";
		//用户电话号码不能为空
		public static final String MOBILE_NOTNULL = "手机号码不能为空";

		//企业注册证件号格式不正确
		public static final String CERTIFICATE_INCORRECT = "企业机构代码格式不正确";
		//企业注册证件号格式不正确
		public static final String CERTIFICATE_NOTNULL = "企业机构代码不能为空";
		//企业地址不能为空
		public static final String ADDRESS_NOTNULL = "企业注册地址不能为空";
		//联系人不能为空
		public static final String CONTACT_NOTNULL = "企业联系人不能为空";
		//联系人电话号码格式不正确
		public static final String CONTACT_MOBILE_INCORRECT = "联系人电话格式不正确";
		//联系人电话号码不能为空
		public static final String CONTACT_MOBILE_NOTNULL = "联系人电话不能为空";


		//验证码不能为空
		public static final String CODE_NOTNULL = "验证码不能为空";
		//验证码格式不正确
		public static final String CODE_INCORRECT = "验证码格式不正确";
		//验证码与验证id不匹配
		public static final String CODE_NO_MATCH = "验证码不匹配";
		//验证码id不能为空
		public static final String SMS_ID_NOTNULL = "验证码id不能为空";
		//验证码id格式不正确
		public static final String SMS_ID_INCORRECT = "验证码id格式不正确";
		//合同不存在
		public static final String CONTRACT_NOTNULL = "合同编号不能为空";
		//合同不存在
		public static final String CONTRACT_NOEXIST = "合同编号不存在";
		//合同编号格式不正确
		public static final String CONTRACT_INCORRECT = "合同编号格式不正确";
		//pdf_width不能为空
		public static final String PDF_WIDTH_NOTNULL = "PDF宽不能为空";
		//pdf_width格式不正确
		public static final String PDF_WIDTH_INCORRECT = "PDF宽格式不正确";
		//pdf_width不能为空
		public static final String PDF_HEIGHT_NOTNULL = "PDF高不能为空";
		//pdf_width格式不正确
		public static final String PDF_HEIGHT_INCORRECT = "PDF高格式不正确";
		//leftzb不能为空
		public static final String LEFTZB_NOTNULL = "签名左边距不能为空";
		//leftzb格式不正确
		public static final String LEFTZB_INCORRECT = "签名左边距格式不正确";
		//leftzb签名左边距超出范围
		public static final String LEFTZB_ULTRA = "签名左边距超出范围";
		//topzb不能为空
		public static final String TOPZB_NOTNULL = "签名上边距不能为空";
		//topzb格式不正确
		public static final String TOPZB_INCORRECT = "签名上边距格式不正确";
		//topzb签名左边距超出范围
		public static final String TOPZB_ULTRA = "签名上边距超出范围";
		//signature上传类型不正确
		public static final String SIGNATURE_NOSTYLE = "签名图片格式不正确";
		//signature不能为空
		public static final String SIGNATURE_NOTNULL = "签章图片不能为空";
		//sign_width不能为空
		public static final String SIGN_WIDTH_NOTNULL = "签章的宽不能为空";
		//sign_width格式不正确
		public static final String SIGN_WIDTH_INCORRECT = "签章的宽格式不正确";
		//sign_height不能为空
		public static final String SIGN_HEIGHT_NOTNULL = "签章的高不能为空";
		//sign_height格式不正确
		public static final String SIGN_HEIGHT_INCORRECT = "签章的高格式不正确";
		//关键字查找签名位置失败
		public static final String KEYWORD_FINDFAILED = "查找关键字签名位置失败";

		//page不能为空
		public static final String PAGE_NOTNULL = "页码不能为空";
		//page格式不正确
		public static final String PAGE_INCORRECT = "页码格式不正确";


	}
}
