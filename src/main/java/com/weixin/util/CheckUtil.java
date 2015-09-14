package com.weixin.util;

import java.util.Arrays;

/**
 * @author : Jay
 */
public class CheckUtil {

	public static final String token = "m7LBAO-iBKjYwGoZuf1bU8uWTSmw47re9XsNlEmeruc41Dd3VN-t6POY8T5zasQT5P7EIOBENj3VhdSGd4sqnKI8HqFhnbw5jHqWNcJZ-fs";

	public static boolean checkSignature(String signature, String timestamp,String nonce) {
		
		//String token = TokenThread.accessToken.getAccessToken();
		//System.out.println("------------token------------ " + token);
		String[] arr = new String[] { token, timestamp, nonce };

		// 排序
		Arrays.sort(arr);

		// 生成字符
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
		}

		// sha1 加密
		EncryptUtil sha1 = new EncryptUtil();
		String str = sha1.Encrypt(sb.toString(), "SHA-1") ;
		System.out.println(str);
		return signature.equals(str);

	}

}
