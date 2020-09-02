package com.live.test.javaee.springboot.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.live.test.javaee.springboot.feign.service.FeignTestServcie;

@Component
@RestController
@RequestMapping("feign")
@EnableDiscoveryClient
//@EnableFeignClients
@EnableFeignClients(basePackages="com.live.test.javaee.springboot.feign.service")
public class FeignConsumerController {

	/**
	 * 通过 Feign 服务调用方式
	 */
	@Autowired
	FeignTestServcie service1;

	@RequestMapping(value = "/echoByFeign/{str}", method = RequestMethod.GET)
	public String echoByFeign(@PathVariable String str) {
		return service1.echo(str);
	}

	/**
	 * 二、通过 RestTemplate + Ribbon 服务调用方式
	 */
//	@NacosInjected
//	@Autowired
//	private NamingService namingService;
	private final RestTemplate restTemplate;

	@Autowired
	public FeignConsumerController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
	public String echo(@PathVariable String str) {
//		return restTemplate.getForObject("http://service-provider/echo/" + str, String.class);
		return restTemplate.getForObject("http://service-provider/discovery/provider/echo/" + str, String.class);
	}
}