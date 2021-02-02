package com.live.test.javaNewFeature.java8;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法引用通过方法的名字来指向一个方法。
 * 
 * 方法引用可以使语言的构造更紧凑简洁，减少冗余代码。
 * 
 * 方法引用使用一对冒号 :: 。
 * 
 * @author live
 */
public class TestMethodReferences {

	public static void main(String[] args) {
		List<String> names = new ArrayList<String>();

		names.add("a");
		names.add("b");
		names.add("c");

		names.forEach(System.out::println);
	}
}
