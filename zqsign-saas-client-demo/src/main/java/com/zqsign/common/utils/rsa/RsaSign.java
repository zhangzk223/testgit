package com.zqsign.common.utils.rsa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * RSA工具类
 */
public class RsaSign {

	private static Logger logger = Logger.getLogger(RsaSign.class);

	//编码集
	public static final String CHARSET_ENCODER = "UTF-8";
	
	
	/**
	 * <p>map参数排序</p>
	 * @param params
	 * @return
	 * @auther zzk
	 * 2017年1月12日下午4:29:45
	 */
	public static String createLinkString(Map<String, ? extends Object> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = String.valueOf(params.get(key));

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }
	
	
	/**
	 * 签名
	 * @param content
	 * @param privateKey
	 * @return
	 */
	public static String sign(String content, String privateKey) {
		return sign(content, privateKey, null);
	}
	
	public static String sign(String content, String privateKey,String input_charset) {
		String charset = isEmpty(input_charset)?CHARSET_ENCODER : input_charset;
		try {
			String s = RSAUtils.sign(content.getBytes(charset), privateKey);
			return s;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	/**
	 * 验签
	 * @param content
	 * @param sign
	 * @param publicKey
	 * @return
	 */
	public static boolean verify(String content, String sign, String publicKey) {
		return verify(content, sign, publicKey,null);
	}
	public static boolean verify(String content, String sign, String publicKey,String input_charset) {
		String charset = isEmpty(input_charset)?CHARSET_ENCODER : input_charset;
		try {
			boolean b = RSAUtils.verify(content.getBytes(charset), publicKey, sign);
			return b;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return false;
	}
	private static boolean isEmpty(String str){
		return str==null||str.trim().length()==0||str.equals("null");
	}
	
}
