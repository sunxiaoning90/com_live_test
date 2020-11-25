package com.live.test.designpattern.structure.proxy.proxyFactory.proxyA;

import java.lang.reflect.Method;

import com.live.test.designpattern.structure.proxy.proxyFactory.BaseTargetProxy;

public class ATargetProxy extends BaseTargetProxy<IATarget> {

	public ATargetProxy(IATarget target) {
		super(target);
		super.setTarget(target);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println(proxy.getClass());
		System.out.println("before");
		
		//Object r = method.invoke(super.getTarget(), args);
		Object r = super.doInvoke(proxy, method, args);
		
		System.out.println("after");
		return r;
	}

}
