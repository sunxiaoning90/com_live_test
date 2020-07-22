package com.live.test.distributed.zookeeper;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
/**
 * zookeeper 实现分布式配置中心
 * @author live
 */
public class ZooKeeperClientSync implements Watcher {

	private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
	private static ZooKeeper zk = null;
	private static Stat stat = new Stat();

	@Override
	public void process(WatchedEvent event) {
		if (KeeperState.SyncConnected == event.getState()) { // zk连接成功通知事件
			if (EventType.None == event.getType() && null == event.getPath()) {
				connectedSemaphore.countDown();
			} else if (event.getType() == EventType.NodeDataChanged) { // zk目录节点数据变化通知事件
				try {
					System.out.println("配置已修改，新值为：" + new String(zk.getData(event.getPath(), true, stat)));
				} catch (Exception e) {
				}
			}
		}

	}

	/**
	 * <pre>
	 * 测试：
	 * 1）启动一个测试类
	 * 2）启动另外一个测试类
	 * 
	 *3）zk节点变化：set /n1 n1new
	 *
	 *4）观察两个客户端都监听到了"/n1"节点的变化
	 *zk:State:CONNECTING sessionid:0x0 local:null remoteserver:null lastZxid:0 xid:1 sent:0 recv:0 queuedpkts:0 pendingresp:0 queuedevents:0
	a1
	按任意键退出
	配置已修改，新值为：a2
	配置已修改，新值为：a3
	 * </pre>
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// zookeeper配置数据存放路径
		String path = "/n1";
		// 连接zookeeper并且注册一个默认的监听器
		zk = new ZooKeeper("192.168.1.52:2181", 5000, //
				new ZooKeeperClientSync());
		System.out.println("zk:" + zk);

		// 等待zk连接成功的通知
		connectedSemaphore.await();

		// 获取path目录节点的配置数据，并注册默认的监听器
		System.out.println(new String(zk.getData(path, true, stat)));

		System.out.println("按任意键退出");
		System.in.read();
		System.out.println("end");
	}

}
