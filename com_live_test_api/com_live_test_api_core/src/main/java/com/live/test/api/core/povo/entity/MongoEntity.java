//package com.live.test.api.core.povo.entity;
//
//import java.io.Serializable;
//import java.lang.reflect.InvocationTargetException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Stack;
//import java.util.StringTokenizer;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.MarkerManager;
//import org.bson.Document;
//
//import com.alibaba.fastjson.JSONObject;
//import com.live.test.api.core.conver.ConvertUtil;
//
//public class MongoEntity implements Entity,Serializable{
//	private static final long serialVersionUID = 2548466791100954623L;
//	private static Logger logger = LogManager.getLogger(MongoEntity.class);
//	protected Document document;
//	private String entityName;
//	private boolean singleEntity = true;
//	
//	public MongoEntity(String entityName) {
//		document = new Document();
//		this.entityName = entityName;
//	}
//
//	public MongoEntity(String json,String entityName) {
//		try {
//			document = Document.parse(json);
//			this.entityName = entityName;
//		}catch(Exception e) {
//			logger.error(MarkerManager.getMarker("core.mongo"),"MongoEntity()","错误信息:{}",e);
//			e.printStackTrace();
//		}
//		
//	}
//	
//	
//	public MongoEntity(Document document, String entityName) {
//		this.document = document;
//		this.entityName = entityName;
//	}
//	public MongoEntity(Object pojo) {
//		try {
//			this.document = BsonUtil.toBson(pojo);
//		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | SecurityException
//				| NoSuchFieldException e) {
//			logger.error(MarkerManager.getMarker("core.mongo"),"MongoEntity()","错误信息:{}",e);
//			e.printStackTrace();
//		}
//		this.entityName = pojo.getClass().getSimpleName();
//	}
//	public MongoEntity(Object pojo, String entityName) {
//			try {
//				this.document = BsonUtil.toBson(pojo);
//			} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | SecurityException
//					| NoSuchFieldException e) {
//				logger.error(MarkerManager.getMarker("core.mongo"),"MongoEntity()","错误信息:{}",e);
//				e.printStackTrace();
//			}
//		this.entityName = entityName;
//	}
//	@Override
//	public <T> T getPojo(Class<T> clazz) {
//		if (!singleEntity)
//			return null;
//			try {
//				return BsonUtil.toBean(document, clazz);
//			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
//					| InvocationTargetException e) {
//				logger.error(MarkerManager.getMarker("core.mongo"),"MongoEntity.getPojo","错误信息:{}",e);
//				e.printStackTrace();
//			}
//		return null;
//	}
//
//	@Override
//	public <T> T getPojo(String entityName, Class<T> clazz) {
//		if (singleEntity && !this.entityName.equals(entityName))
//			return null;
//		try {
//			return BsonUtil.toBean((Document)document.get(entityName), clazz);
//		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
//				| InvocationTargetException e) {
//			logger.error(MarkerManager.getMarker("core.mongo"),"MongoEntity.getPojo","错误信息:{}",e);
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	
//	@Override
//	public Entity addEntityObject(Object entity, String entityName) {
//		if (singleEntity) {
//			Document tmp = new Document();
//			tmp.put(this.entityName, this.document);
//			this.document = tmp;
//			this.singleEntity = false;
//		}
//		try {
//			if (Document.class.isAssignableFrom(entity.getClass()))
//				document.put(entityName, entity);
//			else if(JSONObject.class.isAssignableFrom(entity.getClass()))
//				document.put(entityName,entity);
//			else
//				document.put(entityName, BsonUtil.toBson(entity));
//		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | SecurityException
//				| NoSuchFieldException e) {
//			logger.error(MarkerManager.getMarker("core.mongo"),"MongoEntity.addEntityObject","错误信息:{}",e);
//			e.printStackTrace();
//		}
//		return this;
//	}
//
//	@Override
//	public String getId() {
//		if (!singleEntity)
//			return null;
////		return document.get("_id");
//		if(document.get("id")!=null) 
//			return document.get("id").toString();
//		return null;
//	}
//
//	@Override
//	public String getId(String entityName) {
//		if (singleEntity && !this.entityName.equals(entityName))
//			return null;
////		return ((Document)document.get("entityName")).get("_id");
//		return ((Document)document.get("entityName")).get("id").toString();
//	}
//
//	@SuppressWarnings("unchecked")
//	static private Object deepGetValue(Object doc, String propertyName) {
//		Object source = doc;
//		StringTokenizer st = new StringTokenizer(propertyName, ".[]");
//		while (st.hasMoreTokens()) {
//			String property = st.nextToken();
//			if (Map.class.isAssignableFrom(source.getClass())) {
//				source = ((Map<String, Object>)source).get(property);
//			} else if (List.class.isAssignableFrom(source.getClass())) {
//				if (ConvertUtil.isInteger(property))
//					source = ((List<Object>)source).get(Integer.parseInt(property));
//				else
//					return null;
//			}
//			if (source == null)
//				return null;
//		}
//		return source;
//	}
//	
//	@Override
//	public Object getValue(String propertyName) {
//		if (propertyName.indexOf('.')>0) {
//			return deepGetValue(document, propertyName);
//		}
//		if (!singleEntity)
//			return null;
//		return document.get(propertyName);
//	}
//
//	@Override
//	public Object getValue(String entityName, String propertyName) {
//		if (singleEntity && !this.entityName.equals(entityName))
//			return null;
//		Document doc = (Document)document.get(entityName);
//		if (doc == null)
//			return null;
//		if (propertyName.indexOf('.')>0) {
//			return deepGetValue(doc, propertyName);
//		}
//		return doc.get(propertyName);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public <T> T getValue(String propertyName, Class<T> clazz) {
//		Object result = getValue(propertyName);
//		if (result == null)
//			return null;
//		if (result.getClass().equals(clazz))
//			return (T)result;
//		return ConvertUtil.convert(result, clazz);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public <T> T getValue(String entityName, String propertyName, Class<T> clazz) {
//		Object result = getValue(entityName, propertyName);
//		if (result == null)
//			return null;
//		if (result.getClass().equals(clazz))
//			return (T)result;
//		return ConvertUtil.convert(result, clazz);
//	}
//	@SuppressWarnings("unchecked")
//	@Override
//	public <T> T getValue(String propertyName, Class<T> clazz, Class<?> convertor) {
//		Object result = getValue(propertyName);
//		if (result == null)
//			return null;
//		if (result.getClass().equals(clazz))
//			return (T)result;
//		return ConvertUtil.convert(result, clazz, false, convertor);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public <T> T getValue(String entityName, String propertyName, Class<T> clazz, Class<?> convertor) {
//		Object result = getValue(entityName, propertyName);
//		if (result == null)
//			return null;
//		if (result.getClass().equals(clazz))
//			return (T)result;
//		return ConvertUtil.convert(result, clazz, false, convertor);
//	}
//	@SuppressWarnings("unchecked")
//	static private String deepSetValueStack(Object doc, String propertyName, Stack<Object> objs, Stack<String> names) {
//		Object source = doc;
//		StringTokenizer st = new StringTokenizer(propertyName, ".[");
//		objs.push(doc);
//		String cur = null;
//		while (st.hasMoreTokens()) {
//			cur = st.nextToken();
//			String property = null;
//			Boolean isArray = false;
//			if (cur.endsWith("]")) {
//				property = cur.substring(0, cur.length()-1);
//				isArray = true;
//			} else {
//				property = cur;
//			}
//			if (source == null) {
//				source = null;
//			} else if (!isArray && Map.class.isAssignableFrom(source.getClass())) {
//				source = ((Map<String, Object>)source).get(property);
//			} else if (isArray && List.class.isAssignableFrom(source.getClass())) {
//				if (ConvertUtil.isInteger(property)) {
//					int index = Integer.parseInt(property);
//					List<Object> tmp = (List<Object>)source;
//					if (tmp.size() > index)
//						source = ((List<Object>)source).get(Integer.parseInt(property));
//					else
//						source = null;
//				} else
//					source = null;
//			} else {
//				source = null;
//			}
//			if (source == null)
//				names.push(cur);
//			else {
////				objs.clear();
//				objs.push(source);
//			}
//		}
//		return cur;
//	}
//	@SuppressWarnings({ "unchecked"})
//	static private Object deepSetValue(Object doc, String propertyName, Object value) {
//		Stack<Object> objs = new Stack<Object>();
//		Stack<String> names = new Stack<String>();
//		String name = deepSetValueStack(doc, propertyName, objs, names);
//		Object source = value;
//		while (!names.isEmpty()) {
//			name = names.pop();
//			Boolean isArray = false;
//			if (name.endsWith("]")) {
//				name = name.substring(0, name.length()-1);
//				isArray = true;
//			}
//			if (names.size() > 0) {
//				if (isArray && ConvertUtil.isInteger(name)){
//					List<Object> newObj = new ArrayList<Object>();
//					newObj.add(Integer.parseInt(name), source);
//					source = newObj;
//				} else {
//					Map<String, Object>newObj = new Document();
//					newObj.put(name, source);
//					source = newObj;
//				}
//			}
//		}
//		if (name.endsWith("]")) {
//			name = name.substring(0, name.length()-1);
//		}
//		while (!objs.isEmpty() ) {
//			Object obj = objs.isEmpty()? doc: objs.pop();
//			if (Map.class.isAssignableFrom(obj.getClass())) {
//				return ((Map<String, Object>)obj).put(name, source);
//			} else if (List.class.isAssignableFrom(obj.getClass())) {
//				if (ConvertUtil.isInteger(name)) {
//					Object result = null;
//					List<Object> tmp = (List<Object>)obj;
//					int index = Integer.parseInt(name);
//					if (tmp.size() > index) {
//						result = tmp.set(index, source);
//					} else {
//						tmp.add(index, source);
//					}
//					return result;
//				}
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public Object setValue(String propertyName, Object value) {
//		if (propertyName.indexOf('.')>0) {
//			return deepSetValue(document, propertyName, value);
//		}
//		if (!singleEntity)
//			return null;
//		return document.put(propertyName, value);
//	}
//
//	/**
//	  * setValueCreate 方法
//	  * <p>方法说明:</p> 设置entity中的属性值，如果该entity对象实体不存在就新增一个
//	  * @param entityName
//	  * @param propertyName
//	  * @param value
//	  * @return
//	  * boolean
//	  * @author fantiyu
//	  * @date Apr 23, 2014
//	*/
//	public Object setValueCreate(String entityName,String propertyName,Object value){
//		if(document==null){
//			document = new Document();
//			this.entityName = entityName;
//		}else{
//			if (singleEntity && !this.entityName.equals(entityName)){
//				addEntityObject(new Document(),entityName);
//			}
//		}
//		Document doc = (Document)document.get(entityName);
//		if (doc == null)
//			doc = document;
//		if (propertyName.indexOf('.')>0) {
//			return deepSetValue(doc, propertyName, value);
//		}
//		return doc.put(propertyName, value);
//	}
//
//	
//	@Override
//	public Object setValue(String entityName, String propertyName, Object value) {
//		if (singleEntity && !this.entityName.equals(entityName))
//			return null;
//		Document doc = (Document)document.get(entityName);
//		if (doc == null)
//			return null;
//		if (propertyName.indexOf('.')>0) {
//			return deepSetValue(doc, propertyName, value);
//		}
//		return doc.put(propertyName, value);
//	}
//	
//	@Override
//	public Object getEntityObject() {
//		return document;
//	}
//	
//	@Override
//	public String getEntityName() {
//		if (singleEntity)
//			return entityName;
//		return null;
//	}
//
//	public boolean getSingleEntity() {
//		return singleEntity;
//	}
//
//	@Override
//	public Collection<String> getEntityNameSet() {
//		if (singleEntity)
//			return null;
//		return document.keySet();
//	}
//
//	
//	@Override
//	public Object getEntityObject(String entityName) {
//		if (singleEntity && !this.entityName.equals(entityName))
//			return null;
//		return document.get(entityName);
//	}
//
//	@SuppressWarnings("unchecked")
//	static private Object deepDropValue(Object doc, String propertyName) {
//		Object source = doc;
//		StringTokenizer st = new StringTokenizer(propertyName, ".[]");
//		while (st.hasMoreTokens()) {
//			String property = st.nextToken();
//			if (st.hasMoreTokens()) {
//				if (source == null)
//					return null;
//				if (Map.class.isAssignableFrom(source.getClass())) {
//					source = ((Map<String, Object>)source).get(property);
//				} else if (List.class.isAssignableFrom(source.getClass())) {
//					if (ConvertUtil.isInteger(property))
//						source = ((List<Object>)source).get(Integer.parseInt(property));
//					else
//						return null;
//				}
//			} else if (source != null){
//				if (Map.class.isAssignableFrom(source.getClass())) {
//					return ((Map<String, Object>)source).remove(property);
//				}
//				if (List.class.isAssignableFrom(source.getClass())) {
//					if (ConvertUtil.isInteger(property))
//						return ((List<Object>)source).remove(Integer.parseInt(property));
//				}
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public Object dropValue(String propertyName) {
//		if (propertyName.indexOf('.')>0) {
//			return deepDropValue(document, propertyName);
//		}
//		if (!singleEntity)
//			return null;
//		return document.remove(propertyName);
//	}
//
//	@Override
//	public Object dropValue(String entityName, String propertyName) {
//		if (singleEntity && !this.entityName.equals(entityName))
//			return null;
//		Document doc = (Document)document.get(entityName);
//		if (doc == null)
//			return null;
//		if (propertyName.indexOf('.')>0) {
//			return deepDropValue(doc, propertyName);
//		}
//		return doc.remove(propertyName);
//	}
//
//	@Override
//	public String getJson() {
//		return document.toJson();
//	}
//
//	@Override
//	public String getJson(String entityName) {
//		if (singleEntity && !this.entityName.equals(entityName))
//			return null;
//		Document obj = (Document)document.get(entityName);
//		return obj.toJson();
//	}
//
//	
//	@Override
//	public String getJsonProperties(String properties) {
//		StringTokenizer st = new StringTokenizer(properties, ",");
//		Document newDoc = new Document();
//		while (st.hasMoreTokens()) {
//			String string = st.nextToken();
//			String name1 = null, name2 = null;
//			int pos = string.indexOf("->");
//			if (pos > 0) {
//				name1 = string.substring(0, pos).trim();
//				name2 = string.substring(pos+2, string.length()).trim();
//			} else {
//				name1 = string.trim();
//				name2 = string.trim();
//			}
//			Object value = null;
//			if (name1.indexOf('.')> 0)
//				value = deepGetValue(document, name1);
//			else
//				value = document.get(name1);
//			if (name2.indexOf(".")>0)
//				deepSetValue(newDoc, name2, value);
//			else
//				newDoc.put(name2, value);
//		}
//		return newDoc.toJson();
//	}
//	
//
//	
//	@Override
//	public Entity updateProperties(Object properties) {
//		if (MongoEntity.class.isAssignableFrom(properties.getClass())) {
//			MongoEntity from = (MongoEntity)properties;
//			deepUpdateValue(from.document, document, false);
//		} else if (Document.class.isAssignableFrom(properties.getClass())) {
//			deepUpdateValue((Document) properties, document, false);
//		}
//		return this;
//	}
//	@Override
//	public Entity updateProperties(Object properties, boolean updateNull) {
//		if (MongoEntity.class.isAssignableFrom(properties.getClass())) {
//			MongoEntity from = (MongoEntity)properties;
//			deepUpdateValue(from.document, document, updateNull);
//		} else if (Document.class.isAssignableFrom(properties.getClass())) {
//			deepUpdateValue((Document) properties, document, updateNull);
//		}
//		return this;
//	}
//	@SuppressWarnings("unchecked")
//	static private void deepUpdateValue(Map<String, Object> doc, Map<String, Object> to, boolean replaceNull) {
//		for (Entry<String, Object> entry: doc.entrySet()) {
//			String key = entry.getKey();
//			if (to.containsKey(key)) {
//				Object val1 = entry.getValue();
//				Object val2 = to.get(key);
//				if (Map.class.isAssignableFrom(val1.getClass())) {
//					if (Map.class.isAssignableFrom(val2.getClass())) {
//						deepUpdateValue((Map<String, Object>) val1, (Map<String,Object>) val2, replaceNull);
//					} else {
//						to.put(key, val1);
//					}
//				} else {
//					if (replaceNull || val1 != null && (!val1.equals("")))
//						to.put(key, val1);
//				}
//			} else {
//				Object val1 = entry.getValue();
//				if (replaceNull || val1 != null && (!val1.equals("")))
//					to.put(entry.getKey(), val1);
//			}
//		}
//	}
//	@Override
//	public String toString() {
//		return getJson();
//	}
//	static public Document updatePropertiesInto(Document from, Document to, boolean replaceNull) {
//		deepUpdateValue(from, to, replaceNull);
//		return to;
//	}
//	
//	@Override
//	public void setEntityName(String entityName) {
//		this.entityName = entityName;
//	}
//	
//	public static void main(String[] args) {
//
//	}
//
//	@Override
//	public String get_Id() {
//		if (!singleEntity)
//			return null;
//		
//		return document.getObjectId("_id").toString();
//	}
//	
//}
