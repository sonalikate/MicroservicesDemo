package com.demo.common.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

public class ServiceRequest implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String url;

	private HttpHeaders headers;

	private Map<String, String> pathParams;

	private MultiValueMap<String, String> queryParams;

	private Object payload;

	public ServiceRequest() {

	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public java.lang.Object getPayload() {
		return payload;
	}

	public void setPayload(java.lang.Object payload) {
		this.payload = payload;
	}

	public HttpHeaders getHttpHeaders() {
		return headers;
	}

	public List<String> getHttpHeader(String key) {
		return headers.get(key);
	}

	public void addHttpHeader(String key, String value) {
		headers.add(key, value);
	}

	public void addHttpHeader(String key, List<String> values) {
		headers.addAll(key, values);
	}

	public void addHttpHeaders(HttpHeaders header) {
		headers.addAll(header);
	}

	public void removeHeader(String key) {
		headers.remove(key);
	}

	public Map<String, String> getPathParams() {
		return pathParams;
	}

	public String getPathParam(String key) {
		return pathParams.get(key);
	}

	public void addPathParam(String key, String value) {
		this.pathParams.put(key, value);
	}

	public void addPathParams(Map<String, String> pathParam) {
		this.pathParams.putAll(pathParam);
	}

	public void removePathParam(String key) {
		this.pathParams.remove(key);
	}

	public MultiValueMap<String, String> getQueryParams() {
		return queryParams;
	}

	public List<String> getQueryParam(String key) {
		return queryParams.get(key);
	}

	public void addQueryParam(String key, String value) {
		List<String> values = new ArrayList<>();
		values.add(value);
		this.queryParams.put(key, values);
	}

	public void addQueryParam(String key, List<String> values) {
		this.queryParams.put(key, values);
	}

	public void addQueryParams(MultiValueMap<String, String> qryParams) {
		this.queryParams.putAll(qryParams);
	}

	public void removeQueryParam(String key) {
		this.queryParams.remove(key);
	}
}
