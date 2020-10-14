微服务 开发笔记：
SpringCloudAlibaba

Nacos：配置中心 & 注册中心
	1、com_live_test_MicroService_SpringCloudAlibaba_nacos_SpringBoot_config	
		//Nacos：SpringBoot项目 使用Nacos 做为 配置管理中心
		
			一、使用流程简介
			1）application.yml 配置 Nacos的地址（nacos:   config:  server-addr）
			2.1）Java程序 指定Nacos的 dataId（@NacosPropertySource(dataId = "example", autoRefreshed = true)）
			2.2）Java程序 获取某个配置值@NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
	
	
	2、com_live_test_MicroService_SpringCloudAlibaba_nacos_SpringBoot_discovery	
		//Nacos:	SpringBoot项目 使用Nacos 做为 服务发现中心
		
			一、使用流程简介
			1）application.yml 配置 Nacos的地址（nacos:   config:  server-addr）
			2）Java程序 使用@NacosInjected注解注入 Nacos的 注册中心对象 NamingService
				@NacosInjected
				private NamingService namingService;
			3）增减服务，注册中心可以感知，健康检查默认5秒一次，默认3次均失败则剔除该服务
		
	3、com_live_test_MicroService_SpringCloudAlibaba_nacos_SpringCloud_config
		//Nacos：Spring Cloud项目 使用Nacos 做为 配置管理中心
		
			一、使用流程简介
			通过 Nacos Server 和 spring-cloud-starter-alibaba-nacos-config 实现配置的动态变更。
			
			1）bootstrap.properties 
				1.1）配置application名：spring.application.name=example#之所以需要配置 spring.application.name ，是因为它是构成 Nacos 配置管理 dataId字段的一部分。
				
				1.2）配置 Nacos的地址（nacos:   config:  server-addr）
				spring.cloud.nacos.config.server-addr=192.168.1.52:8848
				spring.cloud.nacos.config.group=DEFAULT_GROUP
				#指定配置的后缀，支持 properties、yaml、yml，默认为 properties
				spring.cloud.nacos.config.file-extension=properties
	
			2）Java程序  使用@Value 或 @NacosValue 注解为字段赋值，并开启自动更新：
				//	@NacosValue(value = "${useLocalCache:true}", autoRefreshed = true) //经测试boolean 并没有自动刷新？
				@Value("${useLocalCache:false}") //经测试boolean 自动刷新
	
			注意：Java程序 不需要指定Nacos的 dataId，在配置文件中已经指定
		
	4、com_live_test_MicroService_SpringCloudAlibaba_nacos_SpringCloud_discovery_provider
		//Nacos：Spring Cloud项目 使用Nacos 做为 注册中心（服务提供）

			一、简介
			
			《通过 Nacos Server 和 spring-cloud-starter-alibaba-nacos-discovery 实现 服务提供》
			
			1）bootstrap.properties 配置 Nacos 注册中心、指定本应用名
			server.port=8081
			
			spring.application.name=service-provider
			
			# nacos 作为注册中心
			spring.cloud.nacos.discovery.server-addr=192.168.1.x:8848
			
			2）通过 Nacos 的 @EnableDiscoveryClient 注解 开启服务发现
			@Component
			@RestController
			@RequestMapping("discovery/provider")
			@EnableDiscoveryClient
			public class DiscoveryProviderController {
			
				@RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
				public String echo(@PathVariable String str) {
					System.out.println(str);
					return "hello" + str;
				}
			
			}

		5、com_live_test_MicroService_SpringCloudAlibaba_nacos_SpringCloud_discovery_consumer
			//Nacos：Spring Cloud项目 使用Nacos 做为 注册中心（服务消费）

				一、简介
				
				《通过 Nacos Server 和 spring-cloud-starter-alibaba-nacos-discovery 实现 服务发现 - 服务消费》
				
				1）bootstrap.properties 配置 Nacos
				server.port=8081
				
				spring.application.name=service-consumer
				
				# nacos 作为注册中心
				spring.cloud.nacos.discovery.server-addr=192.168.1.x:8848
				
				2）通过 Nacos 的 @EnableDiscoveryClient 注解 开启服务发现
				
				3）暂时使用 RestTemplate进行远程服务调用
				return restTemplate.getForObject("http://service-provider/discovery/provider/echo/" + str, String.class);


