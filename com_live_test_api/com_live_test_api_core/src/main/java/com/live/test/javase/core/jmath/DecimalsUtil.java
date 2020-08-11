package com.live.test.javase.core.jmath;

import java.text.NumberFormat;

public class DecimalsUtil {

	/**
	 * 获取百分比
	 * 
	 * @param a      : 被除数
	 * @param b      : 除数
	 * @param digits : 小数位
	 * @return
	 */
	public static String getPercent(int a, int b, int digits) {
		return getPercent(a, b, digits, digits);
	}

	/**
	 * 获取百分比
	 * 
	 * @param a         : 被除数
	 * @param b         : 除数
	 * @param minDigits : 最小小数位
	 * @param maxDigits ： 最大 小数位
	 * @return
	 */
	public static String getPercent(int a, int b, int minDigits, int maxDigits) {
		String percent = "0.00%";
		if (a != 0) {
			NumberFormat numberFormat = NumberFormat.getInstance();
			// numberFormat.setMaximumIntegerDigits(n);//
			numberFormat.setMaximumFractionDigits(minDigits);
			numberFormat.setMinimumFractionDigits(maxDigits);
			percent = numberFormat.format((float) a / (float) b * 100) + "%";
//			percent = numberFormat.format((double) a / (double) b * 100) + "%";
		}

		return percent;
	}
	
	public static void main(String[] args) {
		int sum = 3;
		int a = 1;
		String percent = getPercent(a, sum, 2);
		System.out.println(percent);
	}

}