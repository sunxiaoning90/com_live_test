package com.live.test.javaee.springboot.zuulRouteConfig;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.stereotype.Service;

@Service
public class RouteConfigService {

	public Map<String, ZuulRoute> getRoutes() {
		System.out.println("模拟请求配置中心...");
		
		Map<String, ZuulRoute> routes = new HashMap<String, ZuulRoute>();

		ZuulRoute zuulRoute = new ZuulRoute();
		zuulRoute.setId("id001");
		zuulRoute.setPath("/testToGithub/**");
		zuulRoute.setUrl("https://github.com/");

		routes.put(zuulRoute.getId(), zuulRoute);
		return routes;
	}

}
