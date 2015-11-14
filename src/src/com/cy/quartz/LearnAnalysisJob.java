package com.cy.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.cy.service.CourseLearnAnalysisService;

@Component
public class LearnAnalysisJob extends QuartzJobBean {

	@Autowired
	private CourseLearnAnalysisService courseLearnAnalysisService;

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {

		courseLearnAnalysisService.updateAllJob();
	}
}
