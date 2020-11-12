package com.live;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

public class BeanUtil implements ApplicationContextAware {
	static ApplicationContext ac;
	
	@Override
	public void setApplicationContext(ApplicationContext ac)
			throws BeansException {
		BeanUtil.ac = ac;
	}
	
	private static void checkApplicationContext() {
        Assert.notNull(ac,
                "applicationContext未注入,请在applicationContext.xml中定义BeanUtil");
    }

}
