《Sentinel 控制台》
一、准备服务消费者（端口8080）
1、添加依赖。
<!-- 引入 Sentinel 依赖 -->
			<dependency>
			<groupId>com.alibaba.csp</groupId>
			<artifactId>sentinel-core</artifactId>
			<version>1.7.2</version>
		</dependency>

<!-- 控制台 -->
		<!-- java -Dserver.port=8088 -Dcsp.sentinel.dashboard.server=localhost:8088 
			-Dproject.name=sentinel-dashboard -jar target/sentinel-dashboard.jar 上面其实是把控制台自己接入进来了，如果要接入我们实际工作中的项目，该怎么接入？ 
			客户端需要引入 Transport 模块来与 Sentinel 控制台进行通信。您可以通过 pom.xml 引入 JAR 包: 同样的增加JVM启动参数指定控制台的地址： 
			-Dcsp.sentinel.dashboard.server=localhost:8333 -->

		<dependency>
			<groupId>com.alibaba.csp</groupId>
			<artifactId>sentinel-transport-simple-http</artifactId>
			<version>1.4.1</version>
		</dependency>
		
2、bootstrap.properties 配置 Nacos
server.port=8081

spring.application.name=service-consumer

# nacos 作为注册中心
spring.cloud.nacos.discovery.server-addr=192.168.1.x:8848

#    配置sentinel客户端，注册进控制台里
spring.cloud.sentinel.transport.port=8089
spring.cloud.sentinel.transport.dashboard=192.168.1.52:8088

3、设置资源限流规则
@Component
@RestController
@RequestMapping("sentinelTest")
public class SentinelTestController {

	@PostConstruct
	private void postC() {
		initFlowRules(); // 临时初始化sentinel配置
	}

	/**
	 * helloWorld() 方法就成了我们的一个资源。注意注解支持模块需要配合 Spring AOP 或者 AspectJ 一起使用。
	 */
//	@SentinelResource("HelloWorld")
//	@RequestMapping("/helloWorld")
//	public String helloWorld() {
//		// 资源中的逻辑
//		System.out.println("hello world");
//		return "hello world";
//	}

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
}

测试：
http://xx/sentinelTest/helloWorld
每秒正常服务1个请求，超出的请求被拒绝了