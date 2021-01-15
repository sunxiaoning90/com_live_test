package com.live.test.api.core.json.fastjson;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringEscapeUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Json工具类
 * 
 * @author live
 * @date Dec 14, 2018 11:50:29 AM
 */
public class JsonUtil {
	
//	private static Logger logger = LogManager.getLogger(JsonUtil.class);

	/**
	 * 已有一个json对象source，获取生成一个新的json对象target（target的key是source的fieldNameKey的值，value是source的fieldNameValue的值）
	 * 
	 * @param source
	 * @param fieldNameKey
	 * @param fieldNameValue
	 * @param target
	 * @param depth
	 */
	public static void deepGetKeyValueToJson(JSONObject source, String fieldNameKey, String fieldNameValue,
			JSONObject target, int depth) {
		if (depth <= 0 || source == null || target == null) {
			return;
		}
		depth--;
		Set<Entry<String, Object>> set = source.entrySet();
		for (Entry<String, Object> entry : set) {
			Object entryValue = entry.getValue();
			if (fieldNameKey.equals(entry.getKey())) {
				//Object v = null;
				JSONObject json = new JSONObject();
				for (Entry<String, Object> entry2 : set) {
//					if (fieldNameValue.equals(entry2.getKey())) {
//						//v = entry2.getValue();
////						break;
//					}
					if ("type".equals(entry2.getKey())) {
						json.put("permissionType", entry2.getValue());
					}else if ("value".equals(entry2.getKey())) {
						json.put("permissionValue", entry2.getValue());
					}
				}
//				target.put(entry.getValue().toString(), v);
				target.put(entry.getValue().toString(), json);
			} else if (JSONObject.class.isAssignableFrom(entryValue.getClass())) {
				deepGetKeyValueToJson((JSONObject) entryValue, fieldNameKey, fieldNameValue, target, depth);
			} else if (JSONArray.class.isAssignableFrom(entryValue.getClass())) {
				deepGetKeyValueToJson((JSONArray) entryValue, fieldNameKey, fieldNameValue, target, depth);
			}
		}
	}

