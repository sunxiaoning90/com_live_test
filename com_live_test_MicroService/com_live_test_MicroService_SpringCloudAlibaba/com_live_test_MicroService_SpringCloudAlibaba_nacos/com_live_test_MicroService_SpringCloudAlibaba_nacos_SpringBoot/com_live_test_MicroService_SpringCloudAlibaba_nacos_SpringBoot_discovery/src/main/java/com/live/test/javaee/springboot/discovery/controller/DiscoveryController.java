package com.live.test.javaee.springboot.discovery.controller;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;

@Component
@Controller
@RequestMapping("discovery")
public class DiscoveryController {

	@NacosInjected
    private NamingService namingService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public List<Instance> get(@RequestParam String serviceName) throws NacosException {
    	System.out.println("<<<serviceName:" + serviceName);
        return namingService.getAllInstances(serviceName);
    }
    
}
