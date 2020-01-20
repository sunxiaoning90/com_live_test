package com.live.test.javaee.mybatis.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import junit.framework.TestCase;

public class MybatisHelperBySpring extends TestCase {

	private SqlSessionFactory sqlSessionFactory;

	private MybatisHelperBySpring() {
		String mybatisConfig = "mybatis/mybatis-config.xml";
		InputStream in = null;
		try {
			in = Resources.getResourceAsStream(mybatisConfig);
			System.out.println("in:" + in);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
			System.out.println("sqlSessionFactory:" + sqlSessionFactory);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static MybatisHelperBySpring getInstance() {
		return SingletonHold.instance;
	}

	private static class SingletonHold {
		static final MybatisHelperBySpring instance = new MybatisHelperBySpring();
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

}
