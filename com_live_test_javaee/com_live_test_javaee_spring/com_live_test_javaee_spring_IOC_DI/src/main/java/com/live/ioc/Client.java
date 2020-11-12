package com.live.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
	public static void main(String[] args) {
		Client client = new Client();
		client.test();
	}

	public void test() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("ac:" + ac);
		Person bean = (Person) ac.getBean("person");
		System.out.println("bean:" + bean);
	}
}
