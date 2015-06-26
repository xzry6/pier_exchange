package com.pier.exchange;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class Listener implements ServletContextListener {
	
	protected static Logger logger = Logger.getLogger(Listener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.info("destroyed JobListener");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("starting up to dollar exchange rate crawler day job");
		USexchangeCrawlerJob.executeDayJob();
		
	}

}