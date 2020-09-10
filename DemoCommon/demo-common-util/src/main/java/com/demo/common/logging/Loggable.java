package com.demo.common.logging;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * LOGGABLE Annotation to apply on Method type target. Method marked with this
 * annotation will be logged with method start and end debug information.
 * 
 * @author Sonali Kate
 * 
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface Loggable {

}
