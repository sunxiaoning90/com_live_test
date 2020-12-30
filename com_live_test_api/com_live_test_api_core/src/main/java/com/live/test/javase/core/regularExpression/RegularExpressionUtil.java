package com.live.test.javase.core.regularExpression;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * 
 * @author live
 *
 *       <pre>
 *1、正则表达式的定义
 *       正则表达式定义了字符串的模式。
 *       正则表达式可以用来搜索、编辑或处理文本。
 *       正则表达式并不仅限于某一种语言，但是在每种语言中有细微的差别。
 *       
 *2、语法：
 *	 ^在[]里面为取反 在外面为开头匹配  
     $为结尾匹配
     \\d为数字（其中第一个\是转义） 
     +是匹配一次或者多次
     ?是匹配字表达式的零次或者一次
     []标记表达式开始和结束
     ()子表达式的开始和结束
     .匹配除换行符 \n 之外的任何单字符
     |指明两项之间的一个选择。要匹配 |
 *       </pre>
 */
public class RegularExpressionUtil {
	public static void main(String[] args) {
//		Pattern p = Pattern.compile("a*b");
//		Matcher m = p.matcher("aaaaab");
//		boolean b = m.find();
//		System.out.println(b);
		System.out.println(endWithBrace("aaa}"));
		System.out.println(startWithBraceAndEndWithBrace("{\"code\":0}"));
		System.out.println(over("{\"code\":0}"));
	}

	/**
	 * 当正则完全匹配字符串，从头到尾正好匹配上字符串，matches()方法是true，find()方法为false
	 * 
	 * 当正则只能匹配字符串中的部分内容，matches()方法是fasle ,find()方法是true
	 */
	@Test
	public void testMatchersAndFind() {
		Pattern pattern = Pattern.compile("abc");
		Matcher matcher = pattern.matcher("abcd");

		boolean matchesFlg = matcher.matches();
		System.out.println("matcher.matches() == " + matchesFlg);

		boolean findFlg = matcher.find();
		System.out.println("matcher.find() == " + findFlg);
	}

	public static boolean startWithBraceAndEndWithBrace(String source) {
		Pattern pattern = Pattern.compile("^(\\{)");
		Matcher matcher = pattern.matcher(source);

		return matcher.find();
	}

	public static boolean startWithBrace(String source) {
		Pattern pattern = Pattern.compile("^(\\{)");
		Matcher matcher = pattern.matcher(source);

		return matcher.find();
	}

	public static boolean endWithBrace(String source) {
		Pattern pattern = Pattern.compile("(\\})$");
		Matcher matcher = pattern.matcher(source);

		return matcher.find();
	}

	public static boolean over(String source) {
		String filter = "22AAssfsfs23";
		Pattern pattern = Pattern.compile("[^(\\d+)$]+");
		Matcher matcher = pattern.matcher(filter);
		return matcher.find();
	}

}
