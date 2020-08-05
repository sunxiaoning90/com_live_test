package com.live.test.javaee.springboot.config.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.nacos.api.config.annotation.NacosValue;

@Component
@Controller
@RequestMapping("config")
public class ConfigController {

	// useLocalCache=true
	@NacosValue(value = "${useLocalCache:false}", autoRefreshed = true) //@NacosValue，是nacos提供的注解，支持自动刷新
//	@Value(value="${useLocalCache:false}") //@Value 是Spring提供的， 不支持自动刷新
	private boolean useLocalCache;

	@RequestMapping(value = "/get", method = GET)
	@ResponseBody
	public boolean get() {
		return useLocalCache;
	}

}
