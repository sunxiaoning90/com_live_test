package com.live.hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
	public static void main(String[] args) {
		Client client = new Client();
//		client.test1();
		client.test2();
	}

	// 普通方式
	public void test1() {
		LoginService service = new LoginService();
		service.login();
	}

	// Spring
	public void test2() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("ac:" + ac);
		LoginService bean = (LoginService) ac.getBean("loginService");
		System.out.println("bean:" + bean);
		bean.login();
	}
}
