package com.live.test.api.core.file;

import java.io.File;

/**
 * 删除 工程目录中的 .target 等 IDE的配置
 * @author live
 */
public class HelperDelFile {
	public static void main(String[] args) {
		String path = "/opt/用于和PC复制文件/im/";
		delTarget(path);
	}

	public static void delTarget(String path) {
//		System.out.println(path);
		File file = new File(path);
//		System.out.println(file);

		String name = file.getName();
//		System.out.println(name);

		if ("target".equals(name) || ".svn".equals(name) || ".settings".equals(name)) {
//			System.out.println(path);
			file.setExecutable(true);

			// boolean flg = file.delete();
			boolean flg = deleteFile(file);
			System.out.println("删除：" + path + "结果：" + flg);
		}else {
			
			File[] fs = file.listFiles();
			for (File f : fs) {
				if(f.isDirectory()) {
					delTarget(f.getPath());
				}
			}
		}
		
	}

	/**
	 * 先根遍历序递归删除文件夹
	 *
	 * @param dirFile 要被删除的文件或者目录
	 * @return 删除成功返回true, 否则返回false
	 */
	public static boolean deleteFile(File dirFile) {
		// 如果dir对应的文件不存在，则退出
		if (!dirFile.exists()) {
			return false;
		}

		if (dirFile.isFile()) {
			if (dirFile.getAbsolutePath().startsWith("/opt/用于和PC复制文件/im/")) {
				return dirFile.delete();
//				return true;
			}
		} else {

			for (File file : dirFile.listFiles()) {
				deleteFile(file);
			}
		}

		return dirFile.delete();
	}
}
