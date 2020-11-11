package com.live.aop.hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		Test t = new Test();
		t.test1();
	}

	// Spring
	public void test1() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("ac:" + ac);

		Hello bean = (Hello) ac.getBean("hello");
		System.out.println("bean:" + bean);
		bean.sayHello();
		
		System.out.println(ac.getBean("helloAspect"));
	}
}
