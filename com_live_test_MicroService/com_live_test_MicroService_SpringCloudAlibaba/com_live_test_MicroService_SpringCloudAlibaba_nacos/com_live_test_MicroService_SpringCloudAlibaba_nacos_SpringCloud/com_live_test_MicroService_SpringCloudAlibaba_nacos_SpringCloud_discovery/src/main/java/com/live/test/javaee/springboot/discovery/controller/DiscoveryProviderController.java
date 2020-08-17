package com.live.test.javaee.springboot.discovery.controller;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.nacos.api.exception.NacosException;
/**
 * 服务提供者
 * @author live
 */
@Component
//@Controller
@RestController
@RequestMapping("discovery/provider")
@EnableDiscoveryClient
public class DiscoveryProviderController {

	@RequestMapping(value = "/echo/{str}")
	public String hello(@PathVariable String str) throws NacosException {
		System.out.println("请求路径传参数:" + str);
		return "hello" + str;
	}

}
