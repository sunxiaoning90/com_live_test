package com.live.test.javase.core.binary;
 /**
  * 位运算 & | ~ ^
  * 进制转换
  * @author live
  */
public class BinaryUtil {

	public static void main(String[] args) {
		System.out.println(DecimalToBinary(10));

		radixRange();

		System.out.println(binaryToDecimal("1010"));
		System.out.println(binaryToAny("1010", 2));
		System.out.println(binaryToAny("1010", 8));
		System.out.println(binaryToAny("1010", 16));

		bitTest();
	}

	/**
	 * 位运算 & | ~ ^
	 */
	private static void bitTest() {
		// 位运算 与
		System.out.println(1 & 0);
		// 位运算 或
		System.out.println(1 | 0);
		// 位运算 非
		System.out.println(~1);
		// 位运算 异或
		System.out.println(1 ^ 1);
	}

	/**
	 * 10进制 转 2进制
	 * 
	 * @param str
	 * @return
	 */
	private static String DecimalToBinary(int i) {
		String str = "";
		while (i != 0) {
			str = i % 2 + str;
			i = i / 2;
		}
		return str;
	}

	/**
	 * 2进制 转 10进制
	 * 
	 * @param str
	 * @return
	 */
	private static int binaryToDecimal(String str) {
		return Integer.parseInt(str, 2);
	}

	/**
	 * 2进制 转 任意进制
	 * 
	 * @param str
	 * @return
	 */
	private static int binaryToAny(String str, int radix) {
		return Integer.parseInt(str, radix);
	}

	/**
	 * <pre>
	 * 1、进制的范围：2 ~ 36
	 * 2、用到的最多的是:
	 * 二进制：Binary 
	 * 八进制：O或Octal 
	 * 十进制：Decimal 
	 * 十六进制（0x）：Hexadecimal
	 * </pre>
	 */
	private static void radixRange() {
		int min = Character.MIN_RADIX;
		int max = Character.MAX_RADIX;

		System.out.println("进制的范围：" + min + " ~ " + max);
	}

}
