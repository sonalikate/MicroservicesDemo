package com.service.composite.demo.exception;

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

import com.demo.common.exception.GenericException;

//TODO - Rename the class name and replace the variable part <compositeservice> with the actual service name. Remove this comment.
/**
 * Exception handler class to define exceptions the service.
 * 
 * @author Sonali Kate
 *
 */
@ControllerAdvice
public class CompositeServiceExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(CompositeServiceExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<GenericException> exceptionHandler(Exception ex) {
		LOG.error(ex.getMessage(), ex);
		GenericException exception = new GenericException();
		exception.setTimestamp(new SimpleDateFormat("EEEEE MMMMM yyyy-MM-dd HH:mm:ss.SSSZ").format(new Date()));
		exception.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		exception.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		exception.setMessage(ex.getLocalizedMessage());
		return new ResponseEntity<GenericException>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOG.error(ex.getMessage(), ex);
		GenericException exception = new GenericException();
		exception.setTimestamp(new SimpleDateFormat("EEEEE MMMMM yyyy-MM-dd HH:mm:ss.SSSZ").format(new Date()));
		exception.setStatus(HttpStatus.BAD_REQUEST.value());
		exception.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
		String errorMsg = ex.getBindingResult().getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst().orElse(ex.getMessage());
		exception.setMessage(errorMsg);
		return new ResponseEntity<Object>(exception, HttpStatus.BAD_REQUEST);
	}
}
