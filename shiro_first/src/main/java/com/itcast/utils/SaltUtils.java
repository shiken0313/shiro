package com.itcast.utils;

import java.util.Random;

public class SaltUtils {

	/**
	 * 
	 * @param n
	 * @return
	 */
	public static String getSalt(int n) {
		char[] charArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()".toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < n; i++) {
			char chara = charArray[new Random().nextInt(charArray.length)];
			sb.append(chara);
		}
		return sb.toString();
	}
	
	
}
