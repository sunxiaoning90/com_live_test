Spring开发笔记：主要整理 Spring IOC\DI、Spring AOP、Spring MVC

目录：
com_live_test_javaee_spring_IOC_DI_01
	1、Bean依赖注入的三种方式：1）setter方法注入 	2）构造器注入 	3）接口注入（由于bean要依赖额外的接口，一般不用）
	2、autowire（自动装配）的4种类型：1）no，需要指定property 或 constructor-arg; 	2)byName 	3)byType	4)constructor
	3、Spring 支持注入哪些类型： 基本类型、引用类型、字符串、数组、集合（list\set\map)、props（.properties配置文件）、null、级联赋值、xml
	4、配置Spring Bean的两种方式：1）xml 配置方式 2）注解 配置方式
	5、	scope(Bean作用域)，共5种：singleton、prototype、request、session、application
	6、Spring xml 方式配置bean时，可以使用placeholder 占位符，如读取JDBC配置信息user、password、driverClass、jdbcUrl

com_live_test_javaee_spring_IOC_DI
	1、ApplicationContext 启动流程
	2、getBean("xx")流程
	3、循环依赖

com_live_test_javaee_spring_IOCAndServlet
	1、尝试 Servlet 与 Spring IOC 结合

com_live_test_javaee_spring_IOCAndServletContextListener
	2、尝试 实现web中使用SpringIOC，借助监听器加载并存放 applicationContext

com_live_test_javaee_spring_AOP
	AOP八个概念，个人理解：
	AOP代理 目标对象 ->（切面、（通知、切入点））-> 代理对象
	连接点、织入
	
	getBean（） -->
		ApplicationContext	-->
				AdvisedSupport	-->
					AopConfig -->
						Advice <-- JdkDynamicAopProxy
						
*我的github源代码地址： 
    https://github.com/sunxiaoning90/com_live_test/tree/master/com_live_test_javaee/com_live_test_javaee_mybatis

*我的csdn地址：
    https://blog.csdn.net/Sunxn1991/article/details/105811427