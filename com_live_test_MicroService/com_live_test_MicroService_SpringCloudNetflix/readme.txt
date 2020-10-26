微服务 开发笔记:Spring Cloud Nexflix

一、简介

Spring Cloud Nexflix 提供的组件包括：

服务注册和服务发现：Eureka
服务调用客户端：RestTemplate
客户端负载均衡：Ribbon
声明式REST客户端：Feign
断路器 限流/降级/熔断：Hystrix
微服务网关/智能路由：Zuul

com_live_test_MicroService_SpringCloudNexflix_ticketShopping_order：一个基于 Spring Cloud Nexflix 实现的微服务小项目(网络购票项目，包括订单模块、库存模块、积分模块、提醒模块、网关，使用的技术：Eureka、RestTemplate、Ribbon、Feign、Zull、Hystrix。。。）
二、项目清单

【1】Eureka：注册中心

一、项目清单
1)eureka_server：Eureka 服务，一个Jar，依赖Tomcat 等容器发布
2)eureka_discovery_provider：服务提供方
3)eureka_discovery_consumer：服务消费方


1、com_live_test_MicroService_SpringCloudNetflix_eureka_server
Spring Cloud Netfix 项目： Eureka Server端

	8761端口启动，并从“/ Eureka”提供Eureka API。
	
2、com_live_test_MicroService_SpringCloudNetflix_eureka_discovery_provider
Spring Cloud Netfix 项目： 使用 Eureka 作为注册中心-服务注册-服务提供方
		
		一、使用流程简介
			1）配置pom依赖
				<!-- netflix-eureka-server -->
				<dependency>
					<groupId>org.springframework.cloud</groupId>
					<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
				</dependency>
		
				<!-- starter-config -->
				<dependency>
					<groupId>org.springframework.cloud</groupId>
					<artifactId>spring-cloud-starter-config</artifactId>
				</dependency>
				
				2）配置yml文件
				
				3）2、java启动类
				使用注解标识EurekaServer @EnableEurekaServer
				4）增减服务，注册中心可以感知，默认情况下，如果Eureka Server在一定时间内（默认90秒）没有接收到某个微服务实例的心跳，Eureka Server将会移除该实例。
	
3、com_live_test_MicroService_SpringCloudNetflix_eureka_discovery_consumer
Spring Cloud Netfix 项目： 使用 Eureka 作为注册中心-服务注册-服务消费方
		
		一、简介
			1）配置pom依赖
			<!-- netflix-eureka-client -->
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
			</dependency>
			
			2）配置yml文件
			
			3）2、java启动类
			2、java启动类
			使用注解开启服务发现/注册@EnableEurekaClient 
			如果注册中心只有Eureka时，也可以使用 @EnableDiscoveryClient
			（是Spring cloud的注解，不是Eureka的 org.springframework.cloud.client.discovery.EnableDiscoveryClient）

【2】Ribbon：负载均衡
	1.准备远程服务，供Ribbon负载均衡调用测试
	一、简介
使用 Ribbon 实现 负载均衡（ 客户端）
前提：使用Eureka 作为 注册中心(Eureka Server、服务提供方、服务消费方）

二、项目清单：
1、com_live_test_MicroService_SpringCloudNetflix_ribbon_eureka_server
Spring Cloud Netfix 项目： Eureka Server端
8761端口启动，并从“/ Eureka”提供Eureka API。

2.1、com_live_test_MicroService_SpringCloudNetflix_ribbon_eureka_discovery_provider1
Spring Cloud Netfix 项目： 使用 Eureka 作为注册中心-服务注册-服务提供方1

2.2、com_live_test_MicroService_SpringCloudNetflix_ribbon_eureka_discovery_provider2
Spring Cloud Netfix 项目： 使用 Eureka 作为注册中心-服务注册-服务提供方2

3、com_live_test_MicroService_SpringCloudNetflix_eureka_discovery_consumer
Spring Cloud Netfix 项目： 使用 Eureka 作为注册中心-服务注册-服务消费方

	
【3】OpenFeign/Feign：服务接口调用


【*】(网络购票项目）
com_live_test_MicroService_SpringCloudNexflix_ticketShopping_order：一个基于 Spring Cloud Nexflix 实现的微服务小项目(网络购票项目，包括订单模块、库存模块、积分模块、提醒模块、网关，使用的技术：Eureka、RestTemplate、Ribbon、Feign、Zull、Hystrix。。。）


            