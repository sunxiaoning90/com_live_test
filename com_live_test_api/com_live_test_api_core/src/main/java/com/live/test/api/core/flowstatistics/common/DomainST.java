package com.live.test.api.core.flowstatistics.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DomainST {

	private Map<String, STManager> domainSTManager = new ConcurrentHashMap<String, STManager>();

	private DomainST() {
	}

	public static DomainST getInstance() {
		return SingletonHolder.instance;
	}

	public Map<String, STManager> getDomainSTManager() {
		return domainSTManager;
	}

	@SuppressWarnings("unused")
	private void setDomainSTManager(Map<String, STManager> domainSTManager) {
		this.domainSTManager = domainSTManager;
	}

	public void putSTManager(String domain, STManager manager) {
		this.getDomainSTManager().put(domain, manager);
	}

	public STManager getSTManager(String domain) {
		STManager stManager = this.getDomainSTManager().get(domain);
		if (stManager == null) {
			synchronized (this) {
				if (stManager == null) {
					stManager = new STManager();
					this.putSTManager(domain, stManager);
				}
			}
		}
		return stManager;
	}

	private static class SingletonHolder {
		private static final DomainST instance = new DomainST();
	}

}
