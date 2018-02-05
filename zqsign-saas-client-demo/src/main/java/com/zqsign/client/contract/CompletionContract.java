package com.zqsign.client.contract;

import java.util.HashMap;
import java.util.Map;

import com.zqsign.common.constants.ZqsignManage;
import com.zqsign.common.utils.httpclient.HttpClientUtil;
import com.zqsign.common.utils.rsa.RsaSign;

/**
 * 合同生效接口
 * 
 * 如果对于一个合同签署完成之后，保证永久性、产生法律保障、司法落地，一定要记得调用这个接口。
 * 
 * 合同签署完成并且调用了这个接口，这个合同后续就不能有任何操作了：不能再行签署操作、不能修改、不能作废、不能撤销
 * 
 * @ClassName:    
 * @Description: completionContract
 * @date: 2017年6月7日 上午11:05:59   
 *
 */
public class CompletionContract {
	 
    public static void main(String[] agrs) throws Exception{
    	String result = completionContract("7cb7550706e84675852d436271d35b30");
        System.out.println("请求结果：" + result);//输出服务器响应结果
    }
    
    /**
     * 合同生效
     * @param contractNo
     * @return
     */
    public static String completionContract(String contractNo){
    	String private_key=ZqsignManage.PRIVATE_KEY; //私钥放置的位置
        String request_url = ZqsignManage.REQUEST_URL + "completionContract";
        String zqid = ZqsignManage.ZQID;
        Map<String,String> map = new HashMap<String,String>();
        map.put("zqid", zqid);//商户的zqid,该值需要与private_key对应
        map.put("no", contractNo);//已存在的合同编号
        //签名
        String content = RsaSign.createLinkString(map);
        String sign_val = RsaSign.sign(content,private_key);
 
        map.put("sign_val", sign_val); //请求参数的签名值
        String result = HttpClientUtil.sendPost(request_url, map);//向服务端发送请求，并接收请求结果
    	return result;//返回服务器响应结果
    }
    
    
}
