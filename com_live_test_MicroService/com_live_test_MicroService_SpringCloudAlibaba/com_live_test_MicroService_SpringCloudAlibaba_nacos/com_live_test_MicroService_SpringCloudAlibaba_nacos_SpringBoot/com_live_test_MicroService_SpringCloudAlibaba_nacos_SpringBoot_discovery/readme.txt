《SpringBoot 使用Nacos 启动【服务发现】》
1、添加依赖。
<dependency>
    <groupId>com.alibaba.boot</groupId>
    <artifactId>nacos-discovery-spring-boot-starter</artifactId>
    <version>${latest.version}</version>
</dependency>
注意：版本 0.2.x.RELEASE 对应的是 Spring Boot 2.x 版本，版本 0.1.x.RELEASE 对应的是 Spring Boot 1.x 版本。

2、在 application.properties 中配置 Nacos server 的地址：
nacos.discovery.server-addr=127.0.0.1:8848
使用 @NacosInjected 注入 Nacos 的 NamingService 实例：
@Controller
@RequestMapping("discovery")
public class DiscoveryController {

    @NacosInjected
    private NamingService namingService;

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public List<Instance> get(@RequestParam String serviceName) throws NacosException {
        return namingService.getAllInstances(serviceName);
    }
}

@SpringBootApplication
public class NacosDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosDiscoveryApplication.class, args);
    }
}
3、启动 NacosDiscoveryApplication，调用 curl http://localhost:8080/discovery/get?serviceName=example，此时返回为空 JSON 数组:
[]。

4、通过调用 Nacos Open API 向 Nacos server 注册一个名称为 example 服务
curl -X PUT 'http://127.0.0.1:8848/nacos/v1/ns/instance?serviceName=example&ip=127.0.0.1&port=8080'

5、再次访问 curl http://localhost:8080/discovery/get?serviceName=example，此时返回内容为：
[
    {
        "instanceId": "127.0.0.1#8080#DEFAULT#DEFAULT_GROUP@@example",
        "ip": "127.0.0.1",
        "port": 8080,
        "weight": 1.0,
        "healthy": true,
        "enabled": true,
        "ephemeral": true,
        "clusterName": "DEFAULT",
        "serviceName": "DEFAULT_GROUP@@example",
        "metadata": {},
        "instanceHeartBeatInterval": 5000,
        "instanceHeartBeatTimeOut": 15000,
        "ipDeleteTimeout": 30000,
        "instanceIdGenerator": "simple"
    }
]
