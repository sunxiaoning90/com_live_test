package com.live.test.javase.core.array;

public class TestArray {
	public static void main(String[] args) {
		
//		数组最大容量?
//		Exception in thread "main" java.lang.OutOfMemoryError: Requested array size exceeds VM limit
		byte[] b = new byte[Integer.MAX_VALUE];
		System.out.println(b.length);
	}
}
