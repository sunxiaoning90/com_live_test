《网关：com_live_test_MicroService_SpringCloudAlibaba_gateway》

一、简介

最终拦截效果 http://localhost:8081/userApi  转发到 http://realUrl.userApi001.com[:8082]/userApi

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
 浏览器访问：http://localhost:8081/userApi/get?id=1
 跳转到了           http://realUrl.userApi001.com/userApi/get?id=1