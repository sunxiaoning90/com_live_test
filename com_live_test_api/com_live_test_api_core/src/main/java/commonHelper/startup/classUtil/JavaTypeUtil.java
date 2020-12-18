package commonHelper.startup.classUtil;

import org.junit.Test;

public class JavaTypeUtil {

	/**
	 * clazz.isPrimitive():判断对象是否是原始类型<br>
	 * 仅原始类型返回ture：boolean, byte, char, short, int, long, float, and double.
	 * 
	 * @param args
	 */
	public static boolean ifBase8(Class<?> clazz) {
		if (clazz == null) {
			return false;
		}

		return clazz.isPrimitive();
	}

	@Test
	public void test_ifBase8() {
		boolean flg = ifBase8(String.class);
		System.out.println(flg); // false

		flg = ifBase8(byte.class);
		System.out.println(flg); // true

		int i = 3;
		Object o = i;
		System.out.println(o.getClass().getName() + ", " + o.getClass().isPrimitive());// java.lang.Integer, false
	}

	/**
	 * 判断对象(或者原始数据类型)是否是数组
	 * 
	 * @param array
	 * @return
	 */
	public static boolean isArray(Object array) {
		if (array instanceof Object[]) {
			return true;
		} else if (array instanceof boolean[]) {
			return true;
		} else if (array instanceof byte[]) {
			return true;
		} else if (array instanceof char[]) {
			return true;
		} else if (array instanceof double[]) {
			return true;
		} else if (array instanceof float[]) {
			return true;
		} else if (array instanceof int[]) {
			return true;
		} else if (array instanceof long[]) {
			return true;
		} else if (array instanceof short[]) {
			return true;
		}
		return false;
	}
}
