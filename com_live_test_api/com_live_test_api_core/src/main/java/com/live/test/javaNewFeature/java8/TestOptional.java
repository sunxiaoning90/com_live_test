package com.live.test.javaNewFeature.java8;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;

public class TestOptional {

	public static void main(String args[]) {

		TestOptional java8Tester = new TestOptional();
		java8Tester.testFlatMap();
		
		
		Integer value1 = null;
		Integer value2 = new Integer(10);

		// Optional.ofNullable - 允许传递为 null 参数
		Optional<Integer> a = Optional.ofNullable(value1);

		// Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
		Optional<Integer> b = Optional.of(value2);
		System.out.println(java8Tester.sum(a, b));

		
	}

	@Test
	public void testFilter() {
		Optional<String> a = Optional.ofNullable("abc");
		System.out.println(a);

		Predicate<String> predicate = new Predicate<String>() {

			@Override
			public boolean test(String t) {

				return false;
			}
		};

		Optional<String> filter = a.filter(predicate);
		System.out.println(filter); // 如果匹配，返回原始Optional;则返回 Optional.empty
	}

	@Test
	public void testMap() {
		Optional<String> a = Optional.ofNullable("abc");
		System.out.println(a);

		Function<String, String> mapper = new Function<String, String>() {

			@Override
			public String apply(String t) {
				return StringUtils.upperCase(t);
			}
		};

		Optional<String> map = a.map(mapper);

		System.out.println(map);
	}
	
	/**
	 * flatMap 方法与 map 方法的区别在于，map 方法参数中的函数 mapper 输出的是值，然后 map 方法会使用 Optional.ofNullable 将其包装为 Optional；而 flatMap 要求参数中的函数 mapper 输出的就是 Optional。
	 */
	@Test
	public void testFlatMap() {
		Optional<String> a = Optional.ofNullable("abc");
		System.out.println(a);
		
		
		Function<String, Optional<String>> mapper = new Function<String, Optional<String>>() {

			@Override
			public Optional<String> apply(String t) {
				return Optional.ofNullable(StringUtils.upperCase(t));
			}
		};
		
		Optional<String> map = a.flatMap(mapper );
		
		System.out.println(map);
	}

	public Integer sum(Optional<Integer> a, Optional<Integer> b) {

		// Optional.isPresent - 判断值是否存在

		System.out.println("第一个参数值存在: " + a.isPresent());
		System.out.println("第二个参数值存在: " + b.isPresent());

		// Optional.orElse - 如果值存在，返回它，否则返回默认值
		Integer value1 = a.orElse(new Integer(0));

		// Optional.get - 获取值，值需要存在,否则 报异常： java.util.NoSuchElementException: No value
		// present
		Integer value2 = b.get();
		return value1 + value2;
	}
}
