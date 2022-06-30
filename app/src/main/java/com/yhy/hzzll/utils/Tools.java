package com.yhy.hzzll.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {

	/**
	 * string 转 double
	 * 
	 * @param number
	 * @return
	 */
	public static double stringToDouble(String number) {
		double num1 = 0;
		try {
			return Double.parseDouble(number);
		} catch (Exception e) {
			return num1;
		}
	}

	/**
	 * string 转 float
	 * 
	 * @param number
	 * @return
	 */
	public static float stringToFloat(String number) {
		float num1 = 0;
		try {
			return Float.parseFloat(number);
		} catch (Exception e) {
			return num1;
		}
	}

	/**
	 * string 转 int
	 * 
	 * @param number
	 * @return
	 */
	public static int stringToInt(String number) {
		int num1 = 0;
		try {
			return Integer.parseInt(number);
		} catch (Exception e) {
			return num1;
		}
	}

	/**
	 * string 转 int
	 * 
	 * @param number
	 * @return
	 */
	public static long stringToLong(String number) {
		long num1 = 0;
		try {
			return Long.parseLong(number);
		} catch (Exception e) {
			return num1;
		}
	}

	/**
	 * //获取完整的域名
	 * 
	 * @param text
	 *            获取浏览器分享出来的text文本
	 */
	public static boolean getCompleteUrl(String text) {
		Pattern p = Pattern
				.compile(
						"((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?",
						Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(text);
		boolean find = matcher.find();
		return find;
	}
}
