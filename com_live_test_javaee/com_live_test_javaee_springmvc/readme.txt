springmvc

一.思路
spring管理bean
配置web相关的,dispathservlet\视图解析器等等

二.整合过程
1.保持spring(IOC)正常跑起来
2.重点配置spring的xml文件:
<!-- bean配置形式一:通过注解 -->
	<!-- 需配置 mvc:annotation-driven 标签,开启注解支持-->
	<mvc:annotation-driven></mvc:annotation-driven>
	<!-- 配置自定扫描的包 -->
	<context:component-scan base-package="com.live.test.javaee.springmvc"></context:component-scan>

<!-- bean配置形式二:通过xml配置文件-->
<!-- <import resource="applicationContext.xml"/> -->

	<!-- 配置视图解析器: 如何把 handler 方法返回值解析为实际的物理视图 -->
	<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/views/"></property>
		<property name="suffix" value=".jsp"></property>
	</bean>
	
	<!-- 配置视图  BeanNameViewResolver 解析器: 使用视图的名字来解析视图 -->
	<!-- 通过 order 属性来定义视图解析器的优先级, order 值越小优先级越高 -->
	<bean class="org.springframework.web.servlet.view.BeanNameViewResolver">
		<property name="order" value="100"></property>
	</bean>