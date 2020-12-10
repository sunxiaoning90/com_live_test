package commonHelper.startup.client;

import com.live.test.api.core.startup.annotation.StartupClassWithAppStart;
import com.live.test.api.core.startup.annotation.StartupMethodWithAppStart;

@StartupClassWithAppStart
public class AppStartupLog {

	@StartupMethodWithAppStart
	public void writeLog() {
		System.out.println("系统启动...");
	}
}
