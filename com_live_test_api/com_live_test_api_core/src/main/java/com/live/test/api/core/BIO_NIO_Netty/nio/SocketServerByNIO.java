package com.live.test.api.core.BIO_NIO_Netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 
 * <pre>
第一版NIO:	NIO非阻塞代码示例（轮寻，未使用多路复用）：

缺点：
如果连接数太多的话，会有大量的无效遍历，
假如有10000个连接，其中只有1000个连接有写数据，但是由于其他9000个连接并没有断开，我们还是要每次轮询遍历一万次，其中有十分之九的遍历都是无效的，这显然不是一个让人很满意的状态。
 * 
 * </pre>
 * 
 * @author live
 */
public class SocketServerByNIO {

	private List<SocketChannel> clientList = new ArrayList<>();

	/**
	 * <pre>
	打印：
	判断是否有客户端连接进来：null
	
	正在处理客户端:java.nio.channels.SocketChannel[connected local=/192.168.1.50:7079 remote=/192.168.1.50:49084]
	接收到消息：123
	
	正在处理客户端:java.nio.channels.SocketChannel[connected local=/192.168.1.50:7079 remote=/192.168.1.50:49086]
	接收到消息：abc
	 * </pre>
	 */
	private void startSocketSerer(int port) throws IOException, InterruptedException {
		ServerSocketChannel server = ServerSocketChannel.open();
		System.out.println("server: " + server);

		InetSocketAddress address = new InetSocketAddress(port);
		System.out.println("address: " + address);

		server.bind(address);
		System.out.println("server: " + server);

		// 设置ServerSocketChannel为非阻塞
		server.configureBlocking(false);
		System.out.println("1、server端启动成功：" + server);

		while (true) {
			System.out.println("2、轮寻，非阻塞"); // 非阻塞模式accept方法不会阻塞，否则会阻塞
																	// NIO的非阻塞是由操作系统内部实现的，底层调用了linux内核的accept函数
			SocketChannel client = server.accept();
			System.out.println("判断是否有客户端连接进来：" + client);
			if (client != null) {// 如果有客户端进行连接
				registClient(client);
			}

			// 遍历处理已有的客户端
			Iterator<SocketChannel> it = clientList.iterator();
			while (it.hasNext()) {
				SocketChannel c = it.next();
				System.out.println("正在处理客户端:" + c);

				ByteBuffer buff = ByteBuffer.allocate(128); // ByteBuffer

				// 非阻塞模式read方法不会阻塞，否则会阻塞
				int len = c.read(buff);

				// 如果有数据，把数据打印出来
				if (len > 0) {
					System.out.println("接收到消息：" + new String(buff.array()));
				} else if (len == -1) { // 如果客户端断开，把socket从集合中去掉
					it.remove();
					System.out.println("客户端断开连接:" + c);
				}
			}

			TimeUnit.SECONDS.sleep(2);
		}

		// server.close();
		// System.out.println("5、server 关闭成功:" + server);
	}

	private void registClient(SocketChannel client) throws IOException {
		System.out.println("有客户端连接进来了：" + client);

		// 设置SocketChannel为非阻塞
		client.configureBlocking(false);

		// 保存客户端连接在List中
		clientList.add(client);
	}

	public static void main(String[] args) throws Exception {
		new SocketServerByNIO().startSocketSerer(7079);
	}
}
