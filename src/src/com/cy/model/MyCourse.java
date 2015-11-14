package com.cy.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_my_course
 * @since 2015-07-02 14:06:35
 */
@Alias("MyCourse")
public class MyCourse implements Serializable {
	/** id -- 我的课程表主键 */
	private String id;

	/** user_id -- 用户表外键 */
	private String userId;

	/** course_id -- 课程表外键 */
	private String courseId;

	/** course_name -- 课程名称 */
	private String courseName;

	/** course_cover_url -- 课程封面图片地址 */
	private String courseCoverUrl;

	/** course_author -- 课程作者 */
	private String courseAuthor;

	/** course_schedule -- 课程进度 */
	private Float courseSchedule;

	/** create_time -- 数据创建时间 */
	private Date createTime;

	/** creator -- 数据创建人 */
	private String creator;

	/** update_time -- 数据更新时间 */
	private Date updateTime;

	/** updator -- 数据更新人 */
	private String updator;

	/** my_course_complete -- 课程完成情况----->0 完成  1 未完成 */
	private Integer myCourseComplete;

	/** class_id -- 班级ID */
	private String classId;

	private String className;
	/**courseTerm 学期**/
	private String courseTerm;
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	/** type -- 0:正在学习,1：收藏的课程；2：已完成 */
	private String type;

	private static final long serialVersionUID = 1L;

	/** 获取我的课程表主键 */
	public String getId() {
		return id;
	}

	/** 设置我的课程表主键 */
	public void setId(String id) {
		this.id = id;
	}

	/** 获取用户表外键 */
	public String getUserId() {
		return userId;
	}

	/** 设置用户表外键 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/** 获取课程表外键 */
	public String getCourseId() {
		return courseId;
	}

	/** 设置课程表外键 */
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

	/** 获取课程封面图片地址 */
	public String getCourseCoverUrl() {
		return courseCoverUrl;
	}

	/** 设置课程封面图片地址 */
	public void setCourseCoverUrl(String courseCoverUrl) {
		this.courseCoverUrl = courseCoverUrl == null ? null : courseCoverUrl.trim();
	}

	/** 获取课程作者 */
	public String getCourseAuthor() {
		return courseAuthor;
	}

	/** 设置课程作者 */
	public void setCourseAuthor(String courseAuthor) {
		this.courseAuthor = courseAuthor == null ? null : courseAuthor.trim();
	}

	/** 获取课程进度 */
	public Float getCourseSchedule() {
		return courseSchedule;
	}

	/** 设置课程进度 */
	public void setCourseSchedule(Float courseSchedule) {
		this.courseSchedule = courseSchedule;
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

	/** 获取课程完成情况----->0 完成  1 未完成 */
	public Integer getMyCourseComplete() {
		return myCourseComplete;
	}

	/** 设置课程完成情况----->0 完成  1 未完成 */
	public void setMyCourseComplete(Integer myCourseComplete) {
		this.myCourseComplete = myCourseComplete;
	}

	/** 获取班级ID */
	public String getClassId() {
		return classId;
	}

	/** 设置班级ID */
	public void setClassId(String classId) {
		this.classId = classId;
	}

	/** 获取0:正在学习,1：收藏的课程；2：已完成 */
	public String getType() {
		return type;
	}

	/** 设置0:正在学习,1：收藏的课程；2：已完成 */
	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getCourseTerm() {
		return courseTerm;
	}

	public void setCourseTerm(String courseTerm) {
		this.courseTerm = courseTerm;
	}

	@Override
	public String toString() {
		return "MyCourse [id=" + id + ", userId=" + userId + ", courseId="
				+ courseId + ", courseName=" + courseName + ", courseCoverUrl="
				+ courseCoverUrl + ", courseAuthor=" + courseAuthor
				+ ", courseSchedule=" + courseSchedule + ", createTime="
				+ createTime + ", creator=" + creator + ", updateTime="
				+ updateTime + ", updator=" + updator + ", myCourseComplete="
				+ myCourseComplete + ", classId=" + classId + ", className="
				+ className + ", type=" + type + "]";
	}
	
	
}