	/**
	 * 已有一个json对象source，获取生成一个新的json对象target（target的key是source的fieldNameKey的值，value是source的fieldNameValue的值）
	 * 
	 * @param source
	 *            String pStr =
	 *            "[{\"id\":\"fc3a76d6-4676-4d6b-a4fd-aaf238968370\",\"parentId\":\"root\",\"name\":\"基础设置\",\"moduleName\":\"A\",\"key\":\"sysBasicSet\",\"desc\":\"基础设置\",\"type\":\"null\",\"isStop\":\"null\",\"order\":\"null\",\"parentKey\":\"root\"},{\"id\":\"105cff1f-1544-4441-a140-12f70dea6869\",\"parentId\":\"fc3a76d6-4676-4d6b-a4fd-aaf238968370\",\"name\":\"个人设定\",\"moduleName\":\"A\",\"key\":\"sysPensonalsettings\",\"desc\":\"个人设定\",\"type\":\"null\",\"isStop\":\"null\",\"order\":\"null\",\"parentKey\":\"sysBasicSet.sysPensonalsettings\"},{\"id\":\"bf1260f9-c31c-4064-9b59-120b410708c3\",\"parentId\":\"fc3a76d6-4676-4d6b-a4fd-aaf238968370\",\"name\":\"系统设置\",\"moduleName\":\"A\",\"key\":\"sysSet\",\"desc\":\"系统设置\",\"type\":\"null\",\"isStop\":\"null\",\"order\":\"null\",\"parentKey\":\"sysBasicSet.sysSet\"},{\"id\":\"0423bb7d-5aeb-451d-bd88-ce3163373aba\",\"parentId\":\"bf1260f9-c31c-4064-9b59-120b410708c3\",\"name\":\"组织机构\",\"moduleName\":\"B\",\"key\":\"sysOrg\",\"desc\":\"组织机构\",\"type\":\"null\",\"isStop\":\"null\",\"order\":\"null\",\"parentKey\":\"sysBasicSet.sysSet.sysOrg\"},{\"id\":\"34cff25f-8103-4a8c-a515-a9c076d88d91\",\"parentId\":\"0423bb7d-5aeb-451d-bd88-ce3163373aba\",\"name\":\"人员管理\",\"moduleName\":\"C\",\"key\":\"sysOrgUser\",\"desc\":\"人员管理\",\"type\":\"null\",\"isStop\":\"null\",\"order\":\"null\",\"parentKey\":\"sysBasicSet.sysSet.sysOrg.sysOrgUser\"},{\"id\":\"0d6bf073-148e-44ea-8cd7-0717a81341c3\",\"parentId\":\"0423bb7d-5aeb-451d-bd88-ce3163373aba\",\"name\":\"职务管理\",\"moduleName\":\"C\",\"key\":\"sysOrgDuty\",\"desc\":\"职务管理\",\"type\":\"null\",\"isStop\":\"null\",\"order\":\"null\",\"parentKey\":\"sysBasicSet.sysSet.sysOrg.sysOrgDuty\"},{\"id\":\"1bcc3a8e-e648-41a7-9214-a7b72ccc4957\",\"parentId\":\"0423bb7d-5aeb-451d-bd88-ce3163373aba\",\"name\":\"部门管理\",\"moduleName\":\"C\",\"key\":\"sysOrgDepartment\",\"desc\":\"部门管理\",\"type\":\"null\",\"isStop\":\"null\",\"order\":\"null\",\"parentKey\":\"sysBasicSet.sysSet.sysOrg.sysOrgDepartment\"},{\"id\":\"f4c2be78-298f-4188-beac-28788780785b\",\"parentId\":\"bf1260f9-c31c-4064-9b59-120b410708c3\",\"name\":\"系统日志\",\"moduleName\":\"B\",\"key\":\"sysLog\",\"desc\":\"系统日志\",\"type\":\"null\",\"isStop\":\"null\",\"order\":\"null\",\"parentKey\":\"sysBasicSet.sysSet.sysLog\"},{\"id\":\"743ce3e2-22af-4e44-a1dd-6f51f2ed302f\",\"parentId\":\"bf1260f9-c31c-4064-9b59-120b410708c3\",\"name\":\"角色权限\",\"moduleName\":\"B\",\"key\":\"sysRole\",\"desc\":\"角色权限\",\"type\":\"null\",\"isStop\":\"null\",\"order\":\"null\",\"parentKey\":\"sysBasicSet.sysSet.sysRole\"},{\"id\":\"0965569f-791b-4892-900b-54514a8bffdd\",\"parentId\":\"bf1260f9-c31c-4064-9b59-120b410708c3\",\"name\":\"系统权限\",\"moduleName\":\"B\",\"key\":\"sysPermission\",\"desc\":\"系统权限\",\"type\":\"null\",\"isStop\":\"null\",\"order\":\"null\",\"parentKey\":\"sysBasicSet.sysSet.sysPermission\"},{\"id\":\"6c449202-2292-4aeb-b542-f434a8cf85cd\",\"parentId\":\"bf1260f9-c31c-4064-9b59-120b410708c3\",\"name\":\"数据字典\",\"moduleName\":\"B\",\"key\":\"sysData\",\"desc\":\"数据字典\",\"type\":\"null\",\"isStop\":\"null\",\"order\":\"null\",\"parentKey\":\"sysBasicSet.sysSet.sysData\"}]";
	 * @param fieldNameKey
	 * @param fieldNameValue
	 * @param target
	 * @param depth
	 */
	public static void deepGetKeyValueToJson(JSONArray source, String fieldNameKey, String fieldNameValue,
			JSONObject target, int depth) {
		if (depth <= 0 || source == null || target == null) {
			return;
		}
		depth--;
		for (Object entryValue : source) {
			if (JSONObject.class.isAssignableFrom(entryValue.getClass())) {
				deepGetKeyValueToJson((JSONObject) entryValue, fieldNameKey, fieldNameValue, target, depth);
			} else if (JSONArray.class.isAssignableFrom(entryValue.getClass())) {
				deepGetKeyValueToJson((JSONArray) entryValue, fieldNameKey, fieldNameValue, target, depth);
			}
		}
	}
	
	public static void deepGetKeyValueToJson4Type(JSONArray source, String fieldNameKey, String fieldNameValue,
			JSONObject target, int depth) {
		if (depth <= 0 || source == null || target == null) {
			return;
		}
		depth--;
		for (Object entryValue : source) {
			if (JSONObject.class.isAssignableFrom(entryValue.getClass())) {
				deepGetKeyValueToJson((JSONObject) entryValue, fieldNameKey, fieldNameValue, target, depth);
			} else if (JSONArray.class.isAssignableFrom(entryValue.getClass())) {
				deepGetKeyValueToJson((JSONArray) entryValue, fieldNameKey, fieldNameValue, target, depth);
			}
		}
	}

