Gateway：Spring Cloud项目使用Gateway作为 微服务网关 ,实现服务拦截、转发 (通过配置文件的方式) --整合Nacos

一、简介
com_live_test_MicroService_SpringCloudAlibaba_gateway_byConfigFile_withNacos

在 《Spring Cloud项目使用Gateway作为 微服务网关 ,实现服务拦截、转发 (通过配置文件的方式)》基础上，整合Nacos，实现服务负载均衡

1）配置 pom， 
	新增 Nacos依赖
2）配置 application.yml
	 #注意：转发的目的地址 从 Nacos 注册中心 获取
          uri: lb://service-provider
            # filters不可少
          filters:
            - StripPrefix=1

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

	<!-- Nacos 注册中心 -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
			<!-- <version>2.2.1.RELEASE</version> -->
        <version>0.2.2.RELEASE</version>
		</dependency>
		
	</dependencies>
	
2、配置application.yml
server:
  port: 8082
  
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
          #uri: http://realUrl.userApi001.com/
          #uri: https://www.baidu.com/
          
            #注意：转发的目的地址 从 Nacos 注册中心 获取
          uri: lb://service-provider
            # filters不可少
          filters:
            - StripPrefix=1
          
            #匹配规则
          predicates:
            - Path=/userApi/**
            
    #nacos做为注册中心           
    nacos:
        discovery:
          server-addr: 192.168.1.52:8848
            
            # 网关最终拦截效果 
            #浏览器访问：http://192.168.1.50:8082/userApi/discovery/provider/echo/abc1
            #跳转到了         http://service-provider/discovery/provider/echo/abc1 实际地址：  http://192.168.1.50:8082/userApi/discovery/provider/echo/abc1
 
 3、测试
 浏览器访问：http://192.168.1.50:8082/userApi/discovery/provider/echo/abc1
 跳转到了         http://service-provider/discovery/provider/echo/abc1 实际地址：  http://192.168.1.50:8082/userApi/discovery/provider/echo/abc1
 
 日志：
 2020-10-14 16:03:07.937  INFO 19735 --- [ctor-http-nio-1] r.ipc.netty.tcp.BlockingNettyContext     : Started HttpServer on /0:0:0:0:0:0:0:0:8082
2020-10-14 16:03:07.942  INFO 19735 --- [           main] o.s.b.web.embedded.netty.NettyWebServer  : Netty started on port(s): 8082
2020-10-14 16:03:08.026  INFO 19735 --- [           main] o.s.c.a.n.registry.NacosServiceRegistry  : nacos registry, api-gateway-01 192.168.1.50:8082 register finished
2020-10-14 16:03:08.050  INFO 19735 --- [           main] com.live.test.javaee.springboot.app.App  : Started App in 41.32 seconds (JVM running for 42.508)
context：org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebServerApplicationContext@79767781: startup date [Wed Oct 14 16:02:52 CST 2020]; parent: org.springframework.context.annotation.AnnotationConfigApplicationContext@3fc79729
2020-10-14 16:03:08.465  INFO 19735 --- [ctor-http-nio-2] s.c.a.AnnotationConfigApplicationContext : Refreshing SpringClientFactory-service-provider: startup date [Wed Oct 14 16:03:08 CST 2020]; parent: org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebServerApplicationContext@79767781
2020-10-14 16:03:08.644  INFO 19735 --- [ctor-http-nio-2] f.a.AutowiredAnnotationBeanPostProcessor : JSR-330 'javax.inject.Inject' annotation found and supported for autowiring
2020-10-14 16:03:09.232  INFO 19735 --- [ctor-http-nio-2] c.netflix.config.ChainedDynamicProperty  : Flipping property: service-provider.ribbon.ActiveConnectionsLimit to use NEXT property: niws.loadbalancer.availabilityFilteringRule.activeConnectionsLimit = 2147483647
2020-10-14 16:03:09.316  INFO 19735 --- [ctor-http-nio-2] c.netflix.loadbalancer.BaseLoadBalancer  : Client: service-provider instantiated a LoadBalancer: DynamicServerListLoadBalancer:{NFLoadBalancer:name=service-provider,current list of Servers=[],Load balancer stats=Zone stats: {},Server stats: []}ServerList:null
2020-10-14 16:03:09.327  INFO 19735 --- [ctor-http-nio-2] c.n.l.DynamicServerListLoadBalancer      : Using serverListUpdater PollingServerListUpdater
2020-10-14 16:03:09.395  INFO 19735 --- [ctor-http-nio-2] c.netflix.config.ChainedDynamicProperty  : Flipping property: service-provider.ribbon.ActiveConnectionsLimit to use NEXT property: niws.loadbalancer.availabilityFilteringRule.activeConnectionsLimit = 2147483647
2020-10-14 16:03:09.411  INFO 19735 --- [ctor-http-nio-2] c.n.l.DynamicServerListLoadBalancer      : DynamicServerListLoadBalancer for client service-provider initialized: DynamicServerListLoadBalancer:{NFLoadBalancer:name=service-provider,current list of Servers=[192.168.1.50:8089],Load balancer stats=Zone stats: {unknown=[Zone:unknown;	Instance count:1;	Active connections count: 0;	Circuit breaker tripped count: 0;	Active connections per server: 0.0;]
},Server stats: [[Server:192.168.1.50:8089;	Zone:UNKNOWN;	Total Requests:0;	Successive connection failure:0;	Total blackout seconds:0;	Last connection made:Thu Jan 01 08:00:00 CST 1970;	First connection made: Thu Jan 01 08:00:00 CST 1970;	Active Connections:0;	total failure count in last (1000) msecs:0;	average resp time:0.0;	90 percentile resp time:0.0;	95 percentile resp time:0.0;	min resp time:0.0;	max resp time:0.0;	stddev resp time:0.0]
]}ServerList:org.springframework.cloud.alibaba.nacos.ribbon.NacosServerList@29720a3b
2020-10-14 16:03:10.335  INFO 19735 --- [erListUpdater-0] c.netflix.config.ChainedDynamicProperty  : Flipping property: service-provider.ribbon.ActiveConnectionsLimit to use NEXT property: niws.loadbalancer.availabilityFilteringRule.activeConnectionsLimit = 2147483647
 