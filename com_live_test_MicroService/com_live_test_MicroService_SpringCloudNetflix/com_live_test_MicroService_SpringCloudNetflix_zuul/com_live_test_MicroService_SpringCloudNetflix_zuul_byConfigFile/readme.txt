Spring Cloud Netfix 项目： 使用 Zull 实现 微服务网关(通过配置文件yml的方式）


一、简介

官方资料：
https://docs.spring.io/spring-cloud-netflix/docs/2.2.5.RELEASE/reference/html/#router-and-filter-zuul

路由是微服务体系结构中不可分割的一部分。例如，/可以映射到您的web应用程序，/api/users映射到用户服务，/api/shop映射到商店服务。
Zuul是Netflix的一款基于jvm的路由器和服务器端 负载均衡器。

Netflix uses Zuul for the following:

Authentication

Insights

Stress Testing

Canary Testing

Dynamic Routing

Service Migration

Load Shedding

Security

Static Response handling

Active/Active traffic management

Zuul’s rule engine lets rules and filters be written in essentially any JVM language, with built-in support for Java and Groovy.

1、配置pom依赖
		
		<!-- zuul -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-zuul</artifactId>
		</dependency>
		
2）配置application.yml
server:
  port: 8088

#不进行配置，访问route filter会报没有权限访问。
management:
  security:
    enabled: false  # 默认值是 true, 为true的话那么页面上可能会报没有权限访问
    
zuul: 
     
   #路由策略
  routes:
       
    #路由策略1
    gateway-service01: 
    
        #被拦截的路由
      path: /userApi/**
        
        #转发到目的地址
        #1)url 用于指定 http地址
      url: https://www.baidu.com/
      
      #serviceId 用于指定服务
      #serviceId: http://realUrl.userApi001.com/
          
        #简洁写法
      #userApi: https://www.baidu.com/**
        
       #匹配规则
      #predicates:
        #- Path=/userApi/**
        
## 最终拦截效果 http://localhost:8081/userApi  转发到 http://realUrl.userApi001.com
          
          #排除某些路由
      #ignored-patterns: 
        #- /**/zuulTest/
          
      ignoredServices: '*'

 #路由策略2
    gateway-service02: 
    
        #被拦截的路由
      path: /baiduOrGoogle/**
        
        #转发到目的地址
      
      #serviceId 用于指定服务
      serviceId: serviceId-baiduOrGoogle-service
          
        #简洁写法
      #userApi: serviceId-baiduOrGoogle-service/**    

## 效果：
serviceId-baiduOrGoogle-service: 
  ribbon:
    NIWSServerListClassName: com.netflix.loadbalancer.ConfigurationBasedServerList
    listOfServers: https://www.google.cn/,https://www.baidu.com/
    ConnectTimeout: 1000
    ReadTimeout: 3000
    MaxTotalHttpConnections: 500
    MaxConnectionsPerHost: 100
    

The preceding example means that HTTP calls to /myusers get forwarded to the users service (for example /myusers/101 is forwarded to /101).

3、浏览器访问 
http://{{host}}:8088/userApi/  ==> http://www.baidu.com/

http://{{host}}:8088/baiduOrGoogle/ => http://www.baidu.com/ 或 https://www.google.cn/