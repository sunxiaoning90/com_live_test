com_live_test_MicroService_SpringCloudAlibaba_ticketShopping_order
订单服务

1、服务名
spring.application.name=ticketShopping-order2

2、controller访问Service 的方式： 直接访问本地Service


遇到的问题

Field dubboMetadata in org.apache.dubbo.spring.boot.actuate.endpoint.DubboMetadataEndpoint required a bean of type 'org.apache.dubbo.spring.boot.actuate.endpoint.metadata.DubboMetadata' that could not be found.

2
No such extension org.apache.dubbo.registry.RegistryFactory by name nacos

3\Failed to invoke event listener method
HandlerMethod details: 
Bean [org.springframework.cloud.alibaba.dubbo.autoconfigure.DubboServiceRegistrationAutoConfiguration$$EnhancerBySpringCGLIB$$d514e373]
Method [public void org.springframework.cloud.alibaba.dubbo.autoconfigure.DubboServiceRegistrationAutoConfiguration.onServiceInstancePreRegistered(org.springframework.cloud.alibaba.dubbo.registry.event.ServiceInstancePreRegisteredEvent)]
Resolved arguments: 


4\
Exception in thread "main" org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.live.test.javaee.springboot.order.service.impl.OrderService' available
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBean(DefaultListableBeanFactory.java:346)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBean(DefaultListableBeanFactory.java:333)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBean(DefaultListableBeanFactory.java:344)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBean(DefaultListableBeanFactory.java:333)
	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1107)
	at com.live.test.javaee.springboot.app.App.main(App.java:18)