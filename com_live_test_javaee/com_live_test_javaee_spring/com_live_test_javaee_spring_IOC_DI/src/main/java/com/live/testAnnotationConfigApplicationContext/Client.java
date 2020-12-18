package com.live.testAnnotationConfigApplicationContext;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
	/**
	 * 使用AnnotationConfigApplicationContext可以实现基于Java的配置类加载Spring的应用上下文。避免使用application.xml进行配置。相比XML配置，更加便捷。
	 */
	public void test2() {
//		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		System.out.println("ac:" + ac);

		// 未刷新容器会报错：Exception in thread "main" java.lang.IllegalStateException:
		// org.springframework.context.annotation.AnnotationConfigApplicationContext@6eebc39e
		// has not been refreshed yet
		ac.register(AppConfig.class);
		ac.refresh();

		LoginService bean = (LoginService) ac.getBean("loginService");
		System.out.println("bean:" + bean);
		bean.login();
	}
}
