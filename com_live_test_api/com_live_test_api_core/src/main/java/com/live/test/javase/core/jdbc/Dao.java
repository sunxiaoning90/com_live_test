package com.live.test.javase.core.jdbc;


import java.util.List;
/**
 *  增加(Create)
	查询(Retrieve)、更新(Update)、删除(Delete)
	查询：单个、列表、某些字段（id，统计等）
 * @Description:
 * @author:live
 * @date:2018年4月9日 下午12:21:02
 */
public interface Dao<T> {
	/**
	 * 增加
	 * @return boolean
	 */
	boolean save(T entity);
	
	/**
	 * 查询（list）
	 * @return boolean
	 */
	List<T> findOfListByProperty(String property, String value);
	/**
	 * 查询（单个）
	 * @return boolean
	 */
	T findOfSingleById(String id);
	/**
	 * 查询（单个）
	 * @return boolean
	 */
	T findOfSingleByProperty(String property, String value);
	
	/**
	 * 增加
	 * @return boolean
	 */
	boolean update(T entity);
}
