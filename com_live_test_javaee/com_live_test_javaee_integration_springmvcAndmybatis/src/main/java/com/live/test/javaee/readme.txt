ssm整合小结(springmvc整合mybatis小结)

一.我的整合思路
一分为二,保持springmvc 和 mybatis均正常跑起来
spring管理mybatis即可

二.整合过程
1.保持springmvc正常跑起来
2.保持mybatis正常跑起来
3.重点配置spring的xml文件:

1)配置数据源bean(datasource),可以是druid\c3p0\spring自身的...
<!-- 数据源设置 -->
	<bean id="dataSource" scope="singleton"
		class="com.alibaba.druid.pool.DruidDataSource" init-method="init"
		destroy-method="close">
		<!-- 基本属性 url、user、password -->
		
		<property name="url" value="jdbc:mysql://xxx:3306/xxx?useUnicode=true&amp;characterEncoding=UTF-8&amp;useServerPrepStmts=false&amp;rewriteBatchedStatements=true" />
		<property name="username" value="xxx" />
		<property name="password" value="xxx" />
	</bean>
	
2)配置SqlSessionFactory对象
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <!-- 注入数据库连接池 -->
        <property name="dataSource" ref="dataSource"/>
        <!-- 扫描pojo包，使用别名配置(在mybatis中可以使用别名，即pojo的名称) -->
       <!--  <property name="typeAliasesPackage" value="cn.tycoding.pojo"/> -->

        <!-- 加载mybatis的配置文件 -->
        <property name="configLocation" value="classpath:mybatis/mybatis-config.xml"/>

        <!-- 扫描Mapper层的配置文件 -->
        <property name="mapperLocations" value="classpath:mybatis/mapper/EvaluateMapper.xml"/>
    </bean>
    
3)使用mybatis的接口代理开发模式(必须保证接口和对应的mybatis的xml名称相同，且在一个文件夹内
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!-- 扫描mapper的配置文件 -->
        <property name="basePackage" value="com.live.test.javaee.mybatis.mapper"/>

        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
    </bean>