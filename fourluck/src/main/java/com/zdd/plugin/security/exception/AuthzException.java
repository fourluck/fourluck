package com.zdd.plugin.security.exception;

/**
 * 认证异常类
 * @author four-luck
 * @since 1.0.0
 * @date 2017年9月14日 下午2:21:31
 */
public class AuthzException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8516975668099001704L;

	public AuthzException() {
		super();
	}

	public AuthzException(String message) {
		super(message);
	}
	
	public AuthzException(String message,Throwable cause) {
		super(message,cause);
	}
	
	public AuthzException(Throwable cause) {
		super(cause);
	}
}
