package com.live.test.designpattern.create.singleton.staticinnerclass.pro;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//import com.alibaba.fastjson.JSONObject;

/**
 * 单例
 * 管理路由访问记录
 * @author live
 * @2019年12月3日 @上午11:01:56
 */
public class RouterAccessManager {

	/**
	 * 路由访问记录: k:v键值对
	 * k:路由+"."+容器id
	 * v:访问次数
	 */
	private Map<String, Integer> routerAccessMap = new ConcurrentHashMap<String, Integer>();
	
	private RouterAccessManager() {
	}

	public static RouterAccessManager getInstance() {
		return SingletonHolder.instance;
	}
	
	private static class SingletonHolder {
		private static RouterAccessManager instance = new RouterAccessManager();
	}
	
	public void increment(String key) {
		Integer count = 0;
		if (this.routerAccessMap.containsKey(key)) {
			count = this.routerAccessMap.get(key) + 1;
		}
		this.routerAccessMap.put(key, count);
	}

	/*public String toJson() {
		return JSONObject.toJSONString(this.routerAccessMap);
	}*/
}
