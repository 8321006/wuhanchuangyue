package com.cy.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_course_exam_analysis
 * @since 2015-07-09 14:58:36
 */
@Alias("CourseExamAnalysis")
public class CourseExamAnalysis implements Serializable {
	/** id -- 主键ID */
	private Integer id;

	/** course_name -- 课程名称 */
	private String courseName;

	/** teacher_name -- 老师姓名 */
	private String teacherName;

	/** course_student_count -- 选课人数 */
	private Integer courseStudentCount;

	/** analysis_time -- 数据分析日期 */
	private Date analysisTime;

	/** univercity_id -- 学校ID */
	private String universityId;

	/** exam_submit_count -- 试卷提交数 */
	private Integer examSubmitCount;

	/** exam_correct_count -- 试卷批改数 */
	private Integer examCorrectCount;

	/** teacher_id -- 老师ID---用户表主键 */
	private String teacherId;

	/** class_id -- 班级ID */
	private String classId;

	/** class_name -- 班级名称 */
	private String className;

	/** course_id -- 课程主键 */
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

	/** 获取老师姓名 */
	public String getTeacherName() {
		return teacherName;
	}

	/** 设置老师姓名 */
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName == null ? null : teacherName.trim();
	}

	/** 获取选课人数 */
	public Integer getCourseStudentCount() {
		return courseStudentCount;
	}

	/** 设置选课人数 */
	public void setCourseStudentCount(Integer courseStudentCount) {
		this.courseStudentCount = courseStudentCount;
	}

	/** 获取数据分析日期 */
	public Date getAnalysisTime() {
		return analysisTime;
	}

	/** 设置数据分析日期 */
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

	/** 获取试卷提交数 */
	public Integer getExamSubmitCount() {
		return examSubmitCount;
	}

	/** 设置试卷提交数 */
	public void setExamSubmitCount(Integer examSubmitCount) {
		this.examSubmitCount = examSubmitCount;
	}

	/** 获取试卷批改数 */
	public Integer getExamCorrectCount() {
		return examCorrectCount;
	}

	/** 设置试卷批改数 */
	public void setExamCorrectCount(Integer examCorrectCount) {
		this.examCorrectCount = examCorrectCount;
	}

	/** 获取老师ID---用户表主键 */
	public String getTeacherId() {
		return teacherId;
	}

	/** 设置老师ID---用户表主键 */
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId == null ? null : teacherId.trim();
	}

	/** 获取班级ID */
	public String getClassId() {
		return classId;
	}

	/** 设置班级ID */
	public void setClassId(String classId) {
		this.classId = classId == null ? null : classId.trim();
	}

	/** 获取班级名称 */
	public String getClassName() {
		return className;
	}

	/** 设置班级名称 */
	public void setClassName(String className) {
		this.className = className == null ? null : className.trim();
	}

	/** 获取课程主键 */
	public String getCourseId() {
		return courseId;
	}

	/** 设置课程主键 */
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
		sb.append(", teacherName=").append(teacherName);
		sb.append(", courseStudentCount=").append(courseStudentCount);
		sb.append(", analysisTime=").append(analysisTime);
		sb.append(", universityId=").append(universityId);
		sb.append(", examSubmitCount=").append(examSubmitCount);
		sb.append(", examCorrectCount=").append(examCorrectCount);
		sb.append(", teacherId=").append(teacherId);
		sb.append(", classId=").append(classId);
		sb.append(", className=").append(className);
		sb.append(", courseId=").append(courseId);
		sb.append(", universityName=").append(universityName);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}