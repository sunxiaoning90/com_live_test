《通过 Nacos Server 和 spring-cloud-starter-alibaba-nacos-config 实现配置的动态变更。》

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
server.port=8082

#在 Nacos Spring Cloud 中，dataId 的完整格式如下：
#${prefix}-${spring.profile.active}.${file-extension}
#prefix 默认为 spring.application.name 的值，也可以通过配置项 spring.cloud.nacos.config.prefix来配置。
#spring.profile.active 即为当前环境对应的 profile，详情可以参考 Spring Boot文档。 注意：当 spring.profile.active 为空时，对应的连接符 - 也将不存在，dataId 的拼接格式变成 ${prefix}.${file-extension}
#file-exetension 为配置内容的数据格式，可以通过配置项 spring.cloud.nacos.config.file-extension 来配置。目前只支持 properties 和 yaml 类型。

#之所以需要配置 spring.application.name ，是因为它是构成 Nacos 配置管理 dataId字段的一部分。
spring.application.name=example

# Nacos 控制台添加配置：
# Data ID：example.properties
# Group：DEFAULT_GROUP
# 配置内容：useLocalCache=true
spring.cloud.nacos.config.server-addr=192.168.1.52:8848
spring.cloud.nacos.config.group=DEFAULT_GROUP
#指定配置的后缀，支持 properties、yaml、yml，默认为 properties
spring.cloud.nacos.config.file-extension=properties
#spring.cloud.nacos.config.file-extension=yaml

# endpoint http://localhost:8080/actuator/nacos-config
# health http://localhost:8080/actuator/health
management.endpoints.web.exposure.include=*
management.endpoint.health.show-details=always
#spring.profiles.active=dev

3、使用@Value 注解为字段赋值，并开启自动更新：
v

4、通过 Nacos 的 @NacosValue 注解设置属性值。
@ComponentScan
@Controller
@RefreshScope // Nacos 配置页改动数据时，自动刷新
@RequestMapping("config")
@ResponseBody
public class ConfigController {

//	@NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
	@Value("${useLocalCache:false}")
	private boolean useLocalCache;

	@Value(value = "${test}")
	private String a;

	/**
	 * http://localhost:8080/config/get
	 */
	@RequestMapping("/get")
	public boolean get() {
		System.out.println(a);
		System.out.println("配置：" + useLocalCache);
		return useLocalCache;
	}
}


5、启动 NacosConfigApplication，调用 curl http://localhost:8080/config/get，返回内容是 false。

6、通过调用 Nacos Open API 向 Nacos server 发布配置：dataId 为example，内容为useLocalCache=true
curl -X POST "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=example&group=DEFAULT_GROUP&content=useLocalCache=true"
7、再次访问 http://localhost:8080/config/get，此时返回内容为true，说明程序中的useLocalCache值已经被动态更新了。