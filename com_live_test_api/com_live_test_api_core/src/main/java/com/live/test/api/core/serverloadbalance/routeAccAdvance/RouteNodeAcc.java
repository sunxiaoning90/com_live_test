package com.live.test.api.core.serverloadbalance.routeAccAdvance;

import com.alibaba.fastjson.JSONObject;

/**
 * 路由节点访问 对象
 * 
 * @author live
 * @2019年12月10日 @下午3:26:43
 */
public class RouteNodeAcc {

	private String routeNode;// 路由节点 module.service.method.server
	private long keepCount;// 最高连续命中计数器
	private long count;// 命中总数数计数器
	private long keepCountTmp;// 临时连续命中计数器,当 临时连续命中计数器 大于 最高连续命中计数器时,刷新 最高连续命中计数器

	private RouteNodeAcc() {
	}

	public RouteNodeAcc(String routeNode) {
		this();
		this.setRouteNode(routeNode);
	}

	public String getRouteNode() {
		return routeNode;
	}

	public void setRouteNode(String routeNode) {
		this.routeNode = routeNode;
	}

	public long getKeepCountTmp() {
		return keepCountTmp;
	}

	public void setKeepCountTmp(long keepCountTmp) {
		this.keepCountTmp = keepCountTmp;
		if (keepCountTmp > this.getKeepCount()) {// 当 临时连续命中计数器 大于 最高连续命中计数器时,刷新 最高连续命中计数器
			this.setKeepCount(keepCountTmp);
		}
	}

	public void incrementCount() {
		this.setCount(this.getCount() + 1);
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

	public void setKeepCountTmpReset() {
		this.setKeepCountTmp(0);
	}

	public void incrementKeepCountTmp() {
		this.setKeepCountTmp(this.getKeepCountTmp() + 1);
	}

	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("routeNode(路由节点)", routeNode);
		json.put("count(命中总数数计数器)", count);
		json.put("keepCountTmp(临时连续命中计数器)", keepCountTmp);
		json.put("keepCount(最高连续命中计数器)", keepCount);
		return json;
	}
}
