package com.live.test.api.core.startup.appStartup;

import com.live.test.api.core.startup.startupHandler.StartupHandlerManager;
import com.live.test.api.core.startup.startupHandler.impl.SimpleStartupHandler;

/**
 * 系统启动时，调用该类 :AppStartup.startup();
 * @author live
 *	@date 2020年12月4日 下午3:54:03
 */
public class AppStartup {
	
	public static void startup() {
		StartupHandlerManager.getInstance().registStartupHandler(new SimpleStartupHandler());
	}
	
	public static void main(String[] args) {
		AppStartup.startup();
	}
}
