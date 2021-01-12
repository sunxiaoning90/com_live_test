package com.live.test.api.core.escape;

import org.apache.commons.lang.StringEscapeUtils;

public class TestEscape {
	public static void main(String[] args) {
//	StringEscapeUtils s = new StringEscapeUtils();

		/**
		 * <pre>
		 * 输出
		 * 
		 * &lt;a&gt;abc&lt;/a&gt; 
		 * <a>abc</a>
		 * </pre>
		 */
		System.out.println(StringEscapeUtils.escapeHtml("<a>abc</a>"));
		System.out.println(StringEscapeUtils.unescapeHtml("&lt;a&gt;abc&lt;/a&gt;"));

		/**
		 * <pre>
		 *输出
		
		<script>alert(\'123\')<script>
		<script>alert('123')<script>
		 * </pre>
		 */
		System.out.println(StringEscapeUtils.escapeJavaScript("<script>alert('123')<script>"));
		System.out.println(StringEscapeUtils.unescapeJavaScript("<script>alert(\'123\')<script>"));

		/**
		 * <pre>
		 *输出
		
		\u4F60\u597D
		你好
		 * </pre>
		 */
		System.out.println(StringEscapeUtils.escapeJava("你好"));
		System.out.println(StringEscapeUtils.unescapeJava("\u4F60\u597D"));
	}
}
