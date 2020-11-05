package com.live.test.javase.core.string;

/**
 * String 放入常量池
 * @author live
 */
public class TestIntern {
	public static void main(String[] args) {
		String s2 = "ab";
		String s0 =new String("ab");
		String s1 ="a"+"b";
		System.err.println(s0 == s2);
		System.err.println(s1 == s2);
		
		String intern = s1.intern();
		System.err.println(s1 == intern);
		System.err.println(s1 == s2);
	}
}
