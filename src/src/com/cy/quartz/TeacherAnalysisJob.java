package com.cy.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.cy.service.CourseTeacherAnalysisService;

@Component
public class TeacherAnalysisJob extends QuartzJobBean {

	@Autowired
	private CourseTeacherAnalysisService courseTeacherAnalysisService;


	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		courseTeacherAnalysisService.updateAllJob();
	}
}
