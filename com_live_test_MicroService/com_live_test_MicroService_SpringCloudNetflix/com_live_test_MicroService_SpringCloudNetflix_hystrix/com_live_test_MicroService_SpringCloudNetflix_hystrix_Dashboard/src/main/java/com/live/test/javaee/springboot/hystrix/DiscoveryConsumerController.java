//package com.live.test.javaee.springboot.hystrix;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.live.test.javaee.springboot.app.App;
//import com.live.test.javaee.springboot.feign.service.FeignTestServcie;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
//
//@Component
//@RestController
//@RequestMapping("discovery/consumer")
////@EnableDiscoveryClient
//@EnableDiscoveryClient
//@EnableFeignClients(basePackages = "com.live.test.javaee.springboot.feign.service")
//@EnableCircuitBreaker
//public class DiscoveryConsumerController {
//
//	/**
//	 * 一、使用 RestTemplate
//	 */
//
////	@Bean
////	@LoadBalanced
////	public RestTemplate restTemplate() {
////		RestTemplate rt = new RestTemplate();
////		System.out.println(rt);
////		return rt;
////	}
//
////	@Autowired
////	public RestTemplate restTemplate;
////
////	@RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
////	public String echo(@PathVariable String str) {
////		System.out.println(str);
////
////		// RestTemplate 缺点：使用 RestTemplate 进行http调用时，服务地址是固定的，虽然可以指定域名，可以修改ngxin的配置文件来修改真实服务器地址，但是还是很繁琐不方便
//////		String serverAddress = "http://localhost:8082";
////		
////		//ribbon 负载均衡 ，无需关注服务所在的server地址、端口，指定服务名即可 ,服务名避免使用下划线，下划线导致服务发现失败
//////		String serverAddress = "http://com_live_test_MicroService_SpringCloudNetflix_eureka_discovery_provider";
////		String serverAddress = "http://com-live-test-MicroService-SpringCloudNetflix-eureka-discovery-provider";
////		
////		String url = serverAddress + "/discovery/provider/echo/" + str;
////		
////		String r = restTemplate.getForObject(url, String.class);
////		System.out.println(r);
////		return r;
////	}
//
//	/**
//	 * 二、通过 Feign 服务调用方式
//	 */
//
//	@Autowired
//	FeignTestServcie service1;
//	
//// 指定应急处理方法
////	@HystrixCommand(fallbackMethod = "failFast1") 
////	@RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
////	public String echoByFeign(@PathVariable String str) {
////		System.out.println("by Hystrix:正常处理");
////		return service1.echo(str);
////	}
//	
//	// 指定应急处理方法,并指定具体规则
//	//官方文档 https://github.com/Netflix/Hystrix/wiki
//	/**
//	 * @param str
//	 * @return
//	 */
//	@HystrixCommand(fallbackMethod = "failFast1"
//			
//			,commandProperties = {
//								@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100"),//指定多久超时，单位毫秒。超时进fallback
//								@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//判断熔断的最少请求数，默认是10；只有在一个统计窗口内处理的请求数量达到这个阈值，才会进行熔断与否的判断
//								@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),//判断熔断的阈值，默认值50，表示在一个统计窗口内有50%的请求处理失败，会触发熔断
////								@HystrixProperty(name = "hystrix.collapser.HystrixCollapserKey.maxRequestsInBatch", value = "2")//
//							}
//					
//			,threadPoolProperties = {
//	                        @HystrixProperty(name = "coreSize", value = "30"),
//	                        @HystrixProperty(name = "maxQueueSize", value = "101"),
//	                        @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
//	                        @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
//	                        @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
//	                        @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
//	                        }
//	) 
//	@RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
//	public String echoByFeign(@PathVariable String str) {
//		System.out.println("by Hystrix:正常处理");
//		return service1.echo(str);
//	}
//
////	public String failFast1(@PathVariable String str) {
//	public String failFast1(String str) {
//		String r = "*响应来自：" + App.APP_NAME_ALIAS + "; by Hystrix:应急处理";
//		System.out.println(r);
//		return r;
//	}
//
//	
////	@Bean
////	public Customizer<HystrixCircuitBreakerFactory> defaultConfig() {
////	    return factory -> factory.configureDefault(id -> HystrixCommand.Setter
////	            .withGroupKey(HystrixCommandGroupKey.Factory.asKey(id))
////	            .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
////	            .withExecutionTimeoutInMilliseconds(4000)));
////	}
//}
