package com.live.test.api.core.startup.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**

 * @author live
 *	@date 2020年12月2日 下午6:01:36
 */

@Target(value = { ElementType.METHOD })
@Retention(value = RetentionPolicy.RUNTIME)
@Inherited //允许被继承
@Documented
public @interface StartupMethodWithAppStart {
	String[] runMethods() default ""; // 废弃
}
