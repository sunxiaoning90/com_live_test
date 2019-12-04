package com.live.test.api.core.cache.local.ju.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.MarkerManager;

import com.live.test.api.core.cache.local.ICache;

/**
 * 基于map实现的本地缓存
 * @author live
 * Dec 28, 2018 7:06:58 PM
 * @param <K>
 * @param <V>
 */
public class MapCache<K, V> implements ICache<K, V>{
	
	private static Logger logger = LogManager.getLogger(MapCache.class);

	private Map<K, V> map = new ConcurrentHashMap<K, V>();
	
	@Override
	public V get(K key) {
		return map.get(key);
	}

	@Override
	public V getOrLoad(K key, Callable<? extends V> callable) {
		try {
			return callable.call();
		} catch (Exception e) {
			logger.error(MarkerManager.getMarker("core.platform"),"MapCache.getOrLoad","错误信息:{}",e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void put(K key, V v) {
		map.put(key, v);
	}

	@Override
	public void remove(K key) {
		map.remove(key);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public List<V> getAllAsList() {
		if (map == null) {
			return null;
		}
		
		List<V> list = null;
		if (map != null) {
			list = new ArrayList<V>();
			Iterator<Entry<K, V>> it = map.entrySet().iterator();
			while (it.hasNext()) {
				Entry<K, V> entry = it.next();
				list.add(entry.getValue());
			}
		}
		return list;
	}

	@Override
	public Map<K, V> getAllAsMap() {
		return map;
	}

	@Override
	public long getSize() {
		return 0;
	}

	@Override
	public boolean containsKey(String key) {
		return this.map.containsKey(key);
	}

}
