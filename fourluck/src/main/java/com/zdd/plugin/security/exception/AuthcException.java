package com.zdd.plugin.security.exception;

/**
 * 认证异常类
 * @author four-luck
 * @since 1.0.0
 * @date 2017年9月14日 下午2:21:31
 */
public class AuthcException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5516549206402368944L;
	
	public AuthcException() {
		super();
	}

	public AuthcException(String message) {
		super(message);
	}
	
	public AuthcException(String message,Throwable cause) {
		super(message,cause);
	}
	
	public AuthcException(Throwable cause) {
		super(cause);
	}
}
