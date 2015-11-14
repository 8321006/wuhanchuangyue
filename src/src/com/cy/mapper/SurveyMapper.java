package com.cy.mapper;

import java.util.List;
import java.util.Map;

import com.cy.model.Course;
import com.cy.model.Survey;

public interface SurveyMapper {
	/* @Description: 调查问卷列表
	 * @author pear */
	List<Survey> findsurveylist(Survey survey);
	/*  @Description: 调查问卷课程列表
	 *  @author pear */
	List<Course> findcourselist(Survey survey);
	/*  @Description: 向指定课程、班级发送调查问卷
	 *  @author pear */
	void insertSurvey(List<Survey> list);
	
	int findSurvey(Survey survey);
	List<Survey> findsendSurvey(Survey survey);
	/* @Description: 调查问卷查询
	 * @author pear  */
	String findSurveyPaperId(Survey survey);
	/* @Description: 调查问卷查询--学生
	 * @author pear  */
	List<Survey> findsurveylistByStudent(Survey survey);
	/* @Description: 调查问卷列表--平台
	 * @author pear  */
	List<Survey> findAdminsurvey();
	//调查问卷详情页面答案统计
	int selectopationTotal(Map<String, String> surveytotal);
	//统计该学生未填写的调查问卷
	int findtotalwritesurvey(Survey survey);
	//插入平台的调查问卷
	void addsurveyPlatform(Survey survey);
	//重新建立paper与chapterId的关联，插入t_survey_paper
	void addcourseChapter(Survey survey);
	//查询平台调查问卷列表
	List<Survey> findPlatformsurvey(Survey survey);
	//查询平台未填写的调查问卷
	int findsurveyWrite(Survey survey);
	//统计该课程是否已经发送了调查问卷
	int findSurveypost(String courseId);
	//更新调查问卷的等级
	void insertpaperDegree(Map<String, String> Result);
	//判断当前调查问卷的类型：0：课程调查问卷   1：平台的调查问卷
	int findSurveyType(String paperId);
}
