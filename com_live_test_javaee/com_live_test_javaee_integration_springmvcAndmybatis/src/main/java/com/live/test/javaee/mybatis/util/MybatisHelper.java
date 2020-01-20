package com.live.test.javaee.mybatis.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import junit.framework.TestCase;

public class MybatisHelper extends TestCase {

	private SqlSessionFactory sqlSessionFactory;

	private MybatisHelper() {
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

	public static MybatisHelper getInstance() {
		return SingletonHold.instance;
	}

	private static class SingletonHold {
		static final MybatisHelper instance = new MybatisHelper();
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

}
