package com.live.test.javaee.spring.service;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.live.test.javaee.spring.po.Evaluate;
import com.live.test.javaee.spring.testCycleDepends.TestCycleA;
import com.live.test.javaee.spring.testCycleDepends.TestCycleB;

public class TestGetBean {
	public static void main(String[] args) {
		TestGetBean t = new TestGetBean();
//		 t.test1();
//		 t.test2();
		t.testCycleDepends();
	}

	// 方式一.ApplicationContext
	private void test1() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		System.out.println("ac:" + ac);
		
		EvaluateService bean = ac.getBean("evaluateService2",EvaluateService.class);
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

	private void testCycleDepends() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		System.out.println("ac:" + ac);
		
		TestCycleA a = ac.getBean("testCycleA",TestCycleA.class);
		System.out.println("TestCycleA:" + a);
		System.out.println("b:" + a.getB());
		
		TestCycleB b = ac.getBean("testCycleB",TestCycleB.class);
		System.out.println("TestCycleB:" + b);
		System.out.println("a:" + b.getA());
		
	}
	
}
