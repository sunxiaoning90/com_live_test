package com.live.test.javaee.spring.service;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.live.test.javaee.springmvc.controller.EvaluateController;
import com.live.test.javaee.springmvc.po.Evaluate;
import com.live.test.javaee.springmvc.service.EvaluateService;

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
		
		EvaluateService service = (EvaluateService) ac.getBean("evaluateService");
		System.out.println("service:" + service);
		
		Evaluate entity = service.getEvaluateById(1);
		System.out.println(entity);
		
		
		EvaluateController controller = (EvaluateController) ac.getBean("evaluateController");
		System.out.println("controller:" + controller);
		
//		Evaluate entity2 = controller.getEvaluateById();
//		System.out.println(entity2);
	}
	// 方式二.BeanFactory
	private void test2() {
		BeanFactory bf = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		System.out.println("bf:" + bf);
		EvaluateService bean = (EvaluateService) bf.getBean("evaluateService");
		System.out.println("bean:" + bean);
	}

}
