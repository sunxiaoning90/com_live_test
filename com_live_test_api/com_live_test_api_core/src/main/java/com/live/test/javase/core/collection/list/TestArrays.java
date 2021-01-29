package com.live.test.javase.core.collection.list;

import java.util.Arrays;

/**
 * Java中数组工具类：Arrays类（java.util.Arrays）
 * 
 * @author live
 */
public class TestArrays {

	public static void main(String[] args) {

		Arrays.asList("a", "b", "c");
//		注意：asList返回的是 一个内部类，并不是 java.util.List
//		java.util.Arrays.ArrayList.ArrayList<T>(T[] array);

	}

}
