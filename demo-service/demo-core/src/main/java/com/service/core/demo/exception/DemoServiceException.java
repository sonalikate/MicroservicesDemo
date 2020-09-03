package com.service.core.demo.exception;

import java.io.Serializable;

/**
 * Service exception class to capture error.
 * 
 * @author Sonali Kate
 *
 */
public class DemoServiceException extends RuntimeException implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	public DemoServiceException(String message) {
		super(message);
	}

	public DemoServiceException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
