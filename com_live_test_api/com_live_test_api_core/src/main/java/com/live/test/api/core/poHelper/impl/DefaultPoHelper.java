package com.live.test.api.core.poHelper.impl;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.live.test.api.core.poHelper.IPoHelper;
import com.live.test.api.core.poHelper.po.WorkTime;
import com.live.test.api.core.reflect.ReflectUtil;
import com.live.test.javase.core.jdbc.JdbcTemplate;

/**
 * po帮助类
 * @author live
 * @2020年5月12日 @下午9:58:47
 */
public class DefaultPoHelper implements IPoHelper {

	@Override
	public <T> List<T> converToPo(ResultSet rs, Class<T> clazz) {
		if (rs == null) {
			return null;
		}

		List<T> result = null;
		try {
//			int row = rs.getRow();
			while (rs.next()) {

				ResultSetMetaData metaData = rs.getMetaData();

				if (metaData == null) {
					return null;
				}

				int cllumnCount = metaData.getColumnCount();
				result = new ArrayList<T>(cllumnCount);
				Map<String, Object> map = new HashMap<String, Object>(cllumnCount);
				for (int i = 1; i <= cllumnCount; i++) {
					map.put(rs.getMetaData().getColumnLabel(i), rs.getObject(i));
				}

//				Class clazz = null;//T.getc
				T t = ReflectUtil.converToPoJo(map, clazz);
				if (t != null) {
					result.add(t);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public <T> T converToPo(Map<String, Object> map, Class<T> clazz) {
		return ReflectUtil.converToPoJo(map, clazz);
	}

	public static void main(String[] args) {
		ResultSet rs = new JdbcTemplate().executeQuery("select * from xxx limit 0,1");
		IPoHelper poHelper = new DefaultPoHelper();
		List<WorkTime> list = poHelper.converToPo(rs, WorkTime.class);
		for (int i = 0; i < list.size(); i++) {
			WorkTime po = list.get(i);
			System.out.println(po);
		}
	}
}
