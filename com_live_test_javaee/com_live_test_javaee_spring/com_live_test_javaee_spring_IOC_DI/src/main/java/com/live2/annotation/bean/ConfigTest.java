package com.live2.annotation.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Component
public class ConfigTest {

	@Bean
	public String string() {
		return new String("没有Stirng源码，不能在类上增加诸如 @Component等注解，但是可以使用@bean 将Stirng 纳入IOC");
	}
}
