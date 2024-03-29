ribbon：Spring Cloud项目 使用ribbon 实现负载均衡（@LoadBalanced）（使用Nacos 做为 注册中心（服务消费））

一、简介
nocas 默认集成了ribbon（负载均衡，使用@LoadBance即可）

ribbon原理：拦截器:为RestTemplate增加了@LoanBalanced 注解后，实际上通过配置，为RestTemplate注入负载均衡拦截器，让负载均衡器选择根据其对应的策略选择合适的服务后，再发送请求。

《通过 Nacos Server 和 spring-cloud-starter-alibaba-nacos-discovery 实现 服务发现 - 服务消费 - ribbon负载均衡》

1)	通过 Spring Cloud 原生注解 @EnableDiscoveryClient 开启服务注册发现功能。给 RestTemplate 实例添加 @LoadBalanced 注解，开启 @LoadBalanced 与 Ribbon 的集成：
@SpringBootApplication
@ComponentScan(value = { "com.live.test.javaee.springboot.*" })
public class App {

//	通过 Spring Cloud 原生注解 @EnableDiscoveryClient 开启服务注册发现功能。给 RestTemplate 实例添加 @LoadBalanced 注解，开启 @LoadBalanced 与 Ribbon 的集成：
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		ConfigController bean = context.getBean(ConfigController.class);
		System.out.println("尝试获取bean：" + bean);
	}
}

2)	通过 Nacos 的 @EnableDiscoveryClient 注解 开启服务发现
@Component
@RestController
@RequestMapping("discovery/consumer")
@EnableDiscoveryClient
public class DiscoveryConsumerController {

//	@NacosInjected
//	@Autowired
//	private NamingService namingService;
	private final RestTemplate restTemplate;

	@Autowired
	public DiscoveryConsumerController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
	public String echo(@PathVariable String str) {
		return restTemplate.getForObject("http://service-provider/discovery/provider/echo/" + str, String.class);
	}
}

二、详解
一、准备服务消费方（端口8080）
1、添加依赖。
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.live.test.MicroService</groupId>
	<artifactId>SpringCloudAlibaba-nacos-SpringCloud-discovery-consumer</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>com_live_test_MicroService_SpringCloudAlibaba_ribbon_SpringCloud_consumer</name>
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

		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<version>1.18.2</version>
			<optional>true</optional>
		</dependency>

		<dependency>
			<!-- <groupId>com.alibaba.cloud</groupId> -->
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
			<!-- <version>2.2.1.RELEASE</version> -->
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

spring.application.name=service-consumer

# nacos 作为注册中心
spring.cloud.nacos.discovery.server-addr=192.168.1.x:8848

3.1、通过 Spring Cloud 原生注解 @EnableDiscoveryClient 开启服务注册发现功能。给 RestTemplate 实例添加 @LoadBalanced 注解，开启 @LoadBalanced 与 Ribbon 的集成：
@SpringBootApplication
@ComponentScan(value = { "com.live.test.javaee.springboot.*" })
public class App {

//	通过 Spring Cloud 原生注解 @EnableDiscoveryClient 开启服务注册发现功能。给 RestTemplate 实例添加 @LoadBalanced 注解，开启 @LoadBalanced 与 Ribbon 的集成：
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		ConfigController bean = context.getBean(ConfigController.class);
		System.out.println("尝试获取bean：" + bean);
	}
}
3.2、通过 Nacos 的 @EnableDiscoveryClient 注解 开启服务发现
@Component
@RestController
@RequestMapping("discovery/consumer")
@EnableDiscoveryClient
public class DiscoveryConsumerController {

//	@NacosInjected
//	@Autowired
//	private NamingService namingService;
	private final RestTemplate restTemplate;

	@Autowired
	public DiscoveryConsumerController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
	public String echo(@PathVariable String str) {
		return restTemplate.getForObject("http://service-provider/discovery/provider/echo/" + str, String.class);
	}
}

二、启动多个服务提供方（端口为8082、8083）
测试访问 80801 controller,请求会以轮询的策略 负载到两台服务器： 8082、8083
注意，服务下线测试结果：Nacos 服务管理，健康实例数监测有一定的延时，在此期间会请求到掉线的实例上，导致响应失败



5、检查服务是否已经注册：

方式一：观察Nacos网页端，服务管理 > 服务列表 
结果：
	服务名					分组名称			集群数目	实例数	健康实例数	触发保护阈值	操作	
	service-provider	DEFAULT_GROUP	1			2		2			false			详情|示例代码|删除
	
方式二：访问 Nacos官方OpenAPI（api手册：https://nacos.io/zh-cn/docs/open-api.html） 

1)curl -X GET 192.168.1.52:8848/nacos/v1/ns/instance/list?serviceName=service-provider

结果：
{
	"hosts": [{
		"ip": "192.168.1.50",
		"port": 8081,
		"valid": true,
		"healthy": true,
		"marked": false,
		"instanceId": "192.168.1.50#8081#DEFAULT#DEFAULT_GROUP@@service-provider",
		"metadata": {
			"preserved.register.source": "SPRING_CLOUD"
		},
		"enabled": true,
		"weight": 1.0,
		"clusterName": "DEFAULT",
		"serviceName": "service-provider",
		"ephemeral": true
	}, {
		"ip": "192.168.1.50",
		"port": 8082,
		"valid": true,
		"healthy": true,
		"marked": false,
		"instanceId": "192.168.1.50#8082#DEFAULT#DEFAULT_GROUP@@service-provider",
		"metadata": {
			"preserved.register.source": "SPRING_CLOUD"
		},
		"enabled": true,
		"weight": 1.0,
		"clusterName": "DEFAULT",
		"serviceName": "service-provider",
		"ephemeral": true
	}],
	"dom": "service-provider",
	"name": "DEFAULT_GROUP@@service-provider",
	"cacheMillis": 3000,
	"lastRefTime": 1602474040288,
	"checksum": "3d2b0cfe8a813d5c79f11af9b5ce746c",
	"useSpecifiedURL": false,
	"clusters": "",
	"env": "",
	"metadata": {}
}

2)http://192.168.1.52:8848/nacos/v1/ns/service?serviceName=service-provider

结果：
{
	"name": "service-provider",
	"namespaceId": "public",
	"protectThreshold": 0.0,
	"metadata": {},
	"selector": {
		"type": "none"
	},
	"groupName": "DEFAULT_GROUP",
	"clusters": [{
		"name": "DEFAULT",
		"healthChecker": {
			"type": "TCP"
		},
		"metadata": {}
	}]
}


*注意：
使用RestTemplate方式调用远程服务时，地址是一个指定的路径（服务名），使用起来不方便，能不能更简便的调用远程服务？
可以使用feign组件解决
return restTemplate.getForObject("http://service-provider/discovery/provider/echo/" + str, String.class);