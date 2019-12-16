package com.live.test.api.core.flowstatistics.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;

public class FlatData {
	private Map<String, Object> data = new HashMap<String, Object>();
	public Object get(String key) {
		return data.get(key);
	}
	public void set(String key, Object value) {
		data.put(key, value);
	}
	private void exchange(String key, int number) {
		Object value = data.get(key);
		if (value == null)
			value = 0;
		else if (!value.getClass().equals(Integer.class))
			return;
		data.put(key, ((Integer)value)+number);
	}
	private void exchange(Object key, int number, String node) {
		if (key.getClass().isArray()) {
			String[] keys = (String[]) key;
			for (String string : keys) {
				String finalKey;
				if (node == null)
					finalKey = string;
				else
					finalKey = node.concat(".").concat(string);
				exchange(finalKey, number);
			}
		} else {
			if (node == null)
				exchange((String)key, number);
			else
				exchange(node.concat(".").concat((String)key), number);
		}
	}
	public synchronized void increase(Object key, String node) {
		exchange(key, 1, node);
	}
	public void increase(Object key) {
		increase(key, null);
	}
	public synchronized void decrease(Object key, String node) {
		exchange(key, -1, node);
	}
	public void decrease(Object key) {
		decrease(key, null);
	}
	public String getFlatJson() {
		Gson gson = new Gson();
		return gson.toJson(data);
	}
	public String getJson() {
		TreeData td = new TreeData();
		for (Entry<String, Object> entry : data.entrySet()) {
			td.setValue(entry.getKey(), entry.getValue());
		}
		return td.getJson();
	}
	public static void main(String[] args) {
		FlatData pojo = new FlatData(); 
		pojo.exchange("totalNumber", 23);
		
		pojo.set("项目名称", "测试");
		pojo.set("开始时间", "2018-09-04 10:00:00");
		pojo.increase("status.busy");
		pojo.increase("status.connected");
		pojo.increase("status.connected");
		pojo.increase("status.connected");
		pojo.increase("status.connected");
		pojo.increase("status.outservice");
		pojo.increase("status.busy");
		
		
		pojo.decrease("status.lll");
		
//		System.out.println(pojo.get("status.busy"));
//		System.out.println(pojo.getFlatJson());
		
	}
}