package com.cy.model;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_course_university_analysis
 * @since 2015-08-24 14:06:12
 */
@Alias("CourseUniversityAnalysis")
public class CourseUniversityAnalysis implements Serializable {
	/** id --  */
	private Integer id;

	/** university_id --  */
	private String universityId;

	/** course_term --  */
	private String courseTerm;

	/** course_id --  */
	private String courseId;

	/** class_id --  */
	private String classId;

	/** import_count --  */
	private Integer importCount;

	/** finish_count --  */
	private Integer finishCount;

	/** pass_count --  */
	private Integer passCount;

	/** fail_count --  */
	private Integer failCount;

	/** score_six --  */
	private Integer scoreSix;

	/** score_eight --  */
	private Integer scoreEight;

	/** score_ten --  */
	private Integer scoreTen;

	/** course_state --  */
	private Integer courseState;

	/** course_name --  */
	private String courseName;

	/** teacher_id --  */
	private String teacherId;

	/** teacher_name --  */
	private String teacherName;

	/** class_name --  */
	private String className;

	private static final long serialVersionUID = 1L;

	/** 获取 */
	public Integer getId() {
		return id;
	}

	/** 设置 */
	public void setId(Integer id) {
		this.id = id;
	}

	/** 获取 */
	public String getUniversityId() {
		return universityId;
	}

	/** 设置 */
	public void setUniversityId(String universityId) {
		this.universityId = universityId == null ? null : universityId.trim();
	}

	/** 获取 */
	public String getCourseTerm() {
		return courseTerm;
	}

	/** 设置 */
	public void setCourseTerm(String courseTerm) {
		this.courseTerm = courseTerm == null ? null : courseTerm.trim();
	}

	/** 获取 */
	public String getCourseId() {
		return courseId;
	}

	/** 设置 */
	public void setCourseId(String courseId) {
		this.courseId = courseId == null ? null : courseId.trim();
	}

	/** 获取 */
	public String getClassId() {
		return classId;
	}

	/** 设置 */
	public void setClassId(String classId) {
		this.classId = classId == null ? null : classId.trim();
	}

	/** 获取 */
	public Integer getImportCount() {
		return importCount;
	}

	/** 设置 */
	public void setImportCount(Integer importCount) {
		this.importCount = importCount;
	}

	/** 获取 */
	public Integer getFinishCount() {
		return finishCount;
	}

	/** 设置 */
	public void setFinishCount(Integer finishCount) {
		this.finishCount = finishCount;
	}

	/** 获取 */
	public Integer getPassCount() {
		return passCount;
	}

	/** 设置 */
	public void setPassCount(Integer passCount) {
		this.passCount = passCount;
	}

	/** 获取 */
	public Integer getFailCount() {
		return failCount;
	}

	/** 设置 */
	public void setFailCount(Integer failCount) {
		this.failCount = failCount;
	}

	/** 获取 */
	public Integer getScoreSix() {
		return scoreSix;
	}

	/** 设置 */
	public void setScoreSix(Integer scoreSix) {
		this.scoreSix = scoreSix;
	}

	/** 获取 */
	public Integer getScoreEight() {
		return scoreEight;
	}

	/** 设置 */
	public void setScoreEight(Integer scoreEight) {
		this.scoreEight = scoreEight;
	}

	/** 获取 */
	public Integer getScoreTen() {
		return scoreTen;
	}

	/** 设置 */
	public void setScoreTen(Integer scoreTen) {
		this.scoreTen = scoreTen;
	}

	/** 获取 */
	public Integer getCourseState() {
		return courseState;
	}

	/** 设置 */
	public void setCourseState(Integer courseState) {
		this.courseState = courseState;
	}

	/** 获取 */
	public String getCourseName() {
		return courseName;
	}

	/** 设置 */
	public void setCourseName(String courseName) {
		this.courseName = courseName == null ? null : courseName.trim();
	}

	/** 获取 */
	public String getTeacherId() {
		return teacherId;
	}

	/** 设置 */
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId == null ? null : teacherId.trim();
	}

	/** 获取 */
	public String getTeacherName() {
		return teacherName;
	}

	/** 设置 */
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName == null ? null : teacherName.trim();
	}

	/** 获取 */
	public String getClassName() {
		return className;
	}

	/** 设置 */
	public void setClassName(String className) {
		this.className = className == null ? null : className.trim();
	}

	/**
	
	 * @since 2015-08-24 14:06:12
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", universityId=").append(universityId);
		sb.append(", courseTerm=").append(courseTerm);
		sb.append(", courseId=").append(courseId);
		sb.append(", classId=").append(classId);
		sb.append(", importCount=").append(importCount);
		sb.append(", finishCount=").append(finishCount);
		sb.append(", passCount=").append(passCount);
		sb.append(", failCount=").append(failCount);
		sb.append(", scoreSix=").append(scoreSix);
		sb.append(", scoreEight=").append(scoreEight);
		sb.append(", scoreTen=").append(scoreTen);
		sb.append(", courseState=").append(courseState);
		sb.append(", courseName=").append(courseName);
		sb.append(", teacherId=").append(teacherId);
		sb.append(", teacherName=").append(teacherName);
		sb.append(", className=").append(className);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}