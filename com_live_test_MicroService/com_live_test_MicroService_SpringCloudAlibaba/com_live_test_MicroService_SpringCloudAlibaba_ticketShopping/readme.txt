com_live_test_MicroService_SpringCloudAlibaba_TicketShopping
各个组件整合、流转


一、用户购票 流程：
创建订单 -> 减库存 -> 加积分 -> 通知（短信通知/邮件通知...）

二、项目清单

*用户请求、微服务处理，整个流程示意图：
1、user http请求购票下单网关(Spring Cloud Alibaba Gateway)	->	
									->	2.1、ticketShopping_order （controller + 本地Service）(Spring Cloud Alibaba Nacos注册中心)
									->	2.2、ticketShopping_order2 （controller，Fegin、Ribbion负载均衡调用其它 controller）(Spring Cloud Alibaba Nacos注册中心)
										
										->	3.1（2.2.1）、ticketShopping_order2_provider02 （controller + 本地Service）(Spring Cloud Alibaba Nacos注册中心)
										->	3.2（2.2.2）、ticketShopping_order2_provider03 （controller，远端Service，rpc）(Spring Cloud Alibaba Nacos注册中心、Dubbo 服务消费者)
										
											->	4.1（2.2.2.1）、order3_dubbo_provider01 （Dubbo生产者1,RPC服务提供方1）
											->	4.2（2.2.2.2）、order3_dubbo_provider02 （Dubbo生产者1,RPC服务提供方2）

1、网关（gateway）
项目名：com_live_test_MicroService_SpringCloudAlibaba_TicketShopping_Gateway
作用：购票系统的网关

2、order：订单服务(controller + service)
项目名：
com_live_test_MicroService_SpringCloudAlibaba_ticketShopping_order（设计：web请求调用本地Service）

com_live_test_MicroService_SpringCloudAlibaba_ticketShopping_order2（设计：web请求，负载均衡调用其它服务提供者）
com_live_test_MicroService_SpringCloudAlibaba_ticketShopping_order2_provider02（设计：http请求调用本地Service）
com_live_test_MicroService_SpringCloudAlibaba_ticketShopping_order2_provider01（设计：http请求调用远端Service）

作用：负责服务用户下单请求，业务的核心，做了负载均衡

以上基于http的rest接口（对外http），也可以基于dubbo的rpc接口（对内rpc）:
com_live_test_MicroService_SpringCloudAlibaba_ticketShopping_order3_dubbo_consumer（设计：既有http请求支持，又有rpc服务支持（Dubbo），负载均衡调用其它服务提供者）
com_live_test_MicroService_SpringCloudAlibaba_ticketShopping_order3_dubbo_orderServiceAPI（设计：Dubbo生产者服务接口规范）
com_live_test_MicroService_SpringCloudAlibaba_ticketShopping_order3_dubbo_provider01（设计：Dubbo生产者1）
com_live_test_MicroService_SpringCloudAlibaba_ticketShopping_order3_dubbo_provider02（设计：Dubbo生产者2）

3、stock：库存服务 (controller + service)
项目名：com_live_test_MicroService_SpringCloudAlibaba_ticketShopping_stock

作用：增减库存服务

4、credits：积分服务(controller + service)
项目名：com_live_test_MicroService_SpringCloudAlibaba_ticketShopping_credits

作用：增减库存服务

6、notice:通知服务(controller + service)
项目名：com_live_test_MicroService_SpringCloudAlibaba_ticketShopping_notice

作用：负责通知用户购票信息

三、用户购票测试
1、启动各个服务
1）order：订单服务

订单服务-ticketShopping_order1启动成功...

订单服务-ticketShopping_order2_provider01启动成功...

订单服务-ticketShopping_order2_provider02启动成功...

订单服务-order2启动成功...

订单服务-ticketShopping_order3_dubbo_consumer启动成功...
订单服务-ticketShopping_order3_dubbo_provider01启动成功...
订单服务-ticketShopping_order3_dubbo_provider02启动成功...

2）stock：库存服务 (controller + service)

3）credits：积分服务(controller + service)

4）notice:通知服务(controller + service)

5）网关（gateway）
gateway启动成功：8080

2、用户发起请求
http://{{host}}:8080/apiGteway/ticketShopping/order/createOrder
{
	"userId": 1,
	"ticketId": 1,
	"pcs": 2
}

四、测试正常下单流程、负载均衡、服务降级、服务限流
1、测试正常下单流程：
响应来自:ticketShopping_order2_provider02,购票成功：userId:1,ticketId:1,pcs:2
响应来自:库存服务-ticketShopping_stock,扣库存成功：userId:1,ticketId:1,pcs:2
响应来自:积分服务-ticketShopping_credits,增加积分成功：userId:1,ticketId:1,pcs:2
响应来自:通知服务-ticketShopping_notice,购票成功：userId:1,ticketId:1,pcs:2

