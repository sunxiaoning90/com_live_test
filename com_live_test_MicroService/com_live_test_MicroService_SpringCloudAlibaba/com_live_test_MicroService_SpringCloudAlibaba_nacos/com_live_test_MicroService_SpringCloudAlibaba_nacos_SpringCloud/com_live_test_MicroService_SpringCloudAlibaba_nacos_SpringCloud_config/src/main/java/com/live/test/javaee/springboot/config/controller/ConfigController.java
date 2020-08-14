package com.live.test.javaee.springboot.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ComponentScan
@Controller
@RefreshScope // Nacos 配置页改动数据时，自动刷新
@RequestMapping("config")
@ResponseBody
public class ConfigController {

//	@NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
	@Value("${useLocalCache:false}")
	private boolean useLocalCache;

	@Value(value = "${test}")
	private String a;

	/**
	 * http://localhost:8080/config/get
	 */
	@RequestMapping("/get")
	public boolean get() {
		System.out.println(a);
		System.out.println("配置：" + useLocalCache);
		return useLocalCache;
	}
}