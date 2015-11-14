package com.cy.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.cy.service.CourseUniversityAnalysisService;

@Component
public class UniversityAnalysisJob extends QuartzJobBean {

	@Autowired
	private CourseUniversityAnalysisService courseUniversityAnalysisService;

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
			courseUniversityAnalysisService.updateAllJob();
	}
}
