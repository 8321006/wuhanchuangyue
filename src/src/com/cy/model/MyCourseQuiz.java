package com.cy.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_my_course_quiz
 * @since 2015-07-08 14:27:56
 */
@Alias("MyCourseQuiz")
public class MyCourseQuiz implements Serializable {
	/** id -- 课程提问id */
	private String id;

	/** user_id -- 学生id */
	private String userId;

	/** user_name -- 学生姓名 */
	private String userName;

	/** course_id -- 课程id */
	private String courseId;

	/** course_name -- 课程名称 */
	private String courseName;

	/** teacher_id -- 老师id */
	private String teacherId;

	/** teacher_name -- 老师姓名 */
	private String teacherName;

	/** quiz_content -- 提问问题 */
	private String quizContent;

	/** quiz_answer -- 提问回答 */
	private String quizAnswer;

	/** quiz_time -- 提问时间 */
	private Date quizTime;

	/** answer_time -- 回答时间 */
	private Date answerTime;

	/** quiz_status -- 提问状态 0 未解答 1已解答 */
	private Integer quizStatus;

	/**chapter_id -- 章节id */
	private String chapterId;
	
	/**question_type -- 是否匿名提问 0匿名 1不匿名 */
	private Integer questionType;

	/**parent_id -- 回复id */
	private String parentId;
	
	/**university_id -- 学校id */
	private String universityId;
	
	/**class_id -- 班级id */
	private String classId;
	
	private String quizTimeString;
	
	public String getQuizTimeString() {
		return quizTimeString;
	}

	private String answerTimeString;
	
	public void setAnswerTimeString(String answerTimeString) {
		this.answerTimeString = answerTimeString;
	}


	public String getAnswerTimeString() {
		return answerTimeString;
	}


	public void setQuizTimeString(String quizTimeString) {
		this.quizTimeString = quizTimeString;
	}

	private static final long serialVersionUID = 1L;

	/** 获取课程提问id */
	public String getId() {
		return id;
	}

	/** 设置课程提问id */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/** 获取学生id */
	public String getUserId() {
		return userId;
	}

	/** 设置学生id */
	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	/** 获取学生姓名 */
	public String getUserName() {
		return userName;
	}
	
	/** 设置学生姓名 */
	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	/** 获取课程id */
	public String getCourseId() {
		return courseId;
	}

	/** 设置课程id */
	public void setCourseId(String courseId) {
		this.courseId = courseId == null ? null : courseId.trim();
	}

	/** 获取课程名称 */
	public String getCourseName() {
		return courseName;
	}

	/** 设置课程名称 */
	public void setCourseName(String courseName) {
		this.courseName = courseName == null ? null : courseName.trim();
	}

	/** 获取老师id */
	public String getTeacherId() {
		return teacherId;
	}

	/** 设置老师id */
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId == null ? null : teacherId.trim();
	}

	/** 获取老师姓名 */
	public String getTeacherName() {
		return teacherName;
	}

	/** 设置老师姓名 */
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName == null ? null : teacherName.trim();
	}

	/** 获取提问问题 */
	public String getQuizContent() {
		return quizContent;
	}

	/** 设置提问问题 */
	public void setQuizContent(String quizContent) {
		this.quizContent = quizContent == null ? null : quizContent.trim();
	}

	/** 获取提问回答 */
	public String getQuizAnswer() {
		return quizAnswer;
	}

	/** 设置提问回答 */
	public void setQuizAnswer(String quizAnswer) {
		this.quizAnswer = quizAnswer == null ? null : quizAnswer.trim();
	}

	/** 获取提问时间 */
	public Date getQuizTime() {
		return quizTime;
	}

	/** 设置提问时间 */
	public void setQuizTime(Date quizTime) {
		this.quizTime = quizTime;
	}

	/** 获取回答时间 */
	public Date getAnswerTime() {
		return answerTime;
	}

	/** 设置回答时间 */
	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}

	/** 获取提问状态 0 未解答 1已解答 */
	public Integer getQuizStatus() {
		return quizStatus;
	}

	/** 设置提问状态 0 未解答 1已解答 */
	public void setQuizStatus(Integer quizStatus) {
		this.quizStatus = quizStatus;
	}
	
	/** 获取章节id */
	public String getChapterId() {
		return chapterId;
	}

	/** 设置章节id */
	public void setChapterId(String chapterId) {
		this.chapterId = chapterId;
	}

	/**获取提问是否匿名 0 匿名  1 不匿名*/
	public Integer getQuestionType() {
		return questionType;
	}

	/**设置提问是否匿名 0 匿名  1 不匿名*/
	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}

	/**获取回复id*/
	public String getParentId() {
		return parentId;
	}

	/**设置回复id*/
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**获取学校id*/
	public String getUniversityId() {
		return universityId;
	}

	/**设置学校id */
	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}

	/**获取班级id*/
	public String getClassId() {
		return classId;
	}

	/**设置班级id*/
	public void setClassId(String classId) {
		this.classId = classId;
	}

	/**	
	 * @since 2015-07-08 14:27:56
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", userId=").append(userId);
		sb.append(", userName=").append(userName);
		sb.append(", courseId=").append(courseId);
		sb.append(", courseName=").append(courseName);
		sb.append(", teacherId=").append(teacherId);
		sb.append(", teacherName=").append(teacherName);
		sb.append(", quizContent=").append(quizContent);
		sb.append(", quizAnswer=").append(quizAnswer);
		sb.append(", quizTime=").append(quizTime);
		sb.append(", answerTime=").append(answerTime);
		sb.append(", quizStatus=").append(quizStatus);
		sb.append(", chapterId=").append(chapterId);
		sb.append(", questionType=").append(questionType);
		sb.append(", parentId=").append(parentId);
		sb.append(", universityId=").append(universityId);
		sb.append(", classId=").append(classId);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}