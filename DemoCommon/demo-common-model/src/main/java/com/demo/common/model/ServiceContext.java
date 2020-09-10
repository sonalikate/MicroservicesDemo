package com.demo.common.model;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

public class ServiceContext implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private HttpHeaders headers;

	private Map<String, String> pathParams;

	private MultiValueMap<String, String> queryParams;

	private Object payload;

	public ServiceContext(HttpHeaders headers, Map<String, String> pathParams,
			MultiValueMap<String, String> queryParams, Object payload) {
		this.headers = headers;
		this.pathParams = pathParams;
		this.queryParams = queryParams;
		this.payload = payload;
	}

	public ServiceContext(HttpHeaders headers, Map<String, String> pathParams,
			MultiValueMap<String, String> queryParams) {
		this.headers = headers;
		this.pathParams = pathParams;
		this.queryParams = queryParams;
	}

	public ServiceContext() {
	}

	public HttpHeaders getHeaders() {
		return headers;
	}

	public void setHeaders(HttpHeaders headers) {
		this.headers = headers;
	}

	public String getPathParam(String key) {
		return pathParams.get(key);
	}
	
	public Map<String, String> getPathParams() {
		return pathParams;
	}

	public void setPathParams(Map<String, String> pathParams) {
		this.pathParams = pathParams;
	}

	public MultiValueMap<String, String> getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(MultiValueMap<String, String> queryParams) {
		this.queryParams = queryParams;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}
}
