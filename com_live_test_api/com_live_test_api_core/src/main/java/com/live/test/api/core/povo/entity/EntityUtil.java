//package com.live.test.api.core.povo.entity;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.MarkerManager;
//
//public class EntityUtil {
//	private static Logger logger = LogManager.getLogger(EntityUtil.class);
//	public static Entity buildEntity(Entity entity, Object pojo) {
//
//		if (entity == null) {
//			// String pojoName = pojo.getClass().getSimpleName();
//			// entity = new MongoEntity(pojoName);
//			return null;
//		}
//
//		Field[] fields = pojo.getClass().getDeclaredFields();
//		for (Field filed : fields) {
//			String filedName = filed.getName();
//			String methodName = "get".concat(StringUtils.substring(filedName, 0, 1).toUpperCase())
//					.concat(filedName.substring(1));
//			try {
//				Method method = pojo.getClass().getMethod(methodName);
//				Object result = method.invoke(pojo);
//				entity.setValue(filedName, result);
//			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
//					| InvocationTargetException e) {
//				logger.error(MarkerManager.getMarker("core.platform"),"EntityUtil.buildEntity","错误信息:{}",e);
//				e.printStackTrace();
//			}
//		}
//
//		return entity;
//	}
//
//	public static Entity buildEntity(Object pojo) {
//		if (pojo == null) {
//			return null;
//		}
//		String pojoName = pojo.getClass().getSimpleName();
//
//		Entity entity = new MongoEntity(pojoName);
//		return buildEntity(entity, pojo);
//
//	}
//
//	public static void main(String[] args) {
//	/*	UserBase userBase = new UserBase();
//		userBase.setId("id");
//		userBase.setCreatTime(new Date());
//		Entity entity = buildEntity(userBase);*/
//
//	}
//}
