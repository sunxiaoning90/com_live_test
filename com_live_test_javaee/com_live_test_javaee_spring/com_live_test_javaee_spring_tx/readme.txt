Spring 开启事务的两种方式：

一、注解方式：

1、配置xml，开启注解方式
<!-- 前提：需要加入 aop 对应的 jar 包 （spring-aop-4.0.3.RELEASE.jar-->

	<!-- 1、配置事务管理器Bean -->
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource"></property>
	</bean>
	
	<!-- 2、启用事务注解 -->
	<tx:annotation-driven transaction-manager="transactionManager"/>
	<!-- transaction-manager属性的默认值为transactionManager,可以省略 -->
	<!-- <tx:annotation-driven/> -->
	
2、使用 @Transactional 标记需要开启事务的方法
@Service("accountService")
public class AccountService {
	@Autowired
	IAccountDao dao;

	@Transactional
	public boolean transferMomeny(String form, String to, double money) {
		boolean payFlg = dao.payMomeny(form, money);
		if (payFlg) {
			System.out.println("付款成功，账户：" + form + "，付款：" + money);
		}

		boolean reciveFlg = dao.reciveMomeny(to, money);
		if (reciveFlg) {
			System.out.println("收款成功，账户：" + form + "，收款：" + money);
		}

		return payFlg && reciveFlg;
	}

}


二、xml配置事务方式
1、配置xml

	<!-- 第一部分：配置普通Bean -->
	<bean id="accountService"
		class="com.live.spring.tx.XML.hello.service.AccountService">
		<property name="dao" ref="accountDao"></property>
	</bean>
	<bean id="accountDao"
		class="com.live.spring.tx.XML.hello.dao.AccountDao">
		<property name="jt" ref="jdbcTemplate"></property>
	</bean>

	<!-- 第二部分：配置dataSource（数据源） -->
	<!-- 引入db配置文件，通过context:property-placeholder方式 -->
	<context:property-placeholder
		location="classpath:jdbc.properties" />

	<!-- 配置数据源 -->
	<bean id="dataSource"
		class="org.springframework.jdbc.datasource.DriverManagerDataSource">
		<property name="url" value="${jdbc.url}" />
		<property name="driverClassName"
			value="${jdbc.driverClassName}" />
		<property name="username" value="${jdbc.username}" />
		<property name="password" value="${jdbc.password}" />
	</bean>

	<!-- 第三部分：配置 JDBC（使用哪种工具如：原生JDBC、Spring JDBCTemplate、Hibernate等） -->
	<!-- 配置Spring JDBCTemplate -->
	<bean id="jdbcTemplate"
		class="org.springframework.jdbc.core.JdbcTemplate">
		<property name="dataSource" ref="dataSource"></property>
	</bean>

	<!-- 第四部分：配置Spring声明式事务 -->
	<!-- 1. 配置事务管理器 -->
	<bean id="transactionManager"
		class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource"></property>
	</bean>

	<!-- 2. 配置事务属性 propagation: - REQUIRED - SUPPORTS - MANDATORY - REQUIRES_NEW 
		- NOT_SUPPORTED - NEVER - NESTED -->
	<tx:advice id="txAdvice"
		transaction-manager="transactionManager">
		<tx:attributes>
			<!-- 根据方法名指定事务的属性 -->
			<tx:method name="transferMomeny" propagation="REQUIRES_NEW" />
			<tx:method name="get*" read-only="true" />
			<tx:method name="find*" read-only="true" />
			<tx:method name="*" />
		</tx:attributes>
	</tx:advice>

	<!-- 3. 配置事务切入点, 以及把事务切入点和事务属性关联起来 -->
	<aop:config>
		<aop:pointcut
			expression="execution(* com.live.spring.tx.XML.hello.service.AccountService.*(..))"
			id="txPointCut" />
		<aop:advisor advice-ref="txAdvice"
			pointcut-ref="txPointCut" />
	</aop:config>
</beans>

2、需要开启事务的方法无需做改动，xml中切点表达式指定好规则即可


*事务的传播
propagation: 
	- REQUIRED 
	- SUPPORTS 
	- MANDATORY 
	- REQUIRES_NEW 
	- NOT_SUPPORTED 
	- NEVER 
	- NESTED