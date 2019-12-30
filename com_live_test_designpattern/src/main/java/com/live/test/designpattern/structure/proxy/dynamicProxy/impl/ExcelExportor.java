package com.live.test.designpattern.structure.proxy.dynamicProxy.impl;

import com.live.test.designpattern.structure.proxy.dynamicProxy.IFileExportor;

/**
 * 导出器实现类,负责导出excel
 * @author live
 * @2019年12月30日 @下午3:34:02
 */
public class ExcelExportor implements IFileExportor {

	@Override
	public void export() {
		System.out.println("正在导出excel...");
	}
	
	@Override
	public void export2() {
		System.out.println("正在导出2csv...");
	}
}
