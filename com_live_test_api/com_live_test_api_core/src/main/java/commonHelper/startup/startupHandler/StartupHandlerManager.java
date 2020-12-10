package commonHelper.startup.startupHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class StartupHandlerManager implements IStartupHandler {

	public List<IStartupHandler> handlers = new CopyOnWriteArrayList<IStartupHandler>();

	// singonten
	private static StartupHandlerManager instance;

	private StartupHandlerManager() {
//		handler = new CopyOnWriteArrayList<IStartupHandler>();
	}

	public static StartupHandlerManager getInstance() {
		if (instance == null) {
			synchronized (Object.class) {
				if (instance == null) {
					instance = new StartupHandlerManager();
				}
			}
		}
		return instance;
	}

	public void registStartupHandler(IStartupHandler handler) {
		this.handlers.add(handler);
		handle();
	}

	public void unregistStartupHandler(IStartupHandler handler) {
		this.handlers.remove(handler);
		handle();
	}

	@Override
	public void handle() {
		for (IStartupHandler handler : handlers) {
			handler.handle();
		}
	}

	@Override
	public void handle(Runnable callback) {
		for (IStartupHandler handler : handlers) {
			handler.handle(callback);
		}
	}

//	public IStartupHandler getStartupHandler() {
//	}

}
