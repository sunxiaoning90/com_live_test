com_live_test_MicroService_SpringCloudAlibaba_ticketShopping_order
订单服务

1、服务名
spring.application.name=ticketShopping-order2

2、controller访问Service 的方式： 直接访问本地Service



启动后，访问nacos OpenAPI

http://192.168.1.52:8848/nacos/v1/ns/instance/list?serviceName=service-provider
{
    "hosts": [
        {
            "ip": "192.168.1.50",
            "port": 8017,
            "valid": true,
            "healthy": true,
            "marked": false,
            "instanceId": "192.168.1.50#8017#DEFAULT#DEFAULT_GROUP@@ticketShopping-order2Lb-provider",
            "metadata": {
                "dubbo.metadata-service.urls": "[ \"dubbo://127.0.0.1:20880/org.springframework.cloud.alibaba.dubbo.service.DubboMetadataService?anyhost=true&application=dubbo-order3-provider01&bind.ip=127.0.0.1&bind.port=20880&default.deprecated=false&default.dynamic=false&default.register=true&deprecated=false&dubbo=2.0.2&dynamic=false&generic=false&group=ticketShopping-order2Lb-provider&interface=org.springframework.cloud.alibaba.dubbo.service.DubboMetadataService&methods=getAllServiceKeys,getServiceRestMetadata,getExportedURLs,getAllExportedURLs&pid=24773&register=true&release=2.7.1&revision=0.2.2.RELEASE&side=provider&timestamp=1603158011255&version=1.0.0\" ]",
                "dubbo.protocols.dubbo.port": "20880",
                "preserved.register.source": "SPRING_CLOUD"
            },
            "enabled": true,
            "weight": 1.0,
            "clusterName": "DEFAULT",
            "serviceName": "ticketShopping-order2Lb-provider",
            "ephemeral": true
        }
    ],
    "dom": "ticketShopping-order2Lb-provider",
    "name": "DEFAULT_GROUP@@ticketShopping-order2Lb-provider",
    "cacheMillis": 3000,
    "lastRefTime": 1603158126463,
    "checksum": "78d07682552ae95d7f95a0d371722a61",
    "useSpecifiedURL": false,
    "clusters": "",
    "env": "",
    "metadata": {}
}





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
	
	5\
java.net.BindException: Address already in use
	at sun.nio.ch.Net.bind0(Native Method) ~[na:1.8.0_201]
	at sun.nio.ch.Net.bind(Net.java:433) ~[na:1.8.0_201]
	at sun.nio.ch.Net.bind(Net.java:425) ~[na:1.8.0_201]
	at sun.nio.ch.ServerSocketChannelImpl.bind(ServerSocketChannelImpl.java:223) ~[na:1.8.0_201]
	at io.netty.channel.socket.nio.NioServerSocketChannel.doBind(NioServerSocketChannel.java:128) ~[netty-transport-4.1.27.Final.jar:4.1.27.Final]
	at io.netty.channel.AbstractChannel$AbstractUnsafe.bind(AbstractChannel.java:558) ~[netty-transport-4.1.27.Final.jar:4.1.27.Final]
	at io.netty.channel.DefaultChannelPipeline$HeadContext.bind(DefaultChannelPipeline.java:1358) ~[netty-transport-4.1.27.Final.jar:4.1.27.Final]
	at io.netty.channel.AbstractChannelHandlerContext.invokeBind(AbstractChannelHandlerContext.java:501) ~[netty-transport-4.1.27.Final.jar:4.1.27.Final]
	at io.netty.channel.AbstractChannelHandlerContext.bind(AbstractChannelHandlerContext.java:486) ~[netty-transport-4.1.27.Final.jar:4.1.27.Final]
	at io.netty.channel.DefaultChannelPipeline.bind(DefaultChannelPipeline.java:1019) ~[netty-transport-4.1.27.Final.jar:4.1.27.Final]
	at io.netty.channel.AbstractChannel.bind(AbstractChannel.java:254) ~[netty-transport-4.1.27.Final.jar:4.1.27.Final]
	at io.netty.bootstrap.AbstractBootstrap$2.run(AbstractBootstrap.java:366) ~[netty-transport-4.1.27.Final.jar:4.1.27.Final]
	at io.netty.util.concurrent.AbstractEventExecutor.safeExecute(AbstractEventExecutor.java:163) ~[netty-common-4.1.27.Final.jar:4.1.27.Final]
	at io.netty.util.concurrent.SingleThreadEventExecutor.runAllTasks(SingleThreadEventExecutor.java:404) ~[netty-common-4.1.27.Final.jar:4.1.27.Final]
	at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:464) ~[netty-transport-4.1.27.Final.jar:4.1.27.Final]
	at io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:884) ~[netty-common-4.1.27.Final.jar:4.1.27.Final]
	at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30) ~[netty-common-4.1.27.Final.jar:4.1.27.Final]
	at java.lang.Thread.run(Thread.java:748) ~[na:1.8.0_201]

