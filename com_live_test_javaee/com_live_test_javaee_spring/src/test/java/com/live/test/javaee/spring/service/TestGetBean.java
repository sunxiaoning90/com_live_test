package com.live.test.javaee.spring.service;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.live.test.javaee.spring.po.Evaluate;

public class TestGetBean {
	public static void main(String[] args) {
		TestGetBean t = new TestGetBean();
		 t.test1();
//		 t.test2();
	}

	// 方式一.ApplicationContext
	private void test1() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		System.out.println("ac:" + ac);
		
		EvaluateService bean = (EvaluateService) ac.getBean("evaluateService");
		System.out.println("bean:" + bean);
		
		Evaluate entity = bean.getEvaluateById(1);
		System.out.println(entity);
	}
	// 方式二.BeanFactory
	private void test2() {
		BeanFactory bf = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("bf:" + bf);
		EvaluateService bean = (EvaluateService) bf.getBean("evaluateService");
		System.out.println("bean:" + bean);
	}

}
