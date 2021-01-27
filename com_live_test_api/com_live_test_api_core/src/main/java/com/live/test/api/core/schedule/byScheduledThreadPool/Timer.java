package com.live.test.api.core.schedule.byScheduledThreadPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import spzc.spcc.core.utils.PropertyUtil;

public class Timer {
	protected static Logger log4j = LogManager.getLogger();
	protected static Map<String, Timer> timerMap = new HashMap<String, Timer>();
	private static final Random random = new Random();
	private ScheduledExecutorService scheduledThreadPool;
	private ConcurrentHashMap<Long, Notifiable> handleMap = new ConcurrentHashMap<Long, Notifiable>(16384);
	private volatile long maxId = 0L;
	private String name;
	public int maxTasks = 10;// PropertyUtil.getIntegerProperty("Tasks.queue.max", 100000);
	private long checkTimeMs = 1000;// PropertyUtil.getIntegerProperty("Tasks.queue.checkInterval", 1000);
	private static final int originalPoolSize = 20;// PropertyUtil.getIntegerProperty("Tasks.queue.originalPoolSize",
													// 20);
	private static final int accessorialPoolSize = 5;// PropertyUtil.getIntegerProperty("Tasks.queue.innerPoolSize", 5);
	private long terminateMs = 0;
	private static Thread checkThread = null;
	private static boolean isShutdown = false;
	static {
		checkThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!isShutdown) {
					try {
						Thread.sleep(random.nextInt(1000) + 500);
					} catch (InterruptedException e) {
						break;
					}
					for (Timer timer : timerMap.values()) {
						try {
							Thread.sleep(random.nextInt(200) + 100);
						} catch (InterruptedException e) {
							break;
						}
						timer.check();
					}
				}
			}
		});
		checkThread.start();
	}

	private Timer(String name, int poolsize) {
		System.out.println("initialize timer: {}, pool size: {}"+name+poolsize);
		this.name = name;
		scheduledThreadPool = Executors.newScheduledThreadPool(poolsize);
		scheduledCheck();
	}

	private Timer(String name, int poolsize, long terminateMs) {
		System.out.println("initialize timer: {}, pool size: {}, terminate ms: {}"+ name+ poolsize+ terminateMs);
		this.name = name;
		if (this.checkTimeMs > terminateMs)
			this.checkTimeMs = terminateMs;
		this.terminateMs = terminateMs;
		scheduledThreadPool = Executors.newScheduledThreadPool(poolsize);
		scheduledCheck();
	}

	private Long lastCheckStamp = 0L;

	private void check() {
		int n = 0;
		long stampNow = System.currentTimeMillis();
		Iterator<Map.Entry<Long, Notifiable>> it = handleMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Long, Notifiable> entry = it.next();
			Notifiable notifiable = entry.getValue();
			ScheduledFuture<?> sf = notifiable.getScheduledFuture();
			if (sf.isDone() || sf.isCancelled()) {
				it.remove();
				++n;
			}
			if (terminateMs > 0) {
				if (notifiable.getRunningTime(stampNow) > terminateMs) {
					if (!sf.isDone() && !sf.isCancelled()) {
//						sf.cancel(true);
						sf.cancel(false);
						log4j.warn(String.format("task terminated because long time running: %d ms. allocated from: %s",
								notifiable.getRunningTime(stampNow), notifiable.stackTrace));
						if (notifiable.timeoutAction != null)
							add(0, notifiable.timeoutAction);
					}
				}
			}
		}
		if (n > 1000 || stampNow - lastCheckStamp > 600000 || lastCheckStamp > stampNow) {
			System.out.println(String.format("scheduledCheck[%s]: deleted %d handles, remain: %d", name, n, handleMap.size()));
			lastCheckStamp = stampNow;
		}
	}

	private void scheduledCheck() {
		this.add(checkTimeMs, new Notifiable() {
			@Override
			public void notice() {
				check();
				scheduledCheck();
			}
		});
	}

	static private Timer originalInstance;

	static public Timer getInstance() {
		if (originalInstance == null) {
			synchronized (Timer.class) {
				if (originalInstance == null) {
					originalInstance = new Timer("original", originalPoolSize);
					timerMap.put("original", originalInstance);
				}
			}

		}
		return originalInstance;
	}

	static public Timer getInstance(String name) {
		Timer timer = timerMap.get(name);
		if (timer == null) {
			synchronized (Timer.class) {
				timer = timerMap.get(name);
				if (timer == null) {
					timer = new Timer(name, accessorialPoolSize);
					timerMap.put(name, timer);
				}
			}

		}
		return timer;
	}

	static public Timer getInstance(String name, long terminateMs) {
		Timer timer = timerMap.get(name);
		if (timer == null) {
			synchronized (Timer.class) {
				timer = timerMap.get(name);
				if (timer == null) {
					timer = new Timer(name, accessorialPoolSize, terminateMs);
					timerMap.put(name, timer);
				}
			}

		}
		return timer;
	}

	public String getName() {
		return name;
	}

	public void shutdown() {
		try {
			isShutdown = true;
			for (Timer timer : timerMap.values()) {
				timer.scheduledThreadPool.shutdown();
			}
			for (Timer timer : timerMap.values()) {
				System.out.println(String.format("shutdown Scheduled Executor Service: %s", timer.getName()));
				if (timer.scheduledThreadPool.awaitTermination(1, TimeUnit.SECONDS))
					System.out.println(String.format("Scheduled Executor Service finished: %s", timer.getName()));
				else {
					timer.scheduledThreadPool.shutdownNow();
					if (!timer.scheduledThreadPool.isShutdown() && !timer.scheduledThreadPool.isTerminated()) {
						log4j.warn(String.format("Scheduled Executor Service is not shutdown, still have works: %s",
								timer.getName()));
						if (timer.scheduledThreadPool.awaitTermination(5, TimeUnit.SECONDS))
							System.out.println(String.format("Scheduled Executor Service finished: %s", timer.getName()));
						else
							timer.scheduledThreadPool.shutdownNow();
					}
				}
			}
		} catch (Exception e) {
		}
	}

	public long add(long millseconds, Notifiable notifyObject) {
		if (handleMap.size() > maxTasks) {
			log4j.warn(String.format(
					"task can't queue, too many pending tasks. waiting tasks: %d, taskQueue: %s, stack: %s",
					handleMap.size(), this.name, notifyObject.stackTrace));
			return -1;
		}
		long id = ++maxId;
		try {
			notifyObject.setNotifyTime(System.currentTimeMillis() + millseconds);
			ScheduledFuture<?> sf = scheduledThreadPool.schedule(notifyObject, millseconds, TimeUnit.MILLISECONDS);
			notifyObject.setHandler(id);
			notifyObject.setTimer(this);
			notifyObject.setScheduledFuture(sf);
			handleMap.put(id, notifyObject);
		} catch (RejectedExecutionException e) {
//			log4j.warn(String.format("Timer add error, RejectedExecutionException: %s", e.getLocalizedMessage()));
			return -1;
		} catch (Exception e) {
			log4j.warn("Timer add error", e);
			return -1;
		}
		return id;
	}

	public void removeTimer(long handler) {
		if (handler == -1)
			return;
		Notifiable n = handleMap.remove(handler);
		if (n != null) {
			ScheduledFuture<?> sf = n.getScheduledFuture();
			if (sf != null)
				sf.cancel(false);
		}
	}

	public void removeTimer(long handler, boolean canInterrupt) {
		if (handler == -1)
			return;
		Notifiable n = handleMap.remove(handler);
		if (n != null) {
			ScheduledFuture<?> sf = n.getScheduledFuture();
			if (sf != null)
				sf.cancel(canInterrupt);
		}
	}

	public static void main(String[] args) {
		final List<Long> l = new ArrayList<Long>();
		final Random random = new Random();
//		for (int i = 0; i < 10000; i++) {
		for (int i = 0; i < 2; i++) {
			Thread p = new Thread(new Runnable() {

				@Override
				public void run() {
					Timer.getInstance("terminatable", 3000).add(0, new Notifiable() {
						@Override
						public void notice() {
							while (true) {
								try {
									Thread.sleep(100);
								} catch (Exception e) {
									break;
								}
							}
						}
					});
					for (int i = 0; i < 10; ++i) {
						final int n = i;
						final long time = random.nextInt() % 5000 + 5000 < 3000 ? 0 : random.nextInt() % 5000 + 5000;
						final long alerttime = System.currentTimeMillis() + time;
						try {
							Thread.sleep(random.nextInt() % 5 + 5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Timer.getInstance().add(time, new Notifiable() {
							long t = alerttime;
							int pos = n;

							@Override
							public void notice() {
								long diff = System.currentTimeMillis() - t;
								synchronized (l) {
									l.add(diff);
									System.out.println(String.format("Timer[%d], sleep time: %d, diff: %d, number: %d", pos,
											time, diff, l.size()));
								}
							}
						});

					}
				}
			});
			p.start();
		}
		System.out.println("waiting 60 seconds");
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Timer.getInstance().shutdown();
		System.out.println("finished");
	}
}
