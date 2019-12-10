package com.live.test.api.core.serverloadbalance.routeAcc;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 路由访问 对象
 * 
 * @author live
 * @2019年12月9日 @下午2:46:59
 */
public class RouteAcc {
	private Map<String, RouteNodeAcc> accMap;// route:routeNodeAcc
	private String route;// 路由 module.service.method
	private String keepCountRouteNode;// 连续命中最大值是哪个路由节点 module.service.method.server
	private long keepCount;// 连续命中最大值
	private long count;// 命中总数
	private String last;// 最后一次命中哪个路由节点:module.service.method.server

	private RouteAcc() {
		this.accMap = new ConcurrentHashMap<String, RouteNodeAcc>();
	}

	public RouteAcc(String route) {
		this();
		this.setRoute(route);
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String abc) {
		this.route = abc;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public void put(String route) {
		this.incrementCount();

		RouteNodeAcc acc;
		if (!this.accMap.containsKey(route)) {
			acc = new RouteNodeAcc(route);
			this.accMap.put(route, acc);
		} else {
			acc = this.accMap.get(route);
		}
		acc.incrementCount();

		if (route.equals(this.getLast())) {
			acc.incrementKeepCountTmp();
		} else {
			this.setLast(route);// 设置最后一次访问
			accMap.get(route).setKeepCountTmpReset();
		}

		if (acc.getKeepCount() > this.getKeepCount()) {// 设置命中最大值
			this.setKeepCountAbcd(acc.getRouteNode());
			this.setKeepCount(acc.getKeepCount());
		}
	}

	public String getKeepCountRouteNode() {
		return keepCountRouteNode;
	}

	public void setKeepCountAbcd(String keepCount) {
		this.keepCountRouteNode = keepCount;
	}

	public long getKeepCount() {
		return keepCount;
	}

	public void setKeepCount(long keepCount) {
		this.keepCount = keepCount;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public void incrementCount() {
		this.setCount(this.getCount() + 1);
	}

	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("route(路由)", this.getRoute());
		json.put("count(命中总数)", this.getCount());
		json.put("keepCountRouteNode(最高连续命中路由节点)", this.getKeepCountRouteNode());
		json.put("keepCount(最高连续命中计数器)", this.getKeepCount());

		JSONArray accArray = new JSONArray();
		Iterator<Entry<String, RouteNodeAcc>> iterator = this.accMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, RouteNodeAcc> next = iterator.next();
			JSONObject accJson = new JSONObject();
			accJson.put(next.getKey(), next.getValue().toJson());
			accArray.add(accJson);
		}
		json.put("routeNodeAcc", accArray);

		return json;
	}
}