2、测试负载均衡
两个角度的负载均衡：
1）http请求的负载均衡:order1项目 和 order2项目
2）rpc（Dubbo）请求的负载均衡：order3项目、dubbo_provider01项目、dubbo_provider02项目

响应来自:订单服务-ticketShopping_order1,购票成功：userId:1,ticketId:1,pcs:2
响应来自:订单服务-ticketShopping_order2_provider01,购票成功：userId:1,ticketId:1,pcs:2

响应来自:订单服务-ticketShopping_order1,购票成功：userId:1,ticketId:1,pcs:2
响应来自:ticketShopping_order2_provider02,购票成功：userId:1,ticketId:1,pcs:2

	响应来自:订单服务-ticketShopping_order3_dubbo_provider01,购票成功：userId:1,ticketId:1,pcs:2
	
响应来自:订单服务-ticketShopping_order1,购票成功：userId:1,ticketId:1,pcs:2
响应来自:订单服务-ticketShopping_order2_provider01,购票成功：userId:1,ticketId:1,pcs:2

响应来自:订单服务-ticketShopping_order1,购票成功：userId:1,ticketId:1,pcs:2
响应来自:ticketShopping_order2_provider02,购票成功：userId:1,ticketId:1,pcs:2

	响应来自:订单服务-ticketShopping_order3_dubbo_provider02,购票成功：userId:1,ticketId:1,pcs:2


3、测试服务降级
1）将 积分服务-ticketShopping_credits 服务关闭
2）用户购票请求，观察结果（下单后用户收到的响应是500异常，也就是下单失败

响应来自:ticketShopping_order2_provider02,购票成功：userId:1,ticketId:1,pcs:2
响应来自:库存服务-ticketShopping_stock,扣库存成功：userId:1,ticketId:1,pcs:2
2020-10-16 15:25:58.842 ERROR 19464 --- [nio-8012-exec-6] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is java.lang.IllegalStateException: No instances available for ticketShopping-credit] with root cause

3）使用 Sentinel 进行服务降级配置
配置服务降级时，使用代码设置sentinel，也可以在sentinel-Dashboard控制台设置

4、测试服务限流
降级前，任意一个服务挂掉，主服务挂掉
{
    "timestamp": "2020-10-16T02:53:48.854+0000",
    "status": 404,
    "error": "Not Found",
    "message": "No message available",
    "path": "/order/createOrder"
}

4、服务降级
配置服务降级时，使用代码设置sentinel，也可以在sentinel-Dashboard控制台设置


遇到的问题：
org.springframework.web.client.HttpClientErrorException: 405 null

小结：
集成Dubbo：提供高性能的基于代理的远程调用能力，服务以接口为粒度，屏蔽了远程调用底层细节。
Spring Cloud默认使用的Feign组件进行内部服务调用就是使用的HTTP协议进行调用，这时，我们如果内部服务使用RPC调用，对外使用REST API，将会是一个非常不错的选择，恰巧，Dubbo Spring Cloud给了我们这种选择的实现方式。Spring Cloud默认使用的Feign组件进行内部服务调用就是使用的HTTP协议进行调用，这时，我们如果内部服务使用RPC调用，对外使用REST API，将会是一个非常不错的选择，恰巧，Dubbo Spring Cloud给了我们这种选择的实现方式。

Spring Cloud 为什么需要RPC
在Spring Cloud构建的微服务系统中，大多数的开发者使用都是官方提供的Feign组件来进行内部服务通信，这种声明式的HTTP客户端使用起来非常的简洁、方便、优雅，但是有一点，在使用Feign消费服务的时候，相比较Dubbo这种RPC框架而言，性能堪忧。

虽说在微服务架构中，会讲按照业务划分的微服务独立部署，并且运行在各自的进程中。微服务之间的通信更加倾向于使用HTTP这种简答的通信机制，大多数情况都会使用REST API。这种通信方式非常的简洁高效，并且和开发平台、语言无关，但是通常情况下，HTTP并不会开启KeepAlive功能，即当前连接为短连接，短连接的缺点是每次请求都需要建立TCP连接，这使得其效率变的相当低下。

对外部提供REST API服务是一件非常好的事情，但是如果内部调用也是使用HTTP调用方式，就会显得显得性能低下，Spring Cloud默认使用的Feign组件进行内部服务调用就是使用的HTTP协议进行调用，这时，我们如果内部服务使用RPC调用，对外使用REST API，将会是一个非常不错的选择，恰巧，Dubbo Spring Cloud给了我们这种选择的实现方式。