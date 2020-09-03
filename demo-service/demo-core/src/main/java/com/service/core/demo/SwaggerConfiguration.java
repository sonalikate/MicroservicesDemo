package com.service.core.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ObjectVendorExtension;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger Configuration is to define the methods for swagger implementation.
 * 
 * @author Sonali Kate
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Value("${service.name}")
	private String serviceName;

	@Value("${service.version}")
	private String serviceVersion;

	/**
	 * 
	 * Bean For Vendor Extension in swagger
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.service.core.demo")).paths(PathSelectors.any()).build();
	}

	@SuppressWarnings("rawtypes")
	private ApiInfo apiInfo() {
		List<VendorExtension> extensions = new ArrayList<>();
		ObjectVendorExtension infoSection = new ObjectVendorExtension("");
		infoSection.addProperty(new StringVendorExtension("x-api-category", "SYSTEM"));
		infoSection.addProperty(new StringVendorExtension("x-api-governanceArea", "XXX"));
		infoSection.addProperty(new StringVendorExtension("x-api-dataProtectionClass", "XXX"));
		infoSection.addProperty(new StringVendorExtension("x-api-lifecycleState", "XXXX"));
		infoSection.addProperty(new StringVendorExtension("x-api-application", "XXX"));
		infoSection.addProperty(new StringVendorExtension("x-api-domain", ""));
		infoSection.addProperty(new StringVendorExtension("x-api-identifier", "XXXX"));
		extensions.add(infoSection);

		return new ApiInfoBuilder().title("Service APIs")
				.description("The API provided by the service - " + serviceName).version(serviceVersion)
				.extensions(extensions).build();
	}
}
