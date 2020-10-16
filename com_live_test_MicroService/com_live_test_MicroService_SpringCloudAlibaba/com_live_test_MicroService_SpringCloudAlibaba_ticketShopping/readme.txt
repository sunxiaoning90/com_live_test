com_live_test_MicroService_SpringCloudAlibaba_TicketShoppingt.HttpClientErrorException: 405 nullt.HttpClientErrorException: 405 null
各个组件整合、流转


一、用户购票 流程：
创建订单 -> 减库存 -> 加积分 -> 通知（短信通知/邮件通知...）

二、项目清单
1、网关（gateway）
项目名：com_live_test_MicroService_SpringCloudAlibaba_TicketShopping_Gateway
作用：购票系统的网关

2、order：订单服务(controller + service)
项目名：
com_live_test_MicroService_SpringCloudAlibaba_ticketShopping_order（设计：web请求调用本地Service）

com_live_test_MicroService_SpringCloudAlibaba_ticketShopping_order2（设计：web请求，负载均衡调用其它服务提供者）
com_live_test_MicroService_SpringCloudAlibaba_ticketShopping_order2_provider02（设计：web请求调用本地Service）
com_live_test_MicroService_SpringCloudAlibaba_ticketShopping_order2_provider01（设计：web请求调用远端Service）

作用：负责服务用户下单请求，业务的核心，做了负载均衡


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
响应来自:订单服务-ticketShopping_order1,购票成功：userId:1,ticketId:1,pcs:2
响应来自:订单服务-ticketShopping_order2_provider01,购票成功：userId:1,ticketId:1,pcs:2

响应来自:订单服务-ticketShopping_order1,购票成功：userId:1,ticketId:1,pcs:2
响应来自:ticketShopping_order2_provider02,购票成功：userId:1,ticketId:1,pcs:2

响应来自:订单服务-ticketShopping_order1,购票成功：userId:1,ticketId:1,pcs:2
响应来自:订单服务-ticketShopping_order2_provider01,购票成功：userId:1,ticketId:1,pcs:2

响应来自:订单服务-ticketShopping_order1,购票成功：userId:1,ticketId:1,pcs:2
响应来自:ticketShopping_order2_provider02,购票成功：userId:1,ticketId:1,pcs:2

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
