package com.service.core.demo.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * This class is used to handle all schedulers defined in service.
 * 
 * @author Sonali Kate
 */
@EnableScheduling
@Component
public class DemoScheduler {
	private static final Logger LOG = LoggerFactory.getLogger(DemoScheduler.class);

	/**
	 * Invalidate user session based on configurable session timeout property. This
	 * scheduler is expected to run after an configurable interval. Currently the
	 * scheduler will run after every 15 minutes.
	 */
	@Scheduled(fixedRate = 900000)
	public void invalidateUserSession() {
		LOG.info("In the scheduler...!");
	}
}
