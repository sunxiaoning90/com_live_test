package com.live.test.designpattern.structure.proxy.dynamicProxy;


import org.junit.Test;

import com.live.test.designpattern.structure.proxy.dynamicProxy.impl.ExcelExportor;

public class Client {
	
	/**
	 * 不使用代理类
	 */
	@Test
	public void test1() {
		IFileExportor exportor = new ExcelExportor();
		exportor.export();
	}
	
	/**
	 * 使用代理类(动态代理)
	 */
	@Test
	public void test2() {
		ExportorProxy proxy = new ExportorProxy(new ExcelExportor());
		IFileExportor exportor =  (IFileExportor) proxy.getProxyInstance();
		exportor.export();
		exportor.export2();
	}
	
	/**
	 * 使用代理类(动态代理)代理任何接口类
	 */
	@Test
	public void test3() {
		ExportorProxy2 proxy = new ExportorProxy2(new ExcelExportor());
		IFileExportor exportor =  (IFileExportor) proxy.getProxyInstance();
		exportor.export();
		exportor.export2();
	}
	
}
