package com.service.core.demo.logging;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Logging interceptor to set the log correlation ID in MDC for tracing the end
 * to end user flow.
 * 
 * @author Sonali Kate
 */
@Component
public class LoggingInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
			throws Exception {
		if (request.getHeader("logCorelationId") != null) {
			String correlationId = request.getHeader("logCorelationId");
			MDC.put("logCorelationId", correlationId);
		}
		return true;
	}
}