2020-10-19 19:37:01.184  WARN 31974 --- [           main] o.a.d.qos.protocol.QosProtocolWrapper    :  [DUBBO] Fail to start qos server: , dubbo version: 2.7.1, current host: 127.0.0.1

java.net.BindException: Address already in use
	at sun.nio.ch.Net.bind0(Native Method) ~[na:1.8.0_201]
	at sun.nio.ch.Net.bind(Net.java:433) ~[na:1.8.0_201]
	at sun.nio.ch.Net.bind(Net.java:425) ~[na:1.8.0_201]
	at sun.nio.ch.ServerSocketChannelImpl.bind(ServerSocketChannelImpl.java:223) ~[na:1.8.0_201]
	at io.netty.channel.socket.nio.NioServerSocketChannel.doBind(NioServerSocketChannel.java:128) ~[netty-transport-4.1.27.Final.jar:4.1.27.Final]
	at io.netty.channel.AbstractChannel$AbstractUnsafe.bind(AbstractChannel.java:558) ~[netty-transport-4.1.27.Final.jar:4.1.27.Final]
	at io.netty.channel.DefaultChannelPipeline$HeadContext.bind(DefaultChannelPipeline.java:1358) ~[netty-transport-4.1.27.Final.jar:4.1.27.Final]
	at io.netty.channel.AbstractChannelHandlerContext.invokeBind(AbstractChannelHandlerContext.java:501) ~[netty-transport-4.1.27.Final.jar:4.1.27.Final]
	at io.netty.channel.AbstractChannelHandlerContext.bind(AbstractChannelHandlerContext.java:486) ~[netty-transport-4.1.27.Final.jar:4.1.27.Final]
	at io.netty.channel.DefaultChannelPipeline.bind(DefaultChannelPipeline.java:1019) ~[netty-transport-4.1.27.Final.jar:4.1.27.Final]
	at io.netty.channel.AbstractChannel.bind(AbstractChannel.java:254) ~[netty-transport-4.1.27.Final.jar:4.1.27.Final]
	at io.netty.bootstrap.AbstractBootstrap$2.run(AbstractBootstrap.java:366) ~[netty-transport-4.1.27.Final.jar:4.1.27.Final]
	at io.netty.util.concurrent.AbstractEventExecutor.safeExecute(AbstractEventExecutor.java:163) ~[netty-common-4.1.27.Final.jar:4.1.27.Final]
	at io.netty.util.concurrent.SingleThreadEventExecutor.runAllTasks(SingleThreadEventExecutor.java:404) ~[netty-common-4.1.27.Final.jar:4.1.27.Final]
	at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:464) ~[netty-transport-4.1.27.Final.jar:4.1.27.Final]
	at io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:884) ~[netty-common-4.1.27.Final.jar:4.1.27.Final]
	at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30) ~[netty-common-4.1.27.Final.jar:4.1.27.Final]
	at java.lang.Thread.run(Thread.java:748) ~[na:1.8.0_201]

