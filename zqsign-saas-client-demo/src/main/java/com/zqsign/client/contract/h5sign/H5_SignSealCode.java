package com.zqsign.client.contract.h5sign;

import java.util.Map;

import com.zqsign.common.constants.ZqsignManage;
import com.zqsign.common.utils.httpclient.ZqSubmit;
import com.zqsign.model.req.SignShowSigningRequest;

/**
 * 
 * @ClassName: H5_SignSealCode   
 * @Description: 模拟手机端签章验证签署，将请求结果放在.html文件中进行请求，就能显示当前签署方式的h5页面。
 * @date: 2017年6月7日 下午2:22:07   
 *
 */
public class H5_SignSealCode {

	public static void main(String[] args) throws Exception {
		
		String private_key = ZqsignManage.PRIVATE_KEY;
		String request_url = ZqsignManage.REQUEST_URL + "mobileSignView";
		String zqid = ZqsignManage.ZQID;		

		//封装显示签署的请求参数
		SignShowSigningRequest showSigningRequest = new SignShowSigningRequest();
		showSigningRequest.setZqid(zqid);//商户的zqid,该值需要与private_key对应
		showSigningRequest.setNo("123");//已存在的合同编号
		showSigningRequest.setUser_code("user001");;//已存在的签署人的user_code
		showSigningRequest.setSign_type(ZqsignManage.SignType.SIGNATURECODE);//签章验证签署
		showSigningRequest.setReturn_url("http://127.0.0.1:8080");//同步回调地址:签署成功后,服务器会主动调用该地址,并将请求结果一并返回，若关闭该页面，请求通知结果也会关闭。
		showSigningRequest.setNotify_url("http://127.0.0.1:8080");//异步回调地址:若用户关闭同步通知调用该页面，后台每三分钟向后台发送请求直到用户拿到success结果。
		//签名 、请求
		Map<String, Object> map = showSigningRequest.toMap();
		String requestHtmlText = ZqSubmit.buildRequest(map,request_url, private_key);//向服务端发送请求，并接收请求结果
		System.out.println("请求结果：" + requestHtmlText);//输出服务器响应结果

	}

}
