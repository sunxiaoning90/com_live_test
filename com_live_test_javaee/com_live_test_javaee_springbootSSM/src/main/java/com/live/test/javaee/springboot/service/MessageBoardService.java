package com.live.test.javaee.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.live.test.javaee.springboot.messageBoard.mapper.MessageBoardMapper;
import com.live.test.javaee.springboot.messageBoard.po.MessageBoard;

@Service
public class MessageBoardService {
	
	@Autowired
	private MessageBoardMapper mapper;
	
	public MessageBoard getEvaluateById(int id) {
//		SqlSession sqlSession = MybatisHelper.getInstance().getSqlSessionFactory().openSession();
//		System.out.println("sqlSession:" + sqlSession);
//
//		EvaluateMapper mapper = sqlSession.getMapper(EvaluateMapper.class);
		System.out.println("mapper:" + mapper);

		MessageBoard evaluate = mapper.getById(id);
		System.out.println("evaluate:" + evaluate);
		return evaluate;
	}
	
	/**
	 * dao脱离spring
	 * @param id
	 * @return
	 */
//	public MessageBoard getEvaluateById2(int id) {
//		SqlSession sqlSession = MybatisHelper.getInstance().getSqlSessionFactory().openSession();
//		System.out.println("sqlSession:" + sqlSession);
//		
//		MessageBoardMapper mapper = sqlSession.getMapper(MessageBoardMapper.class);
//		System.out.println("mapper:" + mapper);
//		
//		Evaluate evaluate = mapper.getEvaluateById(id);
//		System.out.println("evaluate:" + evaluate);
//		return evaluate;
//	}
	

	public boolean save(MessageBoard po) {

		System.out.println("mapper:" + mapper);

		int i = mapper.save(po);
		System.out.println("save:" + i);

		return true;
	}
}
