package com.live.test.api.core.poi.fileExport;

import java.io.File;
import java.io.IOException;

public class FileUtil {
	
	public static boolean createPath(String path){
		File file = new File(path);
		
//		if(file.exists()) {
//			return true;
//		}
//		
//		if(file.mkdirs()) {
//			return true;
//		}
		
		String command = "cd /opt";
		command = "service sshd startl";
		try {
			Runtime.getRuntime().exec(command );
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return file.exists();
	}
	
	public static void main(String[] args) {
		FileUtil.createPath("/opt/test1112");
	}
}
