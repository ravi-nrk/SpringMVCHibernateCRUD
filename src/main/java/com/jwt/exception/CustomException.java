package com.jwt.exception;

public class CustomException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CustomException() {
		
	}
	public CustomException(String exceptionName) {
		super(exceptionName);
	}
	
}
