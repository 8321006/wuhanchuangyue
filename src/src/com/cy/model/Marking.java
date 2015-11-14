package com.cy.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("Marking")
public class Marking implements Serializable {
	/** test_id -- 主鍵ID */
	private String testId;

	/** paper_id -- 试卷ID */
	private String paperId;
	
	/** paper_id -- 试卷NAME */
	private String paperName;

	/** class_id -- 班級 */
	private String classId;
	/** class_name -- 班級 */
	private String className;

	/** school_id -- 學校 */
	private Integer schoolId;

	/** user_id -- 学生ID */
	private String userId;
	
	/** user_id -- 学生Name */
	private String userName;
	/** teacher_name -- 老师Name */
	private String teacherName;
	/** userScore -- 考试成绩 */
	private Float userScore;

	/** mark_status -- 批阅状态，0：未批阅，1：已批阅 */
	private Integer markStatus;

	/** test_status -- 交卷状态，0：已交，1：未交， 2：迟交 */
	private Integer testStatus;

	/** test_times -- 考试次数,第几次，从1开始 */
	private Integer testTimes;

	/** makeUpStatus -- 补考状态，0：正常，1：申请补考， 2：审核通过，3审核拒绝  */
	private Integer makeUpStatus;
	
	private String studentId;/*学号*/
	public String getTestId() {
		return testId;
	}



	public void setTestId(String testId) {
		this.testId = testId;
	}



	public String getPaperId() {
		return paperId;
	}



	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}



	public String getPaperName() {
		return paperName;
	}



	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}



	public String getClassId() {
		return classId;
	}



	public void setClassId(String classId) {
		this.classId = classId;
	}



	public Integer getSchoolId() {
		return schoolId;
	}



	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public Float getUserScore() {
		return userScore;
	}



	public void setUserScore(Float userScore) {
		this.userScore = userScore;
	}



	public Integer getMarkStatus() {
		return markStatus;
	}



	public void setMarkStatus(Integer markStatus) {
		this.markStatus = markStatus;
	}



	public Integer getTestStatus() {
		return testStatus;
	}



	public void setTestStatus(Integer testStatus) {
		this.testStatus = testStatus;
	}



	public Integer getTestTimes() {
		return testTimes;
	}



	public void setTestTimes(Integer testTimes) {
		this.testTimes = testTimes;
	}



	public Integer getMakeUpStatus() {
		return makeUpStatus;
	}



	public void setMakeUpStatus(Integer makeUpStatus) {
		this.makeUpStatus = makeUpStatus;
	}
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}


	private static final long serialVersionUID = 1L;
	public String getStudentId() {
		return studentId;
	}


	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

}