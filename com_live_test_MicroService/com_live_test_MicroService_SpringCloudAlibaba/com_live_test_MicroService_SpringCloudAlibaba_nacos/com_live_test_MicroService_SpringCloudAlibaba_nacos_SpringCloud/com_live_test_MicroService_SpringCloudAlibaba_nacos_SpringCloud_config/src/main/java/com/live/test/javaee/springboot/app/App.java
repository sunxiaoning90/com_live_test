package com.live.test.javaee.springboot.app;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

//import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.live.test.javaee.springboot.config.controller.ConfigController;

@SpringBootApplication 
@ComponentScan(value = {"com.live.test.javaee.springboot.*" })
//@NacosPropertySource(dataId = "example", autoRefreshed = true)
//@EnableConfigurationProperties
public class App {

	public static void main(String[] args) {
//		SpringApplication.run(HelloSpringBoot.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		ConfigController bean = context.getBean(ConfigController.class);
		System.out.println("尝试获取bean：" + bean);
		
//		int in = 0;
//		try {
//			in = System.in.read();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println(in);
	}
}