package com.live.test.javase.core.reflect;

import java.lang.reflect.Constructor;

public class TestReflect {
	
	/**
	 * 测试：class.forName()
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class clazz1 = Object.class;
		System.out.println(clazz1);
		
		Object o = new Object();
		Class clazz2 = o.getClass();
		System.out.println(clazz2);
		
//		String className = "server.chatSupport.util.Configuration.java";
//		String className = "server.chatSupport.util.Configuration.class";
		String className = "server.chatSupport.util.Configuration";
		Class clazz3 = Class.forName(className );
		System.out.println(clazz3);
//		Configuration newInstance = (Configuration) clazz3.newInstance();
//		System.out.println(newInstance);
		
	}
	
	/**
	 * 测试 构造方法
	 * @throws Exception
	 */
	private static void TestRelect() throws Exception {
		Constructor<Object> constructor = Object.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		Object o = constructor.newInstance();
		System.out.println(o);
	}
}
