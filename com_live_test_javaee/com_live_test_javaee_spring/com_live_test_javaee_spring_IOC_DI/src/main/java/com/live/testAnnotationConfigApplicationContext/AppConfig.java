package com.live.testAnnotationConfigApplicationContext;

import org.springframework.context.annotation.Bean;

/**
 * 注：

@Configuration可理解为用spring的时候xml里面的<beans>标签

@Bean可理解为用spring的时候xml里面的<bean>标签
 * @author live
 */
public class AppConfig {
	@Bean(name="loginService")
	public LoginService createBeanLoginService() {
		return new LoginService();
	}
	
	@Bean
	public LoginService loginService2() {
		return new LoginService();
	}
}
