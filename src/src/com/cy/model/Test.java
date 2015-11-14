package com.cy.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_test
 * @since 2015-07-15 09:27:26
 */
@Alias("Test")
public class Test implements Serializable {
	/** test_id -- 主鍵ID */
	private String testId;

	/** paper_id -- 试卷ID */
	private String paperId;
	
	/** paper_id -- 试卷NAME */
	private String paperName;

	/** class_id -- 班級 */
	private String classId;

	/** university_Id -- 學校 */
	private String universityId;

	/** user_id -- 学生ID */
	private String userId;

	/** user_answer_cost_time -- 答题花费时间，单位分钟 */
	private Integer userAnswerCostTime;

	/** userScore -- 考试成绩 */
	private Float userScore;

	/** submit_time -- 交卷時間 */
	private Date submitTime;

	/** start_answer_time -- 開始答題時間 */
	private Date startAnswerTime;

	/** end_answer_time -- 結束答題時間 */
	private Date endAnswerTime;

	/** mark_status -- 批阅状态，0：未批阅，1：已批阅 */
	private Integer markStatus;
	
	/** test_type -- 测试类型：0，作业，1：考试 */
	private Integer testType;

	/** test_times -- 考试次数,第几次，从1开始 */
	private Integer testTimes;

	/** content -- 答卷内容 */
	private String content;
	/*userTotal--用户填写调查问卷人数统计 */
	private int userWriteTotal;
	
	/** mark_status -- 状态，0是未查看答案，1是已经查看过答案 */
	private Integer checkStatus;

	private static final long serialVersionUID = 1L;

	/** 获取主鍵ID */
	public String getTestId() {
		return testId;
	}

	/** 设置主鍵ID */
	public void setTestId(String testId) {
		this.testId = testId == null ? null : testId.trim();
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public Float getUserScore() {
		return userScore;
	}

	public void setUserScore(Float userScore) {
		this.userScore = userScore;
	}

	/** 获取试卷ID */
	public String getPaperId() {
		return paperId;
	}

	/** 设置试卷ID */
	public void setPaperId(String paperId) {
		this.paperId = paperId == null ? null : paperId.trim();
	}

	/** 获取班級 */
	public String getClassId() {
		return classId;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	/** 设置班級 */
	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getUniversityId() {
		return universityId;
	}

	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}

	/** 获取学生ID */
	public String getUserId() {
		return userId;
	}

	/** 设置学生ID */
	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	/** 获取答题花费时间，单位分钟 */
	public Integer getUserAnswerCostTime() {
		return userAnswerCostTime;
	}

	/** 设置答题花费时间，单位分钟 */
	public void setUserAnswerCostTime(Integer userAnswerCostTime) {
		this.userAnswerCostTime = userAnswerCostTime;
	}

	/** 获取交卷時間 */
	public Date getSubmitTime() {
		return submitTime;
	}

	/** 设置交卷時間 */
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	/** 获取開始答題時間 */
	public Date getStartAnswerTime() {
		return startAnswerTime;
	}

	/** 设置開始答題時間 */
	public void setStartAnswerTime(Date startAnswerTime) {
		this.startAnswerTime = startAnswerTime;
	}

	/** 获取結束答題時間 */
	public Date getEndAnswerTime() {
		return endAnswerTime;
	}

	/** 设置結束答題時間 */
	public void setEndAnswerTime(Date endAnswerTime) {
		this.endAnswerTime = endAnswerTime;
	}

	/** 获取批阅状态，0：未批阅，1：已批阅 */
	public Integer getMarkStatus() {
		return markStatus;
	}

	/** 设置批阅状态，0：未批阅，1：已批阅 */
	public void setMarkStatus(Integer markStatus) {
		this.markStatus = markStatus;
	}

	/** 获取考试次数,第几次，从1开始 */
	public Integer getTestTimes() {
		return testTimes;
	}

	/** 设置考试次数,第几次，从1开始 */
	public void setTestTimes(Integer testTimes) {
		this.testTimes = testTimes;
	}

	/** 获取答卷内容 */
	public String getContent() {
		return content;
	}

	/** 设置答卷内容 */
	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public Integer getTestType() {
		return testType;
	}

	public void setTestType(Integer testType) {
		this.testType = testType;
	}
	public int getUserWriteTotal() {
		return userWriteTotal;
	}

	public void setUserWriteTotal(int userWriteTotal) {
		this.userWriteTotal = userWriteTotal;
	}

	/**
	
	 * @since 2015-07-15 09:27:26
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", testId=").append(testId);
		sb.append(", paperId=").append(paperId);
		sb.append(", classId=").append(classId);
		sb.append(", universityId=").append(universityId);
		sb.append(", userId=").append(userId);
		sb.append(", userAnswerCostTime=").append(userAnswerCostTime);
		sb.append(", userscore=").append(userScore);
		sb.append(", submitTime=").append(submitTime);
		sb.append(", startAnswerTime=").append(startAnswerTime);
		sb.append(", endAnswerTime=").append(endAnswerTime);
		sb.append(", markStatus=").append(markStatus);
		sb.append(", testTimes=").append(testTimes);
		sb.append(", content=").append(content);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}