2020-10-19 19:37:01.185  WARN 31974 --- [           main] o.a.d.c.s.e.SpringExtensionFactory       :  [DUBBO] No spring extension (bean) named:applicationContext, try to find an extension (bean) of type org.springframework.context.ConfigurableApplicationContext, dubbo version: 2.7.1, current host: 127.0.0.1
2020-10-19 19:37:01.187  WARN 31974 --- [           main] o.a.d.c.s.e.SpringExtensionFactory       :  [DUBBO] No spring extension (bean) named:applicationContext, type:org.springframework.context.ConfigurableApplicationContext found, stop get bean., dubbo version: 2.7.1, current host: 127.0.0.1
2020-10-19 19:37:01.245  INFO 31974 --- [           main] o.s.c.a.d.registry.SpringCloudRegistry   :  [DUBBO] Register: consumer://127.0.0.1/com.live.test.javaee.springboot.order.IOrderService?application=order3-customer&category=consumers&check=false&default.lazy=false&default.sticky=false&dubbo=2.0.2&interface=com.live.test.javaee.springboot.order.IOrderService&lazy=false&methods=createOrder&pid=31974&release=2.7.1&side=consumer&sticky=false&timestamp=1603107420887, dubbo version: 2.7.1, current host: 127.0.0.1
2020-10-19 19:37:01.255  INFO 31974 --- [           main] o.s.c.a.d.registry.SpringCloudRegistry   :  [DUBBO] Subscribe: consumer://127.0.0.1/com.live.test.javaee.springboot.order.IOrderService?application=order3-customer&category=providers,configurators,routers&default.lazy=false&default.sticky=false&dubbo=2.0.2&interface=com.live.test.javaee.springboot.order.IOrderService&lazy=false&methods=createOrder&pid=31974&release=2.7.1&side=consumer&sticky=false&timestamp=1603107420887, dubbo version: 2.7.1, current host: 127.0.0.1
2020-10-19 19:37:01.267  WARN 31974 --- [           main] ConfigServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'orderController': Injection of @org.apache.dubbo.config.annotation.Reference dependencies is failed; nested exception is java.lang.IllegalStateException: Failed to check the status of the service com.live.test.javaee.springboot.order.IOrderService. No provider available for the service com.live.test.javaee.springboot.order.IOrderService from the url spring-cloud://192.168.1.52:9090/org.apache.dubbo.registry.RegistryService?application=order3-customer&default.lazy=false&default.sticky=false&dubbo=2.0.2&interface=com.live.test.javaee.springboot.order.IOrderService&lazy=false&methods=createOrder&pid=31974&register.ip=127.0.0.1&release=2.7.1&side=consumer&sticky=false&timestamp=1603107420887 to the consumer 127.0.0.1 use dubbo version 2.7.1
2020-10-19 19:37:01.275  INFO 31974 --- [           main] o.s.c.a.d.s.DubboGenericServiceFactory   : The Dubbo GenericService ReferenceBeans are destroying...
2020-10-19 19:37:01.275  INFO 31974 --- [           main] f.a.ReferenceAnnotationBeanPostProcessor : class org.apache.dubbo.config.spring.beans.factory.annotation.ReferenceAnnotationBeanPostProcessor was destroying!
2020-10-19 19:37:01.277  INFO 31974 --- [           main] o.apache.catalina.core.StandardService   : Stopping service [Tomcat]
2020-10-19 19:37:01.328  INFO 31974 --- [           main] ConditionEvaluationReportLoggingListener : 

Error starting ApplicationContext. To display the conditions report re-run your application with 'debug' enabled.
2020-10-19 19:37:01.332 ERROR 31974 --- [           main] o.s.boot.SpringApplication               : Application run failed

org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'orderController': Injection of @org.apache.dubbo.config.annotation.Reference dependencies is failed; nested exception is java.lang.IllegalStateException: Failed to check the status of the service com.live.test.javaee.springboot.order.IOrderService. No provider available for the service com.live.test.javaee.springboot.order.IOrderService from the url spring-cloud://192.168.1.52:9090/org.apache.dubbo.registry.RegistryService?application=order3-customer&default.lazy=false&default.sticky=false&dubbo=2.0.2&interface=com.live.test.javaee.springboot.order.IOrderService&lazy=false&methods=createOrder&pid=31974&register.ip=127.0.0.1&release=2.7.1&side=consumer&sticky=false&timestamp=1603107420887 to the consumer 127.0.0.1 use dubbo version 2.7.1
	at org.apache.dubbo.config.spring.beans.factory.annotation.AnnotationInjectedBeanPostProcessor.postProcessPropertyValues(AnnotationInjectedBeanPostProcessor.java:133) ~[dubbo-2.7.1.jar:2.7.1]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1341) ~[spring-beans-5.0.8.RELEASE.jar:5.0.8.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:572) ~[spring-beans-5.0.8.RELEASE.jar:5.0.8.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:495) ~[spring-beans-5.0.8.RELEASE.jar:5.0.8.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:317) ~[spring-beans-5.0.8.RELEASE.jar:5.0.8.RELEASE]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222) ~[spring-beans-5.0.8.RELEASE.jar:5.0.8.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:315) ~[spring-beans-5.0.8.RELEASE.jar:5.0.8.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:199) ~[spring-beans-5.0.8.RELEASE.jar:5.0.8.RELEASE]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:759) ~[spring-beans-5.0.8.RELEASE.jar:5.0.8.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:869) ~[spring-context-5.0.8.RELEASE.jar:5.0.8.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:550) ~[spring-context-5.0.8.RELEASE.jar:5.0.8.RELEASE]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:140) ~[spring-boot-2.0.4.RELEASE.jar:2.0.4.RELEASE]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:762) [spring-boot-2.0.4.RELEASE.jar:2.0.4.RELEASE]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:398) [spring-boot-2.0.4.RELEASE.jar:2.0.4.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:330) [spring-boot-2.0.4.RELEASE.jar:2.0.4.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1258) [spring-boot-2.0.4.RELEASE.jar:2.0.4.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1246) [spring-boot-2.0.4.RELEASE.jar:2.0.4.RELEASE]
	at com.live.test.javaee.springboot.app.App.main(App.java:16) [classes/:na]
