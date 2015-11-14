package com.cy.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_university_course
 * @since 2015-07-10 16:35:13
 */
@Alias("UniversityCourse")
public class UniversityCourse implements Serializable {
	/** id -- 学校课程表主键 */
	private String id;

	/** course_id -- 课程表外键 */
	private String courseId;

	/** course_name -- 课程名称 */
	private String courseName;

	/** university_id -- 学校表外键 */
	private String universityId;

	/** university_name -- 学校名称 */
	private String universityName;

	/** bussniss_type -- 商业类型--> */
	private Integer bussnissType;

	/** course_state -- 学校课程状态-->0 未完成  1 结束 */
	private Integer courseState;

	/** course_extend_id -- 课程扩展信息 */
	private Integer courseExtendId;

	/** course_start_time -- 课程开始时间 */
	private Date courseStartTime;

	/** course_end_time -- 课程结束时间 */
	private Date courseEndTime;

	/** credit -- 学分 */
	private Integer credit;

	/** video_ratio -- 视频比例 */
	private Float videoRatio;

	/** work_ratio -- 作业比例 */
	private Float workRatio;

	/** exam_ratio -- 试卷比例 */
	private Float examRatio;

	/** question_ratio -- 问题比例 */
	private Float questionRatio;

	/** discuss_ratio -- 讨论比例 */
	private Float discussRatio;

	/** term_start_time -- 学期开始时间 */
	private Date termStartTime;

	/** term_end_time -- 学期结束时间 */
	private Date termEndTime;
	
	private String classId;

	/** teacher_id -- 课程负责老师--用户表ID */
	private String teacherId;
	/** teacher_Name -- 课程负责老师- */
	private String teacherName;
	/** period -- 学时- */
	private  int period;
	/**courseStart---课程状态 0：未开课 ；1：已开课*/
	private int  courseStart;
	/**courseCoverUrl---课程封面*/
	private String courseCoverUrl;
	/**courseTerm---学期*/
	private String courseTerm;
	/**paperId---试卷Id*/
	private String paperId;
	/**paperName---试卷名称*/
	private String paperName;
	/**chapterId---章节Id*/
	private String chapterId;
	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	/** userTotal -- 选课 人数- */
	private int userTotal;

	private static final long serialVersionUID = 1L;

	
	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	/** 获取学校课程表主键 */
	public String getId() {
		return id;
	}

	/** 设置学校课程表主键 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/** 获取课程表外键 */
	public String getCourseId() {
		return courseId;
	}

	/** 设置课程表外键 */
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

	/** 获取学校表外键 */
	public String getUniversityId() {
		return universityId;
	}

	/** 设置学校表外键 */
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

	/** 获取商业类型--> */
	public Integer getBussnissType() {
		return bussnissType;
	}

	/** 设置商业类型--> */
	public void setBussnissType(Integer bussnissType) {
		this.bussnissType = bussnissType;
	}

	/** 获取学校课程状态-->0 未完成  1 结束 */
	public Integer getCourseState() {
		return courseState;
	}

	/** 设置学校课程状态-->0 未完成  1 结束 */
	public void setCourseState(Integer courseState) {
		this.courseState = courseState;
	}

	/** 获取课程扩展信息 */
	public Integer getCourseExtendId() {
		return courseExtendId;
	}

	/** 设置课程扩展信息 */
	public void setCourseExtendId(Integer courseExtendId) {
		this.courseExtendId = courseExtendId;
	}

	/** 获取课程开始时间 */
	public Date getCourseStartTime() {
		return courseStartTime;
	}

	/** 设置课程开始时间 */
	public void setCourseStartTime(Date courseStartTime) {
		this.courseStartTime = courseStartTime;
	}

	/** 获取课程结束时间 */
	public Date getCourseEndTime() {
		return courseEndTime;
	}

	/** 设置课程结束时间 */
	public void setCourseEndTime(Date courseEndTime) {
		this.courseEndTime = courseEndTime;
	}

