package com.live.test.javase.core.array;

import java.util.Arrays;

import org.junit.Test;

/**
 * Java数组
 * 
 * <pre>
二、Java中数组工具类
1、Java中数组工具：System.arraycopy()
System类中有一个关于数组的方法：数组copy
System.arraycopy(src, srcPos, dest, destPos, length); //它是一个native方法
详见：System
2、Java中数组工具类：Arrays类（java.util.Arrays）
详见：Java中数组工具类：Arrays类（java.util.Arrays）
3、Java中数组工具类：ArrayUtils类（org.apache.commons.lang.ArrayUtils）
详见：Java中数组工具类：ArrayUtils类（org.apache.commons.lang.ArrayUtils）
 * </pre>
 * 
 * @author live
 */
public class TestArray {

	public static void main(String[] args) {
		new TestArray().testMax();
	}

	/**
	 * 1、数组 增删改查
	 */
	public void testCRUD() {
		// 声明数组
		String[] array = new String[3];

		// 增
		array[0] = "a";
		array[1] = "b";
		array[2] = "c";
		System.out.println(Arrays.toString(array));

		// 删
		array[0] = null;

		// 改
		array[0] = "b";

		// 查
		String s = array[0];
		System.out.println(s);
	}

	/**
	 * 2、数组 打印
	 */
	@Test
	public void testPrint() {
		String[] array = new String[3];
		array[0] = "a";
		array[1] = "b";
		array[2] = "c";

		// 打印 数组地址
		System.out.println(array);// [Ljava.lang.String;@4dcbadb4

		// 打印 数组具体元素
		System.out.println(Arrays.toString(array));// [a, b, c]
	}

	/**
	 * <pre>
	 3、Java数组可以包含的元素数量是否有限制？如果是，那是什么?
	  1) 理论值是：Integer.MAX_VALUE - 5
	  	如果超过上限，则报异常：Exception in thread "main" java.lang.OutOfMemoryError: Requested array size exceeds VM limit
	  
	  2) 实际要根据 JVM内存大小判定
		所以数组不要存放过多数据,否则 报异常：Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	 * </pre>
	 */
	@Test
	public void testMax() {
//		byte[] b = new byte[Integer.MAX_VALUE - 5];
		byte[] b = new byte[Integer.MAX_VALUE / 2];
		System.out.println(b.length);
	}

	/**
	 * 数组 遍历
	 */
	@Test
	public void forEach() {
		String[] array = new String[3];
		array[0] = "a";
		array[1] = "b";
		array[2] = "c";

		for (String s : array) {
			System.out.println(s);
		}
	}

}
