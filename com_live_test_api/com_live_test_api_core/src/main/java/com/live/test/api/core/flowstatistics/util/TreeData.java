package com.live.test.api.core.flowstatistics.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

import com.google.gson.Gson;
import com.live.test.api.core.conver.ConvertUtil;



public class TreeData {
	private Map<String, Object> document = new HashMap<String, Object>();
	@SuppressWarnings("unchecked")
	static private void deepSetValueStack(Object doc, String propertyName, Stack<Object> objs, Stack<String> names) {
		Object source = doc;
		StringTokenizer st = new StringTokenizer(propertyName, ".[");
		while (st.hasMoreTokens()) {
			String cur = st.nextToken();
			String property = null;
			Boolean isArray = false;
			if (cur.endsWith("]")) {
				property = cur.substring(0, cur.length()-1);
				isArray = true;
			} else {
				property = cur;
			}
			if (source == null) {
				source = null;
			} else if (!isArray && Map.class.isAssignableFrom(source.getClass())) {
				source = ((Map<String, Object>)source).get(property);
			} else if (isArray && List.class.isAssignableFrom(source.getClass())) {
				if (ConvertUtil.isInteger(property)) {
					int index = Integer.parseInt(property);
					List<Object> tmp = (List<Object>)source;
					if (tmp.size() > index)
						source = ((List<Object>)source).get(Integer.parseInt(property));
					else
						source = null;
				} else
					source = null;
			} else {
				source = null;
			}
			if (source == null)
				names.push(cur);
			else {
				objs.empty();
				objs.push(source);
			}
		}
	}
	@SuppressWarnings("unchecked")
	static private Object deepSetValue(Object doc, String propertyName, Object value) {
		Stack<Object> objs = new Stack<Object>();
		Stack<String> names = new Stack<String>();
		deepSetValueStack(doc, propertyName, objs, names);
		Object source = value;
		while (!names.isEmpty()) {
			String name = names.pop();
			Boolean isArray = false;
			if (name.endsWith("]")) {
				name = name.substring(0, name.length()-1);
				isArray = true;
			}
			if (names.size() > 0) {
				if (isArray && ConvertUtil.isInteger(name)){
					List<Object> newObj = new ArrayList<Object>();
					newObj.add(Integer.parseInt(name), source);
					source = newObj;
				} else {
					Map<String, Object>newObj = new HashMap<String, Object>();
					newObj.put(name, source);
					source = newObj;
				}
			} else {
				Object obj = objs.isEmpty()? doc: objs.pop();
				if (Map.class.isAssignableFrom(obj.getClass())) {
					return ((Map<String, Object>)obj).put(name, source);
				} else if (List.class.isAssignableFrom(obj.getClass())) {
					if (ConvertUtil.isInteger(name)) {
						Object result = null;
						List<Object> tmp = (List<Object>)obj;
						int index = Integer.parseInt(name);
						if (tmp.size() > index)
							result = tmp.get(index);
						tmp.add(index, source);
						return result;
					}
				}
			}
		}
		return null;
	}

	public Object setValue(String propertyName, Object value) {
		if (propertyName.indexOf('.')>0) {
			return deepSetValue(document, propertyName, value);
		}
		return document.put(propertyName, value);
	}

	public Object setValue(String entityName, String propertyName, Object value) {
		@SuppressWarnings("unchecked")
		Map<String, Object> doc = (Map<String, Object>)document.get(entityName);
		if (doc == null)
			return null;
		if (propertyName.indexOf('.')>0) {
			return deepSetValue(doc, propertyName, value);
		}
		return doc.put(propertyName, value);
	}
	public String getJson() {
		Gson gson = new Gson();
		return gson.toJson(document);
	}
}
