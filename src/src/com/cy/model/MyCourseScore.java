package com.cy.model;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_my_course_score
 * @since 2015-07-08 14:27:56
 */
@Alias("MyCourseScore")
public class MyCourseScore implements Serializable {
	/** id -- 成绩表主键 */
	private String id;

	/** user_id -- 学生表外键 */
	private String userId;

	/** real_name -- 学生姓名 */
	private String realName;

	/** university_id -- 学校表主键 */
	private String universityId;

	/** university_name -- 学校名称 */
	private String universityName;

	/** student_id -- 学生学号 */
	private String studentId;

	/** subject_name -- 学生专业 */
	private String subjectName;

	/** college_name -- 学生所属学院 */
	private String collegeName;

	/** course_id -- 课程主键 */
	private String courseId;

	/** course_name -- 课程名称 */
	private String courseName;

	/** task_score -- 课程作业分数 */
	private Float taskScore;

	/** exam_score -- 课程试卷分数 */
	private Float examScore;

	/** video_scroe -- 观看视频分数 */
	private Float videoScroe;

	/** comment_score -- 参与讨论分数 */
	private Float commentScore;

	/** quiz_score -- 课程提问分数 */
	private Float quizScore;

	/** final_score -- 最终总分数 */
	private Float finalScore;

	/** course_complete -- 学习完成情况 0 未完成 1已完成 */
	private Integer courseComplete;

	/** if_pass -- 是否通过 */
	private Integer ifPass;

	private static final long serialVersionUID = 1L;

	/** 获取成绩表主键 */
	public String getId() {
		return id;
	}

	/** 设置成绩表主键 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/** 获取学生表外键 */
	public String getUserId() {
		return userId;
	}

	/** 设置学生表外键 */
	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	/** 获取学生姓名 */
	public String getRealName() {
		return realName;
	}

	/** 设置学生姓名 */
	public void setRealName(String realName) {
		this.realName = realName == null ? null : realName.trim();
	}

	/** 获取学校表主键 */
	public String getUniversityId() {
		return universityId;
	}

	/** 设置学校表主键 */
	public void setUniversityId(String universityId) {
		this.universityId = universityId == null ? null : universityId.trim();
	}

	/** 获取学校名称 */
	public String getUniversityName() {
		return universityName;
	}

	/** 设置学校名称 */
	public void setUniversityName(String universityName) {
		this.universityName = universityName == null ? null : universityName.trim();
	}

	/** 获取学生学号 */
	public String getStudentId() {
		return studentId;
	}

	/** 设置学生学号 */
	public void setStudentId(String studentId) {
		this.studentId = studentId == null ? null : studentId.trim();
	}

	/** 获取学生专业 */
	public String getSubjectName() {
		return subjectName;
	}

	/** 设置学生专业 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName == null ? null : subjectName.trim();
	}

	/** 获取学生所属学院 */
	public String getCollegeName() {
		return collegeName;
	}

	/** 设置学生所属学院 */
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName == null ? null : collegeName.trim();
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

	/** 获取课程作业分数 */
	public Float getTaskScore() {
		return taskScore;
	}

	/** 设置课程作业分数 */
	public void setTaskScore(Float taskScore) {
		this.taskScore = taskScore;
	}

	/** 获取课程试卷分数 */
	public Float getExamScore() {
		return examScore;
	}

	/** 设置课程试卷分数 */
	public void setExamScore(Float examScore) {
		this.examScore = examScore;
	}

	/** 获取观看视频分数 */
	public Float getVideoScroe() {
		return videoScroe;
	}

	/** 设置观看视频分数 */
	public void setVideoScroe(Float videoScroe) {
		this.videoScroe = videoScroe;
	}

	/** 获取参与讨论分数 */
	public Float getCommentScore() {
		return commentScore;
	}

	/** 设置参与讨论分数 */
	public void setCommentScore(Float commentScore) {
		this.commentScore = commentScore;
	}

	/** 获取课程提问分数 */
	public Float getQuizScore() {
		return quizScore;
	}

	/** 设置课程提问分数 */
	public void setQuizScore(Float quizScore) {
		this.quizScore = quizScore;
	}

	/** 获取最终总分数 */
	public Float getFinalScore() {
		return finalScore;
	}

	/** 设置最终总分数 */
	public void setFinalScore(Float finalScore) {
		this.finalScore = finalScore;
	}

	/** 获取学习完成情况 0 未完成 1已完成 */
	public Integer getCourseComplete() {
		return courseComplete;
	}

	/** 设置学习完成情况 0 未完成 1已完成 */
	public void setCourseComplete(Integer courseComplete) {
		this.courseComplete = courseComplete;
	}

	/** 获取是否通过 */
	public Integer getIfPass() {
		return ifPass;
	}

	/** 设置是否通过 */
	public void setIfPass(Integer ifPass) {
		this.ifPass = ifPass;
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
		sb.append(", userId=").append(userId);
		sb.append(", realName=").append(realName);
		sb.append(", universityId=").append(universityId);
		sb.append(", universityName=").append(universityName);
		sb.append(", studentId=").append(studentId);
		sb.append(", subjectName=").append(subjectName);
		sb.append(", collegeName=").append(collegeName);
		sb.append(", courseId=").append(courseId);
		sb.append(", courseName=").append(courseName);
		sb.append(", taskScore=").append(taskScore);
		sb.append(", examScore=").append(examScore);
		sb.append(", videoScroe=").append(videoScroe);
		sb.append(", commentScore=").append(commentScore);
		sb.append(", quizScore=").append(quizScore);
		sb.append(", finalScore=").append(finalScore);
		sb.append(", courseComplete=").append(courseComplete);
		sb.append(", ifPass=").append(ifPass);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}