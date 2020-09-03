package com.service.core.demo.exception;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception handler class to define exceptions.
 * 
 * @author Sonali Kate
 */
@ControllerAdvice
public class DemoExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(DemoExceptionHandler.class);

	private static final String DATE_TIME_FORMAT = "EEEEE MMMMM yyyy-MM-dd HH:mm:ss.SSSZ";

	@ExceptionHandler({ Exception.class, DemoServiceException.class })
	public ResponseEntity<GenericException> exceptionHandler(Exception ex) {
		LOG.error(ex.getMessage(), ex);
		GenericException exception = new GenericException();
		exception.setTimestamp(new SimpleDateFormat(DATE_TIME_FORMAT).format(new Date()));
		exception.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		exception.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		exception.setMessage(ex.getLocalizedMessage());
		return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOG.error(ex.getMessage(), ex);
		GenericException exception = new GenericException();
		exception.setTimestamp(new SimpleDateFormat(DATE_TIME_FORMAT).format(new Date()));
		exception.setStatus(HttpStatus.BAD_REQUEST.value());
		exception.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
		String errorMsg = ex.getBindingResult().getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst().orElse(ex.getMessage());
		exception.setMessage(errorMsg);
		return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
	}
}
