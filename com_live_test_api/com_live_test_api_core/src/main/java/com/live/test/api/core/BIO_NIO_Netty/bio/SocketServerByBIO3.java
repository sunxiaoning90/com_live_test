package com.live.test.api.core.BIO_NIO_Netty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <pre>
BIO最终版

1、较上一版改进后：支持多个客户端，并且支持并发处理多个客户端

2、缺点：
1、IO代码里read操作是阻塞操作，如果连接不做数据读写操作会导致线程阻塞，浪费资源
2、如果线程很多，会导致服务器线程太多，压力太大，比如C10K问题

3、BIO应用场景：
BIO 方式适用于连接数目比较小且固定的架构， 这种方式对服务器资源要求比较高， 但程序简单易理解。
 * </pre>
 * 
 * @author live
 * @time 2021年2月20日 下午2:42:16
 */
public class SocketServerByBIO3 {

	@SuppressWarnings("resource")
	private void startSocketSerer(int port) throws IOException {
		ServerSocket server = new ServerSocket(port);
		System.out.println("1、server端启动成功：" + server);

		while (true) { // 循环 监听新的客户端
			System.out.println("2、server端accept，阻塞，等待客户端连接。。。"); // 阻塞
			Socket clientSocket = server.accept();

			new Thread(() -> { // 异步处理，开启新线程
				try {
					handle(clientSocket);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}).start(); // 待改用线程池

		}

		// server.close();
		// System.out.println("5、server 关闭成功:" + server);
	}

	private void handle(Socket clientSocket) throws IOException {
		System.out.println("有客户端连接进来了：" + clientSocket);

		System.out.println("3、server端getInputStream，阻塞，等待客户端发送内容...");// 阻塞
		InputStream in = clientSocket.getInputStream();
		OutputStream out = clientSocket.getOutputStream();

		StringBuilder sb = new StringBuilder();

		byte[] buff = new byte[1024];
		int len = -1;
		while ((len = in.read(buff)) != -1) {
			String s = new String(buff, 0, len);
			System.out.println(s);
			out.write(("4、server端收到消息，客户端输入的是：" + s).getBytes());
			sb.append(s);
			if (s.contains("quit")) { // 定义一个客户端输入流退出标志
				break;
			}
		}
		String r = "当前连接即将断开! 客户端发送内容汇总：" + sb.toString();
		System.out.println(r); // 内容含有 \r\n 待处理
		out.write(r.getBytes());
	}

	public static void main(String[] args) throws IOException {
		new SocketServerByBIO3().startSocketSerer(7079);
	}
}
