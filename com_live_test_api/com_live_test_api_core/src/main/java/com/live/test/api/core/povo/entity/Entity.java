package com.live.test.api.core.povo.entity;

import java.util.Collection;

public interface Entity {
	public <T> T getPojo(Class<T> clazz);
	public <T> T getPojo(String entityName, Class<T> clazz);
	public Object addEntityObject(Object entity, String entityName);
	public String getId();
	public String getId(String entityName);
	public Object getValue(String propertyName);
	public Object getValue(String entityName, String propertyName);
	public  <T> T getValue(String propertyName, Class<T> clazz);
	public  <T> T getValue(String entityName, String propertyName, Class<T> clazz);
	public  <T> T getValue(String propertyName, Class<T> clazz, Class<?> convertor);
	public  <T> T getValue(String entityName, String propertyName, Class<T> clazz, Class<?> convertor);
	public Object setValue(String propertyName, Object value);
	public Object setValue(String entityName, String propertyName, Object value);
	public Object setValueCreate(String entityName,String propertyName,Object value);
	public Entity updateProperties(Object properties);
	public Entity updateProperties(Object properties, boolean updateNull);
	public Object getEntityObject();
	public String getEntityName();
	public Collection<String> getEntityNameSet();
	public Object getEntityObject(String entityName);
	public Object dropValue(String propertyName);
	public Object dropValue(String entityName, String propertyName);
	public String getJson();
	public String getJson(String entityName);
	public String getJsonProperties(String properties);
	public void setEntityName(String entityName);
	public String get_Id();
	public boolean getSingleEntity();
	
}
