package com.zqsign.client.contract.create;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.zqsign.common.constants.ZqsignManage;
import com.zqsign.common.utils.httpclient.HttpClientUtil;
import com.zqsign.common.utils.rsa.RsaSign;
import com.zqsign.model.req.SignCreateContractByTemplateRequest;

/**
 * 
 * @ClassName: CreateContract_PdfForm   
 * @Description: 模板创建合同
 * @date: 2017年6月7日 上午10:47:56   
 *
 */

public class CreateContract_PdfForm {

	public static void main(String[] agrs) throws Exception{
		//
		String private_key = ZqsignManage.PRIVATE_KEY;
		String request_url = ZqsignManage.REQUEST_URL + "pdfTemplate";
		String zqid = ZqsignManage.ZQID;
		String contract_no = UUID.randomUUID().toString().replace("-","");//合同编号
		//封装请求参数
		SignCreateContractByTemplateRequest byTemplateRequest = new SignCreateContractByTemplateRequest();
		byTemplateRequest.setZqid(zqid);//众签提供给开发者的应用标识码,该值需要与private_key对应
		byTemplateRequest.setName("贷贷红");//商户平台合同名称
		byTemplateRequest.setNo(contract_no);//自行创建合同编号，该值不可重复使用
		byTemplateRequest.setT_no("80888107787c4b44b7e0d4fd25e2adfe");
		byTemplateRequest.setContract_val("{'jsonVal':[{'1':'中文测试','Signer1':'','Signer2':''}]}");//表单的json串
		//签名
		Map<String, Object> map = byTemplateRequest.toMap();
        String content = RsaSign.createLinkString(map);// 对请求进行签名加密
        String sign_val = RsaSign.sign(content,private_key);
		
		map.put("sign_val", sign_val); //请求参数的签名值
		String response_str = HttpClientUtil.sendPost(request_url, map);//向服务端发送请求，并接收请求结果
		System.out.println("请求结果：" + response_str);//输出服务器响应结果
			
	}
}