	/** 获取学分 */
	public Integer getCredit() {
		return credit;
	}

	/** 设置学分 */
	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	/** 获取视频比例 */
	public Float getVideoRatio() {
		return videoRatio;
	}

	/** 设置视频比例 */
	public void setVideoRatio(Float videoRatio) {
		this.videoRatio = videoRatio;
	}

	/** 获取作业比例 */
	public Float getWorkRatio() {
		return workRatio;
	}

	/** 设置作业比例 */
	public void setWorkRatio(Float workRatio) {
		this.workRatio = workRatio;
	}

	/** 获取试卷比例 */
	public Float getExamRatio() {
		return examRatio;
	}

	/** 设置试卷比例 */
	public void setExamRatio(Float examRatio) {
		this.examRatio = examRatio;
	}

	/** 获取问题比例 */
	public Float getQuestionRatio() {
		return questionRatio;
	}

	/** 设置问题比例 */
	public void setQuestionRatio(Float questionRatio) {
		this.questionRatio = questionRatio;
	}

	/** 获取讨论比例 */
	public Float getDiscussRatio() {
		return discussRatio;
	}

	/** 设置讨论比例 */
	public void setDiscussRatio(Float discussRatio) {
		this.discussRatio = discussRatio;
	}

	/** 获取学期开始时间 */
	public Date getTermStartTime() {
		return termStartTime;
	}

	/** 设置学期开始时间 */
	public void setTermStartTime(Date termStartTime) {
		this.termStartTime = termStartTime;
	}

	/** 获取学期结束时间 */
	public Date getTermEndTime() {
		return termEndTime;
	}

	/** 设置学期结束时间 */
	public void setTermEndTime(Date termEndTime) {
		this.termEndTime = termEndTime;
	}

	/** 获取课程负责老师--用户表ID */
	public String getTeacherId() {
		return teacherId;
	}

	/** 设置课程负责老师--用户表ID */
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId == null ? null : teacherId.trim();
	}

	/**
	
	 * @since 2015-07-10 16:35:13
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", courseId=").append(courseId);
		sb.append(", courseName=").append(courseName);
		sb.append(", universityId=").append(universityId);
		sb.append(", universityName=").append(universityName);
		sb.append(", bussnissType=").append(bussnissType);
		sb.append(", courseState=").append(courseState);
		sb.append(", courseExtendId=").append(courseExtendId);
		sb.append(", courseStartTime=").append(courseStartTime);
		sb.append(", courseEndTime=").append(courseEndTime);
		sb.append(", credit=").append(credit);
		sb.append(", videoRatio=").append(videoRatio);
		sb.append(", workRatio=").append(workRatio);
		sb.append(", examRatio=").append(examRatio);
		sb.append(", questionRatio=").append(questionRatio);
		sb.append(", discussRatio=").append(discussRatio);
		sb.append(", termStartTime=").append(termStartTime);
		sb.append(", termEndTime=").append(termEndTime);
		sb.append(", teacherId=").append(teacherId);
		sb.append(", teacherName=").append(teacherName);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}

	public int getUserTotal() {
		return userTotal;
	}

	public void setUserTotal(int userTotal) {
		this.userTotal = userTotal;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public int getCourseStart() {
		return courseStart;
	}

	public void setCourseStart(int courseStart) {
		this.courseStart = courseStart;
	}

	public String getCourseCoverUrl() {
		return courseCoverUrl;
	}

	public void setCourseCoverUrl(String courseCoverUrl) {
		this.courseCoverUrl = courseCoverUrl;
	}

	public String getCourseTerm() {
		return courseTerm;
	}

	public void setCourseTerm(String courseTerm) {
		this.courseTerm = courseTerm;
	}

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public String getChapterId() {
		return chapterId;
	}

	public void setChapterId(String chapterId) {
		this.chapterId = chapterId;
	}
	
}