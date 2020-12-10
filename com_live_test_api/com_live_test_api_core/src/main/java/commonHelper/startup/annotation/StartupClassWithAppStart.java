package commonHelper.startup.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 系统启动 > 扫描所有 被 @StartupClassWithAppStart 注解标注的类，并执行 类中所有的 被 @StartupMethodWithAppStart 标注的方法
 * 
 * @author live
 * @date 2020年12月2日 下午6:01:36
 */

@Target(value = { ElementType.TYPE })
@Retention(value = RetentionPolicy.RUNTIME)
@Inherited // 允许被继承
@Documented
public @interface StartupClassWithAppStart {
	String[] runMethods() default ""; // 废弃
}
