package com.live.test.api.core.BIO_NIO_Netty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * NIO第一版实现缺点：Server 端服务一个客户端就关闭了，无法支持多个客户端，无法并发
 * 
 * @author live
 */
public class SocketServerByBIO {

	private void startSocketSerer(int port) throws IOException {
		ServerSocket server = new ServerSocket(port);
		System.out.println("1、server端启动成功：" + server);

		System.out.println("2、server端accept，阻塞，等待客户端连接。。。"); // 阻塞
		Socket clientSocket = server.accept();
		handle(clientSocket);

		server.close();
		System.out.println("5、server 关闭成功:" + server);
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
		new SocketServerByBIO().startSocketSerer(7079);
	}
}
