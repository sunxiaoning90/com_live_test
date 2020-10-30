package com.live.test.javaee.springboot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = { "com.live.test.javaee.springboot.*" })
@EnableZuulProxy
public class App {

	public static final String APP_NAME_ALIAS = "微服务网关：zuul --port: 8088";

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		System.out.println("尝试获取context：" + context);
		System.out.println("APPlication启动成功：" + APP_NAME_ALIAS);
	}
	
	/**
	 * 重写 Location 头
	 *
	 * @return
	 */
//	@Bean
//	public LocationRewriteFilter locationRewriteFilter() {
//		LocationRewriteFilter filter = new LocationRewriteFilter();
//		boolean filterDisabled = filter.isFilterDisabled();
//		System.out.println(filterDisabled);
//		return filter;
//	}
	
}