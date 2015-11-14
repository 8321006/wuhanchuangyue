package com.cy.model;

import java.util.Date;

public class Survey {
	/**id---调查ID*/
	private String Id;
	/**userId--用户ID*/
	private String userId;
	/**universityId--学校ID */
	private String universityId;
	/**courseId--课程ID */
	private String courseId;
	/**courseName课程名称**/
	private String courseName;
	/**universityName--学校名称*/
	private String universityName;
	/**surveyContent--调查内容 **/
	private String surveyContent;
	/**realName---用户名称**/
	private String realName;
	/**surveyState--调查状态1：为已调查完毕 0:尚未调查**/
	private int surveyState;
	/**paperId--调查问卷Id**/
	private  String paperId;
	/**类型 0为作业  1为考试  2为调查问卷 **/
	private String testType;
	/** course_start_time -- 课程开始时间 */
	private Date courseStartTime;
	/** course_end_time -- 课程结束时间 */
	private Date courseEndTime;
	/**courseCoverUrl---课程封面*/
	private String courseCoverUrl;
	/** userTotal -- 选课 人数- */
	private int userTotal;
	private String  testId;
	/** 调查问卷结果*/
	private int surveyResult;
	/**courseTerm---学期*/
	private String courseTerm;
	/**count--判断用户是否做调查问卷count=0用户尚未做，count>0用户已做*/
	private int count;
	/**exsitUser--调查问卷记录表中是否存在该用户*/
	private int exsitUser;
	/**paperName--调查问卷名称*/
	private String paperName;
	/** create_time -- 创建时间 */
	private Date createTime;
	/**questionId--题目Id*/
	private String questionId;
	/**userAnswer--用户答案*/
	private String userAnswer;
	/**classId---班级Id*/
	private String classId;
	/**chapterId--课程章节*/
	private String chapterId;
	/**surveyTotal--未填写的调查问卷统计*/
	private int surveyTotal;
	/**userAll用户总数*/
	private int userWriteTotal;
	/**surveyTypew调查问卷的类型：0：课程  1：平台*/
	private int surveyType;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUniversityId() {
		return universityId;
	}
	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getUniversityName() {
		return universityName;
	}
	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}
	public String getSurveyContent() {
		return surveyContent;
	}
	public void setSurveyContent(String surveyContent) {
		this.surveyContent = surveyContent;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public int getSurveyState() {
		return surveyState;
	}
	public void setSurveyState(int surveyState) {
		this.surveyState = surveyState;
	}
	public String getPaperId() {
		return paperId;
	}
	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public Date getCourseStartTime() {
		return courseStartTime;
	}
	public void setCourseStartTime(Date courseStartTime) {
		this.courseStartTime = courseStartTime;
	}
	public Date getCourseEndTime() {
		return courseEndTime;
	}
	public void setCourseEndTime(Date courseEndTime) {
		this.courseEndTime = courseEndTime;
	}
	public String getCourseCoverUrl() {
		return courseCoverUrl;
	}
	public void setCourseCoverUrl(String courseCoverUrl) {
		this.courseCoverUrl = courseCoverUrl;
	}
	public int getUserTotal() {
		return userTotal;
	}
	public void setUserTotal(int userTotal) {
		this.userTotal = userTotal;
	}
	public int getSurveyResult() {
		return surveyResult;
	}
	public void setSurveyResult(int surveyResult) {
		this.surveyResult = surveyResult;
	}
	public String getCourseTerm() {
		return courseTerm;
	}
	public void setCourseTerm(String courseTerm) {
		this.courseTerm = courseTerm;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getUserAnswer() {
		return userAnswer;
	}
	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public int getExsitUser() {
		return exsitUser;
	}
	public void setExsitUser(int exsitUser) {
		this.exsitUser = exsitUser;
	}
	public int getSurveyTotal() {
		return surveyTotal;
	}
	public void setSurveyTotal(int surveyTotal) {
		this.surveyTotal = surveyTotal;
	}
	public String getChapterId() {
		return chapterId;
	}
	public void setChapterId(String chapterId) {
		this.chapterId = chapterId;
	}
	public int getUserWriteTotal() {
		return userWriteTotal;
	}
	public void setUserWriteTotal(int userWriteTotal) {
		this.userWriteTotal = userWriteTotal;
	}
	public int getSurveyType() {
		return surveyType;
	}
	public void setSurveyType(int surveyType) {
		this.surveyType = surveyType;
	}

	
}
