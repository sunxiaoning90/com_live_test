package com.live.test.api.core.BIO_NIO_Netty.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * 
 * <pre>
AIO(NIO 2.0)
异步非阻塞， 由操作系统完成后回调通知服务端程序启动线程去处理， 一般适用于连接数较多且连接时间较长的应用

1、应用场景：
AIO方式适用于连接数目多且连接比较长(重操作)的架构，JDK7 开始支持

2、为什么Netty使用NIO而不是AIO？
在Linux系统上，AIO的底层实现仍使用Epoll，没有很好实现AIO，因此在性能上没有明显的优势，而且被JDK封装了一层不容易深度优
化，Linux上AIO还不够成熟。Netty是异步非阻塞框架，Netty在NIO上做了很多异步的封装。
 * </pre>
 * 
 * @author live
 * @time 2021年2月20日 下午5:08:27
 */

public class AIOServer {

	public static void main(String[] args) throws Exception {
		final AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open()
				.bind(new InetSocketAddress(7079));

		serverChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
			@Override
			public void completed(AsynchronousSocketChannel socketChannel, Object attachment) {
				try {
					System.out.println("2‐‐" + Thread.currentThread().getName());

					// 再此接收客户端连接，如果不写这行代码后面的客户端连接连不上服务端
					serverChannel.accept(attachment, this);
					System.out.println(socketChannel.getRemoteAddress());
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					socketChannel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
						@Override
						public void completed(Integer result, ByteBuffer buffer) {
							System.out.println("3‐‐" + Thread.currentThread().getName());
							buffer.flip();
							System.out.println(new String(buffer.array(), 0, result));
							socketChannel.write(ByteBuffer.wrap("HelloClient".getBytes()));
						}

						@Override
						public void failed(Throwable exc, ByteBuffer buffer) {
							exc.printStackTrace();
						}
					});
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void failed(Throwable exc, Object attachment) {
				exc.printStackTrace();
			}
		});

		System.out.println("1‐‐" + Thread.currentThread().getName());

		Thread.sleep(Integer.MAX_VALUE);
	}
}
