package com.live.test.api.core.beanUtil.apacheBeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import org.apache.commons.beanutils.BeanUtils;

/**
 * <pre>
  1、BeanUtils（org.apache.commons.beanutils.BeanUtils） 简介
  BeanUtils工具是Apache Commons组件的成员之一，主要用于简化JavaBean封装数据的操作。
  Apache Common BeanUtil是一个常用的在对象之间复制数据的工具类，著名的web开发框架struts就是依赖于它进行ActionForm的创建。
  
  2、BeanUtils 优缺点
   优点：BeanUtils给对象封装参数的时候会进行类型自动转换。
   缺点：性能低 （像阿里巴巴的代码扫描插件，都禁止用该类）
 * </pre>
 * 
 * @author live
 */
public class BeanUtilTest {

	public static void main(String[] args) {
		// 原始对象
		Persion p = new Persion();
		System.out.println(p);

		// 待填充的对象
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", "live");

		// 填充
		try {
			BeanUtils.copyProperties(p, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		System.out.println(p);
	}

}
