package com.cy.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_course_class
 * @since 2015-07-02 15:44:18
 */
@Alias("CourseClass")
public class CourseClass implements Serializable {
	/** class_id --  */
	private String classId;

	/** class_name --  */
	private String className;

	/** university_id --  */
	private String universityId;

	/** term_start_time -- 学期开始时间 */
	private Date termStartTime;

	/** term_end_time -- 学期结束时间 */
	private Date termEndTime;

	/** teacher_id --  */
	private String teacherId;

	/** teacher_name -- 老师姓名 */
	private String teacherName;

	/** course_id --  */
	private String courseId;

	/** course_name -- 课程名称 */
	private String courseName;
	
	/** course_start_time -- 课程开始时间 */
	private Date courseStartTime;

	/** course_end_time -- 课程结束时间 */
	private Date courseEndTime;

	
	
	private String universityCourseId;
	/** courseTerm -- 课程学期 */
	private String courseTerm;


	/**courseStart---课程状态 0：未开课 ；1：已开课*/
	private int  courseStart;

	private static final long serialVersionUID = 1L;

	/** 获取 */
	public String getClassId() {
		return classId;
	}

	/** 设置 */
	public void setClassId(String classId) {
		this.classId = classId;
	}

	/** 获取 */
	public String getClassName() {
		return className;
	}

	/** 设置 */
	public void setClassName(String className) {
		this.className = className == null ? null : className.trim();
	}

	/** 获取 */
	public String getUniversityId() {
		return universityId;
	}

	/** 设置 */
	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}

	/** 获取学期开始时间 */
	public Date getTermStartTime() {
		return termStartTime;
	}

	/** 设置学期开始时间 */
	public void setTermStartTime(Date termStartTime) {
		this.termStartTime = termStartTime;
	}

	/** 获取学期结束时间 */
	public Date getTermEndTime() {
		return termEndTime;
	}

	/** 设置学期结束时间 */
	public void setTermEndTime(Date termEndTime) {
		this.termEndTime = termEndTime;
	}

	/** 获取 */
	public String getTeacherId() {
		return teacherId;
	}

	/** 设置 */
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	/** 获取老师姓名 */
	public String getTeacherName() {
		return teacherName;
	}

	/** 设置老师姓名 */
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName == null ? null : teacherName.trim();
	}

	/** 获取 */
	public String getCourseId() {
		return courseId;
	}

	/** 设置 */
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	/** 获取课程名称 */
	public String getCourseName() {
		return courseName;
	}

	/** 设置课程名称 */
	public void setCourseName(String courseName) {
		this.courseName = courseName == null ? null : courseName.trim();
	}



	@Override
	public String toString() {
		return "CourseClass [classId=" + classId + ", className=" + className
				+ ", universityId=" + universityId + ", termStartTime="
				+ termStartTime + ", termEndTime=" + termEndTime
				+ ", teacherId=" + teacherId + ", teacherName=" + teacherName
				+ ", courseId=" + courseId + ", courseName=" + courseName
				+ ", courseStartTime=" + courseStartTime + ", courseEndTime="
				+ courseEndTime + ", universityCourseId=" + universityCourseId
				+ ", courseStart=" + courseStart + "]";
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


	public String getUniversityCourseId() {
		return universityCourseId;
	}

	public void setUniversityCourseId(String universityCourseId) {
		this.universityCourseId = universityCourseId;
	}


	public int getCourseStart() {
		return courseStart;
	}

	public void setCourseStart(int courseStart) {
		this.courseStart = courseStart;
	}

	public String getCourseTerm() {
		return courseTerm;
	}

	public void setCourseTerm(String courseTerm) {
		this.courseTerm = courseTerm;
	}

	
	
	
}