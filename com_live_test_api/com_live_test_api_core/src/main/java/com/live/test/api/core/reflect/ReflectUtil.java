package com.live.test.api.core.reflect;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import com.live.test.api.core.poHelper.po.WorkTime;

/**
 * 反射工具类
 * 
 * @author live
 * @2020年5月12日 @下午 9:57:11
 */
public class ReflectUtil {

	/**
	 * map 转换为 pojo
	 * 
	 * @param clazz
	 * @param map
	 * @return
	 */
	public static Object converToPoJoObject(Map<String, Object> map, Class<?> clazz) {
		if (clazz == null) {
			return null;

		}
		Object obj = null;
		try {
			obj = clazz.newInstance();
			if (map == null) {
				return obj;
			}

			Iterator<Entry<String, Object>> it = map.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Object> entry = it.next();
				String k = entry.getKey();
				Object v = entry.getValue();

				PropertyDescriptor pd = new PropertyDescriptor(k, clazz);
				Method m = pd.getWriteMethod();
				if (m == null) {
					continue;
				}
//				m.getParameterTypes(); TODO 自动转换参数类型
				try {
					m.invoke(obj, v);
				} catch (Exception e) {
					System.out.println("设置属性发生错误，方法：" + m + ",	属性：" + k + ",	值：" + v);
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * map 转换为 pojo
	 * 
	 * @param clazz
	 * @param map
	 * @return
	 */
	public static <T> T converToPoJo(Map<String, Object> map, Class<T> clazz) {
		if (clazz == null) {
			return null;
		}
		T t = null;
		try {
			t = clazz.newInstance();
			if (map == null) {
				return t;
			}

			Iterator<Entry<String, Object>> it = map.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Object> entry = it.next();
				String k = entry.getKey();
				Object v = entry.getValue();
				Method m = null;
				try {
					m = getMethodOfSetterByPropertyDescriptor(clazz, k);
					// m = getMethodOfSetterBygetDeclaredMethod(clazz, k,String.class);
//					m = getMethodOfSetterBygetDeclaredMethod(clazz, k,v.getClass());
					if (m == null) {
						continue;
					}
//					m.getParameterTypes(); TODO 自动转换参数类型
					m.invoke(t, v);
				} catch (Exception e) {
					System.out.println("设置属性发生错误，方法：" + m + ",	属性：" + k + ",	值：" + v);
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 获取 Class 的某个setter
	 * 
	 * @param clazz
	 * @param k
	 * @return
	 */
	public static Method getMethodOfSetterByPropertyDescriptor(Class<?> clazz, String k) {
		try {
			PropertyDescriptor pd = new PropertyDescriptor(k, clazz);
			return pd.getWriteMethod();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取 Class 的某个getter
	 * 
	 * @param clazz
	 * @param k
	 * @return
	 */
	@SuppressWarnings("unused")
	public static Method getMethodOfGetterByPropertyDescriptor(Class<?> clazz, String k) {
		try {
			PropertyDescriptor pd = new PropertyDescriptor(k, clazz);
			return pd.getReadMethod();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取 Class 的某个setter
	 * 
	 * @param clazz
	 * @param k
	 * @return
	 */
	public static Method getMethodOfSetterBygetDeclaredMethod(Class<?> clazz, String k, Class<?>... parameterTypes) {
		String methodName = createSetMethod(k);
		return getMethod(clazz, methodName, parameterTypes); // TODO
	}

	/**
	 * 获取 Class 的某个getter
	 * 
	 * @param clazz
	 * @param k
	 * @return
	 */
	@SuppressWarnings("unused")
	private static Method getMethodOfGetterBygetDeclaredMethod(Class<?> clazz, String k, Class<?>... parameterTypes) {
		String methodName = createGetMethod(k);
		return getMethod(clazz, methodName, parameterTypes); // TODO
	}

	/**
	 * 获取 Class 的某个方法
	 * 
	 * @param clazz
	 * @param k
	 * @return
	 */
	public static <T> Method getMethod(Class<T> clazz, String methodName, Class<?>... parameterTypes) {
		try {
			return clazz.getDeclaredMethod(methodName, parameterTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 生成 setter方法名 id -> setId
	 * 
	 * @param k
	 * @return
	 */
	public static String createSetMethod(String k) {
		return "set".concat(StringUtils.substring(k, 0, 1).toUpperCase()).concat(k.substring(1));
	}

	/**
	 * 生成 getter方法名 id -> getId
	 * 
	 * @param k
	 * @return
	 */
	public static String createGetMethod(String k) {
		return "get".concat(StringUtils.substring(k, 0, 1).toUpperCase()).concat(k.substring(1));
	}

	public static void main(String[] args) {
		Class<WorkTime> clazz = WorkTime.class;

		String k = "id";
		Method m = getMethodOfSetterByPropertyDescriptor(clazz, k);
		System.out.println(m);
		m = getMethodOfSetterBygetDeclaredMethod(clazz, k);
		System.out.println(m);
	}
}