Caused by: java.lang.IllegalStateException: Failed to check the status of the service com.live.test.javaee.springboot.order.IOrderService. No provider available for the service com.live.test.javaee.springboot.order.IOrderService from the url spring-cloud://192.168.1.52:9090/org.apache.dubbo.registry.RegistryService?application=order3-customer&default.lazy=false&default.sticky=false&dubbo=2.0.2&interface=com.live.test.javaee.springboot.order.IOrderService&lazy=false&methods=createOrder&pid=31974&register.ip=127.0.0.1&release=2.7.1&side=consumer&sticky=false&timestamp=1603107420887 to the consumer 127.0.0.1 use dubbo version 2.7.1
	at org.apache.dubbo.config.ReferenceConfig.createProxy(ReferenceConfig.java:390) ~[dubbo-2.7.1.jar:2.7.1]
	at org.apache.dubbo.config.ReferenceConfig.init(ReferenceConfig.java:305) ~[dubbo-2.7.1.jar:2.7.1]
	at org.apache.dubbo.config.ReferenceConfig.get(ReferenceConfig.java:231) ~[dubbo-2.7.1.jar:2.7.1]
	at org.apache.dubbo.config.spring.beans.factory.annotation.ReferenceAnnotationBeanPostProcessor$ReferenceBeanInvocationHandler.init(ReferenceAnnotationBeanPostProcessor.java:174) ~[dubbo-2.7.1.jar:2.7.1]
	at org.apache.dubbo.config.spring.beans.factory.annotation.ReferenceAnnotationBeanPostProcessor$ReferenceBeanInvocationHandler.access$100(ReferenceAnnotationBeanPostProcessor.java:147) ~[dubbo-2.7.1.jar:2.7.1]
	at org.apache.dubbo.config.spring.beans.factory.annotation.ReferenceAnnotationBeanPostProcessor.buildInvocationHandler(ReferenceAnnotationBeanPostProcessor.java:141) ~[dubbo-2.7.1.jar:2.7.1]
	at org.apache.dubbo.config.spring.beans.factory.annotation.ReferenceAnnotationBeanPostProcessor.buildProxy(ReferenceAnnotationBeanPostProcessor.java:123) ~[dubbo-2.7.1.jar:2.7.1]
	at org.apache.dubbo.config.spring.beans.factory.annotation.ReferenceAnnotationBeanPostProcessor.doGetInjectedBean(ReferenceAnnotationBeanPostProcessor.java:117) ~[dubbo-2.7.1.jar:2.7.1]
	at org.apache.dubbo.config.spring.beans.factory.annotation.ReferenceAnnotationBeanPostProcessor.doGetInjectedBean(ReferenceAnnotationBeanPostProcessor.java:50) ~[dubbo-2.7.1.jar:2.7.1]
	at org.apache.dubbo.config.spring.beans.factory.annotation.AnnotationInjectedBeanPostProcessor.getInjectedObject(AnnotationInjectedBeanPostProcessor.java:342) ~[dubbo-2.7.1.jar:2.7.1]
	at org.apache.dubbo.config.spring.beans.factory.annotation.AnnotationInjectedBeanPostProcessor$AnnotatedFieldElement.inject(AnnotationInjectedBeanPostProcessor.java:522) ~[dubbo-2.7.1.jar:2.7.1]
	at org.springframework.beans.factory.annotation.InjectionMetadata.inject(InjectionMetadata.java:91) ~[spring-beans-5.0.8.RELEASE.jar:5.0.8.RELEASE]
	at org.apache.dubbo.config.spring.beans.factory.annotation.AnnotationInjectedBeanPostProcessor.postProcessPropertyValues(AnnotationInjectedBeanPostProcessor.java:129) ~[dubbo-2.7.1.jar:2.7.1]
	... 17 common frames omitted


===
2020-10-20 16:42:23.736  INFO 52370 --- [           main] f.a.ReferenceAnnotationBeanPostProcessor : class org.apache.dubbo.config.spring.beans.factory.annotation.ReferenceAnnotationBeanPostProcessor was destroying!

	