package com.live.test.api.core.BIO_NIO_Netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * <pre>
第二版NIO :NIO引入多路复用器代码示例：
总结：多路服用器。提升效率
 * 
 * </pre>
 * 
 * @author live
 */
public class SocketServerByNIO2 {

	private void startSocketSerer(int port) throws IOException, InterruptedException {
		ServerSocketChannel server = ServerSocketChannel.open();
		System.out.println("server: " + server);

		InetSocketAddress address = new InetSocketAddress(port);
		System.out.println("address: " + address);

		server.bind(address);
		System.out.println("server: " + server);

		// 设置ServerSocketChannel为非阻塞
		server.configureBlocking(false);

		///
		Selector selector = Selector.open(); // 打开Selector处理Channel，即创建epoll

		server.register(selector, SelectionKey.OP_ACCEPT); // 把ServerSocketChannel注册到selector上，并且selector对客户端accept连接操作感兴趣
		///

		System.out.println("1、server端启动成功：" + server);

		while (true) {
			System.out.println("2、轮寻，阻塞");

			selector.select(); // 阻塞等待需要处理的事件发生

			// 获取selector中注册的全部事件的 SelectionKey 实例
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			
			Iterator<SelectionKey> iterator = selectionKeys.iterator();
			// 遍历SelectionKey对事件进行处理
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();

				// 如果是OP_ACCEPT事件，则进行连接获取和事件注册
				if (key.isAcceptable()) {
					ServerSocketChannel s = (ServerSocketChannel) key.channel();
					SocketChannel socketChannel = s.accept();
					socketChannel.configureBlocking(false);
					
					// 这里只注册了读事件，如果需要给客户端发送数据可以注册写事件
					socketChannel.register(selector, SelectionKey.OP_READ);
					System.out.println("客户端连接成功");
				} else if (key.isReadable()) { // 如果是OP_READ事件，则进行读取和打印
					SocketChannel socketChannel = (SocketChannel) key.channel();
					ByteBuffer byteBuffer = ByteBuffer.allocate(128);
					
					int len = socketChannel.read(byteBuffer);
					// 如果有数据，把数据打印出来
					if (len > 0) {
						System.out.println("接收到消息：" + new String(byteBuffer.array()));
					} else if (len == -1) { // 如果客户端断开连接，关闭Socket
						System.out.println("客户端断开连接");
						socketChannel.close();
					}
				}
				
				// 从事件集合里删除本次处理的key，防止下次select重复处理
				iterator.remove();
			}
		}

//		TimeUnit.SECONDS.sleep(2);

		// server.close();
		// System.out.println("5、server 关闭成功:" + server);
	}

	public static void main(String[] args) throws Exception {
		new SocketServerByNIO2().startSocketSerer(7079);
	}
}
