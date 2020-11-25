package com.live.test.designpattern.structure.proxy.proxyFactory;

import com.live.test.designpattern.structure.proxy.proxyFactory.proxyA.ATargetProxy;
import com.live.test.designpattern.structure.proxy.proxyFactory.proxyA.IATarget;
import com.live.test.designpattern.structure.proxy.proxyFactory.proxyA.impl.ATargetImpl;

public class Test {
	public static void main(String[] args) {
		new Test().testProxy();
	}

	private void testProxy() {
		IATarget t = new ATargetImpl();
		BaseTargetProxy<?> p = new ATargetProxy(t);
		IATarget proxyInstance = (IATarget) p.getProxyInstance();
		proxyInstance.doSomething();
	}

}
