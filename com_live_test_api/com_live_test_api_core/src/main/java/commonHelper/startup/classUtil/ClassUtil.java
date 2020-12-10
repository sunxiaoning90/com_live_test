package commonHelper.startup.classUtil;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ClassUtil {
	public static List<Class<?>> scanClass(String packageName) {
		List<Class<?>> clazzs = new ArrayList<Class<?>>();
		String placeholder = "";
//		return scanClass(packageName, clazzs);
		return scanClass(packageName, clazzs,placeholder);
	}
	
	public static List<Class<?>> scanClass(String packageName,List<Class<?>> clazzs){
		// 包名对应的路径名称
		String packageDirName = packageName.replace('.', '/');
		Enumeration<URL> resources = null;
		try {
			resources = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
//		URL dirs = FileUtil.class.getClassLoader().getResource(packageDirName);
		
		while (resources.hasMoreElements()) {
			URL url = resources.nextElement();
			
			 // 得到协议的名称
            String protocol = url.getProtocol();
            // 如果是以文件的形式保存在服务器上
            if ("file".equals(protocol)) {
               System.out.println("扫描，协议：file");
                // 获取包的物理路径
                String filePath = null;
				try {
					filePath = URLDecoder.decode(url.getFile(), "UTF-8");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
                File file = new File(filePath);
                File[] listFiles = file.listFiles();
                
				
				for (File f : listFiles) {
					if(f.isDirectory()) {
						System.out.println("扫描，目录：" + url.getFile());
						String packageName2 = f.getPath().substring(f.getPath().indexOf("classes/")+"classes/".length());
						packageName2 = packageName2.replace("/", ".");
	        			//return null;
//	        			String subPathname = file.getAbsolutePath();
	    				scanClass(packageName2,clazzs);
	        		}else {
	        			System.out.println("扫描，class：" + url.getFile());
//	        			TODO  Class.forName 会触发静态方法， 优化 ：
	        			Class<?> clazz = null;
	    				try {
//	    					Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className)
	    					///home/fan/live/WorkspaceAll/Workspace_SAAS/server-wsChatProxy/target/classes/spzc/server/wsChatProxy/chatProxy/msgProcessor/proxyMsgProcessor
	    					String packageName2 = f.getPath().substring(f.getPath().indexOf("classes/")+"classes/".length());
	    					packageName2 = packageName2.replace("/", ".");
	    					String className = packageName2.substring(0,packageName2.lastIndexOf("."));
	    					 clazz = Class.forName(className);
	    					 clazzs.add(clazz);
	    				} catch (ClassNotFoundException e) {
	    					e.printStackTrace();
	    				}
	        		}
				}
				
        		
        		
                // 以文件的方式扫描整个包下的文件 并添加到集合中
                        }
                    }
//                }catch (IOException e){
//                    // log.error("在扫描用户定义视图时从jar包获取文件出错");
//                    e.printStackTrace();
//                }
		return clazzs;
		}
	
	public static List<Class<?>> scanClass(String packageName,List<Class<?>> clazzs,String placeholder){
		placeholder+=">";
		// 包名对应的路径名称
		String packageDirName = packageName.replace('.', '/');
		Enumeration<URL> resources = null;
		try {
			resources = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
//		URL dirs = FileUtil.class.getClassLoader().getResource(packageDirName);
		
		while (resources.hasMoreElements()) {
			URL url = resources.nextElement();
			
			 // 得到协议的名称
            String protocol = url.getProtocol();
            // 如果是以文件的形式保存在服务器上
            if ("file".equals(protocol)) {
               System.out.println(placeholder+"扫描，协议：file");
                // 获取包的物理路径
                String filePath = null;
				try {
					filePath = URLDecoder.decode(url.getFile(), "UTF-8");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
                File file = new File(filePath);
                File[] listFiles = file.listFiles();
                
				
				for (File f : listFiles) {
					if(f.isDirectory()) {
						System.out.println("\n"+placeholder+"扫描，目录：" + f.getAbsoluteFile());
						String packageName2 = f.getPath().substring(f.getPath().indexOf("classes/")+"classes/".length());
						packageName2 = packageName2.replace("/", ".");
	        			//return null;
//	        			String subPathname = file.getAbsolutePath();
	    				scanClass(packageName2,clazzs,placeholder);
	        		}else {
//	        			TODO  Class.forName 会触发静态方法， 优化 ：
	        			Class<?> clazz = null;
	    				try {
//	    					Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className)
	    					///home/fan/live/WorkspaceAll/Workspace_SAAS/server-wsChatProxy/target/classes/spzc/server/wsChatProxy/chatProxy/msgProcessor/proxyMsgProcessor
	    					String packageName2 = f.getPath().substring(f.getPath().indexOf("classes/")+"classes/".length());
	    					packageName2 = packageName2.replace("/", ".");
	    					String className = packageName2.substring(0,packageName2.lastIndexOf("."));
	    					System.out.println(placeholder+"扫描，class：" + className);
	    					 clazz = Class.forName(className);
	    					 clazzs.add(clazz);
	    				} catch (ClassNotFoundException e) {
	    					e.printStackTrace();
	    				}
	        		}
					
				}
				
        		
        		
                // 以文件的方式扫描整个包下的文件 并添加到集合中
                        }
                    }
//                }catch (IOException e){
//                    // log.error("在扫描用户定义视图时从jar包获取文件出错");
//                    e.printStackTrace();
//                }
		return clazzs;
		}
		
	/**
     * 以文件的形式来获取包下的所有Class
     * 
     * @param packageName
     * @param packagePath
     * @param recursive
     * @param classes
     */
//    public static void findAndAddClassesInPackageByFile(
//            String packageName,
//            String packagePath,
//            final boolean recursive,
//            Set<Class<?>> classes){
//        // 获取此包的目录 建立一个File
//        File dir = new File(packagePath);
//        // 如果不存在或者 也不是目录就直接返回
//        if (!dir.exists() || !dir.isDirectory()) {
//            // log.warn("用户定义包名 " + packageName + " 下没有任何文件");
//            return;
//        }
//        // 如果存在 就获取包下的所有文件 包括目录
//        File[] dirfiles = dir.listFiles(new FileFilter(){
//
//            // 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
//            public boolean accept(File file){
//                return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
//            }
//        });
//        // 循环所有文件
//        for (File file : dirfiles){
//            // 如果是目录 则继续扫描
//            if (file.isDirectory()) {
//                findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
//            }else{
//                // 如果是java类文件 去掉后面的.class 只留下类名
//                String className = file.getName().substring(0, file.getName().length() - 6);
//                try{
//                    // 添加到集合中去
//                    // classes.add(Class.forName(packageName + '.' +
//                    // className));
//                    // 经过回复同学的提醒，这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
//                    classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
//                }catch (ClassNotFoundException e){
//                    // log.error("添加用户自定义视图类错误 找不到此类的.class文件");
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
