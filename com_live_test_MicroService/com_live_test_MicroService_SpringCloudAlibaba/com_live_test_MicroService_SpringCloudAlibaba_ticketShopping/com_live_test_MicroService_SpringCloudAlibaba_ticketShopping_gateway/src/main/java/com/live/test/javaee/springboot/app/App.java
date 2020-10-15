package com.live.test.javaee.springboot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = { "com.live.test.javaee.springboot.*" })
public class App {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		System.out.println("gateway启动成功：8080，context：" + context);
		
		//ConfigController bean = context.getBean(ConfigController.class);
		//System.out.println("尝试获取bean：" + bean);
	}
}