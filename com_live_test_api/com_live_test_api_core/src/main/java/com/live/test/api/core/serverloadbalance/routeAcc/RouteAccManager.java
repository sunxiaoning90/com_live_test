package com.live.test.api.core.serverloadbalance.routeAcc;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 路由访问管理
 * 
 * @author live
 * @2019年12月3日 @上午11:01:56
 */
public class RouteAccManager {

	/**
	 * 路由访问记录: k:v键值对 route: routeAcc
	 */
	private Map<String, RouteAcc> accPoMap = new ConcurrentHashMap<String, RouteAcc>();
	private Date date;

	private RouteAccManager() {
		setDate(new Date());
	}

	public static RouteAccManager getInstance() {
		return SingletonHolder.instance;
	}

	private static class SingletonHolder {
		private static RouteAccManager instance = new RouteAccManager();
	}

	public void put(String routeNode) {
		String route = routeNode.substring(0, routeNode.lastIndexOf("."));

		if (!this.accPoMap.containsKey(route)) {
			this.accPoMap.put(route, new RouteAcc(route));
		}

		RouteAcc accPo = this.accPoMap.get(route);
		accPo.put(routeNode);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@SuppressWarnings("deprecation")
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("date(开始时间)", this.getDate().toLocaleString());
		
		JSONArray routeAccArray = new JSONArray();
		Iterator<Entry<String, RouteAcc>> iterator = this.accPoMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, RouteAcc> next = iterator.next();
			JSONObject routeAcc = new JSONObject();
			routeAcc.put(next.getKey(), next.getValue().toJson());
			routeAccArray.add(routeAcc);
		}

		json.put("routeAccs", routeAccArray);
		return json;
	}
}
