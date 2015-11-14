package com.cy.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_course_score_analysis
 * @since 2015-07-23 15:10:16
 */
@Alias("CourseScoreAnalysis")
public class CourseScoreAnalysis implements Serializable {
	/** score_id -- 主键ID */
	private String scoreId;

	/** student_id -- 学生学号 */
	private String studentId;

	/** student_name -- 学生姓名 */
	private String studentName;

	/** class_id -- 班级ID */
	private String classId;

	/** class_name -- 班级名称 */
	private String className;

	/** course_id -- 课程主键 */
	private String courseId;

	/** course_name -- 课程名称 */
	private String courseName;

	/** video_count -- 视频观看时长 */
	private Integer videoCount;

	/** video_score -- 视频分数 */
	private Integer videoScore;

	/** task_count -- 作业完成数 */
	private Integer taskCount;

	/** task_score -- 作业分数 */
	private Integer taskScore;

	/** comment_count -- 讨论数 */
	private Integer commentCount;

	/** comment_score -- 讨论分数 */
	private Integer commentScore;

	/** quiz_count -- 提问数 */
	private Integer quizCount;

	/** quiz_score -- 提问分数 */
	private Integer quizScore;

	/** exam_count -- 考试统计 */
	private Integer examCount;

	/** exam_score -- 考试得分 */
	private Integer examScore;

	/** analysis_time -- 数据分析时间 */
	private Date analysisTime;

	/** course_score -- 课程总成绩 */
	private Integer courseScore;

	/** ifpass -- 是否通过-0 通过  1  未通过 */
	private Integer ifpass;

	/** university_id -- 学校ID */
	private String universityId;

	/** university_name -- 学院名称 */
	private String universityName;

	/** teacher_id -- 老师ID--用户表主键 */
	private String teacherId;

	/** teacher_name -- 老师姓名 */
	private String teacherName;

	private static final long serialVersionUID = 1L;

	/** 获取主键ID */
	public String getScoreId() {
		return scoreId;
	}

	/** 设置主键ID */
	public void setScoreId(String scoreId) {
		this.scoreId = scoreId;
	}

	/** 获取学生学号 */
	public String getStudentId() {
		return studentId;
	}

	/** 设置学生学号 */
	public void setStudentId(String studentId) {
		this.studentId = studentId == null ? null : studentId.trim();
	}

	/** 获取学生姓名 */
	public String getStudentName() {
		return studentName;
	}

