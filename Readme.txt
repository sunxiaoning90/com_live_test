一.简介
	好记性不如烂笔头.这是开发中的一些笔记.
	
二.目录整理
com_live_test	//总目录
	|--com_live_test_api	//企业开发中常用的api
		|--com_live_test_api_core	//常用的api,核心api
			|--josn
			|--	poi
			|--	random
			|--	xml(待)
	|-- com_live_test_db	//数据库技术
		|-- com_live_test_db_mongodb // MongoDB
		
	|--com_live_test_designpattern	//设计模式
		|--action	//行为型
			|--adapter	//适配器模式
			|--observer	//观察者模式
		|--create	//创建型
			|--builder	//建造者模式
			|--singleton	//单例模式
		|--structure	//结构型
			|--decorator	//装饰器模式
			|--proxy	//代理模式
	
	|-- com_live_test_distributed	//分布式技术点
		|-- com_live_test_distributed_dubbo	//Dubbo
			|-- com_live_test_distributed_dubbo_provider	//服务提供
			|-- com_live_test_distributed_dubbo_consumer	//服务消费
			
		|-- com_live_test_distributed_mq	//mq
			|-- com_live_test_distributed_mq_recoketmq	//recoketmq
		
		|-- com_live_test_distributed_zookeeper	//zookeeper
	
	|--com_live_test_javase	//JavaSE
		|--com_live_test_javase_core	//JavaSE中的 核心技术点
		
	|--com_live_test_javaee	//JavaEE
		|--com_live_test_javaee_integration_springmvcAndmybatis	//整合ssm
		|--com_live_test_javaee_mybatis	//MyBatis
		|--com_live_test_javaee_spring	//spring
		|--com_live_test_javaee_springmvc	//springMVC
		|-- com_live_test_javaee_springboot	//SpringBoot
		|-- com_live_test_javaee_springboot_webflux	//webflux
	
	|--com_live_test_javaweb	//JavaWeb
		|--com_live_test_javaweb_core	//JavaWeb中的 核心技术点
		|--com_live_test_javaweb_sso	//单点登录
		|--com_live_test_javaweb_webservice	//webservice(服务端+客户端）
		
	|--com_live_test_mq	//消息中间件 消息队列
		|--com_live_test_mq_rocketmq	//rocketmq

	|-- com_live_test_MicroService	//微服务
	
	未完待续...