Ribbon：负载均衡
	1.准备远程服务，供Ribbon负载均衡调用测试
	1.1、com_live_test_MicroService_SpringCloudAlibaba_ribbon_SpringCloud_provider1
		ribbon：Spring Cloud项目 使用ribbon 实现负载均衡（使用Nacos 做为 注册中心（服务提供1-服务：service-provider，端口：8081））
		
	1.2、com_live_test_MicroService_SpringCloudAlibaba_ribbon_SpringCloud_provider2
		ribbon：Spring Cloud项目 使用ribbon 实现负载均衡（使用Nacos 做为 注册中心（服务提供2-服务：service-provider，端口：8082））
	
	2、	ribbon：Spring Cloud项目 使用ribbon 实现负载均衡（@LoadBalanced）（使用Nacos 做为 注册中心（服务消费））

	一、简介
	nocas 默认集成了ribbon（负载均衡，使用@LoadBance即可）
	
	ribbon原理：拦截器:为RestTemplate增加了@LoanBalanced 注解后，实际上通过配置，为RestTemplate注入负载均衡拦截器，让负载均衡器选择根据其对应的策略选择合适的服务后，再发送请求。
	
	《通过 Nacos Server 和 spring-cloud-starter-alibaba-nacos-discovery 实现 服务发现 - 服务消费 - ribbon负载均衡》
	
	1)	通过 Spring Cloud 原生注解 @EnableDiscoveryClient 开启服务注册发现功能。给 RestTemplate 实例添加 @LoadBalanced 注解，开启 @LoadBalanced 与 Ribbon 的集成：
	@SpringBootApplication
	@ComponentScan(value = { "com.live.test.javaee.springboot.*" })
	public class App {
	
	//	通过 Spring Cloud 原生注解 @EnableDiscoveryClient 开启服务注册发现功能。给 RestTemplate 实例添加 @LoadBalanced 注解，开启 @LoadBalanced 与 Ribbon 的集成：
		@LoadBalanced
		@Bean
		public RestTemplate restTemplate() {
			return new RestTemplate();
		}
	
		public static void main(String[] args) {
			ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
			ConfigController bean = context.getBean(ConfigController.class);
			System.out.println("尝试获取bean：" + bean);
		}
	}
	
	2)	通过 Nacos 的 @EnableDiscoveryClient 注解 开启服务发现
	@Component
	@RestController
	@RequestMapping("discovery/consumer")
	@EnableDiscoveryClient
	public class DiscoveryConsumerController {
	
	//	@NacosInjected
	//	@Autowired
	//	private NamingService namingService;
		private final RestTemplate restTemplate;
	
		@Autowired
		public DiscoveryConsumerController(RestTemplate restTemplate) {
			this.restTemplate = restTemplate;
		}
	
		@RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
		public String echo(@PathVariable String str) {
			return restTemplate.getForObject("http://service-provider/discovery/provider/echo/" + str, String.class);
		}
	}
	
OpenFeign/Feign：服务接口调用
	1、com_live_test_MicroService_SpringCloudAlibaba_feign_provider
		feign：Spring Cloud项目 使用 feign 实现远程服务接口调用（使用Nacos 做为 注册中心（服务提供-服务：service-provider，端口：8082））
	
	2、com_live_test_MicroService_SpringCloudAlibaba_feign_consumer
		OpenFeign/Feign：Spring Cloud项目 使用feign 实现远程服务接口调用(@FeignClient)

			一、简介
			SpringCloud 通过 OpenFeign/Feign 服务调用方式
			OpenFeign：类似于Dubbo，像调用本地方法一样调用远程服务
			feign原理：代理.
			ribbon原理：拦截器:为RestTemplate增加了@LoanBalanced 注解后，实际上通过配置，为RestTemplate注入负载均衡拦截器，让负载均衡器选择根据其对应的策略选择合适的服务后，再发送请求。
			
			在微服务架构开发时，常常会在一个项目中调用其他服务，其实使用Spring Cloud Ribbon就能实现这个需求，利用RestTemplate 的请求拦截来实现对依赖服务的接口调用， 但是实际项目中对服务依赖的调用可能不止于 一 处，往往 一 个接口会被多处调用，所以我们通常都会针对各个微服务自行封装 一 些客户端类来包装这些依赖服务的调用。 这个时候我们会发现，由于 RestTemplate 的封装，几乎每 一 个调用都是简单的模板化内容。
			
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
				
