SpringBoot 开发笔记：使用SpringBoot 整合 SSM


一、搭建过程
1、修改 pom.xml
1）Spring Boot的依赖
2）mybatis starter
	<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<!-- <version>3.5.0-SNAPSHOT</version> -->
			<version>1.3.1</version>
		</dependency>

2、准备 mybatis的 Mapper 和 Mapper.xml(如果使用注解，可以省略）

3、配置 spring 和 mybatis
application.yml

server: 
 port: 8089
 #context-path: /boot

spring: 
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://192.168.1.51:3306/spim2?useUnicode=true&amp;characterEncoding=UTF-8&amp;useServerPrepStmts=false&amp;rewriteBatchedStatements=true
    username: root
    password: spzc1234
  mvc: 
    view: 
      prefix: /
      suffix: .html
      
mybatis:
  mapper-locations: classpath*:mapper/*Mapper.xml

二、
1、数据表结构
DROP TABLE IF EXISTS `im_messageboard`;
CREATE TABLE `im_messageboard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of im_messageboard
-- ----------------------------
INSERT INTO `im_messageboard` VALUES ('1', '1', null);


1、MyBatis 的sql，写在 注解中好？还是写在xml好？
方式一、写在注解@Insert。。。

/**
 * DAO
 * @author live
 */
@Mapper
public interface MessageBoardMapper {
	
	MessageBoard getById(Integer id);
	
	List<MessageBoard> getAll();
	
    int deleteByPrimaryKey(Integer id);

    @Insert("insert into im_messageboard(id, content, time) values (#{id},#{content},#{time})")
    int save(MessageBoard record);
    
}

方式二、写在xml
<!-- 插入 -->
  <insert id="save2" parameterType="com.live.test.javaee.springboot.messageBoard.po.MessageBoard" useGeneratedKeys="true"	keyProperty="id">
		insert into im_messageboard (id, content, time)
		values (#{id,jdbcType=INTEGER},
		#{content,jdbcType=VARCHAR},
		#{time,jdbcType=VARCHAR})
	</insert>

遇到的问题：
1、Mapped Statements collection already contains value for com.live.test.javaee.springboot.messageBoard.mapper.MessageBoardMapper.save
xml 和 注解同时定义了mapper实现方法，冲突了。

更多请访问：
*我的github源代码地址： 
https://github.com/sunxiaoning90/com_live_test/tree/master/com_live_test_javaee/com_live_test_javaee_springboot

*我的csdn地址：
https://blog.csdn.net/Sunxn1991/article/details/105820857
