SpringMVC项目搭建demo

一.思路
1\修改 web.xml,配置 DispatcherServlet ()
	web.xml是web项目的重要描述,使web项目发现spring的 "servlet"
2\配置 springmvc.xml
配置 扫描包\视图解析器等

二.整合过程
1\修改 web.xml,配置 DispatcherServlet ()
	<!-- 配置 DispatcherServlet -->
	<servlet>
		<servlet-name>dispatcherServlet</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<!-- 配置 DispatcherServlet 初始化参数: 
		通过 contextConfigLocation 配置 SpringMVC 配置文件的位置和名称. 
		默认的配置文件为: /WEB-INF/<servlet-name>-servlet.xml -->
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>classpath:spring/springmvc.xml</param-value>
		</init-param>

		<load-on-startup>1</load-on-startup>
	</servlet>

	<servlet-mapping>
		<servlet-name>dispatcherServlet</servlet-name>
		<url-pattern>*.do</url-pattern>
	</servlet-mapping>
	
2.创建spring的xml文件:
1)配置bean扫描包
2)开启注解支持
3)配置视图解析器
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