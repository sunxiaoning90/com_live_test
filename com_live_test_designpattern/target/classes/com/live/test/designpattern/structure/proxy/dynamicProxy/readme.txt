
动态代理

方式一:
JDK Proxy :必须实现至少一个接口才能实现对方法的拦截。
动态代理类:
 * 1.必须实现 InvocationHandler 接口,并且实现该接口中的invoke()
  //import java.lang.reflect.InvocationHandler;
  
优点
1)被代理类增改方法,动态代理类无需做任何改动
2)代理任何接口类


方式二:CGLIB
需要两个jar包：asm.jar和cglib.jar