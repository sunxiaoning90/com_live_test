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

Sentinel控制台启动：
 java -Dserver.port=8088 -Dcsp.sentinel.dashboard.server=localhost:8088 -Dproject.name=sentinel-dashboard -jar /opt/sca/sentinel-dashboard/Sentinel-1.7.2/sentinel-dashboard/target/sentinel-dashboard.jar

二、详解

首先准备服务消费者（端口8080）
1、添加依赖。
<!-- 引入 Sentinel 依赖 -->
		<dependency>
			<groupId>com.alibaba.csp</groupId>
			<artifactId>sentinel-core</artifactId>
			<version>1.7.2</version>
		</dependency>
		
2、bootstrap.properties 配置 Nacos
server.port=8081

spring.application.name=service-consumer

# nacos 作为注册中心
spring.cloud.nacos.discovery.server-addr=192.168.1.x:8848

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
	 * helloWorld() 方法就成了一个资源。注意注解支持模块需要配合 Spring AOP 或者 AspectJ 一起使用。
	 */
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
每秒正常服务1个请求，超出的请求被拒绝了：
hello world
blocked!
hello world
hello world
blocked!