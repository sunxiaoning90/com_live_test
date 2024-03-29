Dubbo开发笔记：consumer,服务消费者

*我的github源代码地址： 
	https://github.com/sunxiaoning90/com_live_test/tree/master/com_live_test_distributed/com_live_test_distributed_dubbo
	
*我的csdn地址：
	https://blog.csdn.net/Sunxn1991/article/details/107458812

 一、demo
 1、配置Spring.xml
 1)在dtd beans标签添加dubbo支持： 
 
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:dubbo="http://dubbo.apache.org/schema/dubbo"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
							http://www.springframework.org/schema/beans/spring-beans-4.3.xsd
							http://dubbo.apache.org/schema/dubbo
							http://dubbo.apache.org/schema/dubbo/dubbo.xsd">
							
2）<!-- 配置 duboo -->
	<!-- 消费方应用名，用于计算依赖关系，不是匹配条件，不要与提供方一样 -->
	<dubbo:application name="consumer-of-helloworld-app" />

	<!-- 使用multicast广播注册中心暴露发现服务地址 -->
	<dubbo:registry address="multicast://224.5.6.7:1234" />

	<!-- 生成远程服务代理，可以和本地bean一样使用demoService -->
	<dubbo:reference id="demoService"
		interface="dubbo.provider.service.DemoService" />
		
2、修改 pom.xml ，服务消费者引入暴露的接口依赖
<dependencies>
		<!-- 添加依赖： 服务提供者(暴露的接口放在了服务提供者工程，可以抽离) -->
		<dependency>
			<groupId>com.live.test.distributed.dubbo</groupId>
			<artifactId>provider</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
</dependencies>
	
3、编写启动类（启动服务消费者）
public class Consumer {
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(

				new String[] { "spring/applicationContext.xml" });
		System.out.println("ac:" + context);
		context.start();

		DemoService demoService = (DemoService) context.getBean("demoService"); // 获取远程服务代理
		System.out.println("demoService:" + demoService); //demoService:org.apache.dubbo.common.bytecode.proxy0@26a529dc
		String hello = demoService.sayHello("world"); // 执行远程方法
		System.out.println(hello); // 显示调用结果

		context.close();
	}
}


=== 
《Dubbo 使用zk作为注册中心》
1）、spring.xml
<!-- zk单机版 -->
	<dubbo:registry address="zookeeper://192.168.1.52:2181" check="false"/>

2)Maven 依赖
<!-- zk -->
		<!-- https://mvnrepository.com/artifact/org.apache.zookeeper/zookeeper -->
		<dependency>
			<groupId>org.apache.zookeeper</groupId>
			<artifactId>zookeeper</artifactId>
			<version>3.6.1</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/org.apache.curator/curator-framework -->
		<dependency>
			<groupId>org.apache.curator</groupId>
			<artifactId>curator-framework</artifactId>
			<version>5.1.0</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/org.apache.curator/curator-recipes -->
		<dependency>
			<groupId>org.apache.curator</groupId>
			<artifactId>curator-recipes</artifactId>
			<version>5.1.0</version>
		</dependency>

		<!-- slf4j -->
		<!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-api -->
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>2.0.0-alpha1</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/com.google.guava/guava -->
		<dependency>
			<groupId>com.google.guava</groupId>
			<artifactId>guava</artifactId>
			<version>29.0-jre</version>
		</dependency>