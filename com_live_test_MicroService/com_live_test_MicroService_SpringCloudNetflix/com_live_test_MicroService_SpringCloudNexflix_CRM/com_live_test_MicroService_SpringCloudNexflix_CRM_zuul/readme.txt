Spring Cloud Netfix 项目： 使用 Zull 实现 微服务网关(动态路由，自动刷新，java类实现RefreshableRouteLocator并重写locateRoutes方法）


一、简介
动态配置路由规则：
Zuul会自动定时刷新路由规则（实现RefreshableRouteLocator）。

/**
 * 自定义了 三种路由规则
 * url // 路由01:	http://{{host}}:8088/testToGithub/ ==> https://github.com
 * url //路由02:	http://{{host}}:8088/testToProvider2/echo/1 ==》 http://192.168.1.50:8088/zuulTest/echo/1
 * serviceId 负载均衡//路由03:	http://{{host}}:8088/testToProvider3/zuulTest/echo/1 ==》 http://192.168.1.50:8088/zuulTest/echo/1
 */

我们会经常添加一些新的路由规则，每次静态添加不仅多而且麻烦，还会重新启动网关，这时就需要动态配置路由规则了，可以使用代码实现。

在zuul中，默认使用的路径类是：SimpleRouteLocator.java

在它的bean配置类：ZuulServerAutoConfiguration.java中是这样配置的

	@Bean
    @ConditionalOnMissingBean(SimpleRouteLocator.class)
    public SimpleRouteLocator simpleRouteLocator() {
        return new SimpleRouteLocator(this.server.getServlet().getServletPrefix(),
                this.zuulProperties);
    }
它表示当没有此类型SimpleRouteLocator.class的实现时，使用这个bean,所以我们要实现自己的路由配置，只需要重新实现相关的方法即可。

二、详解
1、配置pom依赖
		
		<!-- zuul -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-zuul</artifactId>
		</dependency>
		
2）配置application.yml

server:
  port: 8088
    
zuul:  略

3）Java类

启动类开启 Zuul ：@EnableZuulProxy

3.1）
@Configuration
public class BeanConfig {
    @Autowired
    ZuulProperties zuulProperties;
    
    @Autowired
    ServerProperties server;

    @Bean
    public LogServerRouteLocator getRouteLocator() {
        return new LogServerRouteLocator(this.server.getServlet().getServletPrefix(), this.zuulProperties);
    }
}

3.2）public class LogServerRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {
	@Autowired
	private RouteConfigService routeConfigService;

	public LogServerRouteLocator(String servletPath, ZuulProperties properties) {
		super(servletPath, properties);

	}

	@Override
	public void refresh() {
		doRefresh();
	}

	/**
	 * 重写
	 */
	@Override
	protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
		// 重新定义一个路由映射map
		LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<>();

		// 把父类中的映射继承下来，它主要是从配置文件中取的映射。
		routesMap.putAll(super.locateRoutes());

		// 这里的路由信息来自于配置文件
		for (Map.Entry<String, ZuulRoute> entry : routeConfigService.getRoutes().entrySet()) {
			ZuulRoute route = entry.getValue();

			// 路由01:	http://{{host}}:8088/testToGithub/ ==> https://github.com
			String id = route.getId();
			String path = route.getPath();
			String url = route.getUrl();
			// String serviceId = route.getServiceId();

			ZuulRoute zuulRoute1 = new ZuulRoute();
			zuulRoute1.setId(id);
			zuulRoute1.setPath(path);
			zuulRoute1.setUrl(url);
			// zuulRoute1.setServiceId(serviceId);
			routesMap.put(zuulRoute1.getPath(), zuulRoute1);
		}

		//路由02:	http://{{host}}:8088/testToProvider2/echo/1 ==》 http://192.168.1.50:8088/zuulTest/echo/1
		ZuulRoute zuulRoute2 = new ZuulRoute();
		zuulRoute2.setId("id02");
		zuulRoute2.setPath("/testToProvider2/**");

		// 服务提供方：指定一个固定的服务提供方
		zuulRoute2.setUrl("http://192.168.1.50:8088/zuulTest/");
//		zuulRoute2.setServiceId("api-gateway-byJavaAndAnnotation");

		routesMap.put(zuulRoute2.getPath(), zuulRoute2);
		
		//路由03:	http://{{host}}:8088/testToProvider3/zuulTest/echo/1 ==》 http://192.168.1.50:8088/zuulTest/echo/1
		ZuulRoute zuulRoute3 = new ZuulRoute();
		zuulRoute3.setId("id03");
		zuulRoute3.setPath("/testToProvider3/**");
		
		// 服务提供方：负载均衡 即spring.application.name
		zuulRoute3.setServiceId("api-gateway-byJavaAndAnnotation");
		
		routesMap.put(zuulRoute3.getPath(), zuulRoute3);

		return routesMap;
	}
}

3、浏览器访问 
http://{{host}}:8088/testToGithub/  ==> https://github.com

http://{{host}}:8088/testToProvider2/echo/1 ==》 http://192.168.1.50:8088/zuulTest/echo/1
遇到的问题：
1、Forwarding error