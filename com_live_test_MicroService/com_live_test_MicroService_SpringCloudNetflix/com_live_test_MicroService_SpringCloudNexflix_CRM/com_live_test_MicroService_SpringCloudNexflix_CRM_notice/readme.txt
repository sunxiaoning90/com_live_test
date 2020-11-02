Spring Cloud Netfix 项目： 使用 Ribbon 实现 负载均衡（ 客户端）-服务消费方


一、简介
Spring Cloud Netfix 项目： 使用 Ribbon 实现 负载均衡（ 客户端）-服务消费方

一、简介
1）配置pom依赖
		<!-- openfeign -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-openfeign</artifactId>
		</dependency>
		
2）配置yml文件

SpringCloud 通过 OpenFeign/Feign 服务调用方式
OpenFeign：类似于Dubbo，像调用本地方法一样调用远程服务
feign原理：代理.
ribbon原理：拦截器:为RestTemplate增加了@LoanBalanced 注解后，实际上通过配置，为RestTemplate注入负载均衡拦截器，让负载均衡器选择根据其对应的策略选择合适的服务后，再发送请求。

在微服务架构开发时，常常会在一个项目中调用其他服务，其实使用Spring Cloud Ribbon就能实现这个需求，利用RestTemplate 的请求拦截来实现对依赖服务的接口调用， 但是实际项目中对服务依赖的调用可能不止于 一 处，往往 一 个接口会被多处调用，所以我们通常都会针对各个微服务自行封装 一 些客户端类来包装这些依赖服务的调用。 这个时候我们会发现，由于 RestTemplate 的封装，几乎每 一 个调用都是简单的模板化内容。

Spring Cloud Feign 在此基础上做了进 一 步封装，由它来帮助我们定义和实现依赖服务接口的定义。在 Spring Cloud Feign 的实现下， 我们只需创建 一 个接口并用注解（@FeignClient）的方式来配置它， 即可完成对服务提供方的接口绑定，简化了在使用 Spring Cloud Ribbon 时自行封装服务调用客户端的开发量。 

1）使用@FeignClient， 定义一个 服务接口 ：FeignTestServcie

/**
 * 定义接口
 * 调用远程服务
 * 
 * @author live
 */
@FeignClient(name = "service-provider")
public interface FeignTestServcie {

	@GetMapping("discovery/provider/echo/{str}")
//	String echo(@PathVariable String str) ; //此写法错误，必须使用value指定 @PathVariable(value="str"）String str
	String echo(@PathVariable(value = "str") String str);
}
2）注入后，直接调用 该Service 的方法
/**
	 * 通过 Feign 服务调用方式
	 */
	@Autowired
	FeignTestServcie service1;

	@RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
	public String echoByFeign(@PathVariable String str) {
		return service1.echo(str);
	}
	
二、详解
1、配置pom依赖
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.live.test.MicroService</groupId>
	<artifactId>com_live_test_MicroService_SpringCloudNetflix_feign_eureka_discovery_consumer</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>com_live_test_MicroService_SpringCloudNetflix_feign_eureka_discovery_consumer</name>
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

		<!-- openfeign -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-openfeign</artifactId>
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


1、配置yml文件
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
1)定义接口 调用远程服务 FeignTestServcie.java

//@Component
@FeignClient(name = "com-live-test-MicroService-SpringCloudNetflix-eureka-discovery-provider")
public interface FeignTestServcie {

	@GetMapping("discovery/provider/echo/{str}")
//	String echo(@PathVariable String str) ; //此写法错误，必须使用value指定 @PathVariable(value="str"）String str
	String echo(@PathVariable(value = "str") String str);
//	{
//		return restTemplate.getForObject("http://service-provider/discovery/provider/echo/" + str, String.class);
//	}
}

2)Controller 使用feign
@Component
@RestController
@RequestMapping("discovery/consumer")
//@EnableDiscoveryClient
@EnableFeignClients(basePackages="com.live.test.javaee.springboot.feign.service")
public class DiscoveryConsumerController {
/**
	 * 通过 Feign 服务调用方式
	 */
	@Autowired
	FeignTestServcie service1;

	@RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
	public String echoByFeign(@PathVariable String str) {
		return service1.echo(str);
	}
3、启动Eureka Server 、服务提供者、服务消费者

4、浏览器测试
请求1：http://{{host}}:8083/discovery/consumer/echo/test123
结果1：*响应来自：服务提供方：SpringCloudNetflix_ribbon_eureka_discovery_provider1,响应结果：test123

请求2：http://{{host}}:8083/discovery/consumer/echo/test123
结果2：*响应来自：服务提供方：SpringCloudNetflix_ribbon_eureka_discovery_provider2,响应结果：test123

请求3：http://{{host}}:8083/discovery/consumer/echo/test123
结果3：*响应来自：服务提供方：SpringCloudNetflix_ribbon_eureka_discovery_provider1,响应结果：test123