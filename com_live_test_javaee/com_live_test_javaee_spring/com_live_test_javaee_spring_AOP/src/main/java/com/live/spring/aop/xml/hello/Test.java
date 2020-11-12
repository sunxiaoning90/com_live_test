package com.live.spring.aop.xml.hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-AOP-XML.xml");
		System.out.println("ac:" + ac);

		Hello bean = (Hello) ac.getBean("hello");
		System.out.println("bean:" + bean);
		bean.sayHello();
	}
}
