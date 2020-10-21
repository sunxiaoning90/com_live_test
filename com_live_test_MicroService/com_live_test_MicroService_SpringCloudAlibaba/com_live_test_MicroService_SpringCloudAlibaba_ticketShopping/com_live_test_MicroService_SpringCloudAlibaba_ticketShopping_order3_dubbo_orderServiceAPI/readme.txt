com_live_test_MicroService_SpringCloudAlibaba_ticketShopping_order3_dubbo_consumer
应用1，模拟应用，提供http接口服务。

com_live_test_MicroService_SpringCloudAlibaba_ticketShopping_order3_dubbo_orderServiceAPI
dubbo接口

com_live_test_MicroService_SpringCloudAlibaba_ticketShopping_order3_dubbo_provider01
微服务1，模拟微服务，提供dubbo接口服务。

com_live_test_MicroService_SpringCloudAlibaba_ticketShopping_order3_dubbo_provider02
微服务2，模拟微服务，提供dubbo接口服务。


架构中 application与sevice的区别：

　　　　service 提供了基础服务功能；application组装基础服务功能，提供给用户直接可用的业务。
　　　　service 服务粒度小、功能基础，不易发生改变；application提供上游业务功能，紧贴业务需求，容易发生改变。
　　　　形成 service支撑application的整体架构，增加多变的application甚至不需要变动service。


1、dubbo消费端启动报错:No provider available for the service

解决办法
注册和调用的服务的版本号要一致:

provider:
@com.alibaba.dubbo.config.annotation.Service(version="2020.10")
public class OrderService implements IOrderService {

customer:
//dubbo远端服务
	@org.apache.dubbo.config.annotation.Reference(version="2020.10")
	IOrderService orderService;

2、dubbo消费端启动报错:Error creating bean with name 'orderController': Injection of @org.apache.dubbo.config.annotation.Reference dependencies is failed; nested exception is java.lang.IllegalStateException: Failed to check the status of the service com.live.test.javaee.springboot.order.IOrderService. No provider available for the service com.live.test.javaee.springboot.order.IOrderService:2020.10 from the url spring-cloud://192.168.1.52:9090/org.apache.dubbo.registry.RegistryService?application=order3-customer&default.generic=false&default.lazy=false&default.sticky=false&dubbo=2.0.2&generic=false&interface=com.live.test.javaee.springboot.order.IOrderService&lazy=false&methods=createOrder&pid=48619&register.ip=127.0.0.1&release=2.7.1&revision=2020.10&side=consumer&sticky=false&timestamp=1603177579262&version=2020.10 to the consumer 127.0.0.1 use dubbo version 2.7.1
	
	我的错误是 @Service 导的包是org.springframework.stereotype.Service，这个地方极容易出错

这种错误是服务层代码没有成功注册到注册中心导致，请检查一下你的服务层代码是否添加了@service注解，并且该注解的包一定是com.alibaba.dubbo.config.annotation包，不是org.springframework.stereotype.Service，这个地方极容易出错。另外还有一个原因就是你的服务层工程由于某些原因没有正常启动，也无法注册到注册中心里。