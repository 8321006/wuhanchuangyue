package com.cy.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类 - 表：t_course_comment
 * 
 * @since 2015-07-08 14:27:56
 */
public class CourseComment implements Serializable {
	/** comment_id -- 评论表主键 */
	private String commentId;

	/** course_id -- 课程表外键 */
	private String courseId;

	/** user_id --用户id*/
	private String userId;

	/** comment_time -- 评论时间 */
	private Date commentTime;

	/** commenter -- 评论人-->用户表外键 */
	private String commenterId;

	/** commenter_name -- 评论人姓名 */
	private String commenterName;

	/** university_name -- 学校名称 */
	private String universityName;

	/** creat_time -- 创建数据时间 */
	private Date creatTime;

	/** creator -- 数据创建人 */
	private String creator;

	/** update_time -- 数据更新时间 */
	private Date updateTime;

	/** updator -- 数据更新人 */
	private String updator;
	
	private String commenttimeString;

	public String getCommenttimeString() {
		return commenttimeString;
	}

	public void setCommenttimeString(String commenttimeString) {
		this.commenttimeString = commenttimeString;
	}
	private String parentCommentId;
	
	private String classId;
	
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}
	private static final long serialVersionUID = 1L;

	/**
	 * comment_title -- 评论标题
	 */
	private String commentTitle;

	/** comment_content -- 评论内容 */
	private String commentContent;

	public String getParentCommentId() {
		return parentCommentId;
	}

	public void setParentCommentId(String parentCommentId) {
		this.parentCommentId = parentCommentId;
	}

	/**
	 * 获取评论标题
	 */
	public String getCommentTitle() {
		return commentTitle;
	}

	/**
	 * 设置评论标题
	 */
	public void setCommentTitle(String commentTitle) {
		this.commentTitle = commentTitle == null ? null : commentTitle.trim();
	}

	/** 获取评论内容 */
	public String getCommentContent() {
		return commentContent;
	}

	/** 设置评论内容 */
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent == null ? null : commentContent
				.trim();
	}

	/** 获取评论表主键 */
	public String getCommentId() {
		return commentId;
	}

	/** 设置评论表主键 */
	public void setCommentId(String commentId) {
		this.commentId = commentId == null ? null : commentId.trim();
	}

	/** 获取课程表外键 */
	public String getCourseId() {
		return courseId;
	}

	/** 设置课程表外键 */
	public void setCourseId(String courseId) {
		this.courseId = courseId == null ? null : courseId.trim();
	}

	/** 获取评论时间 */
	public Date getCommentTime() {
		return commentTime;
	}

	/** 设置评论时间 */
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	
	/**获取评论人Id*/
	public String getCommenterId() {
		return commenterId;
	}
	/**设置评论人Id*/
	public void setCommenterId(String commenterId) {
		this.commenterId = commenterId == null?null :commenterId.trim();
	}
	/** 获取评论人姓名 */
	public String getCommenterName() {
		return commenterName;
	}

	/** 设置评论人姓名 */
	public void setCommenterName(String commenterName) {
		this.commenterName = commenterName == null ? null : commenterName
				.trim();
	}

	/** 获取学校名称 */
	public String getUniversityName() {
		return universityName;
	}

	/** 设置学校名称 */
	public void setUniversityName(String universityName) {
		this.universityName = universityName == null ? null : universityName
				.trim();
	}

	/** 获取创建数据时间 */
	public Date getCreatTime() {
		return creatTime;
	}

	/** 设置创建数据时间 */
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
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

	/**获取用户id */
	public String getUserId() {
		return userId;
	}

	/** 设置用户id */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 
	 * @since 2015-07-08 14:27:56
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", commentId=").append(commentId);
		sb.append(", courseId=").append(courseId);
		sb.append(", commentTime=").append(commentTime);
		sb.append(", commenter=").append(commenterId);
		sb.append(", commenterName=").append(commenterName);
		sb.append(", universityName=").append(universityName);
		sb.append(", creatTime=").append(creatTime);
		sb.append(", creator=").append(creator);
		sb.append(", updateTime=").append(updateTime);
		sb.append(", updator=").append(updator);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}