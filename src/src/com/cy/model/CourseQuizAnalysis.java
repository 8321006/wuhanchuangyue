package com.cy.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_course_quiz_analysis
 * @since 2015-07-09 14:58:36
 */
@Alias("CourseQuizAnalysis")
public class CourseQuizAnalysis implements Serializable {
	/** id -- 主键ID */
	private Integer id;

	/** course_name -- 课程名称 */
	private String courseName;

	/** course_student_count -- 选课人数 */
	private Integer courseStudentCount;

	/** teacher_id -- 老师ID----用户表主键 */
	private String teacherId;

	/** teacher_name -- 老师姓名 */
	private String teacherName;

	/** student_quiz_count -- 学生提问数 */
	private Integer studentQuizCount;

	/** teacher_reply_count -- 老师回答数 */
	private Integer teacherreplyCount;

	/** analysis_time -- 数据分析时间 */
	private Date analysisTime;

	/** univercity_id -- 学校ID */
	private String universityId;

	/** course_id -- 课程ID */
	private String courseId;

	/** university_name -- 学校名称 */
	private String universityName;

	private static final long serialVersionUID = 1L;

	/** 获取主键ID */
	public Integer getId() {
		return id;
	}

	/** 设置主键ID */
	public void setId(Integer id) {
		this.id = id;
	}

	/** 获取课程名称 */
	public String getCourseName() {
		return courseName;
	}

	/** 设置课程名称 */
	public void setCourseName(String courseName) {
		this.courseName = courseName == null ? null : courseName.trim();
	}

	/** 获取选课人数 */
	public Integer getCourseStudentCount() {
		return courseStudentCount;
	}

	/** 设置选课人数 */
	public void setCourseStudentCount(Integer courseStudentCount) {
		this.courseStudentCount = courseStudentCount;
	}

	/** 获取老师ID----用户表主键 */
	public String getTeacherId() {
		return teacherId;
	}

	/** 设置老师ID----用户表主键 */
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

	/** 获取学生提问数 */
	public Integer getStudentQuizCount() {
		return studentQuizCount;
	}

	/** 设置学生提问数 */
	public void setStudentQuizCount(Integer studentQuizCount) {
		this.studentQuizCount = studentQuizCount;
	}

	/** 获取老师回答数 */
	public Integer getTeacherreplyCount() {
		return teacherreplyCount;
	}

	/** 设置老师回答数 */
	public void setTeacherreplyCount(Integer teacherreplyCount) {
		this.teacherreplyCount = teacherreplyCount;
	}

	/** 获取数据分析时间 */
	public Date getAnalysisTime() {
		return analysisTime;
	}

	/** 设置数据分析时间 */
	public void setAnalysisTime(Date analysisTime) {
		this.analysisTime = analysisTime;
	}

	/** 获取学校ID */
	public String getUniversityId() {
		return universityId;
	}

	/** 设置学校ID */
	public void setUniversityId(String universityId) {
		this.universityId = universityId == null ? null : universityId.trim();
	}

	/** 获取课程ID */
	public String getCourseId() {
		return courseId;
	}

	/** 设置课程ID */
	public void setCourseId(String courseId) {
		this.courseId = courseId == null ? null : courseId.trim();
	}

	/** 获取学校名称 */
	public String getUniversityName() {
		return universityName;
	}

	/** 设置学校名称 */
	public void setUniversityName(String universityName) {
		this.universityName = universityName == null ? null : universityName.trim();
	}

	/**
	
	 * @since 2015-07-09 14:58:36
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", courseName=").append(courseName);
		sb.append(", courseStudentCount=").append(courseStudentCount);
		sb.append(", teacherId=").append(teacherId);
		sb.append(", teacherName=").append(teacherName);
		sb.append(", studentQuizCount=").append(studentQuizCount);
		sb.append(", teacherreplyCount=").append(teacherreplyCount);
		sb.append(", analysisTime=").append(analysisTime);
		sb.append(", universityId=").append(universityId);
		sb.append(", courseId=").append(courseId);
		sb.append(", universityName=").append(universityName);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}