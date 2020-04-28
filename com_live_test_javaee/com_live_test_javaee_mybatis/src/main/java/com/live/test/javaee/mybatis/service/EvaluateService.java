package com.live.test.javaee.mybatis.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.live.test.javaee.mybatis.mapper.EvaluateMapper;
import com.live.test.javaee.mybatis.po.Evaluate;
import com.live.test.javaee.mybatis.util.MybatisHelper;

public class EvaluateService {
	@Test
	public Evaluate getEvaluateById(int id) {
		SqlSession sqlSession = MybatisHelper.getInstance().getSqlSessionFactory().openSession();
		System.out.println("sqlSession:" + sqlSession);

		EvaluateMapper mapper = sqlSession.getMapper(EvaluateMapper.class);
		System.out.println("mapper:" + mapper);

		Evaluate evaluate = mapper.getEvaluateById(id);
		System.out.println("evaluate:" + evaluate);
		return evaluate;
	}

	public List<Evaluate> getEvaluateAll() {
		SqlSession sqlSession = MybatisHelper.getInstance().getSqlSessionFactory().openSession();
		System.out.println("sqlSession:" + sqlSession);

		EvaluateMapper mapper = sqlSession.getMapper(EvaluateMapper.class);
		System.out.println("mapper:" + mapper);

		List<Evaluate> list = mapper.getEvaluateByAll();
		for (int i = 0; i < list.size(); i++) {
			Evaluate e = list.get(i);
			System.out.println("evaluate:" + e);
		}

		return list;
	}

	public boolean saveEvaluate(Evaluate po) {
		SqlSession sqlSession = MybatisHelper.getInstance().getSqlSessionFactory().openSession();
		System.out.println("sqlSession:" + sqlSession);

		EvaluateMapper mapper = sqlSession.getMapper(EvaluateMapper.class);
		System.out.println("mapper:" + mapper);

		int i = mapper.saveEvaluate(po);
		sqlSession.commit();
		System.out.println("save:" + i);

		return true;
	}
}
