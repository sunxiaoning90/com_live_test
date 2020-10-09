微服务 开发笔记：
SpringCloudAlibaba

nacos
	1、com_live_test_MicroService_SpringCloudAlibaba_nacos_SpringBoot_config	//Nacos：SpringBoot项目 使用Nacos 做为 配置管理中心
		一、使用流程简介
		1）application.yml 配置 Nacos的地址（nacos:   config:  server-addr）
		2.1）Java程序 指定Nacos的 dataId（@NacosPropertySource(dataId = "example", autoRefreshed = true)）
		2.2）Java程序 获取某个配置值@NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
	
	
	2、com_live_test_MicroService_SpringCloudAlibaba_nacos_SpringBoot_discovery	//Nacos:	SpringBoot项目 使用Nacos 做为 服务发现中心
		一、使用流程简介
		1）application.yml 配置 Nacos的地址（nacos:   config:  server-addr）
		2）Java程序 使用@NacosInjected注解注入 Nacos的 注册中心对象 NamingService
			@NacosInjected
			private NamingService namingService;
		3）增减服务，注册中心可以感知，健康检查默认5秒一次，默认3次均失败则剔除该服务
		
3、com_live_test_MicroService_SpringCloudAlibaba_nacos_SpringCloud_config
Nacos：Spring Cloud项目 使用Nacos 做为 配置管理中心

一、使用流程简介
		通过 Nacos Server 和 spring-cloud-starter-alibaba-nacos-config 实现配置的动态变更。
		
		1）bootstrap.properties 
			1.1）配置application名：spring.application.name=example#之所以需要配置 spring.application.name ，是因为它是构成 Nacos 配置管理 dataId字段的一部分。
			
			1.2）配置 Nacos的地址（nacos:   config:  server-addr）
			spring.cloud.nacos.config.server-addr=192.168.1.52:8848
			spring.cloud.nacos.config.group=DEFAULT_GROUP
			#指定配置的后缀，支持 properties、yaml、yml，默认为 properties
			spring.cloud.nacos.config.file-extension=properties

		2）Java程序  使用@Value 或 @NacosValue 注解为字段赋值，并开启自动更新：
			//	@NacosValue(value = "${useLocalCache:true}", autoRefreshed = true) //经测试boolean 并没有自动刷新？
			@Value("${useLocalCache:false}") //经测试boolean 自动刷新

		注意：Java程序 不需要指定Nacos的 dataId，在配置文件中已经指定	
feign
ribbon
sentinel
gateway

