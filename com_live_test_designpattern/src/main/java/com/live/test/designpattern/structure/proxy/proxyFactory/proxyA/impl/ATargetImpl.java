package com.live.test.designpattern.structure.proxy.proxyFactory.proxyA.impl;

import com.live.test.designpattern.structure.proxy.proxyFactory.proxyA.IATarget;

public class ATargetImpl implements IATarget{

	@Override
	public void doSomething() {
		System.out.println("ATargetImpl doSomething");
	}

}
