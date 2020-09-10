package com.swagger.SwaggerAggregator.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;

import com.swagger.SwaggerAggregator.config.SwaggerServiceData;
import com.swagger.SwaggerAggregator.config.SwaggerServicesConfig;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Controller
@Primary
class PropertyResourceProvider implements SwaggerResourcesProvider {
    @Autowired
    private SwaggerServicesConfig config;

    /**
     * Build and return list of Swagger Resources (i.e. each microservice api-docs info)
     */
	@Override
	public List<SwaggerResource> get() {
		List<SwaggerResource> list = new ArrayList<SwaggerResource>();
		for(SwaggerServiceData service :config.getServices()) {
			SwaggerResource swaggerResource = new SwaggerResource();
			swaggerResource.setName(service.getName());
			swaggerResource.setUrl(service.getUrl());
			swaggerResource.setSwaggerVersion(service.getVersion());
			
			list.add(swaggerResource);
		}
		
		Collections.sort(list, new Comparator<SwaggerResource>() {

					@Override
					public int compare(SwaggerResource res1, SwaggerResource res2) {
						return res1.getName().compareTo(res2.getName());
					}
		});

		return list;
	}
}
