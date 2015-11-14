package com.cy.common.util.listener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.context.support.RequestHandledEvent;

/**
 * 记录每个请求花费的时间
 */
@SuppressWarnings("rawtypes")
public class RequestEndListener implements ApplicationListener {

	private static final Logger logger = Logger.getLogger(RequestEndListener.class);
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// TODO Auto-generated method stub
		if(event instanceof RequestHandledEvent){
			RequestHandledEvent e = (RequestHandledEvent)event;
			logger.error("================>>executeTime : " + e.getProcessingTimeMillis() + "ms " + "[" + e.getDescription() + "]");
		}
	}


}
