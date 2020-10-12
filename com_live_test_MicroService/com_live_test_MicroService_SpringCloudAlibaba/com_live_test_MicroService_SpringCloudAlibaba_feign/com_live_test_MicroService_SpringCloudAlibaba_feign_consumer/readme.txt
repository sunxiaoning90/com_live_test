feign：Spring Cloud项目 使用feign 实现远程服务接口调用(@FeignClient)

一、简介
SpringCloud 通过 Feign 服务调用方式

在微服务架构开发是，我们常常会在一个项目中调用其他服务，其实使用Spring Cloud Rbbon就能实现这个需求，利用RestTemplate 的请求拦截来实现对依赖服务的接口调用， 但是实际项目中对服务依赖的调用可能不止于 一 处，往往 一 个接口会被多处调用，所以我们通常都会针对各个微服务自行封装 一 些客户端类来包装这些依赖服务的调用。 这个时候我们会发现，由于 RestTemplate 的封装，几乎每 一 个调用都是简单的模板化内容。

Spring Cloud Feign 在此基础上做了进 一 步封装，由它来帮助我们定义和实现依赖服务接口的定义。在 Spring Cloud Feign 的实现下， 我们只需创建 一 个接口并用注解（@FeignClient）的方式来配置它， 即可完成对服务提供方的接口绑定，简化了在使用 Spring Cloud Ribbon 时自行封装服务调用客户端的开发量。 

1）使用@FeignClient， 定义一个 服务接口 ：FeignTestServcie

/**
 * 定义接口
 * 调用远程服务
 * 
 * @author live
 */
@FeignClient(name = "service-provider")
public interface FeignTestServcie {

	@GetMapping("discovery/provider/echo/{str}")
//	String echo(@PathVariable String str) ; //此写法错误，必须使用value指定 @PathVariable(value="str"）String str
	String echo(@PathVariable(value = "str") String str);
//	{
//		return restTemplate.getForObject("http://service-provider/discovery/provider/echo/" + str, String.class);
//	}
}
2）注入后，直接调用 该Service 的方法
/**
	 * 通过 Feign 服务调用方式
	 */
	@Autowired
	FeignTestServcie service1;

	@RequestMapping(value = "/echoByFeign/{str}", method = RequestMethod.GET)
	public String echoByFeign(@PathVariable String str) {
		return service1.echo(str);
	}
	
二、详解
1、pom依赖
		<!-- feign -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-openfeign</artifactId>
		</dependency>
		
2、/**
 * 定义接口
 * 调用远程服务
 * 
 * @author live
 */
//@Component
@FeignClient(name = "service-provider")
public interface FeignTestServcie {

	@GetMapping("discovery/provider/echo/{str}")
//	String echo(@PathVariable String str) ; //此写法错误，必须使用value指定 @PathVariable(value="str"）String str
	String echo(@PathVariable(value = "str") String str);
//	{
//		return restTemplate.getForObject("http://service-provider/discovery/provider/echo/" + str, String.class);
//	}
}

3、Controller 使用 Feign客户端
1）@EnableFeignClients(basePackages="com.live.test.javaee.springboot.feign.service")
2）@Autowired
	FeignTestServcie service1;


@Component
@RestController
@RequestMapping("feign")
@EnableDiscoveryClient
//@EnableFeignClients
@EnableFeignClients(basePackages="com.live.test.javaee.springboot.feign.service")
public class FeignConsumerController {

	/**
	 * 通过 Feign 服务调用方式
	 */
	@Autowired
	FeignTestServcie service1;

	@RequestMapping(value = "/echoByFeign/{str}", method = RequestMethod.GET)
	public String echoByFeign(@PathVariable String str) {
		return service1.echo(str);
	}
	
测试：

http://localhost:8082/feign/echoByFeign/hello123