package com.live.test.javaee.springboot.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = { "com.live.test.javaee.springboot.*" })
@MapperScan("com.live.test.javaee.springboot.*")
public class App {
	public static final String APP_NAME_ALIAS = "Spring Boot SSM -- port:8089";

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		System.out.println(context);

		System.out.println(APP_NAME_ALIAS + "启动成功...");
	}
}
