package com.live.spring.tx.XML.hello.exception;

public class AccountException extends RuntimeException{

	/** 
	 * @Fields serialVersionUID:
	 */ 
	private static final long serialVersionUID = -4266936739352325969L;

	public AccountException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public AccountException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AccountException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AccountException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
