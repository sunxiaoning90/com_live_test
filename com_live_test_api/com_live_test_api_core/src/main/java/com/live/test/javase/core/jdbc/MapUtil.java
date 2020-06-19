package com.live.test.javase.core.jdbc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MapUtil {

	/**
	 * 将Map<String, String[]> 转为 Map<String, String>
	 * 规则：取String[] 的第一个值
	 * @return Map<String,String>
	 */
	public static Map<String, String> convertParameterMap(Map<String, String[]> parameterMap){
		Map<String, String> map = new HashMap<String, String>();
		
		Iterator<Entry<String, String[]>> iterator =parameterMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String[]> entry = iterator.next();
			map.put(entry.getKey(), entry.getValue()[0]);
		}
		
//		return map.size() == 0 ? null : map;
		return map;
	}

	/**
	 * 获取map中的值，如果value==null，则返回 空字符串
	 * @return String
	 */
	public static String getWrapperValue(Map<String, Object> map, String key) {
		if(map.containsKey(key)){
			return map.get(key) != null ?  map.get(key).toString() : "";
		}else{
			return null;
		}
	}
	
}
