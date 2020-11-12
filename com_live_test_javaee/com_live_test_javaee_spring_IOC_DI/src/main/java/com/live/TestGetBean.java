package com.live;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.live.hello.LoginService;

public class TestGetBean {
	public static void main(String[] args) {
		TestGetBean t = new TestGetBean();
		// t.test1();
		// t.test2();
		t.test3();
	}

	// 方式一
	private void test1() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("ac:" + ac);
		LoginService bean = (LoginService) ac.getBean("loginService");
		System.out.println("bean:" + bean);
	}
	// 方式二
	private void test2() {
		BeanFactory bf = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("bf:" + bf);
		LoginService bean = (LoginService) bf.getBean("loginService");
		System.out.println("bean:" + bean);
	}
	//方式三
	private void test3() {
		ApplicationContext ac = BeanUtil.ac;
		System.out.println("ac:" + ac);
		LoginService bean = (LoginService) ac.getBean("loginService");
		System.out.println("bean:" + bean);
	}

}
