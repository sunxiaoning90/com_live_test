package com.live.test.javase.core.exception;

public class TestExceptionStackTrace {
	public static void main(String[] args) {
		System.out.println(0.1 + 0.2);

		m2();

	}

	public static void m2() {
		m3();
	}

	public static void m3() {
		Exception e = new Exception();

//		Throwable[] suppressed = e.getSuppressed();
//		for (Throwable t : suppressed) {
//			StackTraceElement[] stackTrace = t.getStackTrace();
//			for (StackTraceElement st : stackTrace) {
//
////				Class<? extends StackTraceElement> clazz = st.getClass();
//				String fileName = st.getFileName();
//				String className = st.getClassName();
//				String methodName = st.getMethodName();
//				int lineNumber = st.getLineNumber();
//
//				System.err.println("fileName:" + fileName + ",	className:" + className + ",	methodName:"
//						+ methodName + ",	lineNumber:" + lineNumber);
//			}
//		}

		StackTraceElement[] stackTrace = e.getStackTrace();
		for (StackTraceElement st : stackTrace) {

//			Class<? extends StackTraceElement> clazz = st.getClass();
			String fileName = st.getFileName();
			String className = st.getClassName();
			String methodName = st.getMethodName();
			int lineNumber = st.getLineNumber();

			System.err.println("fileName:" + fileName + ",	className:" + className + ",	methodName:" + methodName
					+ ",	lineNumber:" + lineNumber);
		}
	}
}
