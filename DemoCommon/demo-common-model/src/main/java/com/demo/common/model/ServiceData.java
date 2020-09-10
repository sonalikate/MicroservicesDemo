package com.demo.common.model;

public class ServiceData implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String stepIdentifier;

	private String serviceIdentifier;

	private ServiceContext serviceContext;

	private ServiceExecution serviceExecution;

	private ServiceRequest serviceRequest;

	private Object serviceResponse;

	private boolean fallbackFlag;

	private Throwable throwable;

	protected ServiceData(ServiceContext context) {
		this.serviceContext = context;
	}

	public ServiceContext getServiceContext() {
		return serviceContext;
	}

	public ServiceExecution getServiceExecution() {
		return serviceExecution;
	}

	protected void setServiceExecution(ServiceExecution unit) {
		this.serviceExecution = unit;
	}

	public String getStepIdentifier() {
		return stepIdentifier;
	}

	public void setStepIdentifier(String stepIdentifier) {
		this.stepIdentifier = stepIdentifier;
	}

	public String getServiceIdentifier() {
		return serviceIdentifier;
	}

	public void setServiceIdentifier(String serviceIdentifier) {
		this.serviceIdentifier = serviceIdentifier;
	}

	public Object getServiceResponse() {
		return serviceResponse;
	}

	public void setServiceResponse(Object response) {
		this.serviceResponse = response;
	}

	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}

	public boolean isFallbackFlag() {
		return fallbackFlag;
	}

	public void setFallbackFlag(boolean fallbackFlag) {
		this.fallbackFlag = fallbackFlag;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

}
