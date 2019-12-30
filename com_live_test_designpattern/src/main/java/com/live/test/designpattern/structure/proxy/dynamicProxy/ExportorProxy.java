package com.live.test.designpattern.structure.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 导出器代理类(动态代理),只代理IFileExportor
 * @author live
 * @2019年12月30日 @下午3:42:09
 */
public class ExportorProxy implements InvocationHandler {

	IFileExportor exportor;//只代理IFileExportor
	
	public ExportorProxy(IFileExportor exportor) {
		super();
		this.exportor = exportor;
	}
	
	/**
	 * 对外提供一个方法,用来产生一个代理对象
	 * @return
	 */
	public Object getProxyInstance(){
		return Proxy.newProxyInstance(exportor.getClass().getClassLoader(), exportor.getClass().getInterfaces(), this);
	}
	
	/**
	 * 代理类被执行时,实际上执行的是invoke方法
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		System.out.println("导出前");
		long start = System.currentTimeMillis();
		
		// 代理类只对原始功能增强,实际功能交给被代理类执行
		Object result = method.invoke(exportor, args);
		
		System.out.println("导出后");
		long i = System.currentTimeMillis() - start;
		System.out.println("耗时:" + i);
		return result;
	}

}
