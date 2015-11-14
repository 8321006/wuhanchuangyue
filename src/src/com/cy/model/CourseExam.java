package com.cy.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_course_exam
 * @since 2015-07-08 14:27:56
 */
@Alias("CourseExam")
public class CourseExam implements Serializable {
	/** exam_id -- 试卷表主键 */
	private String examId;

	/** course_id -- 课程外键 */
	private String courseId;

	/** exam_name -- 试卷名称 */
	private String examName;

	/** exam_desc -- 试卷描述 */
	private String examDesc;

	/** exam_file -- 试卷文件静态化地址 */
	private String examFile;

	/** exam_complete_time -- 考试时间----->单位分钟 */
	private Integer examCompleteTime;

	/** create_time -- 数据创建时间 */
	private Date createTime;

	/** creator -- 数据创建人 */
	private String creator;

	/** update_time -- 数据更新时间 */
	private Date updateTime;

	/** updator -- 数据更新人 */
	private String updator;

	private static final long serialVersionUID = 1L;

	/** 获取试卷表主键 */
	public String getExamId() {
		return examId;
	}

	/** 设置试卷表主键 */
	public void setExamId(String examId) {
		this.examId = examId == null ? null : examId.trim();
	}

	/** 获取课程外键 */
	public String getCourseId() {
		return courseId;
	}

	/** 设置课程外键 */
	public void setCourseId(String courseId) {
		this.courseId = courseId == null ? null : courseId.trim();
	}

	/** 获取试卷名称 */
	public String getExamName() {
		return examName;
	}

	/** 设置试卷名称 */
	public void setExamName(String examName) {
		this.examName = examName == null ? null : examName.trim();
	}

	/** 获取试卷描述 */
	public String getExamDesc() {
		return examDesc;
	}

	/** 设置试卷描述 */
	public void setExamDesc(String examDesc) {
		this.examDesc = examDesc == null ? null : examDesc.trim();
	}

	/** 获取试卷文件静态化地址 */
	public String getExamFile() {
		return examFile;
	}

	/** 设置试卷文件静态化地址 */
	public void setExamFile(String examFile) {
		this.examFile = examFile == null ? null : examFile.trim();
	}

	/** 获取考试时间----->单位分钟 */
	public Integer getExamCompleteTime() {
		return examCompleteTime;
	}

	/** 设置考试时间----->单位分钟 */
	public void setExamCompleteTime(Integer examCompleteTime) {
		this.examCompleteTime = examCompleteTime;
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

	/**
	
	 * @since 2015-07-08 14:27:56
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", examId=").append(examId);
		sb.append(", courseId=").append(courseId);
		sb.append(", examName=").append(examName);
		sb.append(", examDesc=").append(examDesc);
		sb.append(", examFile=").append(examFile);
		sb.append(", examCompleteTime=").append(examCompleteTime);
		sb.append(", createTime=").append(createTime);
		sb.append(", creator=").append(creator);
		sb.append(", updateTime=").append(updateTime);
		sb.append(", updator=").append(updator);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}