Spring Cloud Netfix 项目： 使用 Hystrix Dashboard


一、简介

官方资料：

https://docs.spring.io/spring-cloud-netflix/docs/2.2.5.RELEASE/reference/html/#circuit-breaker-hystrix-dashboard

To run the Hystrix Dashboard, annotate your Spring Boot main class with @EnableHystrixDashboard. Then visit /hystrix and point the dashboard to an individual instance’s /hystrix.stream endpoint in a Hystrix client application.
要运行Hystrix仪表板，请使用@EnableHystrixDashboard注释您的Spring引导主类。然后访问/hystrix并将仪表板指向单个实例的/hystrix。Hystrix客户端应用程序中的流端点。

1、配置pom依赖
		<!-- hystrix-dashboard -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
		</dependency>
		
2、开启 Hystrix Dashboard
@EnableHystrixDashboard //Hystrix 控制台仪表盘（作用在Java类上）

3、浏览器访问 Hystrix Dashboard
http://192.168.1.50:8083/hystrix


二、SpringBoot2.0 优化了Hystrix Dashboard
1、需要注意的是在SpringBoot2.x版本Spring团队对原有的规则进行了修改：原来直接通过/hystrix.stream,在SpringBoot2.x版本需要加上/actuator，即/actuator/hystrix.stream。并且在原有的基础还需要添加一个servlet，直接在启动类加上以下代码即可：

    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }

2、浏览器访问：http://192.168.1.50:8083/actuator/hystrix.stream
结果：
ping: 
data: {"type":"HystrixCommand","name":"echoByFeign","group":"DiscoveryConsumerController","currentTime":1603794964144,"isCircuitBreakerOpen":false,"errorPercentage":0,"errorCount":0,"requestCount":0,"rollingCountBadRequests":0,"rollingCountCollapsedRequests":0,"rollingCountEmit":0,"rollingCountExceptionsThrown":0,"rollingCountFailure":0,"rollingCountFallbackEmit":0,"rollingCountFallbackFailure":0,"rollingCountFallbackMissing":0,"rollingCountFallbackRejection":0,"rollingCountFallbackSuccess":0,"rollingCountResponsesFromCache":0,"rollingCountSemaphoreRejected":0,"rollingCountShortCircuited":0,"rollingCountSuccess":0,"rollingCountThreadPoolRejected":0,"rollingCountTimeout":0,"currentConcurrentExecutionCount":0,"rollingMaxConcurrentExecutionCount":0,"latencyExecute_mean":0,"latencyExecute":{"0":0,"25":0,"50":0,"75":0,"90":0,"95":0,"99":0,"99.5":0,"100":0},"latencyTotal_mean":0,"latencyTotal":{"0":0,"25":0,"50":0,"75":0,"90":0,"95":0,"99":0,"99.5":0,"100":0},"propertyValue_circuitBreakerRequestVolumeThreshold":10,"propertyValue_circuitBreakerSleepWindowInMilliseconds":5000,"propertyValue_circuitBreakerErrorThresholdPercentage":10,"propertyValue_circuitBreakerForceOpen":false,"propertyValue_circuitBreakerForceClosed":false,"propertyValue_circuitBreakerEnabled":true,"propertyValue_executionIsolationStrategy":"SEMAPHORE","propertyValue_executionIsolationThreadTimeoutInMilliseconds":100,"propertyValue_executionTimeoutInMilliseconds":100,"propertyValue_executionIsolationThreadInterruptOnTimeout":true,"propertyValue_executionIsolationThreadPoolKeyOverride":null,"propertyValue_executionIsolationSemaphoreMaxConcurrentRequests":1,"propertyValue_fallbackIsolationSemaphoreMaxConcurrentRequests":1,"propertyValue_metricsRollingStatisticalWindowInMilliseconds":10000,"propertyValue_requestCacheEnabled":true,"propertyValue_requestLogEnabled":true,"reportingHosts":1,"threadPool":"DiscoveryConsumerController"}

3、PostMan测试100个请求：
http://{{host}}:8083/discovery/consumer/echo/test123