	/** 设置学生姓名 */
	public void setStudentName(String studentName) {
		this.studentName = studentName == null ? null : studentName.trim();
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

	/** 获取课程主键 */
	public String getCourseId() {
		return courseId;
	}

	/** 设置课程主键 */
	public void setCourseId(String courseId) {
		this.courseId = courseId == null ? null : courseId.trim();
	}

	/** 获取课程名称 */
	public String getCourseName() {
		return courseName;
	}

	/** 设置课程名称 */
	public void setCourseName(String courseName) {
		this.courseName = courseName == null ? null : courseName.trim();
	}

	/** 获取视频观看时长 */
	public Integer getVideoCount() {
		return videoCount;
	}

	/** 设置视频观看时长 */
	public void setVideoCount(Integer videoCount) {
		this.videoCount = videoCount;
	}

	/** 获取视频分数 */
	public Integer getVideoScore() {
		return videoScore;
	}

	/** 设置视频分数 */
	public void setVideoScore(Integer videoScore) {
		this.videoScore = videoScore;
	}

	/** 获取作业完成数 */
	public Integer getTaskCount() {
		return taskCount;
	}

	/** 设置作业完成数 */
	public void setTaskCount(Integer taskCount) {
		this.taskCount = taskCount;
	}

	/** 获取作业分数 */
	public Integer getTaskScore() {
		return taskScore;
	}

	/** 设置作业分数 */
	public void setTaskScore(Integer taskScore) {
		this.taskScore = taskScore;
	}

	/** 获取讨论数 */
	public Integer getCommentCount() {
		return commentCount;
	}

	/** 设置讨论数 */
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	/** 获取讨论分数 */
	public Integer getCommentScore() {
		return commentScore;
	}

	/** 设置讨论分数 */
	public void setCommentScore(Integer commentScore) {
		this.commentScore = commentScore;
	}

	/** 获取提问数 */
	public Integer getQuizCount() {
		return quizCount;
	}

	/** 设置提问数 */
	public void setQuizCount(Integer quizCount) {
		this.quizCount = quizCount;
	}

	/** 获取提问分数 */
	public Integer getQuizScore() {
		return quizScore;
	}

	/** 设置提问分数 */
	public void setQuizScore(Integer quizScore) {
		this.quizScore = quizScore;
	}

	/** 获取考试统计 */
	public Integer getExamCount() {
		return examCount;
	}

	/** 设置考试统计 */
	public void setExamCount(Integer examCount) {
		this.examCount = examCount;
	}

	/** 获取考试得分 */
	public Integer getExamScore() {
		return examScore;
	}

	/** 设置考试得分 */
	public void setExamScore(Integer examScore) {
		this.examScore = examScore;
	}

	/** 获取数据分析时间 */
	public Date getAnalysisTime() {
		return analysisTime;
	}

	/** 设置数据分析时间 */
	public void setAnalysisTime(Date analysisTime) {
		this.analysisTime = analysisTime;
	}

	/** 获取课程总成绩 */
	public Integer getCourseScore() {
		return courseScore;
	}

	/** 设置课程总成绩 */
	public void setCourseScore(Integer courseScore) {
		this.courseScore = courseScore;
	}

	/** 获取是否通过-0 通过  1  未通过 */
	public Integer getIfpass() {
		return ifpass;
	}

	/** 设置是否通过-0 通过  1  未通过 */
	public void setIfpass(Integer ifpass) {
		this.ifpass = ifpass;
	}

	/** 获取学校ID */
	public String getUniversityId() {
		return universityId;
	}

	/** 设置学校ID */
	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}

	/** 获取学院名称 */
	public String getUniversityName() {
		return universityName;
	}

	/** 设置学院名称 */
	public void setUniversityName(String universityName) {
		this.universityName = universityName == null ? null : universityName.trim();
	}

	/** 获取老师ID--用户表主键 */
	public String getTeacherId() {
		return teacherId;
	}

	/** 设置老师ID--用户表主键 */
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId == null ? null : teacherId.trim();
	}

	/** 获取老师姓名 */
	public String getTeacherName() {
		return teacherName;
	}

	/** 设置老师姓名 */
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName == null ? null : teacherName.trim();
	}

	/**
	
	 * @since 2015-07-23 15:10:16
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", scoreId=").append(scoreId);
		sb.append(", studentId=").append(studentId);
		sb.append(", studentName=").append(studentName);
		sb.append(", classId=").append(classId);
		sb.append(", className=").append(className);
		sb.append(", courseId=").append(courseId);
		sb.append(", courseName=").append(courseName);
		sb.append(", videoCount=").append(videoCount);
		sb.append(", videoScore=").append(videoScore);
		sb.append(", taskCount=").append(taskCount);
		sb.append(", taskScore=").append(taskScore);
		sb.append(", commentCount=").append(commentCount);
		sb.append(", commentScore=").append(commentScore);
		sb.append(", quizCount=").append(quizCount);
		sb.append(", quizScore=").append(quizScore);
		sb.append(", examCount=").append(examCount);
		sb.append(", examScore=").append(examScore);
		sb.append(", analysisTime=").append(analysisTime);
		sb.append(", courseScore=").append(courseScore);
		sb.append(", ifpass=").append(ifpass);
		sb.append(", universityId=").append(universityId);
		sb.append(", universityName=").append(universityName);
		sb.append(", teacherId=").append(teacherId);
		sb.append(", teacherName=").append(teacherName);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}