	/**
	 * 字符串 转 JSONArray
	 * 
	 * @param permissionList
	 * @return
	 */
	public static JSONArray stringToJssionArray(String permissionList) {
		if (permissionList == null || permissionList.trim().equals("")) {
			return null;
		}
		try {
			return JSONArray.parseArray(permissionList);
		} catch (Exception e) {
//			logger.error(MarkerManager.getMarker("core.platform"),"JsonUtil.stringToJssionArray","错误信息:{}",e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取指定key的值 单层
	 * 
	 * @param source
	 * @param fieldName
	 * @return
	 */
	public static Object getValue(Set<Entry<String, Object>> set, String fieldName) {
		if (set == null) {
			return null;
		}

		Iterator<Entry<String, Object>> iterator = set.iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			String key = entry.getKey();
			if (fieldName.equals(key)) {
				return entry.getValue();
			}
		}
		return null;
	}

	/**
	 * 已有一个json对象source，获取生成一个新的json对象target（target的key是source的fieldNameKey的值，value是source的fieldNameValue的值）
	 * 
	 * @param source
	 * @param fieldNameKey
	 * @param fieldNameValue
	 * @param target
	 * @param depth
	 */
	public static void deepChangeKey(JSONObject source, String fieldName, String separator, int depth) {
		if (depth <= 0 || source == null || separator == null) {
			return;
		}
		depth--;

		Object parentKeyValue = source.get(fieldName);// eg： parentKey=root.cus
		if (parentKeyValue == null) {
			return;
		}

		JSONObject copyJson = JSONObject.parseObject(source.toString());
		Iterator<Entry<String, Object>> iterator = copyJson.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			Object entryValue = entry.getValue();
			if (JSONObject.class.isAssignableFrom(entryValue.getClass())) {
				deepChangeKey((JSONObject) entryValue, fieldName, separator, depth);
			} else if (JSONArray.class.isAssignableFrom(entryValue.getClass())) {
				deepChangeKey((JSONArray) entryValue, fieldName, separator, depth);
			} else {
				String key = entry.getKey();// eg: key = desc
				String targenKey = parentKeyValue.toString() + separator + entry.getKey(); // eg：
																							// targenKey=root.cus.desc
				source.remove(key);
				source.put(targenKey, entryValue);
			}
		}
	}

	/**
	 * 修改 key 为 parentKey+"."+ key;
	 * 
	 * @param source
	 *            String pStr =
	 *            "[{\"id\":\"fc3a76d6-4676-4d6b-a4fd-aaf238968370\",\"parentId\":\"root\",\"name\":\"基础设置\",\"moduleName\":\"A\",\"key\":\"sysBasicSet\",\"desc\":\"基础设置\",\"type\":\"null\",\"isStop\":\"null\",\"order\":\"null\",\"parentKey\":\"root\"},{\"id\":\"105cff1f-1544-4441-a140-12f70dea6869\",\"parentId\":\"fc3a76d6-4676-4d6b-a4fd-aaf238968370\",\"name\":\"个人设定\",\"moduleName\":\"A\",\"key\":\"sysPensonalsettings\",\"desc\":\"个人设定\",\"type\":\"null\",\"isStop\":\"null\",\"order\":\"null\",\"parentKey\":\"sysBasicSet.sysPensonalsettings\"},{\"id\":\"bf1260f9-c31c-4064-9b59-120b410708c3\",\"parentId\":\"fc3a76d6-4676-4d6b-a4fd-aaf238968370\",\"name\":\"系统设置\",\"moduleName\":\"A\",\"key\":\"sysSet\",\"desc\":\"系统设置\",\"type\":\"null\",\"isStop\":\"null\",\"order\":\"null\",\"parentKey\":\"sysBasicSet.sysSet\"},{\"id\":\"0423bb7d-5aeb-451d-bd88-ce3163373aba\",\"parentId\":\"bf1260f9-c31c-4064-9b59-120b410708c3\",\"name\":\"组织机构\",\"moduleName\":\"B\",\"key\":\"sysOrg\",\"desc\":\"组织机构\",\"type\":\"null\",\"isStop\":\"null\",\"order\":\"null\",\"parentKey\":\"sysBasicSet.sysSet.sysOrg\"},{\"id\":\"34cff25f-8103-4a8c-a515-a9c076d88d91\",\"parentId\":\"0423bb7d-5aeb-451d-bd88-ce3163373aba\",\"name\":\"人员管理\",\"moduleName\":\"C\",\"key\":\"sysOrgUser\",\"desc\":\"人员管理\",\"type\":\"null\",\"isStop\":\"null\",\"order\":\"null\",\"parentKey\":\"sysBasicSet.sysSet.sysOrg.sysOrgUser\"},{\"id\":\"0d6bf073-148e-44ea-8cd7-0717a81341c3\",\"parentId\":\"0423bb7d-5aeb-451d-bd88-ce3163373aba\",\"name\":\"职务管理\",\"moduleName\":\"C\",\"key\":\"sysOrgDuty\",\"desc\":\"职务管理\",\"type\":\"null\",\"isStop\":\"null\",\"order\":\"null\",\"parentKey\":\"sysBasicSet.sysSet.sysOrg.sysOrgDuty\"},{\"id\":\"1bcc3a8e-e648-41a7-9214-a7b72ccc4957\",\"parentId\":\"0423bb7d-5aeb-451d-bd88-ce3163373aba\",\"name\":\"部门管理\",\"moduleName\":\"C\",\"key\":\"sysOrgDepartment\",\"desc\":\"部门管理\",\"type\":\"null\",\"isStop\":\"null\",\"order\":\"null\",\"parentKey\":\"sysBasicSet.sysSet.sysOrg.sysOrgDepartment\"},{\"id\":\"f4c2be78-298f-4188-beac-28788780785b\",\"parentId\":\"bf1260f9-c31c-4064-9b59-120b410708c3\",\"name\":\"系统日志\",\"moduleName\":\"B\",\"key\":\"sysLog\",\"desc\":\"系统日志\",\"type\":\"null\",\"isStop\":\"null\",\"order\":\"null\",\"parentKey\":\"sysBasicSet.sysSet.sysLog\"},{\"id\":\"743ce3e2-22af-4e44-a1dd-6f51f2ed302f\",\"parentId\":\"bf1260f9-c31c-4064-9b59-120b410708c3\",\"name\":\"角色权限\",\"moduleName\":\"B\",\"key\":\"sysRole\",\"desc\":\"角色权限\",\"type\":\"null\",\"isStop\":\"null\",\"order\":\"null\",\"parentKey\":\"sysBasicSet.sysSet.sysRole\"},{\"id\":\"0965569f-791b-4892-900b-54514a8bffdd\",\"parentId\":\"bf1260f9-c31c-4064-9b59-120b410708c3\",\"name\":\"系统权限\",\"moduleName\":\"B\",\"key\":\"sysPermission\",\"desc\":\"系统权限\",\"type\":\"null\",\"isStop\":\"null\",\"order\":\"null\",\"parentKey\":\"sysBasicSet.sysSet.sysPermission\"},{\"id\":\"6c449202-2292-4aeb-b542-f434a8cf85cd\",\"parentId\":\"bf1260f9-c31c-4064-9b59-120b410708c3\",\"name\":\"数据字典\",\"moduleName\":\"B\",\"key\":\"sysData\",\"desc\":\"数据字典\",\"type\":\"null\",\"isStop\":\"null\",\"order\":\"null\",\"parentKey\":\"sysBasicSet.sysSet.sysData\"}]";
	 * @param fieldName
	 * @param separator
	 * @param target
	 * @param depth
	 */
	public static void deepChangeKey(JSONArray source, String fieldName, String separator, int depth) {
		if (depth <= 0 || source == null || separator == null) {
			return;
		}
		depth--;
		for (Object entryValue : source) {
			if (JSONObject.class.isAssignableFrom(entryValue.getClass())) {
				deepChangeKey((JSONObject) entryValue, fieldName, separator, depth);
			} else if (JSONArray.class.isAssignableFrom(entryValue.getClass())) {
				deepChangeKey((JSONArray) entryValue, fieldName, separator, depth);
			}
		}
	}

