package com.live.test.api.core.cache.local;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 缓存 
 * 存储形式：键值对； 
 * 主要功能：存、取单个、清除单个、清除全部、取全部
 * @author live Dec 29, 2018 10:15:13 AM
 * @param <K>
 * @param <V>
 */
public interface ICache<K, V> {
	/**
	 * 取单个
	 * 
	 * @param key
	 * @return
	 */
	V get(K key);

	/**
	 * 取，如果无值会自动穿透
	 * 
	 * @param key
	 * @param callable
	 * @return
	 */
	V getOrLoad(K key, Callable<? extends V> callable);

	/**
	 * 放
	 * 
	 * @param key
	 * @param v
	 */
	void put(K key, V v);

	/**
	 * 移除单个
	 * 
	 * @param key
	 */
	void remove(K key);

	/**
	 * 清空全部
	 */
	void clear();
	
	/**
	 * 取全部
	 * 
	 * @return
	 */
	List<V> getAllAsList();
	
	/**
	 * 取全部
	 * 
	 * @return
	 */
	Map<K,V> getAllAsMap();
	
	long getSize();

	boolean containsKey(String key);
}
