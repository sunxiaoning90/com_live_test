待
netflix
服务注册和服务发现-Eureka
负载均衡-Ribbon的使用
声明式REST客户端-Feign的使用
断路器-Hystrix的认识

live:
1、服务注册 http：//服务名/具体请求方法

live:
2、nocas 默认集成了ribbon（负载均衡，使用@LoadBance即可）

live:
3、openfine：类似于dubbo，像调用本地方法一样调用远程服务


feign原理：代理.
ribbon原理：拦截器:为RestTemplate增加了@LoanBalanced 注解后，实际上通过配置，为RestTemplate注入负载均衡拦截器，让负载均衡器选择根据其对应的策略选择合适的服务后，再发送请求。

live:
限流http://21349ee4.wiz03.com/wapp/pages/view/share/s/0xd9XA3_U4q92z2wFv30iNbH0C1ojY35EQXD2s0-Hn2VjzR_

live:
sata：golableTruncation

live:
！im 网关：前台

live:
copyonwrite：写时复制

1、亿级流量电商网站微服务架构详解
2、Nacos注册中心实现电商微服务拆分实战
3、Ribbon多实例服务负载均衡调用实战
4、Sentinel服务高可用限流熔断降级实战
5、微服务架构下的分布式事务Seata实战
6、路由&限流&安全认证网关Gateway实战
7、Nacos分布式配置中心详解
8、微服务调用链路追踪Pinpoint详解
9、微服务自动化监控Prometheus&Grafana详解
10、阿里京东后端微服务中台架构实战