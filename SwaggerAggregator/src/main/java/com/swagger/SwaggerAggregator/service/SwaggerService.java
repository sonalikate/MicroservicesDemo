package com.swagger.SwaggerAggregator.service;

public interface SwaggerService {

	String fetchSwagger(String serviceName, String serviceVersion, String URL);

	boolean generateDocuments(String swaggerJSON, String URL, String serviceName);
}
