package com.service.composite.demo.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.common.model.ServiceContext;
import com.service.composite.demo.ICompositeService;
import com.service.composite.demo.datatypes.CompositeResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

//TODO - Rename the class name and replace the variable part <compositeservice> with the actual service name. Remove this comment.
/**
 * Rest Controller to define the REST endpoints for the service.
 * 
 * @author Sonali Kate
 *
 */
@RestController
@RequestMapping("/compositeservice")
@Api(value = "compositeservice", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class CompositeServiceController {

	private final static Logger LOG = LoggerFactory.getLogger(CompositeServiceController.class);

	@Autowired
	private ICompositeService compositeService;

	/**
	 * API Method description goes here
	 * 
	 */
	@ApiOperation(value = "Get data", response = Object.class, responseContainer = "String", tags = "GET /entity")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrived data"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/entity/{id}")
	public ResponseEntity<Object> getEntity(@PathVariable("id") String templateId,
			@ApiIgnore @RequestHeader(required = false) HttpHeaders headers,
			@ApiIgnore @PathVariable(required = false) Map<String, String> pathParams,
			@ApiIgnore @RequestParam(required = false) MultiValueMap<String, String> queryParams) {

		return new ResponseEntity<Object>(null, HttpStatus.OK);
	}

	/**
	 * API Method description goes here
	 * 
	 */
	@ApiOperation(value = "Save data", response = Object.class, responseContainer = "String", tags = "POST /entity")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully saved data"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/entity}")
	public ResponseEntity<CompositeResponse> saveEntity(@RequestBody Object object,
			@ApiIgnore @RequestHeader(required = false) HttpHeaders headers,
			@ApiIgnore @PathVariable(required = false) Map<String, String> pathParams,
			@ApiIgnore @RequestParam(required = false) MultiValueMap<String, String> queryParams) {
		CompositeResponse compositeResponse = compositeService
				.create(new ServiceContext(headers, pathParams, queryParams, object));
		return new ResponseEntity<CompositeResponse>(compositeResponse, HttpStatus.OK);
	}

	/**
	 * API Method description goes here
	 * 
	 */
	@ApiOperation(value = "Update data", response = Object.class, responseContainer = "String", tags = "PATCH /entity")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated data"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PatchMapping("/entity}")
	public ResponseEntity<Object> updateEntity(@ApiIgnore @RequestHeader(required = false) HttpHeaders headers,
			@ApiIgnore @PathVariable(required = false) Map<String, String> pathParams,
			@ApiIgnore @RequestParam(required = false) MultiValueMap<String, String> queryParams) {

		return new ResponseEntity<Object>(null, HttpStatus.OK);
	}

	/**
	 * API Method description goes here
	 * 
	 */
	@ApiOperation(value = "Delete data", response = Object.class, responseContainer = "String", tags = "DELETE /entity")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted data"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@DeleteMapping("/entity}")
	public ResponseEntity<Object> deleteEntity(@ApiIgnore @RequestHeader(required = false) HttpHeaders headers,
			@ApiIgnore @PathVariable(required = false) Map<String, String> pathParams,
			@ApiIgnore @RequestParam(required = false) MultiValueMap<String, String> queryParams) {

		return new ResponseEntity<Object>(null, HttpStatus.OK);
	}
}
