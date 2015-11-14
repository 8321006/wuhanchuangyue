package com.cy.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_course
 * @since 2015-06-08 15:47:16
 */
@Alias("t_video")
public class Video implements Serializable {
	/** course_id -- 课程主键 */
	private String id;

	/** course_name -- 课程名称 */
	private String videoId;
	
	private String courseId;

	/** course_author -- 课程作者 */
	private String creator;
	
	/** course_author -- 课程作者 */
	private Date createTime;

	/** course_cover_url -- 课程封面图片 */
	private Date startTime;

	/** course_create_time -- 课程创建时间 */
	private Date endTime;

	/** course_upload_time -- 课程上传时间 */
	private long viewTime;

	/** course_uploader -- 课程上传人 */
	private String userId;
	
	private String chapterId;
	
	private String classId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public long getViewTime() {
		return viewTime;
	}

	public void setViewTime(long viewTime) {
		this.viewTime = viewTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getChapterId() {
		return chapterId;
	}

	public void setChapterId(String chapterId) {
		this.chapterId = chapterId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

}