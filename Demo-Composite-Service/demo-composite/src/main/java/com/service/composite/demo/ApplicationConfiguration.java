package com.service.composite.demo;

import org.modelmapper.ModelMapper;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class to initialize the beans defined in the service.
 * 
 * @author Sonali Kate
 *
 */
@Configuration
@EnableHystrix
public class ApplicationConfiguration {

	/**
	 * Bean for modelMapper.
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
