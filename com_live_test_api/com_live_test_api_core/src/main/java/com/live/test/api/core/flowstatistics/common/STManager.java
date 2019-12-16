package com.live.test.api.core.flowstatistics.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class STManager {

	@SuppressWarnings("rawtypes")
	private Map<String, IFilterChain> filterChains = new ConcurrentHashMap<String, IFilterChain>();

	public IFilterChain<?, ?> getFilterChain(String filterChainName) {
		return filterChains.get(filterChainName);
	}

	public void putFilterChain(String filterChainName, IFilterChain<?, ?> filterChain) {
		filterChains.put(filterChainName, filterChain);
	}

}
