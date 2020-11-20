package com.live.test.javase.core.threadLocal;

/**
 * <pre>
 * ThreadLocal源码：
 * 	ThreadLocalHelper.set(1);
	Object v = ThreadLocalHelper.get();
	
 * 1)ThreadLocalHelper.set(1);
 * public void set(T value) {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null)
            map.set(this, value);
        else
            createMap(t, value);
    }
    
    
 * 2）Object v = ThreadLocalHelper.get();
 *  public T get() {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null) {
            ThreadLocalMap.Entry e = map.getEntry(this);
            if (e != null) {
                &#64;SuppressWarnings("unchecked")
                T result = (T)e.value;
                return result;
            }
        }
        return setInitialValue();
    }
    
     private T setInitialValue() {
        T value = initialValue();
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null)
            map.set(this, value);
        else
            createMap(t, value);
        return value;
    }
 * </pre>
 * 
 * @author live
 */
public class ThreadLocalTest {

	private static final ThreadLocal<Object> local = new ThreadLocal<Object>();

	public static void remove() {
		local.remove();
	}

	public static void set(Object v) {
		local.set(v);
	}

	public static Object get() {
		return local.get();
	}

	public static void main(String[] args) {
		ThreadLocalHelper.remove();
		ThreadLocalHelper.set(1);
		Object v = ThreadLocalHelper.get();
		System.out.println(v);
	}

}
