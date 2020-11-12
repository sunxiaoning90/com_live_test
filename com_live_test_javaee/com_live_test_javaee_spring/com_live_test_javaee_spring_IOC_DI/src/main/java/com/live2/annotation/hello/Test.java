package com.live2.annotation.hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-annotation.xml");
		System.out.println("ac:" + ac);
		
		Hello bean = (Hello) ac.getBean("hello");
		System.out.println("bean:" + bean);
	}
}
