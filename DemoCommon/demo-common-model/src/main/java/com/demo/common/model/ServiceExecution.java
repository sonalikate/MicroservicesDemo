package com.demo.common.model;

import java.util.Map;

public class ServiceExecution implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, Object> stepExecutionResult;

	private ServiceContext serviceContext;

	private Map<String, Boolean> serviceCallMap;

	public ServiceExecution(ServiceContext context) {
		this.serviceContext = context;
	}

	public ServiceData createServiceData() {
		ServiceData serviceData = new ServiceData(serviceContext);
		serviceData.setServiceExecution(this);
		return serviceData;
	}

	public ServiceContext getServiceContext() {
		return this.serviceContext;
	}

	public Map<String, Object> getStepExecutionResults() {
		return this.stepExecutionResult;
	}

	public Object getStepExecutionResult(String stepId) {
		return stepExecutionResult.get(stepId);
	}

	public void setStepExecutionResults(Map<String, Object> stepExecutionResult) {
		this.stepExecutionResult.putAll(stepExecutionResult);

	}

	public void setStepExecutionResult(String stepIdentifier, Object stepResult) {
		this.stepExecutionResult.put(stepIdentifier, stepResult);
	}

	public void setServiceCall(String stepIdentifier, boolean stepResult) {
		this.setServiceCall(stepIdentifier, stepResult);
	}

	public boolean getServiceCall(java.lang.String stepIdentifier) {
		return serviceCallMap.get(stepIdentifier);
	}
}
