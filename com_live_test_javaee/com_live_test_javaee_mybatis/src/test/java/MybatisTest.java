
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.util.List;

import org.apache.ibatis.binding.MapperProxy;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.live.test.javaee.mybatis.mapper.EvaluateMapper;
import com.live.test.javaee.mybatis.po.Evaluate;

import junit.framework.TestCase;

/**
 * 
 * <pre>
 * Mybatis 框架中用的设计模式有哪些？
  1、工厂模式
  SqlSessionFactory sqlSessionFactory
  
  2、建造器模式
  SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
  
  3、单例模式
  
  4、适配器模式
  Mybatis 日志模块 sl4j \ log4j2 ...
  
  5、代理模式
   mapperProxyFactory.newInstance(sqlSession);
  
   protected T newInstance(MapperProxy<T> mapperProxy) {
    return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] { mapperInterface }, mapperProxy);
  }
  
  6、模板方法模式
  
  7、装饰器模式
  Cache 类
 * 
 * </pre>
 * 
 * @author live
 */
public class MybatisTest extends TestCase {

	@Test
	public void test1() {
		String mybatisConfig = "mybatis-config.xml";
		InputStream in = null;
		try {
			in = Resources.getResourceAsStream(mybatisConfig);
			System.out.println("in:" + in);
		} catch (IOException e) {
			e.printStackTrace();
		}

		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
		System.out.println("sqlSessionFactory:" + sqlSessionFactory);

		SqlSession sqlSession = sqlSessionFactory.openSession();
		System.out.println("sqlSession:" + sqlSession);

		EvaluateMapper mapper = sqlSession.getMapper(EvaluateMapper.class);

		int id = 1;
		Evaluate evaluate = mapper.getEvaluateById(id);
		System.out.println("evaluate:" + evaluate);

		List<Evaluate> evaluateList = mapper.getEvaluateByAll();
		for (int i = 0; i < evaluateList.size(); i++) {
			Evaluate e = evaluateList.get(i);
			System.out.println("evaluate:" + e);
		}

		int i = mapper.saveEvaluate(new Evaluate(20, "a", "b"));
		sqlSession.commit();
		System.out.println("save:" + i);

	}
}
