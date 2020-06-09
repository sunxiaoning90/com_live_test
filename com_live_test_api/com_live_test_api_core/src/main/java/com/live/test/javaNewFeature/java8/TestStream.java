package com.live.test.javaNewFeature.java8;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import org.junit.Test;

/**
 * <pre>
1.java8默认提供哪些函数接口
1.stream中间与最终操作的区别
2.parallelStream与stream实现的区别
3.stream结合lambda表达式实现参数过滤
4.stream实现对mybatis plus 条件查询
 * </pre>
 * @author live
 */
public class TestStream {

	@Test
	public void testIinStream() {
		IntStream s = IntStream.of(1, 2);
		System.out.println(s);

		IntConsumer action = new IntConsumer() {

			@Override
			public void accept(int v) {
				System.out.println(v);
			}
		};
		s.forEach(action);
	}
}
