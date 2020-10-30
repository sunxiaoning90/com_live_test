package com.live.test.javaee.springboot.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = { "com.live.test.javaee.springboot.*" })
public class App {

	public static final String APP_NAME_ALIAS = "服务提供方：com_live_test_MicroService_SpringCloudNexflix_CRM_order3_dubbo_orderServiceAPI --port: 8039";
	
	public static void main(String[] args) {
//		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		ConfigurableApplicationContext context  = new SpringApplicationBuilder(App.class).web(true).run(args);
		System.out.println("尝试获取context：" + context);
		System.out.println("APPlication启动成功：" + APP_NAME_ALIAS);

	}
}