package com.zqsign.common.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil extends org.apache.commons.lang3.StringUtils{
	
	 /**
     * 检查对象是否为数字型字符串,包含负数开头的。
     */
	public static boolean isNumeric(Object obj) {
		if (obj == null) {
			return false;
		}
		char[] chars = obj.toString().toCharArray();
		int length = chars.length;
		if(length < 1)
			return false;
		
		int i = 0;
		if(length > 1 && chars[0] == '-')
			i = 1;
		
		for (; i < length; i++) {
			if (!Character.isDigit(chars[i])) {
				return false;
			}
		}
		return true;
	}

    /**
     * 检查指定的字符串列表是否不为空。
     */
	public static boolean areNotEmpty(String... values) {
		boolean result = true;
		if (values == null || values.length == 0) {
			result = false;
		} else {
			for (String value : values) {
				result &= !isEmpty(value);
			}
		}
		return result;
	}

    /**
     * 把通用字符编码的字符串转化为汉字编码。
     */
	public static String unicodeToChinese(String unicode) {
		StringBuilder out = new StringBuilder();
		if (!isEmpty(unicode)) {
			for (int i = 0; i < unicode.length(); i++) {
				out.append(unicode.charAt(i));
			}
		}
		return out.toString();
	}

    /**
     * 过滤不可见字符
     */
	public static String stripNonValidXMLCharacters(String input) {
		if (input == null || ("".equals(input)))
			return "";
		StringBuilder out = new StringBuilder();
		char current;
		for (int i = 0; i < input.length(); i++) {
			current = input.charAt(i);
			if ((current == 0x9) || (current == 0xA) || (current == 0xD)
					|| ((current >= 0x20) && (current <= 0xD7FF))
					|| ((current >= 0xE000) && (current <= 0xFFFD))
					|| ((current >= 0x10000) && (current <= 0x10FFFF)))
				out.append(current);
		}
		return out.toString();
	}
	
	/**
	 * 从头截取字符串
	 * @param str   源字符串
	 * @param length  截取的长度
	 * @return
	 */
	public static String subStr(String str , int length){
		String stri = null;
		if(!StringUtil.isBlank(str)){
			if(str.length() > length){
				stri = str.substring(0, length)+"...";
			}else{
				stri = str;
			}
		}else{
			stri = "";
		}
		return stri;
	}
	/**
	 * 字符串替换
	 * StringUtil.replace("我爱中国共产党","*",2,4)
	 * @param source 源字符串
	 * @param replacement 需要被替换的结果
	 * @param start  替换字符串的开始位置
	 * @param end    替换字符串的结束位置
	 * @return
	 */
	public static String replace(String source,String replacement,int start,int end){
		if(source==null||source.length()<1) return source;
		if(end<=start) return source;
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < source.length(); i++) {
			char c = source.charAt(i);
			if(i==start&&start!=end) {
				buffer.append(replacement);
				start++;
			}else{
				buffer.append(c);
			}
		}
		return buffer.toString();
	}
	/**
	 * 去除字符串的空白或者\t|\r|\n
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			str = m.replaceAll("");
		}
		return str;
	}
	/**
	 * 判断字符串是否为空值
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value){
		return value==null||value.trim().length()==0||value.equals("null");
	}
	
	
	/**
	 * 判断字符串是否为空
	 * 
	 * @param true 字符串不为空  false 字符串为空
	 * @return
	 */
	public static boolean isNotNull(String str) {
		if (str != null && !"".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断对象是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotNull(Object obj) {
		if (obj != null && obj.toString() != null && !"".equals(obj.toString().trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据字符delim 拆分解析字符串str
	 * 返回拆分解析后的数值
	 * @param str
	 *            the string need to be parsed
	 * @param delim
	 *            the delimiter to seperate created by zqf at 6/1/2013
	 */
	public static String[] parseToArray(String str, String delim) {
		ArrayList arr = new ArrayList();
		StringTokenizer st = new StringTokenizer(str, delim);
		while (st.hasMoreTokens()) {
			arr.add(st.nextToken());
		}
		String[] ret = new String[arr.size()];
		for (int i = 0; i < arr.size(); i++) {
			ret[i] = (String) arr.get(i);
		}
		return ret;
	}

	/**
	 * 把字符串 str 里的sub替换为 rep
	 * replace a sub substring with rep in str
	 * 
	 * @param str
	 *            the string need to be replaced
	 * @param sub
	 *            the string need to be removed
	 * @param rep
	 *            the string to be inserted
	 * @return string replaced
	 */
	public static String replace(String str, String sub, String rep) {
		if ((str == null) || (sub == null) || (rep == null)) {// if one is null
																// return ""
			return "";
		}
		int index = str.indexOf(sub);
		if ((index < 0) || "".equals(sub)) { // if no old string found or
												// nothing to replace,return the
												// origin
			return str;
		}
		StringBuffer strBuf = new StringBuffer(str);
		while (index >= 0) { // found old part
			strBuf.delete(index, index + sub.length());
			strBuf.insert(index, rep);
			index = strBuf.toString().indexOf(sub);
		}
		return strBuf.toString();
	}

	/**
	 * 带逗号分隔的数字转换为NUMBER类型
	 * 
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Number stringToNumber(String str) throws ParseException {
		if (str == null || "".equals(str)) {
			return null;
		}
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		dfs.setGroupingSeparator(',');
		dfs.setMonetaryDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("###,###,###,###.##", dfs);
		return df.parse(str);
	}

	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	/**
	 * 判断字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (str == null || str.length() == 0) {
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @description 获取当前服务器日期
	 * @return
	 */
	public static String getCurrdate(String formatStr) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
		String mDateTime = formatter.format(cal.getTime());
		return mDateTime;
	}

	/**
	 * 将Object值转换成Double类型
	 * 
	 * @param value
	 * @return
	 */
	public static double getDoubleByObj(Object value) {
		if (value == null) {
			return 0;
		}
		return Double.valueOf(String.valueOf(value));
	}

	/**
	 * 将Object值转换成Float类型
	 * 
	 * @param value
	 * @return
	 */
	public static float getFloatByObj(Object value) {
		if (value == null) {
			return 0;
		}
		return Float.valueOf(String.valueOf(value));
	}

	/**
	 * 将Object值转换成Integer类型
	 * 
	 * @param value
	 * @return
	 */
	public static Integer getIntegerByObj(Object value) {
		if (value == null) {
			return 0;
		}
		return Integer.valueOf(String.valueOf(value));
	}

	/**
	 * 解析字符串 ---> 去掉字符串中回车、换行、空格
	 * 
	 * @param str
	 *            被解析字符串
	 * @return String 解析后的字符串
	 */
	public static String parse(String str) {
		return str.replaceAll("\n", "").replaceAll("chr(13)", "").replaceAll(" ", "");
	}

	/**
	 * 获取UUID
	 * 
	 * @return UUID
	 */
	public static String getUUID() {

		return (UUID.randomUUID() + "").replaceAll("-", "");
	}

	/**
	 * 将字符串转移为ASCII码
	 * 
	 * @param cnStr
	 * @return
	 */
	public static String getCnASCII(String cnStr) {
		StringBuffer strBuf = new StringBuffer();
		byte[] bGBK = cnStr.getBytes();
		for (int i = 0; i < bGBK.length; i++) {
			// System.out.println(Integer.toHexString(bGBK[i]&0xff));
			strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
		}
		return strBuf.toString();
	}

	/**
	 * 
	 * @param initCode
	 *            初始化编码
	 * @param length
	 *            需要生成编码长度
	 * @param ind
	 *            地增量
	 * @return 递增后的编码
	 */
	public static String getNextCode(String initCode, int length, int ind) {
		Integer temp = Integer.parseInt(initCode);
		temp = temp + ind;
		String tempCode = temp.toString();
		int tempLen = 0;
		if (tempCode.length() < length) {
			tempLen = length - tempCode.length();
		}
		for (int i = 0; i < tempLen; i++) {
			tempCode = "0" + tempCode;
		}
		return tempCode;
	}


	public static String geneStrAry(String str, String splits) {
		if (StringUtil.isEmpty(str))
			return "";
		String[] ary = str.split(splits);
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < ary.length; i++) {
			sb.append("'");
			sb.append(ary[i]);
			sb.append("'");
			if (i < ary.length - 1)
				sb.append(",");
		}
		return sb.toString();
	}

	public static boolean equals(String str1, String str2) {
		return str1 == null ? false : str2 == null ? true : str1.equals(str2);
	}

	public static boolean equalsIgnoreCase(String str1, String str2) {
		return str1 == null ? false : str2 == null ? true : str1.equalsIgnoreCase(str2);
	}

	/**
	 * 
	 * @param obj
	 *            传数值类型的obj
	 * @param format
	 * @return
	 */
	public static String decimalFormat(Object obj) {
		if (null == obj)
			return "";
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(obj);
	}

	/**
	 * 
	 * @param obj
	 *            传数值类型的obj
	 * @param format
	 * @return
	 */
	public static String decimalFormat(Object obj, String format) {
		if (null == obj)
			return "";
		DecimalFormat df = new DecimalFormat(format);
		return df.format(obj);
	}
	
	
	/**
	 * 随机生成指定长度字符串
	 *     生成字符串可能重复
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) { //length表示生成字符串的长度  
	    String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 } 
	/**
	 * 随机生成指定长度字符串
	 *     生成字符串可能重复
	 * @param length
	 * @return
	 */
	public static String getRandomNumber(int length) { //length表示生成字符串的长度  
	    String base = "0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 } 
	
	
	
	public static void main(String[] args) {
		System.out.println(getRandomNumber(6));
	}
	
}
