package com.cy.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_course_learn_analysis
 * @since 2015-07-22 08:30:59
 */
@Alias("CourseLearnAnalysis")
public class CourseLearnAnalysis implements Serializable {
	/** learn_id -- 进度id */
	private Integer learnId;

	/** student_id -- 学生id */
	private String studentId;

	/** student_name -- 学生名 */
	private String studentName;

	/** course_id -- 课程id */
	private String courseId;

	/** course_name -- 课程名 */
	private String courseName;

	/** class_id -- 班级id */
	private String classId;

	/** class_name -- 班级名 */
	private String className;

	/** teacher_id -- 老师id */
	private String teacherId;

	/** teacher_name -- 老师名 */
	private String teacherName;

	/** university_id -- 学校id */
	private String universityId;

	/** university_name -- 学校名 */
	private String universityName;

	/** learn_status -- 课程状态 0 未完结 1已完结 */
	private Integer learnStatus;

	/** learn_rate -- 进度百分比 */
	private Integer learnRate;

	/** comment_count -- 评论数 */
	private Integer commentCount;

	/** quiz_count -- 提问数 */
	private Integer quizCount;

	/** task_count -- 作业数 */
	private Integer taskCount;

	/** exam_count -- 考试数 */
	private Integer examCount;
	private Integer totalScore;

	/** video_count -- 视频时长 */
	private Integer videoCount;

	private Date analysisTime;
	
	private static final long serialVersionUID = 1L;

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public Date getAnalysisTime() {
		return analysisTime;
	}

	public void setAnalysisTime(Date analysisTime) {
		this.analysisTime = analysisTime;
	}

	/** 获取进度id */
	public Integer getLearnId() {
		return learnId;
	}

	/** 设置进度id */
	public void setLearnId(Integer learnId) {
		this.learnId = learnId;
	}

	/** 获取学生id */
	public String getStudentId() {
		return studentId;
	}

	/** 设置学生id */
	public void setStudentId(String studentId) {
		this.studentId = studentId == null ? null : studentId.trim();
	}

	/** 获取学生名 */
	public String getStudentName() {
		return studentName;
	}

	/** 设置学生名 */
	public void setStudentName(String studentName) {
		this.studentName = studentName == null ? null : studentName.trim();
	}

	/** 获取课程id */
	public String getCourseId() {
		return courseId;
	}

	/** 设置课程id */
	public void setCourseId(String courseId) {
		this.courseId = courseId == null ? null : courseId.trim();
	}

	/** 获取课程名 */
	public String getCourseName() {
		return courseName;
	}

	/** 设置课程名 */
	public void setCourseName(String courseName) {
		this.courseName = courseName == null ? null : courseName.trim();
	}

	/** 获取班级id */
	public String getClassId() {
		return classId;
	}

	/** 设置班级id */
	public void setClassId(String classId) {
		this.classId = classId == null ? null : classId.trim();
	}

	/** 获取班级名 */
	public String getClassName() {
		return className;
	}

	/** 设置班级名 */
	public void setClassName(String className) {
		this.className = className == null ? null : className.trim();
	}

	/** 获取老师id */
	public String getTeacherId() {
		return teacherId;
	}

	/** 设置老师id */
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId == null ? null : teacherId.trim();
	}

	/** 获取老师名 */
	public String getTeacherName() {
		return teacherName;
	}

	/** 设置老师名 */
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName == null ? null : teacherName.trim();
	}

	/** 获取学校id */
	public String getUniversityId() {
		return universityId;
	}

	/** 设置学校id */
	public void setUniversityId(String universityId) {
		this.universityId = universityId == null ? null : universityId.trim();
	}

	/** 获取学校名 */
	public String getUniversityName() {
		return universityName;
	}

	/** 设置学校名 */
	public void setUniversityName(String universityName) {
		this.universityName = universityName == null ? null : universityName.trim();
	}

	/** 获取课程状态 0 未完结 1已完结 */
	public Integer getLearnStatus() {
		return learnStatus;
	}

	/** 设置课程状态 0 未完结 1已完结 */
	public void setLearnStatus(Integer learnStatus) {
		this.learnStatus = learnStatus;
	}

	/** 获取进度百分比 */
	public Integer getLearnRate() {
		return learnRate;
	}

	/** 设置进度百分比 */
	public void setLearnRate(Integer learnRate) {
		this.learnRate = learnRate;
	}

	/** 获取评论数 */
	public Integer getCommentCount() {
		return commentCount;
	}

	/** 设置评论数 */
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	/** 获取提问数 */
	public Integer getQuizCount() {
		return quizCount;
	}

	/** 设置提问数 */
	public void setQuizCount(Integer quizCount) {
		this.quizCount = quizCount;
	}

	/** 获取作业数 */
	public Integer getTaskCount() {
		return taskCount;
	}

	/** 设置作业数 */
	public void setTaskCount(Integer taskCount) {
		this.taskCount = taskCount;
	}

	/** 获取考试数 */
	public Integer getExamCount() {
		return examCount;
	}

	/** 设置考试数 */
	public void setExamCount(Integer examCount) {
		this.examCount = examCount;
	}

	/** 获取视频时长 */
	public Integer getVideoCount() {
		return videoCount;
	}

	/** 设置视频时长 */
	public void setVideoCount(Integer videoCount) {
		this.videoCount = videoCount;
	}

	/**
	
	 * @since 2015-07-22 08:30:59
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", learnId=").append(learnId);
		sb.append(", studentId=").append(studentId);
		sb.append(", studentName=").append(studentName);
		sb.append(", courseId=").append(courseId);
		sb.append(", courseName=").append(courseName);
		sb.append(", classId=").append(classId);
		sb.append(", className=").append(className);
		sb.append(", teacherId=").append(teacherId);
		sb.append(", teacherName=").append(teacherName);
		sb.append(", universityId=").append(universityId);
		sb.append(", universityName=").append(universityName);
		sb.append(", learnStatus=").append(learnStatus);
		sb.append(", learnRate=").append(learnRate);
		sb.append(", commentCount=").append(commentCount);
		sb.append(", quizCount=").append(quizCount);
		sb.append(", taskCount=").append(taskCount);
		sb.append(", examCount=").append(examCount);
		sb.append(", videoCount=").append(videoCount);
		sb.append(", anaylysisTime=").append(analysisTime);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}