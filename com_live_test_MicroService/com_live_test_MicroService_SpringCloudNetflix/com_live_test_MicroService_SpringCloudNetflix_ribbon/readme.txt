Spring Cloud Netfix 项目： 使用 Ribbon 实现 负载均衡（ 客户端）


一、简介
使用 Ribbon 实现 负载均衡（ 客户端）
前提：使用Eureka 作为 注册中心(Eureka Server、服务提供方、服务消费方）

二、项目清单：
1、com_live_test_MicroService_SpringCloudNetflix_ribbon_eureka_server
Spring Cloud Netfix 项目： Eureka Server端
8761端口启动，并从“/ Eureka”提供Eureka API。

2.1、com_live_test_MicroService_SpringCloudNetflix_ribbon_eureka_discovery_provider1
Spring Cloud Netfix 项目： 使用 Eureka 作为注册中心-服务注册-服务提供方1

2.2、com_live_test_MicroService_SpringCloudNetflix_ribbon_eureka_discovery_provider2
Spring Cloud Netfix 项目： 使用 Eureka 作为注册中心-服务注册-服务提供方2

3、com_live_test_MicroService_SpringCloudNetflix_eureka_discovery_consumer
Spring Cloud Netfix 项目： 使用 Eureka 作为注册中心-服务注册-服务消费方

三、测试Ribbon实现负载均衡
1、启动 项目
1）APPlication启动成功：Eureka Server

2.1）APPlication启动成功：服务提供方：SpringCloudNetflix_ribbon_eureka_discovery_provider1

2.2）APPlication启动成功：服务提供方：SpringCloudNetflix_ribbon_eureka_discovery_provider2

3）APPlication启动成功：服务提供方：SpringCloudNetflix_eureka_discovery_consumer

2、首先测试各个服务是否正常
1）Eureka Server
请求：http://{{host}}:8761/eureka/apps
结果：
<applications>
    <versions__delta>1</versions__delta>
    <apps__hashcode>UP_2_</apps__hashcode>
    <application>
        <name>COM_LIVE_TEST_MICROSERVICE_SPRINGCLOUDNETFLIX_EUREKA_DISCOVERY_PROVIDER</name>
        <instance>
            <instanceId>192.168.1.50:com_live_test_MicroService_SpringCloudNetflix_eureka_discovery_provider:8082</instanceId>
            <hostName>192.168.1.50</hostName>
            <app>COM_LIVE_TEST_MICROSERVICE_SPRINGCLOUDNETFLIX_EUREKA_DISCOVERY_PROVIDER</app>
            <ipAddr>192.168.1.50</ipAddr>
            <status>UP</status>
            <overriddenstatus>UNKNOWN</overriddenstatus>
            <port enabled="true">8082</port>
            <securePort enabled="false">443</securePort>
            <countryId>1</countryId>
            <dataCenterInfo class="com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo">
                <name>MyOwn</name>
            </dataCenterInfo>
            <leaseInfo>
                <renewalIntervalInSecs>30</renewalIntervalInSecs>
                <durationInSecs>90</durationInSecs>
                <registrationTimestamp>1603693162169</registrationTimestamp>
                <lastRenewalTimestamp>1603693162169</lastRenewalTimestamp>
                <evictionTimestamp>0</evictionTimestamp>
                <serviceUpTimestamp>1603693161652</serviceUpTimestamp>
            </leaseInfo>
            <metadata>
                <management.port>8082</management.port>
            </metadata>
            <homePageUrl>http://192.168.1.50:8082/</homePageUrl>
            <statusPageUrl>http://192.168.1.50:8082/actuator/info</statusPageUrl>
            <healthCheckUrl>http://192.168.1.50:8082/actuator/health</healthCheckUrl>
            <vipAddress>com_live_test_MicroService_SpringCloudNetflix_eureka_discovery_provider</vipAddress>
            <secureVipAddress>com_live_test_MicroService_SpringCloudNetflix_eureka_discovery_provider</secureVipAddress>
            <isCoordinatingDiscoveryServer>false</isCoordinatingDiscoveryServer>
            <lastUpdatedTimestamp>1603693162169</lastUpdatedTimestamp>
            <lastDirtyTimestamp>1603693161510</lastDirtyTimestamp>
            <actionType>ADDED</actionType>
        </instance>
        <instance>
            <instanceId>localhost:com_live_test_MicroService_SpringCloudNetflix_eureka_discovery_provider:8089</instanceId>
            <hostName>192.168.1.50</hostName>
            <app>COM_LIVE_TEST_MICROSERVICE_SPRINGCLOUDNETFLIX_EUREKA_DISCOVERY_PROVIDER</app>
            <ipAddr>192.168.1.50</ipAddr>
            <status>UP</status>
            <overriddenstatus>UNKNOWN</overriddenstatus>
            <port enabled="true">8089</port>
            <securePort enabled="false">443</securePort>
            <countryId>1</countryId>
            <dataCenterInfo class="com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo">
                <name>MyOwn</name>
            </dataCenterInfo>
            <leaseInfo>
                <renewalIntervalInSecs>30</renewalIntervalInSecs>
                <durationInSecs>90</durationInSecs>
                <registrationTimestamp>1603693015076</registrationTimestamp>
                <lastRenewalTimestamp>1603693254036</lastRenewalTimestamp>
                <evictionTimestamp>0</evictionTimestamp>
                <serviceUpTimestamp>1603693013892</serviceUpTimestamp>
            </leaseInfo>
            <metadata>
                <management.port>8089</management.port>
            </metadata>
            <homePageUrl>http://192.168.1.50:8089/</homePageUrl>
            <statusPageUrl>http://192.168.1.50:8089/actuator/info</statusPageUrl>
            <healthCheckUrl>http://192.168.1.50:8089/actuator/health</healthCheckUrl>
            <vipAddress>com_live_test_MicroService_SpringCloudNetflix_eureka_discovery_provider</vipAddress>
            <secureVipAddress>com_live_test_MicroService_SpringCloudNetflix_eureka_discovery_provider</secureVipAddress>
            <isCoordinatingDiscoveryServer>false</isCoordinatingDiscoveryServer>
            <lastUpdatedTimestamp>1603693015076</lastUpdatedTimestamp>
            <lastDirtyTimestamp>1603693013420</lastDirtyTimestamp>
            <actionType>ADDED</actionType>
        </instance>
    </application>
</applications>
http://{{host}}:8089/discovery/provider/echo/test123

2.1）服务提供方：SpringCloudNetflix_ribbon_eureka_discovery_provider1
请求：http://{{host}}:8761/eureka/apps
结果：*响应来自：服务提供方：SpringCloudNetflix_ribbon_eureka_discovery_provider1,响应结果：test123

2.2）服务提供方：SpringCloudNetflix_ribbon_eureka_discovery_provider2
请求：http://{{host}}:8089/discovery/provider/echo/test123
结果：*响应来自：服务提供方：SpringCloudNetflix_ribbon_eureka_discovery_provider2,响应结果：test123

3）服务提供方：SpringCloudNetflix_ribbon_eureka_discovery_provider2
请求：http://{{host}}:8083/discovery/consumer/echo/test123
结果：

3、测试Ribbon实现负载均衡
请求1：http://{{host}}:8083/discovery/consumer/echo/test123
结果1：*响应来自：服务提供方：SpringCloudNetflix_ribbon_eureka_discovery_provider1,响应结果：test123

请求2：http://{{host}}:8083/discovery/consumer/echo/test123
结果2：*响应来自：服务提供方：SpringCloudNetflix_ribbon_eureka_discovery_provider2,响应结果：test123

请求3：http://{{host}}:8083/discovery/consumer/echo/test123
结果3：*响应来自：服务提供方：SpringCloudNetflix_ribbon_eureka_discovery_provider1,响应结果：test123

*使用Ribbon后，请求过程分析
浏览器发起请求：http://{{host}}:8083/discovery/consumer/echo/test123

consumer服务受理controller请求，并经Ribbon找到实际服务地址
org.eclipse.jetty.server.HttpChannel     : /discovery/consumer/echo/test123
http://com_live_test_MicroService_SpringCloudNetflix_eureka_discovery_provider/discovery/provider/echo/test123

实际服务地址：
http://{{host}}:8083/discovery/consumer/echo/test123

*遇到的错误
1、Ribon在调用微服务时报错：

String serverAddress = "http://COM_LIVE_TEST_MICROSERVICE_SPRINGCLOUDNETFLIX_EUREKA_DISCOVERY_PROVIDER";
String url = serverAddress + "/discovery/provider/echo/" + str;
String r = restTemplate.getForObject(url, String.class);
		
报错：
org.springframework.web.util.NestedServletException: Request processing failed; nested exception is java.lang.IllegalStateException: Request URI does not contain a valid hostname: http://COM_LIVE_TEST_MICROSERVICE_SPRINGCLOUDNETFLIX_EUREKA_DISCOVERY_PROVIDER/discovery/provider/echo/test123

解决：

问题原因：

在注册服务的时候，properties文件中的服务名（spring.application.name）带上了下划线（如：COM_LIVE_TEST_MICROSERVICE_SPRINGCLOUDNETFLIX_EUREKA_DISCOVERY_PROVIDER）
解决办法：

将下划线 改为 中划线即可：(COM-LIVE-TEST-MICROSERVICE-SPRINGCLOUDNETFLIX-EUREKA-DISCOVERY-PROVIDER)

