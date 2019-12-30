package com.live.test.designpattern.structure.proxy.staticProxy;

/**
 * 导出器代理类(静态代理)
 * 
 * "代理类"与"被代理类"需要实现相同的接口(IFileExportor).
 * 代理类要关联"被代理类",代理类只对原始功能增强,实际功能交给被代理类执行
 * 一个静态代理类,只能代理同一接口下的实现类.demo中"导出代理类"(ExportorProxy*代理了IFileExportor的实现类,如果需要代理"导入"类,则需要增加"导入代理类"
 * 
 * @author live
 * @2019年12月30日 @下午3:42:09
 */
public class ExportorProxy implements IFileExportor {

	IFileExportor exportor;// 被代理类

	public ExportorProxy(IFileExportor exportor) {
		super();
		this.exportor = exportor;
	}

	@Override
	public void export() {
		System.out.println("导出前");
		long start = System.currentTimeMillis();
		exportor.export();// 代理类只对原始功能增强,实际功能交给被代理类执行
		System.out.println("导出后");
		long i = System.currentTimeMillis() - start;
		System.out.println("耗时:" + i);
	}

}
