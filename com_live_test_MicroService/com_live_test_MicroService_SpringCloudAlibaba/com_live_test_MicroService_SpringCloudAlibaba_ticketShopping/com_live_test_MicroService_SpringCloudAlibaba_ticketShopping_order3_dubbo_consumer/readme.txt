com_live_test_MicroService_SpringCloudAlibaba_ticketShopping_order3_dubbo_consumer
订单服务提供者，基于Dubbo的rpc接口


controller访问Service 的方式：rpc,Dubbo 

测试：
浏览器访问：
	http://{{host}}:8016/order/createOrder
连续请求两次，响应是负载均衡的：
	响应来自:订单服务-ticketShopping_order3_dubbo_provider01,购票成功：userId:1,ticketId:1,pcs:2
	响应来自:订单服务-ticketShopping_order3_dubbo_provider02,购票成功：userId:1,ticketId:1,pcs:2
	
观察服务日志：

1）orderService 是由 dubbo代理而来
com_live_test_MicroService_SpringCloudAlibaba_ticketShopping_order3_dubbo_consumer中的接口实例：
orderService：org.apache.dubbo.common.bytecode.proxy1@52348112

2）执行orderService.orderService.createOrder(map); 已经调用到远端服务
com_live_test_MicroService_SpringCloudAlibaba_ticketShopping_order3_dubbo_provider01
响应来自:订单服务-ticketShopping_order3_dubbo_provider01,购票成功：userId:1,ticketId:1,pcs:2


观察Nacos的服务注册
dubbo.metadata-service.urls=[ "dubbo://192.168.1.50:20880/org.springframework.cloud.alibaba.dubbo.service.DubboMetadataService?anyhost=true&application=ticketShopping_order2Lb_provider&bind.ip=192.168.1.50&bind.port=20880&deprecated=false&dubbo=2.0.2&dynamic=true&generic=false&group=ticketShopping_order2Lb_provider&interface=org.springframework.cloud.alibaba.dubbo.service.DubboMetadataService&methods=getAllServiceKeys,getServiceRestMetadata,getExportedURLs,getAllExportedURLs&pid=42787&qos.enable=false&release=2.7.5&revision=0.2.2.RELEASE&side=provider&timestamp=1603436506820&version=1.0.0" ]

dubbo.protocols.dubbo.port=20880

preserved.register.source=SPRING_CLOUD


遇到的问题：
Caused by: java.lang.IllegalStateException: Failed to check the status of the service org.springframework.cloud.alibaba.dubbo.service.DubboMetadataService. No provider available for the service dubbo-order3-provider01/org.springframework.cloud.alibaba.dubbo.service.DubboMetadataService:1.0.0 from the url spring-cloud://192.168.1.52:9090/org.apache.dubbo.registry.RegistryService?application=order3-customer&default.lazy=false&default.sticky=false&dubbo=2.0.2&generic=true&group=dubbo-order3-provider01&interface=org.springframework.cloud.alibaba.dubbo.service.DubboMetadataService&lazy=false&pid=47100&register.ip=127.0.0.1&release=2.7.1&side=consumer&sticky=false&timestamp=1603176088233&version=1.0.0 to the consumer 127.0.0.1 use dubbo version 2.7.1
