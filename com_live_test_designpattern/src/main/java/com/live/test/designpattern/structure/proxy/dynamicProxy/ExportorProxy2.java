package com.live.test.designpattern.structure.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 导出器代理类(动态代理),代理任何接口类
 * @author live
 * @2019年12月30日 @下午3:42:09
 */
public class ExportorProxy2 implements InvocationHandler {

	Object obj;//代理任何接口类
	
	public ExportorProxy2(Object obj) {//传入一个被代理类的对象进来,相当于静态代理类中的含参构造中绑定一个指定的接口
		super();
		this.obj = obj;
	}
	
	public Object getProxyInstance(){
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		System.out.println("执行前");
		long start = System.currentTimeMillis();
		
		// 代理类只对原始功能增强,实际功能交给被代理类执行
		Object result = method.invoke(obj, args);
		
		System.out.println("执行后");
		long i = System.currentTimeMillis() - start;
		System.out.println("耗时:" + i);
		return result;
	}

}
