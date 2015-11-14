package com.cy.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_course_teacher_analysis
 * @since 2015-07-29 11:12:42
 */
@Alias("CourseTeacherAnalysis")
public class CourseTeacherAnalysis implements Serializable {
	/** id --  */
	private Integer id;

	/** teacher_id --  */
	private String teacherId;

	/** teacher_name --  */
	private String teacherName;

	/** course_id --  */
	private String courseId;

	/** course_name --  */
	private String courseName;

	/** class_id --  */
	private String classId;

	/** class_name --  */
	private String className;

	/** university_id --  */
	private String universityId;

	/** university_name --  */
	private String universityName;

	/** analysis_time -- 分析时间 */
	private Date analysisTime;

	/** learn_rate -- 学习总进度 */
	private Integer learnRate;

	/** comment_count -- 评论总数 */
	private Integer commentCount;

	/** comment_reply -- 评论回复数 */
	private Integer commentReply;

	/** quiz_count -- 提问总数 */
	private Integer quizCount;

	/** quiz_reply -- 提问回复数 */
	private Integer quizReply;

	/** task_count -- 作业总数 */
	private Integer taskCount;

	/** task_read -- 作业批阅 */
	private Integer taskRead;

	/** exam_count -- 考试总数 */
	private Integer examCount;

	/** exam_read -- 考试批阅 */
	private Integer examRead;

	/** video_count -- 视频总时长 /min */
	private Integer videoCount;

	/** course_status -- 0 在学 1 完结 */
	private Integer courseStatus;

	private static final long serialVersionUID = 1L;

	/** 获取 */
	public Integer getId() {
		return id;
	}

	/** 设置 */
	public void setId(Integer id) {
		this.id = id;
	}

	/** 获取 */
	public String getTeacherId() {
		return teacherId;
	}

	/** 设置 */
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId == null ? null : teacherId.trim();
	}

	/** 获取 */
	public String getTeacherName() {
		return teacherName;
	}

	/** 设置 */
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName == null ? null : teacherName.trim();
	}

	/** 获取 */
	public String getCourseId() {
		return courseId;
	}

	/** 设置 */
	public void setCourseId(String courseId) {
		this.courseId = courseId == null ? null : courseId.trim();
	}

	/** 获取 */
	public String getCourseName() {
		return courseName;
	}

	/** 设置 */
	public void setCourseName(String courseName) {
		this.courseName = courseName == null ? null : courseName.trim();
	}

	/** 获取 */
	public String getClassId() {
		return classId;
	}

	/** 设置 */
	public void setClassId(String classId) {
		this.classId = classId == null ? null : classId.trim();
	}

	/** 获取 */
	public String getClassName() {
		return className;
	}

	/** 设置 */
	public void setClassName(String className) {
		this.className = className == null ? null : className.trim();
	}

	/** 获取 */
	public String getUniversityId() {
		return universityId;
	}

	/** 设置 */
	public void setUniversityId(String universityId) {
		this.universityId = universityId == null ? null : universityId.trim();
	}

	/** 获取 */
	public String getUniversityName() {
		return universityName;
	}

	/** 设置 */
	public void setUniversityName(String universityName) {
		this.universityName = universityName == null ? null : universityName.trim();
	}

	/** 获取分析时间 */
	public Date getAnalysisTime() {
		return analysisTime;
	}

	/** 设置分析时间 */
	public void setAnalysisTime(Date analysisTime) {
		this.analysisTime = analysisTime;
	}

	/** 获取学习总进度 */
	public Integer getLearnRate() {
		return learnRate;
	}

	/** 设置学习总进度 */
	public void setLearnRate(Integer learnRate) {
		this.learnRate = learnRate;
	}

	/** 获取评论总数 */
	public Integer getCommentCount() {
		return commentCount;
	}

	/** 设置评论总数 */
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	/** 获取评论回复数 */
	public Integer getCommentReply() {
		return commentReply;
	}

	/** 设置评论回复数 */
	public void setCommentReply(Integer commentReply) {
		this.commentReply = commentReply;
	}

	/** 获取提问总数 */
	public Integer getQuizCount() {
		return quizCount;
	}

	/** 设置提问总数 */
	public void setQuizCount(Integer quizCount) {
		this.quizCount = quizCount;
	}

	/** 获取提问回复数 */
	public Integer getQuizReply() {
		return quizReply;
	}

	/** 设置提问回复数 */
	public void setQuizReply(Integer quizReply) {
		this.quizReply = quizReply;
	}

	/** 获取作业总数 */
	public Integer getTaskCount() {
		return taskCount;
	}

	/** 设置作业总数 */
	public void setTaskCount(Integer taskCount) {
		this.taskCount = taskCount;
	}

	/** 获取作业批阅 */
	public Integer getTaskRead() {
		return taskRead;
	}

	/** 设置作业批阅 */
	public void setTaskRead(Integer taskRead) {
		this.taskRead = taskRead;
	}

	/** 获取考试总数 */
	public Integer getExamCount() {
		return examCount;
	}

	/** 设置考试总数 */
	public void setExamCount(Integer examCount) {
		this.examCount = examCount;
	}

	/** 获取考试批阅 */
	public Integer getExamRead() {
		return examRead;
	}

	/** 设置考试批阅 */
	public void setExamRead(Integer examRead) {
		this.examRead = examRead;
	}

	/** 获取视频总时长 /min */
	public Integer getVideoCount() {
		return videoCount;
	}

	/** 设置视频总时长 /min */
	public void setVideoCount(Integer videoCount) {
		this.videoCount = videoCount;
	}

	/** 获取0 在学 1 完结 */
	public Integer getCourseStatus() {
		return courseStatus;
	}

	/** 设置0 在学 1 完结 */
	public void setCourseStatus(Integer courseStatus) {
		this.courseStatus = courseStatus;
	}

	/**
	
	 * @since 2015-07-29 11:12:42
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", teacherId=").append(teacherId);
		sb.append(", teacherName=").append(teacherName);
		sb.append(", courseId=").append(courseId);
		sb.append(", courseName=").append(courseName);
		sb.append(", classId=").append(classId);
		sb.append(", className=").append(className);
		sb.append(", universityId=").append(universityId);
		sb.append(", universityName=").append(universityName);
		sb.append(", analysisTime=").append(analysisTime);
		sb.append(", learnRate=").append(learnRate);
		sb.append(", commentCount=").append(commentCount);
		sb.append(", commentReply=").append(commentReply);
		sb.append(", quizCount=").append(quizCount);
		sb.append(", quizReply=").append(quizReply);
		sb.append(", taskCount=").append(taskCount);
		sb.append(", taskRead=").append(taskRead);
		sb.append(", examCount=").append(examCount);
		sb.append(", examRead=").append(examRead);
		sb.append(", videoCount=").append(videoCount);
		sb.append(", courseStatus=").append(courseStatus);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}