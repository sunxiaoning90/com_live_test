Spring Cloud Netfix 项目： 使用 Hystrix Dashboard


一、简介

官方资料：

https://docs.spring.io/spring-cloud-netflix/docs/2.2.5.RELEASE/reference/html/#circuit-breaker-hystrix-dashboard

To run the Hystrix Dashboard, annotate your Spring Boot main class with @EnableHystrixDashboard. Then visit /hystrix and point the dashboard to an individual instance’s /hystrix.stream endpoint in a Hystrix client application.
要运行Hystrix仪表板，请使用@EnableHystrixDashboard注释您的Spring引导主类。然后访问/hystrix并将仪表板指向单个实例的/hystrix。Hystrix客户端应用程序中的流端点。

1、配置pom依赖
		<!-- hystrix-dashboard -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
		</dependency>
		
2、开启 Hystrix Dashboard
@EnableHystrixDashboard //Hystrix 控制台仪表盘（作用在Java类上）

3、浏览器访问 Hystrix Dashboard
http://192.168.1.50:8083/hystrix