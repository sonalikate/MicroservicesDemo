package com.demo.common.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect Logger to log Nemo-Microservices Service methods details.
 * 
 * @author Sonali Kate
 */
@Aspect
@Component
public class LoggingAspect {

	private static final ILoggerFactory loggerFactory = LoggerFactory.getILoggerFactory();

	/**
	 * This Method logs the any @LOGGABLE method entry and exit logs.
	 * 
	 * @param pjp ProceedingJoinPoint
	 * @throws Throwable
	 */
	@Around(value = "@annotation(com.tsystem.nemo.common.logging.Loggable)")
	public Object logMethodDebugInfo(final ProceedingJoinPoint pjp) throws Throwable {
		Object proceed = null;
		Class<?> targeClass = pjp.getTarget().getClass();
		Logger logger = loggerFactory.getLogger(targeClass.getCanonicalName());
		StringBuilder loggerBuilder;
		String targeClassName = targeClass.getSimpleName();
		CodeSignature signature = (CodeSignature) pjp.getSignature();
		loggerBuilder = new StringBuilder();
		loggerBuilder.append(targeClassName).append("[").append(signature.getName()).append("]");
		logger.info("Method START: {} ", loggerBuilder);
		proceed = pjp.proceed();
		loggerBuilder = new StringBuilder();
		loggerBuilder.append(targeClassName).append("[").append(signature.getName()).append("]");
		logger.info("Method END: {}", loggerBuilder);
		loggerBuilder.setLength(0);
		return proceed;
	}
}
