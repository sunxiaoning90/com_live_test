com_live_test_MicroService_SpringCloudNetflix_CRM
Spring Cloud Netflix 微服务 组件整合、流转：Eureka、Ribbon、Fengin、Hystrix、zuul


一、用户购票 流程：
创建CRM投诉工单 -> 通知（通知客户/客服...）

二、项目清单

*用户请求、微服务处理，整个流程示意图：
1、user http请求购票下单网关	SpringCloudNexflixCRM_zuul->	
									->	1、SpringCloudNexflix_CRM_order1 （controller + 本地Service）(Eureka 注册中心)
									
									->	2、SpringCloudNexflix_CRM_order2 （controller，Fegin、Ribbion负载均衡调用其它 controller）(Eureka注册中心)
										->	2.1、SpringCloudNexflix_CRM_order2_provider01 （controller + 本地Service）
										->	2.2、SpringCloudNexflix_CRM_order2_provider02 （controller + 本地Service）
										
									->	3、SpringCloudNexflix_CRM_order3_dubbo_consumer （controller，远端Service，rpc）(Spring Cloud Alibaba Nacos注册中心、Dubbo 服务消费者)
										->	3.1、SpringCloudNexflix_CRM_order3_dubbo_provider01 （Dubbo生产者1,RPC服务提供方1）
										->	3.2、SpringCloudNexflix_CRM_order3_dubbo_provider02 （Dubbo生产者2,RPC服务提供方1）

1、网关（zuul）
项目名：com_live_test_MicroService_SpringCloudNexflix_CRM_zuul
作用：CRM微服务系统的网关

2、CRM_order：CRM投诉工单服务(controller + service)
项目名：
com_live_test_MicroService_SpringCloudNexflix_CRM_order1（设计：web请求调用本地Service）

com_live_test_MicroService_SpringCloudNexflix_CRM_order2（设计：web请求，负载均衡调用其它服务提供者）
com_live_test_MicroService_SpringCloudNexflix_CRM_order2_provider01（设计：http请求调用本地Service）
com_live_test_MicroService_SpringCloudNexflix_CRM_order2_provider02（设计：http请求调用远端Service）

作用：负责服务用户下单请求，业务的核心，做了负载均衡

以上基于http的rest接口（对外http），也可以基于dubbo的rpc接口（对内rpc）:

com_live_test_MicroService_SpringCloudNexflix_CRM_order3_dubbo_consumer（设计：既有http请求支持，又有rpc服务支持（Dubbo），负载均衡调用其它服务提供者）
com_live_test_MicroService_SpringCloudNexflix_CRM_order3_dubbo_orderServiceAPI（设计：Dubbo生产者服务接口规范）
com_live_test_MicroService_SpringCloudNexflix_CRM_order3_dubbo_provider01（设计：Dubbo生产者1）
com_live_test_MicroService_SpringCloudNexflix_CRM_order3_dubbo_provider02（设计：Dubbo生产者2）

hystrix断路器：
com_live_test_MicroService_SpringCloudNexflix_CRM_order4_hystrix_consumer

3、notice:通知服务(controller + service)
项目名：com_live_test_MicroService_SpringCloudAlibaba_ticketShopping_notice

作用：负责通知用户

三、测试
1、启动各个服务

2、用户发起请求
http://{{host}}:8088/crmOrder/crmOrder/createOrder/123

四、测试正常下单流程、负载均衡、服务降级、服务限流
1、测试正常下单流程：

2、测试负载均衡
两个角度的负载均衡：
1）http请求的负载均衡:CRM_order1项目 、 CRM_order2项目、CRM_order3项目
2）rpc（Dubbo）请求的负载均衡：CRM_order3项目、CRM_order3_provider01项目、CRM_order3_provider02项目

3、测试断路器
1）将 com_live_test_MicroService_SpringCloudNexflix_CRM_notice 服务关闭
2）用户购票请求，观察结果（下单后用户收到的响应是500异常，也就是下单失败

3）使用 Hystrix 进行服务降级配置

开启 Hystrix 后，即使 通知服务挂了，也不影响整个CRM投诉工单的流程

============

http://{{host}}:8088/crmOrder/crmOrder/createOrder/123

响应来自:服务提供方：com_live_test_MicroService_SpringCloudNexflix_CRM_order3_dubbo_provider01 --port: 8031,CRM-投诉成功：userId:null,ticketId:null,pcs:null

响应来自:服务提供方：com_live_test_MicroService_SpringCloudNexflix_CRM_order3_dubbo_provider01 --port: 8031,CRM-投诉成功：userId:null,ticketId:null,pcs:null

{
    "timestamp": "2020-11-02T03:28:52.206+0000",
    "status": 504,
    "error": "Gateway Timeout",
    "message": "com.netflix.zuul.exception.ZuulException: Hystrix Readed time out"
}

服务消费方：com_live_test_MicroService_SpringCloudNexflix_CRM_order1--port: 8010 --name: SpringCloudNexflix-CRM-order123

*响应来自：服务提供方：com_live_test_MicroService_SpringCloudNexflix_CRM_order3_dubbo_provider02 --port: 8032,响应结果：123

*响应来自：服务提供方：com_live_test_MicroService_SpringCloudNexflix_CRM_order2_provider02 --port: 8022,响应结果：123
