package com.live.test.arithmetic.other;

/**
 * 字符串反转
 * @author live
 * @time 2020年12月16日 下午4:35:41
 */
public class ReverseString {
	public static void main(String[] args) {

		String s = "abcd";
		System.out.println(s);

		String s1 = re(s);
		System.out.println(s1);

		String s2 = re2(s);
		System.out.println(s2);

		String s3 = re3(s);
		System.out.println(s3);

		String s4 = re4(s);
		System.out.println(s4);
	}

	private static String re(String s) {
		if (s.length() <= 1) {
			return s;
		}

		char[] charArrayNew = new char[s.length()];

		char[] charArray = s.toCharArray();

		for (int i = 0; i < s.length(); i++) {
			charArrayNew[(s.length() - 1) - i] = charArray[i];
		}

		return new String(charArrayNew);
	}

	private static String re2(String s) {
		StringBuffer sb = new StringBuffer(s);
		sb.reverse();
		return sb.toString();
	}

	private static String re3(String s) {
		String reverseStr = "";
		for (int i = s.length() - 1; i >= 0; i--) {
			reverseStr += s.charAt(i);
		}
		return reverseStr;
	}

	private static String re4(String s) {
		System.out.println(s);
		int length = s.length();

		if (length <= 1) {
			return s;
		}

		String left = s.substring(0, length / 2);
		String right = s.substring(length / 2, length);

		// 右半部分 + 左半部分
		String reverseStr = re4(right) + re4(left);
		return reverseStr;
	}
}
