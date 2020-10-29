package com.live.test.javaee.springboot.zuulRouteConfig;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
/**
 * 自定义了 三种路由规则
 * url // 路由01:	http://{{host}}:8088/testToGithub/ ==> https://github.com
 * url //路由02:	http://{{host}}:8088/testToProvider2/echo/1 ==》 http://192.168.1.50:8088/zuulTest/echo/1
 * serviceId 负载均衡//路由03:	http://{{host}}:8088/testToProvider3/zuulTest/echo/1 ==》 http://192.168.1.50:8088/zuulTest/echo/1
 * @author live
 */
public class LogServerRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {
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
