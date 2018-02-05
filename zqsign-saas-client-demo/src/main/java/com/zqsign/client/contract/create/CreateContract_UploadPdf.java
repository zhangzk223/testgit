package com.zqsign.client.contract.create;

import java.util.Map;
import java.util.UUID;

import com.zqsign.common.base64.Base64Utils;
import com.zqsign.common.constants.ZqsignManage;
import com.zqsign.common.utils.httpclient.HttpClientUtil;
import com.zqsign.common.utils.rsa.RsaSign;
import com.zqsign.model.req.SignCreateContractByUploadRequest;

/**
 * 
 * @ClassName: CreateContract_UploadPdf   
 * @Description: 模拟上传PDF文件创建合同
 * @date: 2017年6月7日 上午10:49:00   
 *
 */
public class CreateContract_UploadPdf {

	public static void main(String[] agrs) throws Exception{
		String private_key = ZqsignManage.PRIVATE_KEY;
		String request_url = ZqsignManage.REQUEST_URL + "uploadPdf";
		String zqid = ZqsignManage.ZQID;
		String contract_no = UUID.randomUUID().toString();//合同编号
		//读取合同文件
		byte[] fileToByte = Base64Utils.fileToByte("E:/test/test.pdf");
		String encode = Base64Utils.encode(fileToByte);
		//封装请求参数
		SignCreateContractByUploadRequest byUploadRequest = new SignCreateContractByUploadRequest();
		byUploadRequest.setZqid(zqid);//众签提供给开发者的应用标识码,该值需要与private_key对应
		byUploadRequest.setName("合同上传");//商户平台合同名称
		byUploadRequest.setNo(contract_no);//自行创建合同编号，该值不可重复使用
		byUploadRequest.setContract(encode);//合同文件的base64
		//签名
		Map<String, Object> map = byUploadRequest.toMap();
        String content = RsaSign.createLinkString(map);// 对请求进行签名加密
        String sign_val = RsaSign.sign(content,private_key);
		map.put("sign_val", sign_val); //请求参数的签名值
		//发送请求
		String response_str = HttpClientUtil.sendPost(request_url, map);//向服务端发送请求，并接收请求结果
		System.out.println("contract_no："+contract_no+"==请求结果：" + response_str);//输出服务器响应结果
	
			
	}
}
