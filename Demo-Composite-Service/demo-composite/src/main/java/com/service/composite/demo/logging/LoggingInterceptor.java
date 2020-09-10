package com.service.composite.demo.logging;

import static com.demo.common.util.UtilityConstants.LOG_CO_RELATION_ID;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * @author Sonali Kate
 *
 */
@Component
public class LoggingInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
			throws Exception {
		if (request.getHeader(LOG_CO_RELATION_ID) != null) {
			String correlationId = request.getHeader(LOG_CO_RELATION_ID).toString();
			MDC.put(LOG_CO_RELATION_ID, correlationId);
		}
		return true;
	}

	@Override
	public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
			final Object handler, final Exception ex) throws Exception {
		// MDC.remove(CORRELATION_ID_HEADER_NAME);
	}

	private String getCorrelationIdFromHeader(final HttpServletRequest request) {
		String correlationId = request.getHeader(LOG_CO_RELATION_ID);
		if (StringUtils.isNotBlank(correlationId)) {
			correlationId = generateUniqueCorrelationId();
		}
		return correlationId;
	}

	private String generateUniqueCorrelationId() {
		return UUID.randomUUID().toString();
	}
}
