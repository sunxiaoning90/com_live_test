package com.live.test.api.core.reflect;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 反射
 * 将反射获取的 Method 缓存，提升效率
 * 
 * @author live
 * @2020年5月14日 @上午10:37:58
 */
public class ReflectUtilCache {

	/**
	 * 将反射获取的gettter setter缓存
	 */
	private static Map<String, Map<String, Method>> clazzMap = new HashMap<String, Map<String, Method>>(128);

	/**
	 * 获取 setter
	 * 
	 * @param clazz
	 * @param fieldname
	 * @return
	 */
	public static Method getMethodOfSetterByFieldname(Class<?> clazz, String fieldname) {
		String className = clazz.getName();
		Map<String, Method> methodMap = clazzMap.get(className);
		if (methodMap == null) {
			synchronized (ReflectUtilCache.class) {
				if (methodMap == null) {
					methodMap = new HashMap<String, Method>();
					clazzMap.put(className, methodMap);
				}
			}
		}

		if (!methodMap.containsKey(fieldname)) {// 防止缓存穿透，即使是null也返回
			return methodMap.get(fieldname);
		} else {
			Method method = ReflectUtil.getMethodOfGetterByPropertyDescriptor(clazz, fieldname);
			return methodMap.put(fieldname, method);
		}
	}

	/**
	 * 获取 getter
	 * 
	 * @param clazz
	 * @param fieldname
	 * @return
	 */
	public static Method getMethodOfGetterByFieldname(Class<?> clazz, String fieldname) {
		String className = clazz.getName();
		Map<String, Method> methodMap = clazzMap.get(className);
		if (methodMap == null) {
			synchronized (ReflectUtilCache.class) {
				if (methodMap == null) {
					methodMap = new HashMap<String, Method>();
					clazzMap.put(className, methodMap);
				}
			}
		}

		if (!methodMap.containsKey(fieldname)) {// 防止缓存穿透，即使是null也返回
			return methodMap.get(fieldname);
		} else {
			Method method = ReflectUtil.getMethodOfSetterByPropertyDescriptor(clazz, fieldname);
			return methodMap.put(fieldname, method);
		}
	}
	
	/**
	 * 获取 Mothed
	 * 
	 * @param clazz
	 * @param fieldname
	 * @return
	 */
	public static Method getMethodByMethodname(Class<?> clazz, String methodname) {
		String className = clazz.getName();
		Map<String, Method> methodMap = clazzMap.get(className);
		if (methodMap == null) {
			synchronized (ReflectUtilCache.class) {
				if (methodMap == null) {
					methodMap = new HashMap<String, Method>();
					clazzMap.put(className, methodMap);
				}
			}
		}

		if (!methodMap.containsKey(methodname)) {// 防止缓存穿透，即使是null也返回
			return methodMap.get(methodname);
		} else {
			Method method = ReflectUtil.getMethod(clazz, methodname);
			return methodMap.put(methodname, method);
		}
	}
}
