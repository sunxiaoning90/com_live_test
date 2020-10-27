Spring Cloud Netfix 项目： 使用 Hystrix 实现 服务限流、降级、熔断-服务消费方


一、简介
Spring Cloud Netfix 项目： 使用 Hystrix 实现 服务限流、降级、熔断-服务消费方

一、简介
1）配置pom依赖
		<!-- hystrix -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
		</dependency>
2、
为资源指定保护规则

二、详解
1、配置pom依赖
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.live.test.MicroService</groupId>
	<artifactId>com_live_test_MicroService_SpringCloudNetflix_hystrix_consumer</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>com_live_test_MicroService_SpringCloudNetflix_hystrix_consumer</name>
	<url>http://maven.apache.org</url>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>

		<spring-boot.version>2.0.4.RELEASE</spring-boot.version>
		<spring-cloud.version>Finchley.RELEASE</spring-cloud.version>

		<maven.compiler.source>1.8</maven.compiler.source>
		<maven.compiler.target>1.8</maven.compiler.target>
	</properties>

	<!-- 1) SpringBoot -->
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.0.4.RELEASE</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>

	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>Finchley.SR1</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

	<!-- 3) dependencies -->
	<dependencies>

		<!-- web -->
		<!-- 配置springBoot的启动web服务器，starter默认自带Tomcat,剔除自带Tomcat依赖，更换为jetty -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
			<exclusions>
				<exclusion>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-starter-tomcat</artifactId>
				</exclusion>
			</exclusions>
		</dependency>

		<!-- 1、tomcat -->
		<!-- <dependency> <groupId>org.springframework.boot</groupId> <artifactId>spring-boot-starter-tomcat</artifactId> 
			</dependency> -->

		<!-- jetty -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jetty</artifactId>
		</dependency>

		<!-- jetty -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jetty</artifactId>
		</dependency>

		<!-- netflix-eureka-client -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>

		<!-- netflix-ribbon -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
		</dependency>

		<!-- openfeign -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-openfeign</artifactId>
		</dependency>
		
		<!-- hystrix -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
		</dependency>
		
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>

			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<!-- <version>3.1</version> -->
				<configuration>
					<source>1.8</source>
					<target>1.8</target>
				</configuration>
			</plugin>

			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-surefire-plugin</artifactId>
				<configuration>
					<testFailureIgnore>true</testFailureIgnore>
				</configuration>
			</plugin>

		</plugins>
	</build>
</project>


2、配置yml文件
server:
  port: 8083

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/

2）bootstrap.yml
spring:
  application:
    name: spring:
  application:
    name: com-live-test-MicroService-SpringCloudNetflix-feign-eureka-discovery-consumer
  cloud:
    config:
      uri: ${CONFIG_SERVER_URL:http://localhost:8888}
      
2、java
@Autowired
	FeignTestServcie service1;

	@HystrixCommand(fallbackMethod = "failFast1") // 指定应急处理方法
	@RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
	public String echoByFeign(@PathVariable String str) {
		System.out.println("by Hystrix:正常处理");
		return service1.echo(str);
	}

//	public String failFast1(@PathVariable String str) {
	public String failFast1(String str) {
		String r = "by Hystrix:应急处理";
		System.out.println(r);
		return r;
	}
	
3、启动Eureka Server 、服务提供者、服务消费者

4、浏览器测试
请求1：http://{{host}}:8083/discovery/consumer/echo/test123
结果1：*响应来自：服务提供方：SpringCloudNetflix_ribbon_eureka_discovery_provider1,响应结果：test123

请求2：http://{{host}}:8083/discovery/consumer/echo/test123
结果2：*响应来自：服务提供方：SpringCloudNetflix_ribbon_eureka_discovery_provider2,响应结果：test123

关闭 服务提供者，Hystrix快速失败

请求3：http://{{host}}:8083/discovery/consumer/echo/test123
结果3：*响应来自：服务消费方：SpringCloudNetflix_hystrix_consumer; by Hystrix:应急处理
