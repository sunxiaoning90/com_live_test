package com.live.test.javase.core.juc.threadpool;

import java.util.concurrent.ThreadPoolExecutor;

import org.junit.Test;

//import core.spzc.platform.web.helper.Helper;
//import spzc.module.fileImport.importFromMemory.ImportSaveThread;
//import spzc.module.fileImport.importFromMemory.ThreadPoolManager;

public class ThreadPoolManagerTest {

	@Test
	public void t1() {
		ThreadPoolExecutor pool = ThreadPoolManager.getInstance().getThreadPool("import_save");
		System.out.println(pool);
		//threadPool.execute(new ImportSaveThread(this,Helper.getContext()));
	}

}
