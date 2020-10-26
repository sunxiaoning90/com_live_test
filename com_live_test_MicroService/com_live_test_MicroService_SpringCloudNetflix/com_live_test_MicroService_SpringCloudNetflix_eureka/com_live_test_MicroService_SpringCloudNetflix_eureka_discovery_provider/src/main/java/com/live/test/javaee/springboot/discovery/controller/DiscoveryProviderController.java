package com.live.test.javaee.springboot.discovery.controller;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.live.test.javaee.springboot.app.App;

@Component
@RestController
@RequestMapping("discovery/provider")
//@EnableDiscoveryClient
@EnableEurekaClient
public class DiscoveryProviderController {

	@RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
	public String echo(@PathVariable String str) {
		String r = "*响应来自：" + App.APP_NAME_ALIAS + ",响应结果：" + str;
		System.out.println(r);
		return r;
	}

}
