package com.cy.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_course_task
 * @since 2015-07-08 14:27:56
 */
@Alias("CourseTask")
public class CourseTask implements Serializable {
	/** task_id -- 作业表主键 */
	private Integer taskId;

	/** course_id -- 课程表外键 */
	private Integer courseId;

	/** course_name -- 课程名称 */
	private String courseName;

	/** task_file -- 作业静态化文件地址 */
	private String taskFile;

	/** task_compelet_time -- 作业完成时间---->单位分钟 */
	private Integer taskCompeletTime;

	/** create_time -- 数据创建时间 */
	private Date createTime;

	/** creator -- 数据创建人 */
	private String creator;

	/** update_time -- 数据更新时间 */
	private Date updateTime;

	/** updator -- 数据更新人 */
	private String updator;

	private static final long serialVersionUID = 1L;

	/** 获取作业表主键 */
	public Integer getTaskId() {
		return taskId;
	}

	/** 设置作业表主键 */
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	/** 获取课程表外键 */
	public Integer getCourseId() {
		return courseId;
	}

	/** 设置课程表外键 */
	public void setCourseId(Integer courseId) {
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

	/** 获取作业静态化文件地址 */
	public String getTaskFile() {
		return taskFile;
	}

	/** 设置作业静态化文件地址 */
	public void setTaskFile(String taskFile) {
		this.taskFile = taskFile == null ? null : taskFile.trim();
	}

	/** 获取作业完成时间---->单位分钟 */
	public Integer getTaskCompeletTime() {
		return taskCompeletTime;
	}

	/** 设置作业完成时间---->单位分钟 */
	public void setTaskCompeletTime(Integer taskCompeletTime) {
		this.taskCompeletTime = taskCompeletTime;
	}

	/** 获取数据创建时间 */
	public Date getCreateTime() {
		return createTime;
	}

	/** 设置数据创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** 获取数据创建人 */
	public String getCreator() {
		return creator;
	}

	/** 设置数据创建人 */
	public void setCreator(String creator) {
		this.creator = creator == null ? null : creator.trim();
	}

	/** 获取数据更新时间 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/** 设置数据更新时间 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/** 获取数据更新人 */
	public String getUpdator() {
		return updator;
	}

	/** 设置数据更新人 */
	public void setUpdator(String updator) {
		this.updator = updator == null ? null : updator.trim();
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
		sb.append(", taskId=").append(taskId);
		sb.append(", courseId=").append(courseId);
		sb.append(", courseName=").append(courseName);
		sb.append(", taskFile=").append(taskFile);
		sb.append(", taskCompeletTime=").append(taskCompeletTime);
		sb.append(", createTime=").append(createTime);
		sb.append(", creator=").append(creator);
		sb.append(", updateTime=").append(updateTime);
		sb.append(", updator=").append(updator);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}