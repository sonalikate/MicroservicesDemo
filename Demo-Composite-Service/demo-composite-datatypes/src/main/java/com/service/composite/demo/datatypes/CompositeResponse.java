package com.service.composite.demo.datatypes;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//TODO - Rename the class name and replace the variable part <compositeservice> with the actual service name. Remove this comment.
/**
 * Composite service response.
 * 
 * @author Sonali Kate
 *
 */
@ApiModel(value = "CompositeResponse", description = "POJO that describes the composite service details alongwith with service status")
public class CompositeResponse implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(required = true, value = "Unique field that stores core service1 data")
	private CoreService1 coreService1;

	@ApiModelProperty(required = true, value = "Unique field that stores core service2 data")
	private CoreService2 coreService2;

	@ApiModelProperty(required = true, value = "Unique field that stores service status")
	private String serviceStatus;

	/**
	 * Default constructor to this class.
	 */
	public CompositeResponse() {
		super();
	}

	public CompositeResponse(CoreService1 coreService1, CoreService2 coreService2, String serviceStatus) {
		super();
		this.coreService1 = coreService1;
		this.coreService2 = coreService2;
		this.serviceStatus = serviceStatus;
	}

	public CoreService1 getCoreService1() {
		return coreService1;
	}

	public void setCoreService1(CoreService1 coreService1) {
		this.coreService1 = coreService1;
	}

	public CoreService2 getCoreService2() {
		return coreService2;
	}

	public void setCoreService2(CoreService2 coreService2) {
		this.coreService2 = coreService2;
	}

	public String getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	@Override
	public String toString() {
		return "CompositeResponse [coreService1=" + coreService1 + ", coreService2=" + coreService2 + ", serviceStatus="
				+ serviceStatus + "]";
	}

}
