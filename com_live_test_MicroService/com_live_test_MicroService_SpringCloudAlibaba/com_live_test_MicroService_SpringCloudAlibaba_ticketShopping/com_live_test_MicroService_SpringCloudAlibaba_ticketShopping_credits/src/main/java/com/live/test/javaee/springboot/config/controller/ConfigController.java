package com.live.test.javaee.springboot.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@ComponentScan
@Controller
@RefreshScope // Nacos 配置页改动数据时，自动刷新
@RequestMapping("config")
@ResponseBody
public class ConfigController {

//	通过 Spring Cloud 原生注解 @EnableDiscoveryClient 开启服务注册发现功能。给 RestTemplate 实例添加 @LoadBalanced 注解，开启 @LoadBalanced 与 Ribbon 的集成：
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
//	@NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
	@Value("${useLocalCache:false}")
	private boolean useLocalCache;

	@Value(value = "${test:test1}")
	private String a;

	/**
	 * http://localhost:8080/config/get
	 */
	@RequestMapping("/get")
	public boolean get() {
		System.out.println(a);
		System.out.println("读取nacaos配置：" + useLocalCache);
		return useLocalCache;
	}
}