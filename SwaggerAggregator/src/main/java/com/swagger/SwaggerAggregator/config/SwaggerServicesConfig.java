package com.swagger.SwaggerAggregator.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "documentation.swagger")
public class SwaggerServicesConfig {
	List<SwaggerServiceData> services;

	public List<SwaggerServiceData> getServices() {
		return services;
	}

	public void setServices(List<SwaggerServiceData> services) {
		this.services = services;
	}
}
