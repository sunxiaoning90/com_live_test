package com.live.spring.aop.xml.hello;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class HelloAspect {

	/**
	 * @Before:前置通知,每一个方法开始之前触发
	 */
	public void before(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		Object [] args = joinPoint.getArgs();
		System.out.println("前置通知（LogAspect）。methodName:" + methodName +",args:" + Arrays.asList(args));
	}
	
	/**
	 * @After:后置通知,每一个方法执行之后触发（无论该方法是否出现异常)
	 */
	public void after(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		System.out.println("后置通知（LogAspect）。methodName:" + methodName);
	}
	
	/**
	 * @AfterReturning:返回通知,每一个方法正常结束之后触发（若出现异常，则不触发)
	 * 返回通知是可以访问到方法的返回值的：returning="result"
	 */
	public void afterRetruning(JoinPoint joinPoint, Object result){
		String methodName = joinPoint.getSignature().getName();
		System.out.println("返回通知（LogAspect）。methodName:" + methodName +",result:" + result);
	}
	
	/**
	 * @AfterThrowing:异常通知,方法遇到异常后触发（若无异常，则不触发)
	 * 可以访问到方法遇到的异常：throwing="e"
	 * 且可以指定在出现特定异常时在执行通知代码（如，遇到空指针才触发）
	 */
	public void afterThrowing(JoinPoint joinPoint, Exception e){
		String methodName = joinPoint.getSignature().getName();
		System.out.println("异常通知（LogAspect）。methodName:" + methodName +",e:" + e);
	}
	
	/**
	 * 环绕通知需要携带 ProceedingJoinPoint 类型的参数. 
	 * 环绕通知类似于动态代理的全过程: ProceedingJoinPoint 类型的参数可以决定是否执行目标方法.
	 * 且环绕通知必须有返回值, 返回值即为目标方法的返回值
	 */
	
	public Object aroundMethod(ProceedingJoinPoint pjd){
		
		String methodName = pjd.getSignature().getName();
		Object [] args = pjd.getArgs();
		Object result = null;
		
		try {
			//前置通知
			System.out.println("环绕通知-前置通知（LogAspect）。methodName:" + methodName +",args:" + Arrays.asList(args));
			//执行目标方法
			result = pjd.proceed();
			//返回通知
			System.out.println("环绕通知-返回通知（LogAspect）。methodName:" + methodName +",result:" + result);
		} catch (Throwable e) {
			//异常通知
			System.out.println("环绕通知-异常通知（LogAspect）。methodName:" + methodName +",e:" + e);
			throw new RuntimeException(e);
		}
		//后置通知
		System.out.println("环绕通知-后置通知（LogAspect）。methodName:" + methodName);
		
		return result;
	}
}
