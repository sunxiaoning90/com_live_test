//package com.live.test.javaee.springboot.feign.service;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
///**
// * 定义接口
// * 调用远程服务
// * 
// * @author live
// */
////@Component
//@FeignClient(name = "com-live-test-MicroService-SpringCloudNetflix-eureka-discovery-provider")
//public interface FeignTestServcie {
//
//	@GetMapping("discovery/provider/echo/{str}")
////	String echo(@PathVariable String str) ; //此写法错误，必须使用value指定 @PathVariable(value="str"）String str
//	String echo(@PathVariable(value = "str") String str);
////	{
////		return restTemplate.getForObject("http://service-provider/discovery/provider/echo/" + str, String.class);
////	}
//}
