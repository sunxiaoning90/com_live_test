package commonHelper.startup.startupHandler.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.live.test.api.core.startup.annotation.StartupClassWithAppStart;
import com.live.test.api.core.startup.annotation.StartupMethodWithAppStart;
import com.live.test.api.core.startup.classUtil.ClassUtil;
import com.live.test.api.core.startup.startupHandler.IStartupHandler;

public class SimpleStartupHandler implements IStartupHandler {

	@Override
	public void handle() {
		List<Class<?>> classes = ClassUtil.scanClass("com.live.test.api.core.startup");
		for (Class<?> clazz : classes) {
			System.out.println(clazz);
			if (clazz.isInterface() || clazz.isAnnotation()) {
				continue;
			}

			if (clazz.getAnnotation(StartupClassWithAppStart.class) == null) {
				continue;
			}

			try {
				Object obj = clazz.newInstance();

				// 获取Method
				Method[] methods = clazz.getDeclaredMethods();

				for (Method method : methods) {
//					Annotation[] annotations = method.getAnnotations();
					StartupMethodWithAppStart annotation = method.getAnnotation(StartupMethodWithAppStart.class);
					if (annotation == null) {
						continue;
					}

					try {
						method.invoke(obj);
					} catch (IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}

				}

			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void handle(Runnable callback) {
		// TODO Auto-generated method stub

	}

}
