package com.service.core.demo;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Application Config is to define the model mapper for the service.
 * 
 * @author Sonali Kate
 */
@Configuration
public class ApplicationConfig {

	/**
	 * Bean for modelMapper.
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
