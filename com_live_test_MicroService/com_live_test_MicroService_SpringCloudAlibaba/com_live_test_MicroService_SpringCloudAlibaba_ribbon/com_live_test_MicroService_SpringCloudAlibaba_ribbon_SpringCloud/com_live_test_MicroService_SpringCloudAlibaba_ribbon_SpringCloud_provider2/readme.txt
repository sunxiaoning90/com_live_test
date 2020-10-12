ribbon：Spring Cloud项目 使用ribbon 实现负载均衡（使用Nacos 做为 注册中心（服务提供2-服务：service-provider，端口：8082））
一、简介
《通过 Nacos Server 和 spring-cloud-starter-alibaba-nacos-discovery 实现 ribbon负载均衡- 服务提供2》

二、详解
1、添加依赖。
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.live.test.MicroService</groupId>
	<artifactId>SpringCloudAlibaba-nacos-SpringCloud-config</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>com_live_test_MicroService_SpringCloudAlibaba_nacos_SpringCloud_config</name>
	<url>http://maven.apache.org</url>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>

		<spring-boot.version>2.0.4.RELEASE</spring-boot.version>
		<spring-cloud.version>Finchley.RELEASE</spring-cloud.version>

		<maven.compiler.source>1.8</maven.compiler.source>
		<maven.compiler.target>1.8</maven.compiler.target>
	</properties>

	<!-- 1)parent：定义spring boot的版本 2)dependencyManagement：spring cloud的版本以及spring 
		cloud alibaba的版本，由于spring cloud alibaba还未纳入spring cloud的主版本管理中，所以需要自己加入 3)dependencies：当前应用要使用的依赖内容。这里主要新加入了Nacos的配置客户端模块：spring-cloud-starter-alibaba-nacos-config。由于在dependencyManagement中已经引入了版本，所以这里就不用指定具体版本了。 -->
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
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-alibaba-dependencies</artifactId>
				<version>0.2.2.RELEASE</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

	<!-- 3) dependencies -->
	<dependencies>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
			<scope>provided</scope>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
			<version>0.2.1.RELEASE</version>
		</dependency>

		<!-- <dependency> <groupId>com.alibaba.nacos</groupId> <artifactId>nacos-client</artifactId> 
			<version>${version}</version> </dependency> -->

		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<version>1.18.2</version>
			<optional>true</optional>
		</dependency>

	</dependencies>

	<build>
		<plugins>
			<!-- 3、添加 SpringBoot 插件 -->
			<!-- Spring Boot的Maven插件（Spring Boot Maven plugin）能够以Maven的方式为应用提供Spring 
				Boot的支持，即为Spring Boot应用提供了执行Maven操作的可能。 Spring Boot Maven plugin能够将Spring 
				Boot应用打包为可执行的jar或war文件，然后以通常的方式运行Spring Boot应用。 -->
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>

			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.1</version>
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

2、bootstrap.properties 配置 Nacos
server.port=8081

spring.application.name=service-provider

# nacos 作为注册中心
spring.cloud.nacos.discovery.server-addr=192.168.1.x:8848

3、通过 Nacos 的 @EnableDiscoveryClient 注解 开启服务发现
@Component
//@Controller
@RestController
@RequestMapping("discovery/provider")
@EnableDiscoveryClient
public class DiscoveryProviderController {

	@RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
	public String echo(@PathVariable String str) {
		System.out.println(str);
		return "hello" + str;
	}

}


5、测试访问 controller
