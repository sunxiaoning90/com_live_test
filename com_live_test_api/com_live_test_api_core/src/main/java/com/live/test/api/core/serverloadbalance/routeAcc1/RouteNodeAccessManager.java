package com.live.test.api.core.serverloadbalance.routeAcc1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSONObject;

/**
 * 路由节点访问记录,简易版本:eg:{routeNode1:1,routeNode2:10}
 * @author live
 * @2019年12月3日 @上午11:01:56
 */
public class RouteNodeAccessManager {

	/**
	 * 路由访问记录: k:v键值对
	 * k:路由+"."+容器id
	 * v:访问次数
	 */
	private Map<String, Integer> routerAccessMap = new ConcurrentHashMap<String, Integer>();
	
	private RouteNodeAccessManager() {
	}

	public static RouteNodeAccessManager getInstance() {
		return SingletonHolder.instance;
	}
	
	private static class SingletonHolder {
		private static RouteNodeAccessManager instance = new RouteNodeAccessManager();
	}
	
	
	public void increment(String key) {
		Integer count = 0;
		if (this.routerAccessMap.containsKey(key)) {
			count = this.routerAccessMap.get(key) + 1;
		}
		this.routerAccessMap.put(key, count);
	}

	public String toJson() {
		return JSONObject.toJSONString(this.routerAccessMap);
	}
}
