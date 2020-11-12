package com.live.scope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans-scope.xml");
		
		Person person1 = (Person) ac.getBean("person");
		System.out.println("第一次获取person：" + person1);

		Person person2 = (Person) ac.getBean("person");
		System.out.println("第二次获取person：" + person2);
		
		System.out.println("二者是否==：" + (person1 == person2));
	}
}
