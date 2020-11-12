package com.live.test.javaee.spring.service;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.live.test.javaee.spring.aop.UserService;
import com.live.test.javaee.spring.po.Evaluate;
import com.live.test.javaee.spring.testCycleDepends.TestCycleA;
import com.live.test.javaee.spring.testCycleDepends.TestCycleB;

/**
 * 
 * @author live
 * 
 *         <pre>
1、IOC/DI 几个重要的类
ApplicationContext :IoC容器的封装、入口类
BeanDefinition	：Bean定义，保存配置信息的封装类
BeanWrapper	：Bean包装，保存所有的实例的封装类(原生对象、代理对象)
BeanDefinitionReader：工具类，读取并解析配置文件（xml/yml/properties），统一封装为 BeanDefinition

2、Spring 在设计IOC时，使用ApplicationContext、BeanDefinition、BeanWrapper等 进行一系列封装的目的：
代码健壮性、提高可扩展性、代码可读性、单一职贵原则、开闭原则、解耦 ...

3、ApplicationContext的getBean()方法的两大职责：
1）instaniateBean()创建对象
2）populateBean()依赖注入
 *         </pre>
 * 
 */
public class TestGetBean {
	public static void main(String[] args) {
		TestGetBean t = new TestGetBean();
//		 t.test1();
//		 t.test2();
//		t.testCycleDepends();
		t.testAOP();
	}

	// 方式一.ApplicationContext
	private void test1() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		System.out.println("ac:" + ac);

		EvaluateService bean = ac.getBean("evaluateService2", EvaluateService.class);
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

		TestCycleA a = ac.getBean("testCycleA", TestCycleA.class);
		System.out.println("TestCycleA:" + a);
		System.out.println("b:" + a.getB());

		TestCycleB b = ac.getBean("testCycleB", TestCycleB.class);
		System.out.println("TestCycleB:" + b);
		System.out.println("a:" + b.getA());

	}

	private void testAOP() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		System.out.println("ac:" + ac);

		UserService bean = ac.getBean("userService", UserService.class);
		System.out.println("bean:" + bean);

		boolean flg = bean.delete("id01");
		System.out.println(flg);
	}

}
