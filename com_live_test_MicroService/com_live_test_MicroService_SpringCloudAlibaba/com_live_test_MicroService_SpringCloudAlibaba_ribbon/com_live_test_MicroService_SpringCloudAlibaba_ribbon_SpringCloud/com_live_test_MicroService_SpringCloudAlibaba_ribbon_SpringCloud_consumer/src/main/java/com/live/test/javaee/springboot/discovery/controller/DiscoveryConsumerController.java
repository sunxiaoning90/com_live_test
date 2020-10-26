package com.live.test.javaee.springboot.discovery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Component
@RestController
@RequestMapping("discovery/consumer")
@EnableDiscoveryClient
public class DiscoveryConsumerController {

//	@NacosInjected
//	@Autowired
//	private NamingService namingService;
	private final RestTemplate restTemplate;

	@Autowired
	public DiscoveryConsumerController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
	public String echo(@PathVariable String str) {
//		return restTemplate.getForObject("http://service-provider/echo/" + str, String.class);
		return restTemplate.getForObject("http://service-provider" + "/discovery/provider/echo/" + str, String.class);
	}
}