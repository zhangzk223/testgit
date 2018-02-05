package com.zqsign.client.template.create;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;

import com.alibaba.fastjson.JSONObject;
import com.zqsign.common.base64.Base64Utils;
import com.zqsign.common.constants.ZqsignManage;
import com.zqsign.common.utils.httpclient.HttpClientUtil;
import com.zqsign.common.utils.httpclient.HttpResponseCallBack;
import com.zqsign.common.utils.rsa.RsaSign;
import com.zqsign.model.req.SignCreateTemplateRequest;

/**
 * 
 * @ClassName: CreateContract_PdfForm
 * @Description: 上传pdf文件创建合同模板
 * @date: 2017年3月28日 下午2:45:10
 * ·
 */

public class Create_Template {

	public static void main(String[] agrs) throws Exception {

		String private_key = ZqsignManage.PRIVATE_KEY;
		String request_url = ZqsignManage.REQUEST_URL + "createTemplate";
		String zqid = ZqsignManage.ZQID;
		byte[] fileToByte = Base64Utils.fileToByte("D:\\9、债权转让协议【VIP-兆润】.pdf");//合同路径
		String contract = Base64Utils.encode(fileToByte);
		//封装参数
		SignCreateTemplateRequest createTemplateRequest = new SignCreateTemplateRequest();
		createTemplateRequest.setZqid(zqid);//众签提供给开发者的应用标识码,该值需要与private_key对应
		createTemplateRequest.setT_name("9、债权转让协议【VIP-兆润】");// ---------需要用户修改 --模板名称
		createTemplateRequest.setContract(contract);//合同文件的base64/平台方合同文件的网络地址
		//签名
		Map<String, Object> map = createTemplateRequest.toMap();
        String content = RsaSign.createLinkString(map);// 对请求进行签名加密
        String sign_val = RsaSign.sign(content,private_key);
        
		map.put("sign_val", sign_val); // 请求参数的签名值
		String response_str = HttpClientUtil.sendPost(request_url, map);// 向服务端发送请求，并接收请求结果
		System.out.println("请求结果：" + response_str);// 输出服务器响应结果
	}
	/**
	 * 上传模板
	 * @param filepath 上传pdf文件路径
	 * @return
	 * @throws Exception
	 * @auther zzk
	 * 2017年10月10日上午11:55:38
	 */
	public static SignCreateTemplateRequest create_Template(String filepath) throws Exception {

		String private_key = ZqsignManage.PRIVATE_KEY;
		String request_url = ZqsignManage.REQUEST_URL + "createTemplate";
		String zqid = ZqsignManage.ZQID;
		byte[] fileToByte = Base64Utils.fileToByte(filepath);
		String contract = Base64Utils.encode(fileToByte);
		//封装参数
		SignCreateTemplateRequest createTemplateRequest = new SignCreateTemplateRequest();
		createTemplateRequest.setZqid(zqid);//众签提供给开发者的应用标识码,该值需要与private_key对应
		createTemplateRequest.setT_name("9、债权转让协议【VIP-兆润】");// ---------需要用户修改 --模板名称
		createTemplateRequest.setContract(contract);//合同文件的base64/平台方合同文件的网络地址
		//签名
		Map<String, Object> map = createTemplateRequest.toMap();
        String content = RsaSign.createLinkString(map);// 对请求进行签名加密
        String sign_val = RsaSign.sign(content,private_key);
        
		map.put("sign_val", sign_val); // 请求参数的签名值
		
		return HttpClientUtil.doPost(request_url, map, new HttpResponseCallBack<SignCreateTemplateRequest>() {
			@Override
			public SignCreateTemplateRequest doResponse(HttpResponse response) throws IOException {
				int statusCode = response.getStatusLine().getStatusCode();
				if(200 == statusCode){
					InputStream inputStream = null;
					try {
						inputStream = response.getEntity().getContent();
						return JSONObject.parseObject(inputStream, SignCreateTemplateRequest.class);
					} finally {
						if(inputStream!=null) inputStream.close();
					}
				}
				return null;
			}
		});
		
	}

}
