package com.live.test.javaNewFeature.java8;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * <pre>
1、Optional，我们便可以方便且优雅的在自己的代码中处理 null 值，而不再需要一昧通过容易忘记和麻烦的 if (object != null) 来判断值不为 null。
 
2、JDK 提供三个静态方法来构造一个Optional：
2.1 Optional.of(T value)，该方法通过一个非 null 的 value 来构造一个 Optional，返回的 Optional 包含了 value 这个值。对于该方法，传入的参数一定不能为 null，否则便会抛出 NullPointerException。
2.2 Optional.ofNullable(T value)，该方法和 of 方法的区别在于，传入的参数可以为 null , 但是前面 javadoc 不是说 Optional 只能包含非 null 值吗？原来该方法会判断传入的参数是否为 null，如果为 null 的话，返回的就是 Optional.empty()。
2.3 Optional.empty()，该方法用来构造一个空的 Optional，即该 Optional 中不包含值,其实底层实现还是 如果 Optional 中的 value 为 null 则该 Optional 为不包含值的状态，然后在 API 层面将 Optional 表现的不能包含 null 值，使得 Optional 只存在 包含值 和 不包含值 两种状态。
 * </pre>
 * 
 * @author live
 */
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
	 * flatMap 方法与 map 方法的区别在于，map 方法参数中的函数 mapper 输出的是值，然后 map 方法会使用
	 * Optional.ofNullable 将其包装为 Optional；而 flatMap 要求参数中的函数 mapper 输出的就是 Optional。
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

		Optional<String> map = a.flatMap(mapper);

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
