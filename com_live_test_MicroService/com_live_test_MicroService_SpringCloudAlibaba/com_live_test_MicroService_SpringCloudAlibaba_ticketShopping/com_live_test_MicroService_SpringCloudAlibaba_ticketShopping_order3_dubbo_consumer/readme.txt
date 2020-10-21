com_live_test_MicroService_SpringCloudAlibaba_ticketShopping_order
订单服务

基于http的rest接口，也可以基于dubbo的rpc接口。

1、服务名
spring.application.name=ticketShopping-order

2、controller访问Service 的方式：rpc,Dubbo 



遇到的问题：
Caused by: java.lang.IllegalStateException: Failed to check the status of the service org.springframework.cloud.alibaba.dubbo.service.DubboMetadataService. No provider available for the service dubbo-order3-provider01/org.springframework.cloud.alibaba.dubbo.service.DubboMetadataService:1.0.0 from the url spring-cloud://192.168.1.52:9090/org.apache.dubbo.registry.RegistryService?application=order3-customer&default.lazy=false&default.sticky=false&dubbo=2.0.2&generic=true&group=dubbo-order3-provider01&interface=org.springframework.cloud.alibaba.dubbo.service.DubboMetadataService&lazy=false&pid=47100&register.ip=127.0.0.1&release=2.7.1&side=consumer&sticky=false&timestamp=1603176088233&version=1.0.0 to the consumer 127.0.0.1 use dubbo version 2.7.1
