package com.live.test.javaee.springboot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.live.test.javaee.springboot.discovery.controller.DiscoveryController;

@SpringBootApplication
@ComponentScan(value = { "com.live.test.javaee.springboot.*" })
@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class App {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		DiscoveryController bean = context.getBean(DiscoveryController.class);
		System.out.println(bean);
	}
	
}
