com_live_test_MicroService_SpringCloudAlibaba_gateway_byConfigFile
Gateway：Spring Cloud项目使用Gateway作为 微服务网关 ,实现服务拦截、转发 (通过配置文件的方式)

一、简介

1）引入依赖 spring-cloud-starter-gateway
2）配置application.yml
cloud:
    gateway:
      discovery: 
        locator: 
              #开启以服务id去注册中心上获取转发地址
            enabled: true
            
        #路由策略
      routes:
        - id: gateway-service1
            #转发到目的地址 http://realUrl.userApi001.com
          uri: http://realUrl.userApi001.com
          #uri: https://www.baidu.com/
            #匹配规则
          predicates:
            - Path=/userApi/**
            ## 最终拦截效果 http://localhost:8081/userApi  转发到 http://realUrl.userApi001.com
 3、测试
 浏览器访问：http://192.168.1.50:8081/userApi/get?id=1
 跳转到了           http://realUrl.userApi001.com/userApi/get?id=1

1、什么是微服务网关
微服务网关是整个微服务API请求的入口，可以实现过滤Api接口。
作用：可以实现用户的验证登录、解决跨域、日志拦截、权限控制、限流、熔断、负载均衡、黑名单与白名单机制等。

微服务中的架构模式采用前后端分离，前端调用接口地址都能够被抓包分析到。
 
在微服务中，我们所有的企业入口必须先经过Api网关，经过Api网关转发到真实的服务器中。

如果此时需要添加验证会话信息：
传统的方式我们可以使用过滤器拦截用户会话信息，这个过程所有的服务器都必须写入该验证会话登录的代码。

2、SpringCloudGateway基于Spring5构建，能够实现响应式非阻塞式的Api，支持长连接，能够更好的整合Spring体系的产品，依赖SpringBoot-WebFux。

二、详解
1、pom依赖
<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.0.0.RELEASE</version>
	</parent>
	<dependencies>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-gateway</artifactId>
			<version>2.0.0.RELEASE</version>
		</dependency>

		<!--
			注意：不需要web依赖
			***************************
			APPLICATION FAILED TO START
			***************************
			Description:
			Parameter 0 of method modifyRequestBodyGatewayFilterFactory in org.springframework.cloud.gateway.config.GatewayAutoConfiguration required a bean of type 'org.springframework.http.codec.ServerCodecConfigurer' that could not be found.
			
			Action:
			Consider defining a bean of type 'org.springframework.http.codec.ServerCodecConfigurer' in your configuration.
		  -->
		  
		<!-- <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency> -->

	</dependencies>
	
2、配置application.yml
server:
  port: 8081
  
spring:
  application:
    name: api-gateway-01
    
  cloud:
    gateway:
      discovery: 
        locator: 
              #开启以服务id去注册中心上获取转发地址
            enabled: true
            
        #路由策略
      routes:
        - id: gateway-service1
            #转发到目的地址 http://realUrl.userApi001.com
          uri: http://realUrl.userApi001.com
          #uri: https://www.baidu.com/
            #匹配规则
          predicates:
            - Path=/userApi/**
            ## 最终拦截效果 http://localhost:8081/userApi  转发到 http://realUrl.userApi001.com
 3、测试
 浏览器访问：http://192.168.1.50:8081/userApi/get?id=1
 跳转到了           http://realUrl.userApi001.com/userApi/get?id=1