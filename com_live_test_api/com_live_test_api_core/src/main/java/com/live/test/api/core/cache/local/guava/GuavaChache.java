package com.live.test.api.core.cache.local.guava;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.MarkerManager;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.live.test.api.core.cache.local.ICache;


/**
 * 基于Guava实现的本地缓存
 * 
 * @author live Dec 28, 2018 7:06:58 PM
 * @param <K>
 * @param <V>
 */
public class GuavaChache<K, V> implements ICache<K, V> {
	Logger log = LogManager.getLogger(GuavaChache.class);
	// 最大值
	private Long maximumSize;
	// 写入到缓存后，多久过期
	private Duration expireAfterWrite;
	// 访问后，多久过期
	private Duration expireAfterAccess;

	private Cache<K, V> cache;

	public GuavaChache() {
		this(null, null, null);
	}

	public GuavaChache(Long maximumSize, Duration expireAfterWrite, Duration expireAfterAccess) {
		this.maximumSize = maximumSize;
		this.expireAfterWrite = expireAfterWrite;
		this.expireAfterAccess = expireAfterAccess;
		this.cache = new GuavaCacheUtil<K, V>().buildCache(maximumSize, expireAfterWrite, expireAfterAccess);
	}

	public GuavaChache(Long maximumSize, Duration expireAfterWrite) {
		this(maximumSize, expireAfterWrite, null);
	}

	public GuavaChache(Duration expireAfterWrite) {
		this(null, expireAfterWrite, null);
	}

	public Long getMaximumSize() {
		return maximumSize;
	}

	public void setMaximumSize(Long maximumSize) {
		this.maximumSize = maximumSize;
	}

	public Duration getExpireAfterWrite() {
		return expireAfterWrite;
	}

	public void setExpireAfterWrite(Duration expireAfterWrite) {
		this.expireAfterWrite = expireAfterWrite;
	}

	public Duration getExpireAfterAccess() {
		return expireAfterAccess;
	}

	public void setExpireAfterAccess(Duration expireAfterAccess) {
		this.expireAfterAccess = expireAfterAccess;
	}

	public Cache<K, V> getCache() {
		return cache;
	}

	public void setCache(Cache<K, V> cache) {
		this.cache = cache;
	}

	@Override
	public V get(K key) {
		return cache.getIfPresent(key);
	}

	public V getOrLoad(K key, Callable<? extends V> callable) {
		try {
			return cache.get(key, callable);
		} catch (ExecutionException e) {
			log.error(MarkerManager.getMarker("core.platform"),"GuavaChache.getOrLoad","错误信息:{}",e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void put(K key, V v) {
		this.cache.put(key, v);
	}

	@Override
	public void remove(K key) {
		this.cache.invalidate(key);
	}

	@Override
	public void clear() {
		this.cache.invalidateAll();
	}

	/**
	 * 工具类
	 * 构建 com.google.common.cache.Cache
	 * @author live
	 * Dec 29, 2018 10:45:47 AM
	 * @param <X>
	 * @param <Y>
	 */
	private class GuavaCacheUtil<X, Y> {
		Cache<X, Y> buildCache(Long maximumSize, Duration expireAfterWrite, Duration expireAfterAccess) {
			CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder();
			if (maximumSize != null) {
				builder.maximumSize(maximumSize);
			}
			if (expireAfterWrite != null) {
				builder.expireAfterWrite(expireAfterWrite.getTime(), expireAfterWrite.getTimeUnit());
			}
			if (expireAfterAccess != null) {
				builder.expireAfterWrite(expireAfterAccess.getTime(), expireAfterAccess.getTimeUnit());
			}
			return builder.build();
		}
	}

	/**
	 * “时长类”：
	 * 封装了时间、时间单位
	 * @author live
	 * Dec 29, 2018 10:44:55 AM
	 */
	public static class Duration {
		private Long time;
		private TimeUnit timeUnit;

		public Duration(Long time, TimeUnit timeUnit) {
			super();
			this.time = time;
			this.timeUnit = timeUnit;
		}

		public Long getTime() {
			return time;
		}

		public void setTime(Long time) {
			this.time = time;
		}

		public TimeUnit getTimeUnit() {
			return timeUnit;
		}

		public void setTimeUnit(TimeUnit timeUnit) {
			this.timeUnit = timeUnit;
		}
	}

	@Override
	public List<V> getAllAsList() {
		if (cache == null) {
			return null;
		}
		
		ConcurrentMap<K, V> map = cache.asMap();
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
		return cache !=null ? cache.asMap() : null;
	}

	@Override
	public long getSize() {
		return cache.size();
	}

	@Override
	public boolean containsKey(String key) {
		if(cache != null && cache.asMap() != null) {
			return cache.asMap().containsKey(key);
		}
		return false;
	}

}
