package com.live.test.javaee.mybatis.mapper;

import java.util.List;

import com.live.test.javaee.mybatis.po.Evaluate;

/**
 * 满意度mapper
 * @author live
 * @2019年12月27日 @下午5:47:09
 */
public interface EvaluateMapper {
	
	Evaluate getEvaluateById(Integer id);
	
	List<Evaluate> getEvaluateByAll();
	
    int deleteByPrimaryKey(Integer id);

    int saveEvaluate(Evaluate record);
    
    
}