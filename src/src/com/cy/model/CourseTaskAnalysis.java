package com.cy.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_course_task_analysis
 * @since 2015-07-09 14:58:36
 */
@Alias("CourseTaskAnalysis")
public class CourseTaskAnalysis implements Serializable {
	/** id -- 主键ID
 */
	private Integer id;

	/** course_name -- 课程名称 */
	private String courseName;

	/** teacher_name -- 教师名称 */
	private String teacherName;

	/** course_student_count -- 选课人数 */
	private Integer courseStudentCount;

	/** task_publish_count -- 作业发布次数 */
	private Integer taskPublishCount;

	/** task_submit_count -- 作业提交次数 */
	private Integer taskSubmitCount;

	/** task_correct_count -- 作业批改次数 */
	private Integer taskCorrectCount;

	/** analysis_time -- 数据分析日期 */
	private Date analysisTime;

	/** univercity_id -- 学校ID */
	private String universityId;

	/** teacher_id -- 老师ID----用户表主键 */
	private String teacherId;

	/** class_id -- 班级ID */
	private String classId;

	/** class_name -- 班级名称 */
	private String className;

	/** course_id -- 课程ID */
	private String courseId;

	/** university_name -- 学校名称 */
	private String universityName;

	private static final long serialVersionUID = 1L;

	/** 获取主键ID
 */
	public Integer getId() {
		return id;
	}

	/** 设置主键ID
 */
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

	/** 获取教师名称 */
	public String getTeacherName() {
		return teacherName;
	}

	/** 设置教师名称 */
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

	/** 获取作业发布次数 */
	public Integer getTaskPublishCount() {
		return taskPublishCount;
	}

	/** 设置作业发布次数 */
	public void setTaskPublishCount(Integer taskPublishCount) {
		this.taskPublishCount = taskPublishCount;
	}

	/** 获取作业提交次数 */
	public Integer getTaskSubmitCount() {
		return taskSubmitCount;
	}

	/** 设置作业提交次数 */
	public void setTaskSubmitCount(Integer taskSubmitCount) {
		this.taskSubmitCount = taskSubmitCount;
	}

	/** 获取作业批改次数 */
	public Integer getTaskCorrectCount() {
		return taskCorrectCount;
	}

	/** 设置作业批改次数 */
	public void setTaskCorrectCount(Integer taskCorrectCount) {
		this.taskCorrectCount = taskCorrectCount;
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

	/** 获取老师ID----用户表主键 */
	public String getTeacherId() {
		return teacherId;
	}

	/** 设置老师ID----用户表主键 */
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
		sb.append(", teacherName=").append(teacherName);
		sb.append(", courseStudentCount=").append(courseStudentCount);
		sb.append(", taskPublishCount=").append(taskPublishCount);
		sb.append(", taskSubmitCount=").append(taskSubmitCount);
		sb.append(", taskCorrectCount=").append(taskCorrectCount);
		sb.append(", analysisTime=").append(analysisTime);
		sb.append(", universityId=").append(universityId);
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