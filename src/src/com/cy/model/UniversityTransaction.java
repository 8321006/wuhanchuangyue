package com.cy.model;

import java.util.Date;

public class UniversityTransaction {
	/** id -- 事务id */
	private String id;
	/** transactionType -- 事务类型 */
	private int transactionType;
	/** transactionState -- 事务状态 */
	private int transactionState;
	/** UserId -- 申请人id */
	private String userId;
	/** realName -- 申请人姓名 */
	private String realName;
	/** courseId---课程Id */
	private String courseId;
	/** courseName -- 课程名称 */
	private String courseName;
	/** teacherName -- 负责人名称 */
	private String teacherName;
	/** transactionReason -- 事务原因 */
	private String transactionReason;
	/** transactionTime---申请时间 */
	private Date transactionTime;
	/** classId---班级Id */
	private String classId;
	/** className---班级名称 */
	private String className;
	/** teacherId---负责人Id */
	private String teacherId;
	/** universityId---学校Id */
	private String universityId;
	/** universityName---学校名称*/
	private String universityName;
	/** paperId -- 试卷Id */
	private String paperId;
	/** paperId -- 试卷名称 */
	private String paperName;
	/**user_type--*/
	private int userType;
	

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}

	public int getTransactionState() {
		return transactionState;
	}

	public void setTransactionState(int transactionState) {
		this.transactionState = transactionState;
	}

	public String getTransactionReason() {
		return transactionReason;
	}

	public void setTransactionReason(String transactionReason) {
		this.transactionReason = transactionReason;
	}

	public Date getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getUniversityId() {
		return universityId;
	}

	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}


}