Sentinel：限流/降级/熔断
	1、com_live_test_MicroService_SpringCloudAlibaba_sentinel_provider
		该项目仅仅负责提供服务，一般限流/降级/熔断 控制在客户端（服务消费端），不在服务提供端做任何支持
	
	2、com_live_test_MicroService_SpringCloudAlibaba_sentinel_consumer
		Sentinel：Spring Cloud项目 使用 Sentinel实现 限流/降级/熔断

		一、简介
		一般 限流/降级/熔断 控制在客户端（服务消费端），不在服务提供端做任何支持
		
		1）准备资源限流规则 FlowRule
			/**
			 * 使用代码设置sentinel，也可以在sentinel-Dashboard控制台设置
			 */
			// @RequestMapping("/initFlowRules")
			private void initFlowRules() {
				List<FlowRule> rules = new ArrayList<>();
				FlowRule rule = new FlowRule();
				rule.setResource("HelloWorld");
				rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
				// Set limit QPS to 20.
		//		rule.setCount(20);
				rule.setCount(1);
				rules.add(rule);
				FlowRuleManager.loadRules(rules);
			}
			
		2）为某个资源设置准备好的规则	：@SentinelResource("xxx")
			@SentinelResource("HelloWorld")
				@RequestMapping("/helloWorld")
				public String helloWorld() {
			
					// 1.5.0 版本开始可以直接利用 try-with-resources 特性，自动 exit entry
					try (Entry entry = SphU.entry("HelloWorld")) {
						// 被保护的逻辑
						System.out.println("hello world");
						return "hello world";
					} catch (BlockException ex) {
						// 处理被流控的逻辑
						System.out.println("blocked!");
					}
			
					return "blocked";
				}
				
		3）访问被限流的请求
	
GateWay：微服务网关

1、com_live_test_MicroService_SpringCloudAlibaba_gateway_byConfigFile
Gateway：Spring Cloud项目使用Gateway作为 微服务网关 ,实现服务拦截、转发 (通过配置文件的方式)

一、简介

1）引入依赖 spring-cloud-starter-gateway
2）配置application.yml
cloud:
    gateway:
      discovery: 
        locator: 
              #开启以服务id去注册中心上获取转发地址
            enabled: true
            
        #路由策略
      routes:
        - id: gateway-service1
            #转发到目的地址 http://realUrl.userApi001.com
          uri: http://realUrl.userApi001.com
          #uri: https://www.baidu.com/
            #匹配规则
          predicates:
            - Path=/userApi/**
            ## 最终拦截效果 http://localhost:8081/userApi  转发到 http://realUrl.userApi001.com
 3、测试
 浏览器访问：http://192.168.1.50:8081/userApi/get?id=1
 跳转到了           http://realUrl.userApi001.com/userApi/get?id=1

2、com_live_test_MicroService_SpringCloudAlibaba_gateway_byConfigFile_withNacos
Gateway：Spring Cloud项目使用Gateway作为 微服务网关 ,实现服务拦截、转发 (通过配置文件的方式) --整合Nacos

一、简介

在 《Spring Cloud项目使用Gateway作为 微服务网关 ,实现服务拦截、转发 (通过配置文件的方式)》基础上，整合Nacos，实现服务负载均衡

1）配置 pom， 
	新增 Nacos依赖
2）配置 application.yml
	 #注意：转发的目的地址 从 Nacos 注册中心 获取
          uri: lb://service-provider
            # filters不可少
          filters:
            - StripPrefix=1


filex
服务注册和服务发现-Eureka
负载均衡-Ribbon的使用
声明式REST客户端-Feign的使用
断路器-Hystrix的认识
