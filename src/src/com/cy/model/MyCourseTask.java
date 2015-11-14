package com.cy.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_my_course_task
 * @since 2015-07-08 14:27:56
 */
@Alias("MyCourseTask")
public class MyCourseTask implements Serializable {
	/** id -- 主键 */
	private Integer id;

	/** task_id -- 作业id */
	private Integer taskId;

	/** task_file -- 完成的作业文件地址 */
	private String taskFile;

	/** student_id -- 学生id */
	private Integer studentId;

	/** student_name -- 学生名字 */
	private String studentName;

	/** course_id -- 课程id */
	private Integer courseId;

	/** course_name -- 课程名 */
	private String courseName;

	/** teacher_id -- 教师id */
	private Integer teacherId;

	/** teacher_name -- 教师名字 */
	private String teacherName;

	/** complete_time -- 完成时间 */
	private Date completeTime;

	/** task_status -- 作业状态 0未批改 1已批改 */
	private Integer taskStatus;

	/** task_score -- 作业分数 */
	private Integer taskScore;

	private static final long serialVersionUID = 1L;

	/** 获取主键 */
	public Integer getId() {
		return id;
	}

	/** 设置主键 */
	public void setId(Integer id) {
		this.id = id;
	}

	/** 获取作业id */
	public Integer getTaskId() {
		return taskId;
	}

	/** 设置作业id */
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	/** 获取完成的作业文件地址 */
	public String getTaskFile() {
		return taskFile;
	}

	/** 设置完成的作业文件地址 */
	public void setTaskFile(String taskFile) {
		this.taskFile = taskFile == null ? null : taskFile.trim();
	}

	/** 获取学生id */
	public Integer getStudentId() {
		return studentId;
	}

	/** 设置学生id */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	/** 获取学生名字 */
	public String getStudentName() {
		return studentName;
	}

	/** 设置学生名字 */
	public void setStudentName(String studentName) {
		this.studentName = studentName == null ? null : studentName.trim();
	}

	/** 获取课程id */
	public Integer getCourseId() {
		return courseId;
	}

	/** 设置课程id */
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	/** 获取课程名 */
	public String getCourseName() {
		return courseName;
	}

	/** 设置课程名 */
	public void setCourseName(String courseName) {
		this.courseName = courseName == null ? null : courseName.trim();
	}

	/** 获取教师id */
	public Integer getTeacherId() {
		return teacherId;
	}

	/** 设置教师id */
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	/** 获取教师名字 */
	public String getTeacherName() {
		return teacherName;
	}

	/** 设置教师名字 */
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName == null ? null : teacherName.trim();
	}

	/** 获取完成时间 */
	public Date getCompleteTime() {
		return completeTime;
	}

	/** 设置完成时间 */
	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	/** 获取作业状态 0未批改 1已批改 */
	public Integer getTaskStatus() {
		return taskStatus;
	}

	/** 设置作业状态 0未批改 1已批改 */
	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}

	/** 获取作业分数 */
	public Integer getTaskScore() {
		return taskScore;
	}

	/** 设置作业分数 */
	public void setTaskScore(Integer taskScore) {
		this.taskScore = taskScore;
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
		sb.append(", taskId=").append(taskId);
		sb.append(", taskFile=").append(taskFile);
		sb.append(", studentId=").append(studentId);
		sb.append(", studentName=").append(studentName);
		sb.append(", courseId=").append(courseId);
		sb.append(", courseName=").append(courseName);
		sb.append(", teacherId=").append(teacherId);
		sb.append(", teacherName=").append(teacherName);
		sb.append(", completeTime=").append(completeTime);
		sb.append(", taskStatus=").append(taskStatus);
		sb.append(", taskScore=").append(taskScore);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}