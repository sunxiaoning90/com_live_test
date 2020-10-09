Nacos：SpringBoot项目 使用Nacos 做为 配置管理中心

一、使用流程简介
1）application.yum 配置 Nacos的地址（nacos:   config:  server-addr）
2.1）Java程序 指定Nacos的 dataId（@NacosPropertySource(dataId = "example", autoRefreshed = true)）
2.2）Java程序 获取某个配置值@NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)

二、使用流程详解
1、添加依赖。
<dependency>
    <groupId>com.alibaba.boot</groupId>
    <artifactId>nacos-config-spring-boot-starter</artifactId>
    <version>${latest.version}</version>
</dependency>
注意：版本 0.2.x.RELEASE 对应的是 Spring Boot 2.x 版本，版本 0.1.x.RELEASE 对应的是 Spring Boot 1.x 版本。

2、在 application.properties 中配置 Nacos server 的地址：
nacos.config.server-addr=127.0.0.1:8848

3、使用 @NacosPropertySource 加载 dataId 为 example 的配置源，并开启自动更新：
@SpringBootApplication 
@ComponentScan(value = {"com.live.test.javaee.springboot.*" })
@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class App {
 public static void main(String[] args) {
//	SpringApplication.run(HelloSpringBoot.class, args);
  ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
  ConfigController bean = context.getBean(ConfigController.class);
  System.out.println(bean);
 }
}

4、通过 Nacos 的 @NacosValue 注解设置属性值。

@Component
@Controller
@RequestMapping("config")
public class ConfigController {

 // useLocalCache=true
 @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true) //@NacosValue，是nacos提供的注解，支持自动刷新
//	@Value(value="${useLocalCache:false}") //@Value 是Spring提供的， 不支持自动刷新
 private boolean useLocalCache;

 @RequestMapping(value = "/get", method = GET)
 @ResponseBody
 public boolean get() {
  return useLocalCache;
 }

}
5、启动 NacosConfigApplication，调用 curl http://localhost:8080/config/get，返回内容是 false。

6、通过调用 Nacos Open API 向 Nacos server 发布配置：dataId 为example，内容为useLocalCache=true
curl -X POST "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=example&group=DEFAULT_GROUP&content=useLocalCache=true"\

7、再次访问 http://localhost:8080/config/get，此时返回内容为true，说明程序中的useLocalCache值已经被动态更新了。