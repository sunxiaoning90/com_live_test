SpringBoot 开发笔记：使用SpringBoot搭建一个web项目

一、搭建过程
1、修改 pom.xml
	<!-- 1、设置spring boot的parent -->
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.5.6.RELEASE</version>
	</parent>
	
	<dependencies>
		<!-- 2、引入依赖：spring boot的web支持 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
	</dependencies>

	<!-- 3、添加 SpringBoot 插件 -->
	<!-- Spring Boot的Maven插件（Spring Boot Maven plugin）能够以Maven的方式为应用提供Spring Boot的支持，即为Spring Boot应用提供了执行Maven操作的可能。
		  Spring Boot Maven plugin能够将Spring Boot应用打包为可执行的jar或war文件，然后以通常的方式运行Spring Boot应用。 -->
	<plugin>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-maven-plugin</artifactId>
	</plugin>
	
2、编写controller
	@SpringBootApplication // SpringBoot 的核心注解，主要目的是开启自动配置
	@Controller
	public class HelloSpringBoot {
	
		@RequestMapping("/test")
		@ResponseBody
		public String hello() {
			return "test";
		}
	
		public static void main(String[] args) {
			SpringApplication.run(HelloSpringBoot.class, args);
		}
	}

3、运行、访问（默认端口为8080）

更多请访问：
*我的github源代码地址： 
*我的csdn地址：
