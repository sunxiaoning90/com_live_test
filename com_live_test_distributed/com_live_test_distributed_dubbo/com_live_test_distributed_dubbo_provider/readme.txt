Dubbo开发笔记：provider,服务提供者

*我的github源代码地址： 

*我的csdn地址：
    
    
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
	<!-- 提供方应用信息，用于计算依赖关系 -->
	<dubbo:application name="hello-world-app" />

	<!-- 使用multicast广播注册中心暴露服务地址 -->
	<dubbo:registry address="multicast://224.5.6.7:1234" />

	<!-- 用dubbo协议在20880端口暴露服务 -->
	<dubbo:protocol name="dubbo" port="20880" />

	<!-- 声明需要暴露的服务接口 -->
	<dubbo:service
		interface="dubbo.provider.service.DemoService" ref="demoService" />

	<!-- 和本地bean一样实现服务 -->
	<bean id="demoService"
		class="dubbo.provider.service.DemoServiceImpl" />
		
2、编写 需要暴露的服务接口
dubbo.provider.service.DemoService.java

public interface DemoService {
	String sayHello(String name);
}

3、编写 实现类
dubbo.provider.service.DemoServiceImpl.java

public class DemoServiceImpl implements DemoService {

	@Override
	public String sayHello(String name) {
		return "Hello" + name;
	}

}

4、编写启动类（启动服务）
public class Provider {
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring/applicationContext.xml" });

		System.out.println("ac:" + context);
		context.start();
		
		System.in.read(); // 按任意键退出
		context.stop();
		System.out.println("dubbo stop");
	}
}


