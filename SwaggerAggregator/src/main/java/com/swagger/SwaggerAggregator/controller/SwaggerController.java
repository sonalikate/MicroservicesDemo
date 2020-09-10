package com.swagger.SwaggerAggregator.controller;

import static com.swagger.SwaggerAggregator.utils.IConstants.SLASH;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.swagger.SwaggerAggregator.service.SwaggerService;

@Controller
class SwaggerController {

  private static final Logger LOGGER = LoggerFactory.getLogger(SwaggerController.class);

  @Autowired
  private SwaggerService swaggerService;

  @Value("${URL_CONTEXT}")
  private String urlContext;

  @GetMapping(path = "/swagger")
  String redirectToSwagger() {
    return "redirect:/swagger-ui.html";
  }

  @GetMapping(path = "/swaggerDef/{serviceName}/{serviceVersion}")
  ResponseEntity<String> getSwaggerJSON(@PathVariable("serviceName") String serviceName,
      @PathVariable("serviceVersion") String serviceVersion) {

    String url = urlContext + serviceName + SLASH + serviceVersion + SLASH;
    LOGGER.info("URL generated successfully {}", url);
    String swaggerJSON = swaggerService.fetchSwagger(serviceName, serviceVersion, url);

    if (StringUtils.isNotBlank(swaggerJSON)) {
      if (swaggerService.generateDocuments(swaggerJSON, url, serviceName)) {
        LOGGER.info("Documents generated successfully...!");
        return new ResponseEntity<String>("Documents generated successfuly", HttpStatus.OK);
      } else {
        LOGGER.info("Failed to generate documents...!");
        return new ResponseEntity<String>("Failed to generate documents", HttpStatus.NOT_FOUND);
      }
    } else {
      LOGGER.info("Failed to generate documents...!");
      return new ResponseEntity<String>("Failed to generate documents", HttpStatus.NOT_FOUND);
    }
  }
}
