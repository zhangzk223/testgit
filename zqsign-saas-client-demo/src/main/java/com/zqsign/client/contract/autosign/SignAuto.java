package com.zqsign.client.contract.autosign;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zqsign.common.constants.ZqsignManage;
import com.zqsign.common.utils.httpclient.HttpClientUtil;
import com.zqsign.common.utils.rsa.RsaSign;
import com.zqsign.model.req.SignAutoSigningContractRequest;

/**
 * 默认的自动签署
 * 
 * 1、签署时传入的签署方必须是通过我们众签已经颁发过证书的用户
 * 2、签署的合同必须提前上传到众签平台
 * 3、合同上需要有签署关键字：我们规定的关键字，必须是签署人的user_code
 * 
 * 签署完成，合同上会有签署方的签章图片  + 数字证书
 * 
 * 通过下载合同接口可以查看
 * 
 * @ClassName: SignAuto   
 * @date: 2017年6月7日 上午11:07:28   
 *
 */
public class SignAuto {

	public static void main(String[] agrs) throws Exception{
		List<String> signers = new ArrayList<>();
		signers.add("ccfe7cd4-93e2-4a86-9a98-0b35f3b80b1c");
		signers.add("2f811789-2d6a-4975-9af9-60a49b5c087a");
		String result = signAuto("31f10fcb-2aaf-4b78-b4a7-0626b29f7c5a", signers );
		System.out.println("请求结果：" + result);//输出服务器响应结果
		
	}
	
	public static String signAuto(String contractNo,List<String> signers){
		if(signers==null || signers.size()==0) return null;
		String private_key = ZqsignManage.PRIVATE_KEY;
		String request_url = ZqsignManage.REQUEST_URL + "signAuto";
		String zqid = ZqsignManage.ZQID;
		//自动签署的参数
		SignAutoSigningContractRequest autoSigningContractRequest = new SignAutoSigningContractRequest();
		autoSigningContractRequest.setZqid(zqid);
		autoSigningContractRequest.setNo(contractNo);//已存在的合同编号
		
		StringBuilder builder = new StringBuilder();
		for (String signer : signers) {
			builder.append(",").append(signer);
		}
		autoSigningContractRequest.setSigners(builder.substring(1));//签署人user_code,多个用逗号分隔
	    //签名
		Map<String, Object> map = autoSigningContractRequest.toMap();
        String content = RsaSign.createLinkString(map);
        String sign_val = RsaSign.sign(content,private_key);

		map.put("sign_val", sign_val); //请求参数的签名值
		String result = HttpClientUtil.sendPost(request_url, map);//向服务端发送请求，并接收请求结果
		return result;//返回服务器响应结果
	}
	
	
}
