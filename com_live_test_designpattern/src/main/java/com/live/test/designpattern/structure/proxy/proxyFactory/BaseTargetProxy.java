package com.live.test.designpattern.structure.proxy.proxyFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 基类，辅助创建 代理类
 * 
 * @author live
 * @time 2020年11月25日 上午11:51:38
 */
public abstract class BaseTargetProxy<T> implements InvocationHandler {

	private T target;

	public BaseTargetProxy(T target) {
		this.target = target;
	}

	/*
	 * @Override public Object invoke(Object proxy, Method method, Object[] args)
	 * throws Throwable { System.out.println(proxy.getClass());
	 * System.out.println("before"); Object r = method.invoke(target, args);
	 * System.out.println("end"); return r; }
	 */

	/**
	 * 具体实现下沉至子类
	 */
	public abstract Object invoke(Object proxy, Method method, Object[] args) throws Throwable;

	public Object getProxyInstance() {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}
	
	/**
	 * 方便子类调用super
	 * 
	 * @param proxy
	 * @param method
	 * @param args
	 * @return
	 * @throws Throwable
	 */
	protected Object doInvoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object r = method.invoke(target, args);
		return r;
	}
	
	public Object getTarget() {
		return target;
	}

	public void setTarget(T target) {
		this.target = target;
	}


}
