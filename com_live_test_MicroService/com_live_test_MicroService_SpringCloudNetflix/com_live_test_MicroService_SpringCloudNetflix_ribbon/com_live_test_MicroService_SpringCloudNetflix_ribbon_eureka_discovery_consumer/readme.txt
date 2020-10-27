Spring Cloud Netfix 项目： 使用 Ribbon 实现 负载均衡（ 客户端）-服务消费方


一、简介
Spring Cloud Netfix 项目： 使用 Ribbon 实现 负载均衡（ 客户端）-服务消费方

1、RestTemplate 缺点：
	使用 RestTemplate 进行http调用时，服务地址是固定的，虽然可以指定域名，可以修改ngxin的配置文件来修改真实服务器地址，但是还是很繁琐不方便
	
	String serverAddress = "http://localhost:8082";
	String url = serverAddress + "/discovery/provider/echo/" + str;
	return restTemplate.getForObject(url, String.class);

2、使用注解开启 Ribbon负载均衡 @LoadBalanced 
//ribbon 负载均衡 ，无需关注服务所在的server地址、端口，指定服务名即可 ,服务名避免使用下划线，下划线导致服务发现失败
//		String serverAddress = "http://com_live_test_MicroService_SpringCloudNetflix_eureka_discovery_provider";
		String serverAddress = "http://com-live-test-MicroService-SpringCloudNetflix-eureka-discovery-provider";
		
		String url = serverAddress + "/discovery/provider/echo/" + str;
		
		String r = restTemplate.getForObject(url, String.class);

spring-cloud-starter-netflix-ribbon
	
二、详解
1）配置pom依赖
		<!-- netflix-eureka-client -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
		
2）配置yml文件

3）2、java启动类
2、java启动类
使用注解开启服务发现/注册 @EnableDiscoveryClient （是Spring cloud的注解，不是Eureka的 org.springframework.cloud.client.discovery.EnableDiscoveryClient）

二、详解
1、配置pom依赖
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.live.test.MicroService</groupId>
	<artifactId>com_live_test_MicroService_SpringCloudNetflix_eureka_discovery_consumer</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>com_live_test_MicroService_SpringCloudNetflix_eureka_discovery_consumer</name>
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
		<!-- <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
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
    name: com_live_test_MicroService_SpringCloudNetflix_eureka_discovery_consumer
  cloud:
    config:
      uri: ${CONFIG_SERVER_URL:http://localhost:8888}
  cloud:
    config:
      uri: ${CONFIG_SERVER_URL:http://localhost:8888}
      
2、java启动类
使用注解开启 Ribbon负载均衡 @LoadBalanced 
使用注解开启服务发现/注册 @EnableDiscoveryClient （是Spring cloud的注解，不是Eureka的 org.springframework.cloud.client.discovery.EnableDiscoveryClient）

@Component
@RestController
@RequestMapping("discovery/consumer")
//@EnableDiscoveryClient
@EnableEurekaClient
public class DiscoveryConsumerController {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate rt = new RestTemplate();
		System.out.println(rt);
		return rt;
	}

	@Autowired
	public RestTemplate restTemplate;

	@RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
	public String echo(@PathVariable String str) {
		System.out.println(str);

		// RestTemplate 缺点：使用 RestTemplate 进行http调用时，服务地址是固定的，虽然可以指定域名，可以修改ngxin的配置文件来修改真实服务器地址，但是还是很繁琐不方便
//		String serverAddress = "http://localhost:8082";
		
		//ribbon 负载均衡 ，无需关注服务所在的server地址、端口，指定服务名即可 ,服务名避免使用下划线，下划线导致服务发现失败
//		String serverAddress = "http://com_live_test_MicroService_SpringCloudNetflix_eureka_discovery_provider";
		String serverAddress = "http://com-live-test-MicroService-SpringCloudNetflix-eureka-discovery-provider";
		
		String url = serverAddress + "/discovery/provider/echo/" + str;
		
		String r = restTemplate.getForObject(url, String.class);
		System.out.println(r);
		return r;
	}

}
}

3、启动Eureka Server 、服务提供者、服务消费者

4、浏览器测试
请求1：http://{{host}}:8083/discovery/consumer/echo/test123
结果1：*响应来自：服务提供方：SpringCloudNetflix_ribbon_eureka_discovery_provider1,响应结果：test123

请求2：http://{{host}}:8083/discovery/consumer/echo/test123
结果2：*响应来自：服务提供方：SpringCloudNetflix_ribbon_eureka_discovery_provider2,响应结果：test123

请求3：http://{{host}}:8083/discovery/consumer/echo/test123
结果3：*响应来自：服务提供方：SpringCloudNetflix_ribbon_eureka_discovery_provider1,响应结果：test123

*使用Ribbon后，请求过程分析
浏览器发起请求：http://{{host}}:8083/discovery/consumer/echo/test123

consumer服务受理controller请求，并经Ribbon找到实际服务地址
org.eclipse.jetty.server.HttpChannel     : /discovery/consumer/echo/test123
http://com_live_test_MicroService_SpringCloudNetflix_eureka_discovery_provider/discovery/provider/echo/test123

实际服务地址：
http://{{host}}:8083/discovery/consumer/echo/test123

*遇到的错误
1、Ribon在调用微服务时报错：

String serverAddress = "http://COM_LIVE_TEST_MICROSERVICE_SPRINGCLOUDNETFLIX_EUREKA_DISCOVERY_PROVIDER";
String url = serverAddress + "/discovery/provider/echo/" + str;
String r = restTemplate.getForObject(url, String.class);
		
报错：
org.springframework.web.util.NestedServletException: Request processing failed; nested exception is java.lang.IllegalStateException: Request URI does not contain a valid hostname: http://COM_LIVE_TEST_MICROSERVICE_SPRINGCLOUDNETFLIX_EUREKA_DISCOVERY_PROVIDER/discovery/provider/echo/test123

解决：

问题原因：

在注册服务的时候，properties文件中的服务名（spring.application.name）带上了下划线（如：COM_LIVE_TEST_MICROSERVICE_SPRINGCLOUDNETFLIX_EUREKA_DISCOVERY_PROVIDER）
解决办法：

将下划线 改为 中划线即可：(COM-LIVE-TEST-MICROSERVICE-SPRINGCLOUDNETFLIX-EUREKA-DISCOVERY-PROVIDER)
