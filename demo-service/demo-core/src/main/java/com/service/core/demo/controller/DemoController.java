package com.service.core.demo.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.core.demo.datatypes.UserData;
import com.service.core.demo.service.DemoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Rest Controller to define the REST endpoints for the service.
 * 
 * @author Sonali Kate
 */
@RestController
@RequestMapping("/demo")
@Api(value = "demo", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class DemoController {

	private static final Logger LOG = LoggerFactory.getLogger(DemoController.class);

	@Autowired
	private DemoService demoService;

	/**
	 * getUser operation
	 */
	@ApiOperation(value = "Retrive user data", response = UserData.class, responseContainer = "UserData", tags = "GET /users")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrived user data based on userId"),
			@ApiResponse(code = 404, message = "The data which you were trying to fetch is not found") })
	@GetMapping("/users/{userId}")
	public ResponseEntity<UserData> getUser(@ApiIgnore @RequestHeader(required = true) HttpHeaders headers,
			@ApiIgnore @PathVariable(required = true) Map<String, String> pathParams,
			@ApiIgnore @RequestParam(required = false) MultiValueMap<String, String> queryParams) {
		UserData userData = demoService.getUser(Long.valueOf(pathParams.get("userId")));
		return new ResponseEntity<>(userData, headers, HttpStatus.OK);
	}

	/**
	 * getUsers operation
	 */
	@ApiOperation(value = "Retrive user details", response = UserData.class, responseContainer = "List", tags = "GET /users")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrived user list"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/users")
	public ResponseEntity<List<UserData>> getUsers(@ApiIgnore @RequestHeader(required = false) HttpHeaders headers,
			@ApiIgnore @PathVariable(required = false) Map<String, String> pathParams,
			@ApiIgnore @RequestParam(required = false) MultiValueMap<String, String> queryParams) {
		LOG.info("Fetching user details...");
		List<UserData> users = demoService.getUsers();
		if (users != null) {
			LOG.info("Users fetched successfully.");
			return new ResponseEntity<>(users, HttpStatus.OK);
		} else {
			LOG.info("Users not found.");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * postUser operation
	 */
	@ApiOperation(value = "Add a user details", tags = "POST /users")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created user details") })
	@PostMapping("/users")
	public ResponseEntity<Boolean> postUser(@Valid @RequestBody UserData userData,
			@ApiIgnore @RequestHeader(required = false) HttpHeaders headers,
			@ApiIgnore @PathVariable(required = false) Map<String, String> pathParams,
			@ApiIgnore @RequestParam(required = false) MultiValueMap<String, String> queryParams) {
		LOG.info("Add user data started...");
		Boolean success = demoService.addUser(userData);
		if (success) {
			LOG.info("User added succesfull.");
			return new ResponseEntity<>(success, HttpStatus.CREATED);
		} else {
			LOG.info("Failed to add user data");
			return new ResponseEntity<>(success, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * putUser operation
	 */
	@ApiOperation(value = "Update a user details", tags = "PUT /users/{userId}")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successfully updated user details"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PutMapping("/users/{userId}")
	public ResponseEntity<Boolean> putUser(@PathVariable("userId") String userid, @Valid @RequestBody UserData userData,
			@ApiIgnore @RequestHeader(required = false) HttpHeaders headers,
			@ApiIgnore @PathVariable(required = false) Map<String, String> pathParams,
			@ApiIgnore @RequestParam(required = false) MultiValueMap<String, String> queryParams) {
		LOG.info("Updating user data started...");
		Boolean success = demoService.addUser(userData);
		if (success) {
			LOG.info("User updated succesfull.");
			return new ResponseEntity<>(success, HttpStatus.NO_CONTENT);
		} else {
			LOG.info("Failed to update user data");
			return new ResponseEntity<>(success, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * deleteUser operation
	 */
	@ApiOperation(value = "Delete a user details", tags = "DELETE /users/{userId}")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successfully deleted user details"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable("userId") String userId,
			@ApiIgnore @RequestHeader(required = false) HttpHeaders headers,
			@ApiIgnore @PathVariable(required = false) Map<String, String> pathParams,
			@ApiIgnore @RequestParam(required = false) MultiValueMap<String, String> queryParams) {
		LOG.info("User deletion started...");
		Boolean success = demoService.deleteUser(Long.valueOf(userId));
		if (success) {
			LOG.info("User updated succesfull.");
			return new ResponseEntity<>(success, HttpStatus.NO_CONTENT);
		} else {
			LOG.info("Failed to update user data");
			return new ResponseEntity<>(success, HttpStatus.NOT_FOUND);
		}
	}
}
