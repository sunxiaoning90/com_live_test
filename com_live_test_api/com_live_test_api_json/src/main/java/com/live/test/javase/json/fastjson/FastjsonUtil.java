package com.live.test.javase.json.fastjson;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
/**
 *  Fastjson 工具
 *  Fastjson是一个Java语言编写的高性能功能完善的JSON库，由阿里巴巴公司团队开发的
 * @author live
 * @2019年12月3日 @上午11:48:16
 */
public class FastjsonUtil {

	/**
	 * json 转 字符串
	 * @param map
	 * @return
	 */
	public static String json2String(Map map) {
		String str = JSONObject.toJSONString(map);
		return str;
	}
	
	/**
	 * json 转 字符串
	 * @param map
	 * @return
	 */
	public static String json2String2(Map map) {
		String str = JSON.toJSONString(map);
		return str;
	}
}
