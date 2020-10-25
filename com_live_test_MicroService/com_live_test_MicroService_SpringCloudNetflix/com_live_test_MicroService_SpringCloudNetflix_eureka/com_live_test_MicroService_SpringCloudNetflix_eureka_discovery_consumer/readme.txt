Spring Cloud Netfix 项目： 使用 Eureka 作为注册中心-服务注册


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
使用注解开启服务发现/注册 @EnableDiscoveryClient （是Spring cloud的注解，不是Eureka的 org.springframework.cloud.client.discovery.EnableDiscoveryClient）
@Component
@RestController
@RequestMapping("discovery/discoveryClient")
@EnableDiscoveryClient
//@EnableEurekaClient
public class DiscoveryConsumerGetDiscoveryClientController {

	@Autowired
	private EurekaClient discoveryClient;

	@RequestMapping(value = "/getDiscoveryClient", method = RequestMethod.GET)
	public String getDiscoveryClient() {
		//InstanceInfo instance = discoveryClient.getNextServerFromEureka("STORES", false);
//		return instance.getHomePageUrl();
		
//		discoveryClient.getAllKnownRegions(); //[us-east-1]
		
//		List instancesById = discoveryClient.getInstancesById("com_live_test_MicroService_SpringCloudNetflix_eureka_discovery_provider");
//		for (Object obj : instancesById) {
//			System.out.println(obj);
//		}
		
		Applications applications = discoveryClient.getApplications();
		List<Application> registeredApplications = applications.getRegisteredApplications();
		
		return Arrays.toString(registeredApplications.toArray());
	}
}

3、启动Eureka Server 、服务提供者、服务消费者

4、浏览器测试
http://{{host}}:8083/discovery/discoveryClient/getDiscoveryClient

