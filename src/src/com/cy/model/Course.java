package com.cy.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_course
 * @since 2015-06-08 15:47:16
 */
@Alias("Course")
public class Course implements Serializable {
	/** course_id -- 课程主键 */
	private String courseId;

	/** course_name -- 课程名称 */
	private String courseName;

	/** course_author -- 课程作者 */
	private String courseAuthor;
	
	/** course_author -- 课程作者 */
	private String courseAuthorDetail;

	/** course_cover_url -- 课程封面图片 */
	private String courseCoverUrl;

	/** course_create_time -- 课程创建时间 */
	private Date courseCreateTime;

	/** course_upload_time -- 课程上传时间 */
	private Date courseUploadTime;

	/** course_uploader -- 课程上传人 */
	private String courseUploader;

	/** course_task_id -- 课程作业ID */
	private String courseTaskId;

	/** course_exam_id -- 课程试卷ID */
	private String courceExamId;
	
	//课程学习开始时间
	private Date studystarttime;
	//课程学习结束时间
	private Date studyendtime;
	
	private Integer videonum;


	private static final long serialVersionUID = 1L;

	
	/** course_desc -- 课程描述 */
	private String courseDesc;
	
	private String courseTag;

	/** course_team -- 课程团队  ----->JSON格式 */
	private String courseTeam;

	/** course_plan -- 课程计划 */
	private String coursePlan;

	/** course_outline -- 课程大纲 */
	private String courseOutline;

	/** author_desc -- 作者简介 */
	private String authorDesc;
	
	/** author_desc -- 课程类型 */
	private String courseType;
	
	private String courseTypeName;
	
	private int num;
	
	private int percent;
	
	private String classId;
	
	private String exampercent;

	public Date getStudystarttime() {
		return studystarttime;
	}

	public void setStudystarttime(Date studystarttime) {
		this.studystarttime = studystarttime;
	}

	public Date getStudyendtime() {
		return studyendtime;
	}

	public void setStudyendtime(Date studyendtime) {
		this.studyendtime = studyendtime;
	}

	/** 获取课程描述 */
	public String getCourseDesc() {
		return courseDesc;
	}

	/** 设置课程描述 */
	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc == null ? null : courseDesc.trim();
	}

	/** 获取课程团队  ----->JSON格式 */
	public String getCourseTeam() {
		return courseTeam;
	}

	/** 设置课程团队  ----->JSON格式 */
	public void setCourseTeam(String courseTeam) {
		this.courseTeam = courseTeam == null ? null : courseTeam.trim();
	}

	/** 获取课程计划 */
	public String getCoursePlan() {
		return coursePlan;
	}

	/** 设置课程计划 */
	public void setCoursePlan(String coursePlan) {
		this.coursePlan = coursePlan == null ? null : coursePlan.trim();
	}

	/** 获取课程大纲 */
	public String getCourseOutline() {
		return courseOutline;
	}

	/** 设置课程大纲 */
	public void setCourseOutline(String courseOutline) {
		this.courseOutline = courseOutline == null ? null : courseOutline.trim();
	}

	/** 获取作者简介 */
	public String getAuthorDesc() {
		return authorDesc;
	}

	/** 设置作者简介 */
	public void setAuthorDesc(String authorDesc) {
		this.authorDesc = authorDesc == null ? null : authorDesc.trim();
	}
	/** 获取课程主键 */
	public String getCourseId() {
		return courseId;
	}

	/** 设置课程主键 */
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

	/** 获取课程作者 */
	public String getCourseAuthor() {
		return courseAuthor;
	}

	/** 设置课程作者 */
	public void setCourseAuthor(String courseAuthor) {
		this.courseAuthor = courseAuthor;
	}

	/** 获取课程封面图片 */
	public String getCourseCoverUrl() {
		return courseCoverUrl;
	}

	/** 设置课程封面图片 */
	public void setCourseCoverUrl(String courseCoverUrl) {
		this.courseCoverUrl = courseCoverUrl == null ? null : courseCoverUrl.trim();
	}

	/** 获取课程创建时间 */
	public Date getCourseCreateTime() {
		return courseCreateTime;
	}

	/** 设置课程创建时间 */
	public void setCourseCreateTime(Date courseCreateTime) {
		this.courseCreateTime = courseCreateTime;
	}

	/** 获取课程上传时间 */
	public Date getCourseUploadTime() {
		return courseUploadTime;
	}

	/** 设置课程上传时间 */
	public void setCourseUploadTime(Date courseUploadTime) {
		this.courseUploadTime = courseUploadTime;
	}

	/** 获取课程上传人 */
	public String getCourseUploader() {
		return courseUploader;
	}

	/** 设置课程上传人 */
	public void setCourseUploader(String courseUploader) {
		this.courseUploader = courseUploader == null ? null : courseUploader.trim();
	}

	/** 获取课程作业ID */
	public String getCourseTaskId() {
		return courseTaskId;
	}

	/** 设置课程作业ID */
	public void setCourseTaskId(String courseTaskId) {
		this.courseTaskId = courseTaskId == null ? null : courseTaskId.trim();
	}

	/** 获取课程试卷ID */
	public String getCourceExamId() {
		return courceExamId;
	}

	/** 设置课程试卷ID */
	public void setCourceExamId(String courceExamId) {
		this.courceExamId = courceExamId == null ? null : courceExamId.trim();
	}

	public String getCourseAuthorDetail() {
		return courseAuthorDetail;
	}

	public void setCourseAuthorDetail(String courseAuthorDetail) {
		this.courseAuthorDetail = courseAuthorDetail;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getCourseTypeName() {
		return courseTypeName;
	}

	public void setCourseTypeName(String courseTypeName) {
		this.courseTypeName = courseTypeName;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getCourseTag() {
		return courseTag;
	}

	public void setCourseTag(String courseTag) {
		this.courseTag = courseTag;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public Integer getVideonum() {
		return videonum;
	}

	public void setVideonum(Integer videonum) {
		this.videonum = videonum;
	}

	public String getExampercent() {
		return exampercent;
	}

	public void setExampercent(String exampercent) {
		this.exampercent = exampercent;
	}
	
}