package com.live.test.javase.core.string;

public class StringTest {
	public static void main(String[] args) {
		String a = null + "";// null
		System.out.println("a:" + a);

		String b = String.valueOf(null);// new String(char value[])异常 NPE ,底层: this.value = Arrays.copyOf(value, value.length);
		System.out.println("b:" + b);
		
		char[] data = null;
		String c = new String(data);// new String(data) 异常 NPE ,底层: this.value = Arrays.copyOf(value, value.length);
		System.out.println("c:" + c);
	}
}
