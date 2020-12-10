package commonHelper.startup.startupHandler;

public interface IStartupHandler {
	void handle();
	void handle(Runnable callback);
}
