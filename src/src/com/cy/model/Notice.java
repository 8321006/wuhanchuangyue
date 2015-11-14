package com.cy.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_notice
 * @since 2015-07-27 11:56:21
 */
@Alias("Notice")
public class Notice implements Serializable {
	/** notice_id -- 主键ID */
	private String noticeId;


	/** notice_time -- 公告时间 */
	private Date noticeTime;

	/** sender -- 发送人 */
	private Integer sender;

	/** recevicer_range -- 接收人  1  老师   2   学生 */
	private Integer recevicerRange;

	/** attachment -- 通知附件 */
	private String attachment;

	/** univercity_id -- 学校ID */
	private String universityId;

	/** course_id -- 课程ID */
	private String courseId;

	/** class_id -- 班级ID */
	private String classId;

	/** user_id -- 用户ID */
	private String userId;
		
	private String noticetimeString;
	
	private Integer noticetype;

	public Integer getNoticetype() {
		return noticetype;
	}

	public void setNoticetype(Integer noticetype) {
		this.noticetype = noticetype;
	}

	public String getNoticetimeString() {
		return noticetimeString;
	}

	public void setNoticetimeString(String noticetimeString) {
		this.noticetimeString = noticetimeString;
	}

	private static final long serialVersionUID = 1L;


	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}
	/** 获取公告时间 */
	public Date getNoticeTime() {
		return noticeTime;
	}

	/** 设置公告时间 */
	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}

	/** 获取发送人 */
	public Integer getSender() {
		return sender;
	}

	/** 设置发送人 */
	public void setSender(Integer sender) {
		this.sender = sender;
	}

	/** 获取接收人  1  老师   2   学生 */
	public Integer getRecevicerRange() {
		return recevicerRange;
	}

	/** 设置接收人  1  老师   2   学生 */
	public void setRecevicerRange(Integer recevicerRange) {
		this.recevicerRange = recevicerRange;
	}

	/** 获取通知附件 */
	public String getAttachment() {
		return attachment;
	}

	/** 设置通知附件 */
	public void setAttachment(String attachment) {
		this.attachment = attachment == null ? null : attachment.trim();
	}



	public String getUniversityId() {
		return universityId;
	}

	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}

	/** 获取课程ID */
	public String getCourseId() {
		return courseId;
	}

	/** 设置课程ID */
	public void setCourseId(String courseId) {
		this.courseId = courseId == null ? null : courseId.trim();
	}

	/** 获取班级ID */
	public String getClassId() {
		return classId;
	}

	/** 设置班级ID */
	public void setClassId(String classId) {
		this.classId = classId == null ? null : classId.trim();
	}

	/** 获取用户ID */
	public String getUserId() {
		return userId;
	}

	/** 设置用户ID */
	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	/**
	
	 * @since 2015-07-27 11:56:21
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", noticeId=").append(noticeId);
		sb.append(", noticeTime=").append(noticeTime);
		sb.append(", sender=").append(sender);
		sb.append(", recevicerRange=").append(recevicerRange);
		sb.append(", attachment=").append(attachment);
		sb.append(", universityId=").append(universityId);
		sb.append(", courseId=").append(courseId);
		sb.append(", classId=").append(classId);
		sb.append(", userId=").append(userId);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}