	/**
	 * 将 json对象中的kv，装入entity对象
	 * 
	 * @param source
	 * @param entity
	 * @param depth
	 */
	public static void intEntityByJson(JSONObject source, Entity entity, int depth) {
		if (depth <= 0 || source == null) {
			return;
		}

		depth--;
		Iterator<Entry<String, Object>> it = source.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			Object value = entry.getValue();
			if (value == null) {
				continue;
			}

			if (value.getClass().isAssignableFrom(JSONObject.class)) {
				intEntityByJson((JSONObject) value, entity, depth);
			} else if (value.getClass().isAssignableFrom(JSONArray.class)) {
				intEntityByJsonArray((JSONArray) value, entity, depth);
			} else {
				String key = entry.getKey();
				entity.setValue(key, value);
			}
		}
	}

	/**
	 * 将 json对象中的kv，装入entity对象
	 * 
	 * @param source
	 * @param entity
	 * @param depth
	 */
	public static void intEntityByJsonArray(JSONArray source, Entity entity, int depth) {
		if (depth <= 0 || source == null) {
			return;
		}
		depth--;

		for (Object value : source) {
			if (value.getClass().isAssignableFrom(JSONObject.class)) {
				intEntityByJson((JSONObject) value, entity, depth);
			} else if (value.getClass().isAssignableFrom(JSONArray.class)) {
				intEntityByJsonArray((JSONArray) value, entity, depth);
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		String mock = "{'externalName':'hell'}";
		//System.out.println(mock);
		
		//mock = StringEscapeUtils.escapeJava("{'externalName':'hello'}");
		//System.out.println(mock);
		
		JSONObject json = JSONObject.parseObject(mock);
		System.out.println(json);
		
//		json.put("imUser", JSONObject.parse("{\"externalName\":\"test\"}"));
	}
}
