package com.live.test.javaee.springboot.discovery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@Component
@RestController
@RequestMapping("discovery/consumer")
//@EnableDiscoveryClient
@EnableEurekaClient
public class DiscoveryConsumerController {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	public RestTemplate restTemplate;
	
	@RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
	public String echo(@PathVariable String str) {
		System.out.println(str);
//		return "hello" + str;
//		String url = "http://localhost:8082/"+"discovery/provider/echo/"+str;
		String url = "http://192.168.1.50:8082/"+"discovery/provider/echo/"+str;
		return restTemplate.getForObject(url, String.class);
	}

}
