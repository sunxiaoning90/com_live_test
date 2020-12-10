package com.live.test.api.core.startup.startupHandler;

public interface IStartupHandler {
	void handle();
	void handle(Runnable callback);
}
