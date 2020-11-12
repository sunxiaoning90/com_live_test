Spring IOC/DI

一、目录
1、Bean依赖注入的三种方式：1）setter方法注入 	2）构造器注入 	3）接口注入（由于bean要依赖额外的接口，一般不用）
2、autowire（自动装配）的4种类型：1）no，需要指定property 或 constructor-arg; 	2)byName 	3)byType	4)constructor
3、Spring 支持注入哪些类型： 基本类型、引用类型、字符串、数组、集合（list\set\map)、props（.properties配置文件）、null、级联赋值、xml
4、配置Spring Bean的两种方式：1）xml 配置方式 2）注解 配置方式
5、	scope(Bean作用域)，共5种：singleton、prototype、request、session、application
6、Spring xml 方式配置bean时，可以使用placeholder 占位符，如读取JDBC配置信息user、password、driverClass、jdbcUrl

二、详解
1、Bean依赖注入的三种方式：
	1）setter方法注入 
	2）构造器注入 
	3）接口注入（由于bean要依赖额外的接口，一般不用）
	
	eg：
	<!-- 1）setter注入 ：属性提供getter() -->
	<bean id="userService1" class="com.live.autowire.UserService" autowire="no">
		<property name="userDao" ref="userDao"></property>
	</bean>
	
	<!-- 2）构造器注入 -->
	<bean id="userService2" class="com.live.autowire.UserService" autowire="no">
		<constructor-arg name="userDao" ref="userDao"></constructor-arg>
	</bean>
	
2、autowire（自动装配）的4种类型：
	1）no，需要指定property 或 constructor-arg; 
	2)byName 
	3)byType 
	4)constructor

	eg：
	<!-- 1）no，需要指定property 或 constructor-arg -->
	<bean id="userService1" class="com.live.autowire.UserService" autowire="no">
		<property name="userDao" ref="userDao"></property>
	</bean>
	<!-- 2)byName -->
	<bean id="userService2" class="com.live.autowire.UserService" autowire="byName"></bean>
	<!-- 3)byType -->
	<bean id="userService3" class="com.live.autowire.UserService" autowire="byType"></bean>
	<!-- 4)constructor -->
	<bean id="userService4" class="com.live.autowire.UserService" autowire="constructor"></bean>
	
3、Spring 支持注入哪些类型： 
基本类型、引用类型、字符串、数组、集合（list\set\map)、props（.properties配置文件）、null、级联赋值、xml
	
!-- 属性注入方式 -->
	<bean id="person" class="com.live.ioc.Person">
		<!-- <property name="name" value="张三"/> -->
		
		<!-- 注入，基本类型 -->
		<property name="age" value="18"/>
		
		<!-- 注入，数组-->
		<property name="array">
			<array>
				<value>a1</value>
				<value>a2</value>
				<value>a3</value>
			</array>
		</property>
		
		<!-- 注入，集合 -->
		<property name="list">
			<list>
				<value>list-1</value>
				<value>list-2</value>
				<value>list-3</value>
			</list>
		</property>
		
		<property name="set">
			<set>
				<value>set-1</value>
				<value>set-2</value>
				<value>set-3</value>
			</set>
		</property>
		
		<property name="map">
			<map>
				<entry key="k1" value="v1"/>
				<entry key="k2" value="v2"/>
				<entry key="k3" value="v3"/>
			</map>
		</property>
		
		<property name="props">
			<props>
				<prop key="k1">v1</prop>
				<prop key="k2">v2</prop>
				<prop key="k3">v3</prop>
			</props>
		</property>
		
		<!-- 注入，引用类型类型 -->
		<property name="car">
			<ref bean="car01"/>
		</property>
		
		<!-- 级联赋值：给注入的car 的属性name赋值 -->
		<property name="car.name">
			<value>奥迪</value>
		</property>
		
		<!-- 注入，空字符串值 -->
		<!-- <property name="name" value=""/> -->
		
		<!-- 注入，null -->
		 <property name="name">
		 	<null></null>
		 </property>
		 
		<!-- 注入，XML特殊字符：使用<![CDATA[]]>标记处理 --> 
		<!-- <property name="name">
			<value><![CDATA[<包含特殊字符>]]></value>
		</property> -->
		
		<!-- 定义内部Bean -->
		<!-- <property name="car">
			<bean class="com.live.ioc.Car"></bean>
		</property> -->
		
		<!-- 定义可复用的bean -->
		 <!-- <util:map id="map">
			<entry key="k1" value="v1" />
			<entry key="k2" value="v2" />
			<entry key="k3" value="v3" />
		</util:map> -->
		
		<!-- <property name="list" value="list-common">
		</property> -->
	</bean>
	
	<!-- 构造器注入方式 -->
	<bean id="person2" class="com.live.ioc2.Person">
		<constructor-arg>
			<value>李四</value>
		</constructor-arg>
		<constructor-arg>
			<value>19</value>
		</constructor-arg>
		<constructor-arg>
			<ref bean="car01"/>
		</constructor-arg>
	</bean>
	
4、配置Spring Bean的两种方式：
1）xml 配置方式
2）注解 配置方式

eg：
1）xml

2）注解 配置方式
2.1）配置自动扫描Bean
<context:component-scan base-package="com.live2.annotation"/>
2.2）使用注解
类级别注解： @Controller、@Service、@Repository、@Component （org.springframework.stereotype.@Component ）
方法级别：@Autowired、@Resource

@Service
public class UserService implements IUserService {

	@Autowired
	IUserDao userDao;

	public UserService() {
		System.out.println("This is UserService Contructor!");
	}

	@Override
	public boolean saveUser(User user) {
		System.out.println("service:saveUser!");
		return userDao.saveUser(user);
	}
}

5、	scope(Bean作用域)，共5种：singleton、prototype、request、session、application
	<bean id="person" class="com.live.scope.Person" scope="singleton"/>
	<bean id="person" class="com.live.scope.Person" scope="prototype"/>
	<bean id="person" class="com.live.scope.Person" scope="request"/>
	<bean id="person" class="com.live.scope.Person" scope="session"/>
	<bean id="person" class="com.live.scope.Person" scope="application"/>
	
6、Spring xml 方式配置bean时，可以使用placeholder 占位符，如读取JDBC配置信息user、password、driverClass、jdbcUrl
	<!-- context:property-placeholder(用于引入外部配置文件) -->
	<context:property-placeholder location="classpath:jdbc.properties"/>
	
	<!-- <bean class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
		<property name="location" value="<context:property-placeholder location="classpath:jdbc.properties"/>"></property>
	</bean> -->
	
	<!-- 使用引入的外部文件配置 -->
	<bean id="jdbcTemplate" class="com.live.propertyPlaceholder.JdbcTemplate">
		<property name="user" value="${jdbc.user}"></property>
		<property name="password" value="${jdbc.password}"></property>
		<property name="driverClass" value="${jdbc.driverClass}"></property>
		<property name="jdbcUrl" value="${jdbc.jdbcUrl}"></property>
	</bean>