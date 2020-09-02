package com.live.test.javaee.springboot.discovery.controller;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Component
//@Controller
@RestController
@RequestMapping("discovery/provider")
@EnableDiscoveryClient
public class DiscoveryProviderController {

	@RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
	public String echo(@PathVariable String str) {
		System.out.println("*provider1:" + str);
		return "hello" + str;
	}

}
