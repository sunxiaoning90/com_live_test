MyBatis 开发笔记:MyBatis 脱离 Spring使用（基于xml配置）

一、搭建流程
1、创建 mybatis.xml
	数据源、数据库类型、指定mapper.xml位置
2、创建po 及poMapper
3、创建 poMapper.xml
	nameapace、id（增删改查）
4、测试：
		SqlSession sqlSession = MybatisHelper.getInstance().getSqlSessionFactory().openSession();
		EvaluateMapper mapper = sqlSession.getMapper(EvaluateMapper.class);
		Evaluate evaluate = mapper.getEvaluateById(id);

二、MyBatis 执行流程
mybatis.xml > in > sqlsessionfactory > seqsession > mapper >增删改查

更多请参考我的整理：

*我的github源代码地址：
https://github.com/sunxiaoning90/com_live_test/tree/master/com_live_test_javaee/com_live_test_javaee_mybatis

*我的csdn地址：
https://blog.csdn.net/Sunxn1991/article/details/105821471