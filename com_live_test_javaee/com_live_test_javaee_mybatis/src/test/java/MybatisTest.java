
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.live.test.javaee.mybatis.mapper.EvaluateMapper;
import com.live.test.javaee.mybatis.po.Evaluate;

import junit.framework.TestCase;

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
		
		evaluate = mapper.getEvaluateById(id);
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
