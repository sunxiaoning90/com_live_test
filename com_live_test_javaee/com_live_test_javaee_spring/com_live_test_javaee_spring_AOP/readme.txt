Spring开发笔记：主要整理 AOP

AOP八个概念，个人理解：
AOP代理 目标对象 ->（切面、（通知、切入点））-> 代理对象
连接点、织入

getBean（） -->
	ApplicationContext	-->
			AdvisedSupport	-->
				AopConfig -->
					Advice <-- JdkDynamicAopProxy

AbstractAutowireCapableBeanFactory 创建bean记录：
org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory
protected Object createBean(String beanName, RootBeanDefinition mbd, Object[] args) throws BeanCreationException {}					
proxyTargetClass=false; optimize=false; opaque=false; exposeProxy=false; frozen=false


一、AOP概念
1、*切面（Aspect）：一个关注点的模块化，这个关注点可能会横切多个对象。事务管理是Java应用程序中一个关于横切关注点的很好的例子。在Spring AOP中，切面可以使用通过类（基于模式（XML）的风格）或者在普通类中以@Aspect注解（AspectJ风格）来实现。

eg：

@Aspect
@Component
public class HelloAspect {

	//1、通知(Advice)中的前置通知：@Before
	//2、切入点表达式:	execution(public void com.live.aop.hello.Hello.sayHello())
	//3、通知（Advice）跟切入点表达式关联
	@Before("execution(public void com.live.aop.hello.Hello.sayHello())")
	public void before(JoinPoint joinPoint) {
		System.out.println("前置通知！");
	}
}

2、连接点（Join point）：程序执行过程中某个特定的点，比如某方法调用的时候或者处理异常的时候。在Spring AOP中一个连接点总是代表一个方法的执行。

个人理解：AOP拦截到的方法就是一个连接点。通过声明一个org.aspectj.lang.JoinPoint类型参数我们可以在通知(Advice)中获得连接点的信息。这个在稍后会给出案例。

3、*通知(Advice)：在切面（Aspect）的某个特定连接点上（Join point）执行的动作。通知的类型包括"around"，"before"，"after"等等。通知的类型将在后面进行讨论。许多AOP框架，包括Spring 都是以拦截器作为通知的模型，并维护一个以连接点为中心的拦截器链。总之就是AOP对连接点的处理通过通知来执行。

个人理解：Advice指当一个方法被AOP拦截到的时候要执行的代码。

	通知(Advice)的类型：
	1）前置通知（Before advice）：在某个连接点（Join point）之前执行的通知，但这个通知不能阻止连接点的执行（除非它抛出一个异常）。
	2）返回后通知（After returning advice）：在某个连接点（Join point）正常完成后执行的通知。例如，一个方法没有抛出任何异常正常返回。
	抛出异常后通知（After throwing advice）：在方法抛出异常后执行的通知。
	3）后置通知（After（finally）advice）：当某个连接点（Join point）退出的时候执行的通知（不论是正常返回还是发生异常退出）。
	4）环绕通知（Around advice）：包围一个连接点（Join point）的通知，如方法调用。这是最强大的一种通知类型。环绕通知可以在方法前后完成自定义的行为。它也会选择是否继续执行连接点或直接返回它们自己的返回值或抛出异常来结束执行。

4、*切入点（Pointcut）：匹配连接点（Join point）的断言。通知（Advice）跟切入点表达式关联，并在与切入点匹配的任何连接点上面运行。切入点表达式如何跟连接点匹配是AOP的核心，Spring默认使用AspectJ作为切入点语法。

个人理解：通过切入点的表达式来确定哪些方法要被AOP拦截，之后这些被拦截的方法会执行相对应的Advice代码。

5、引入（Introduction）：声明额外的方法或字段。Spring AOP允许你向任何被通知(Advice)对象引入一个新的接口（及其实现类）。

个人理解：AOP允许在运行时动态的向代理对象实现新的接口来完成一些额外的功能并且不影响现有对象的功能。

6、目标对象（Target object）：被一个或多个切面（Aspect）所通知（Advice）的对象，也称作被通知对象。由于Spring AOP是通过运行时代理实现的，所以这个对象永远是被代理对象。

个人理解：所有的对象在AOP中都会生成一个代理类，AOP整个过程都是针对代理类在进行处理。

7、AOP代理（AOP proxy）：AOP框架创建的对象，用来实现切面契约（aspect contract）（包括通知方法执行等功能），在Spring中AOP可以是JDK动态代理或者是CGLIB代理。

8、织入（Weaving）：把切面（aspect）连接到其他的应用程序类型或者对象上，并创建一个被通知对象。这些可以在编译时（例如使用AspectJ编译器），类加载时和运行时完成。Spring和其他纯AOP框架一样，在运行时完成织入。

个人理解：把切面跟对象关联并创建该对象的代理对象的过程。


一、注意：
1、Spring和其他纯AOP框架一样，在运行时完成织入。

*我的github源代码地址： 
    https://github.com/sunxiaoning90/com_live_test/tree/master/com_live_test_javaee/com_live_test_javaee_mybatis

*我的csdn地址：
    https://blog.csdn.net/Sunxn1991/article/details/105811427