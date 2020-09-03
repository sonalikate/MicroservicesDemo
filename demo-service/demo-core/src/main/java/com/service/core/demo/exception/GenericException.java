package com.service.core.demo.exception;

/**
 * Capture the exception information.
 * 
 * @author Sonali Kate
 *
 */
public class GenericException {

	/** timestamp information of the error. */
	private String timestamp;

	/** HttpStatus information of the error. */
	private int status;

	/** reason phrase of the error. */
	private String error;

	/** detailed information of the error. */
	private String message;

	/** get timestamp. */
	public String getTimestamp() {
		return timestamp;
	}

	/** set timestamp. */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/** get status. */
	public int getStatus() {
		return status;
	}

	/** set status. */
	public void setStatus(int status) {
		this.status = status;
	}

	/** get error. */
	public String getError() {
		return error;
	}

	/** set error. */
	public void setError(String error) {
		this.error = error;
	}

	/** get message. */
	public String getMessage() {
		return message;
	}

	/** set message. */
	public void setMessage(String message) {
		this.message = message;
	}
}
