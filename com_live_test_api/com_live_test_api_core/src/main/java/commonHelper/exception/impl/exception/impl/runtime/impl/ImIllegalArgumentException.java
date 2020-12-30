package commonHelper.exception.impl.exception.impl.runtime.impl;

import commonHelper.exception.ImThrowable;

public class ImIllegalArgumentException extends IllegalArgumentException implements ImThrowable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ImIllegalArgumentException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ImIllegalArgumentException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ImIllegalArgumentException(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	public ImIllegalArgumentException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
