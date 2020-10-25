package com.live.test.javaee.springboot.discovery.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;

@Component
@RestController
@RequestMapping("discovery/discoveryClient")
@EnableDiscoveryClient
//@EnableEurekaClient
public class DiscoveryConsumerGetDiscoveryClientController {

	@Autowired
	private EurekaClient discoveryClient;

	@RequestMapping(value = "/getDiscoveryClient", method = RequestMethod.GET)
	public String getDiscoveryClient() {
		//InstanceInfo instance = discoveryClient.getNextServerFromEureka("STORES", false);
//		return instance.getHomePageUrl();
		
//		discoveryClient.getAllKnownRegions(); //[us-east-1]
		
//		List instancesById = discoveryClient.getInstancesById("com_live_test_MicroService_SpringCloudNetflix_eureka_discovery_provider");
//		for (Object obj : instancesById) {
//			System.out.println(obj);
//		}
		
		Applications applications = discoveryClient.getApplications();
		List<Application> registeredApplications = applications.getRegisteredApplications();
		
		return Arrays.toString(registeredApplications.toArray());
	}
}
