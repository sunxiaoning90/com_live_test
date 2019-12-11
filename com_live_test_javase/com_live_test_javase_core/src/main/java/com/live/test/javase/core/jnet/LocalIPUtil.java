package com.live.test.javase.core.jnet;

import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

/**
 * 获取本机IP(精确版)
 * 服务器或PC一般都拥有多个网卡，每个网卡拥有一个IP地址，但并不是所有的IP地址能被外部或局域网访问，比如说虚拟机网卡地址等等。也就是说InetAddress.getLocalHost().getHostAddress()的IP不一定是正确的IP。本文介绍两种方式，可以在绝对部分场景下获取到想要的IP地址。
 * @author live
 * @2019年12月11日 @上午10:12:25
 */
public class LocalIPUtil {
	/**
	 * 方式1:通过过滤获取IP地址
	 * 
	 * @return
	 * @throws SocketException
	 */
	public static List<Inet4Address> getLocalIp4AddressFromNetworkInterface() throws SocketException {
		List<Inet4Address> addresses = new ArrayList<>(1);
		Enumeration<?> e = NetworkInterface.getNetworkInterfaces();
		if (e == null) {
			return addresses;
		}
		while (e.hasMoreElements()) {
			NetworkInterface n = (NetworkInterface) e.nextElement();
			if (!isValidInterface(n)) {
				continue;
			}
			Enumeration<?> ee = n.getInetAddresses();
			while (ee.hasMoreElements()) {
				InetAddress i = (InetAddress) ee.nextElement();
				if (isValidAddress(i)) {
					addresses.add((Inet4Address) i);
				}
			}
		}
		return addresses;
	}

	/**
	 * 过滤回环网卡、点对点网卡、非活动网卡、虚拟网卡并要求网卡名字是eth或ens开头
	 *
	 * @param ni 网卡
	 * @return 如果满足要求则true，否则false
	 */
	private static boolean isValidInterface(NetworkInterface ni) throws SocketException {
		return !ni.isLoopback() && !ni.isPointToPoint() && ni.isUp() && !ni.isVirtual()
				&& (ni.getName().startsWith("eth") || ni.getName().startsWith("ens"));
	}

	/**
	 * 判断是否是IPv4，并且内网地址并过滤回环地址.
	 */
	private static boolean isValidAddress(InetAddress address) {
		return address instanceof Inet4Address && address.isSiteLocalAddress() && !address.isLoopbackAddress();
	}

	/**
	 * 方式2:通过访问外网 当有多个网卡的时候，使用这种方式一般都可以得到想要的IP。甚至不要求外网地址8.8.8.8是可连通的。
	 * 
	 * @return
	 * @throws SocketException
	 */
	private static Optional<Inet4Address> getIpBySocket() throws SocketException {
		try (final DatagramSocket socket = new DatagramSocket()) {
			socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			if (socket.getLocalAddress() instanceof Inet4Address) {
				return Optional.of((Inet4Address) socket.getLocalAddress());
			}
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
		return Optional.empty();
	}

	/**
	 * 在实际使用中，为了兼容更多的场景，项目中一般兼容了以上两种方式。
	 * 
	 * @return
	 * @throws SocketException
	 */
	public static Optional<Inet4Address> getLocalIp4Address() throws SocketException {
		final List<Inet4Address> ipByNi = getLocalIp4AddressFromNetworkInterface();
		if (ipByNi.isEmpty() || ipByNi.size() > 1) {
			final Optional<Inet4Address> ipBySocketOpt = getIpBySocket();
			if (ipBySocketOpt.isPresent()) {
				return ipBySocketOpt;
			} else {
				return ipByNi.isEmpty() ? Optional.empty() : Optional.of(ipByNi.get(0));
			}
		}
		return Optional.of(ipByNi.get(0));
	}

	public static void main(String[] args) {
		try {
			Optional<Inet4Address> localIp4Address = getLocalIp4Address();
			Inet4Address inet4Address = localIp4Address.get();
			String hostAddress = inet4Address.getHostAddress();
			System.out.println("本机IP:" + hostAddress);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

}
