package com.live.test.javaee.springboot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaServer
@ComponentScan(value = { "com.live.test.javaee.springboot.*" })
public class App {

	private static final String APP_NAME_ALIAS = "Eureka Server";

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		System.out.println("尝试获取context：" + context);
		System.out.println("APPlication启动成功：" + APP_NAME_ALIAS);

	}
}