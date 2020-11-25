package com.live.test.javase.core.baseTypeOf8;



public class IntegerTest {
	public static void main(String[] args) {
		testParseInt();
	}

	/**
	 * parseInt("0", 10) returns 0
 parseInt("473", 10) returns 473
 parseInt("+42", 10) returns 42
 parseInt("-0", 10) returns 0
 parseInt("-FF", 16) returns -255
 parseInt("1100110", 2) returns 102
 parseInt("2147483647", 10) returns 2147483647
 parseInt("-2147483648", 10) returns -2147483648
 parseInt("2147483648", 10) throws a NumberFormatException
 parseInt("99", 8) throws a NumberFormatException
 parseInt("Kona", 10) throws a NumberFormatException
 parseInt("Kona", 27) returns 411787
	 */
	private static void testParseInt() {
		System.out.println(Integer.parseInt("123"));
		System.out.println(Integer.parseInt("1010",2));
		System.out.println(Integer.parseInt("123", 16));
		System.out.println(Integer.parseInt("A", 16));
	}
	
	
}
