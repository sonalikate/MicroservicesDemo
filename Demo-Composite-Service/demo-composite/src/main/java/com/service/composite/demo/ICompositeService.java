package com.service.composite.demo;

import com.demo.common.model.ServiceContext;
import com.service.composite.demo.datatypes.CompositeResponse;

//TODO - Rename the class name and replace the variable part <compositeservice> with the actual service name. Remove this comment.
/**
 * Interface to defined composite service methods.
 * 
 * @author Sonali Kate
 *
 */
public interface ICompositeService {

	/**
	 * Sample method.
	 * 
	 * @param context
	 * @return
	 */
	CompositeResponse create(ServiceContext context);
}
