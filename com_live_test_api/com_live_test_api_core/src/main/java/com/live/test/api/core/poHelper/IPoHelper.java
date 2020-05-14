package com.live.test.api.core.poHelper;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 * po帮助类，接口
 * 如：map转pojo resultSet转pojo
 * @author live
 * @2020年5月12日 @下午9:59:11
 */
public interface IPoHelper {
	<T> List<T> converToPo(ResultSet rs,Class<T> clazz);

	<T> T converToPo(Map<String, Object> map,Class<T> clazz);
}
