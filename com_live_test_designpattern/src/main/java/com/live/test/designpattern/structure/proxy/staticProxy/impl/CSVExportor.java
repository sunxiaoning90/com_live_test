package com.live.test.designpattern.structure.proxy.staticProxy.impl;

import com.live.test.designpattern.structure.proxy.staticProxy.IFileExportor;
/**
 * 导出器实现类,负责导出 csv
 * @author live
 * @2019年12月30日 @下午3:34:02
 */
public class CSVExportor implements IFileExportor {

	@Override
	public void export() {
		System.out.println("正在导出csv...");
	}
}
