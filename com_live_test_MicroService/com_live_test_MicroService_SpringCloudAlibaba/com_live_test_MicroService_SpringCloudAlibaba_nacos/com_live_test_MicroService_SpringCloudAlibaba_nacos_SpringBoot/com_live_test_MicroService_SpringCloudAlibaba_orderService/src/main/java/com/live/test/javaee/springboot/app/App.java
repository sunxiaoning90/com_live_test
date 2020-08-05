package com.live.test.javaee.springboot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.live.test.javaee.springboot.controller.HelloController;

@SpringBootApplication 
@ComponentScan(value = {"com.live.test.javaee.springboot.*" })
@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class App {
	public static void main(String[] args) {
//		SpringApplication.run(HelloSpringBoot.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		HelloController bean = context.getBean(HelloController.class);
		System.out.println(bean);
	}
}
