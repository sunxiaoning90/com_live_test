MyBatis 开发笔记:MyBatis 脱离 Spring使用（基于xml配置）

一\搭建流程
1、mybatis.xml：数据源、数据库类型、mapper.xml位置
2、po、pomapper
3、mapper.xml：nameapace、id（增删改查）
4、test：mybatis.xml 》in〉sqlsessionfactory》seqsession〉mapper》增删改查

源代码地址：
https://github.com/sunxiaoning90/com_live_test/tree/master/com_live_test_javaee/com_live_test_javaee_mybatis