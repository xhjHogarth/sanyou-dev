package com.sanyou.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;

/**
 * @author asus
 */
public class MD5Utils {

	/**
	 * @Description: 对字符串进行md5加密
	 */
	public static String getMD5Str(String strValue) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		return Base64.encodeBase64String(md5.digest(strValue.getBytes()));
	}

}
