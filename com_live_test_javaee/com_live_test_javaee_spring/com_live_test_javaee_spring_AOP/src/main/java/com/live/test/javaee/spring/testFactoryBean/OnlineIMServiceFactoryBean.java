package com.live.test.javaee.spring.testFactoryBean;

import org.springframework.beans.factory.FactoryBean;

public class OnlineIMServiceFactoryBean implements FactoryBean<OnlineIMService>{

	@Override
	public OnlineIMService getObject() throws Exception {
		return new OnlineIMService();
	}

	@Override
	public Class<?> getObjectType() {
		return null;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}
	
}
