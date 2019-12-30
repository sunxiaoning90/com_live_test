package com.live.test.designpattern.structure.proxy.staticProxy;


import org.junit.Test;

import com.live.test.designpattern.structure.proxy.staticProxy.impl.ExcelExportor;

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
	 * 使用代理类(静态代理)
	 */
	@Test
	public void test2() {
		IFileExportor exportor = new ExportorProxy(new ExcelExportor());//
		exportor.export();
	}
	
}
