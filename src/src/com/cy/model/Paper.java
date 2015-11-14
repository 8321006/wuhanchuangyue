package com.cy.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_paper
 * @since 2015-06-08 15:47:16
 */
@Alias("Paper")
public class Paper implements Serializable {

	private static final long serialVersionUID = 8904457649783160991L;

	/** paper_id -- 试卷主键 */
	private String paperId;

	/** paper_name -- 试卷名称 */
	private String paperName;

	/** subject_id -- 科目Id */
	private Integer subjectId;

	/** subject_name -- 科目名称 */
	private String subjectName;
	
	/** school_id -- 科目Id */
	private Integer schoolId;

	/** school_name -- 科目名称 */
	private String schoolName;

	/** answer_cost_time -- 答题时间 */
	private Integer answerCostTime;
	
	/** score -- 试卷总分 */
	private Integer score;
	
	/** pass_score -- 及格分数 */
	private Integer passScore;
	
	/** create_time -- 创建时间 */
	private Date createTime;

	/** creator -- 创建者账号 */
	private String creator;
	/** 测试类型---------*/
	private int  testType;
	/*--是否存在主观题--*/
	private int isSubjective;
	private String courseId;
	public int getTestType() {
		return testType;
	}

	public void setTestType(int testType) {
		this.testType = testType;
	}

	/** description -- 试卷介绍 */
	private String description;
	
	/** subjective -- 是否含主观题 */
	private Integer subjective;
	
	/** content -- 试卷内容 */
	private String content;
	
	/** answer_sheet -- 答题卡 */
	private String answerSheet;
	/** chapterId -- 章节ID */
	private String chapterId;
	private Date paperendTime;

	public Date getPaperendTime() {
		return paperendTime;
	}

	public void setPaperendTime(Date paperendTime) {
		this.paperendTime = paperendTime;
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

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Integer getAnswerCostTime() {
		return answerCostTime;
	}

	public void setAnswerCostTime(Integer answerCostTime) {
		this.answerCostTime = answerCostTime;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getPassScore() {
		return passScore;
	}

	public void setPassScore(Integer passScore) {
		this.passScore = passScore;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSubjective() {
		return subjective;
	}

	public void setSubjective(Integer subjective) {
		this.subjective = subjective;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAnswerSheet() {
		return answerSheet;
	}

	public void setAnswerSheet(String answerSheet) {
		this.answerSheet = answerSheet;
	}

	public String getChapterId() {
		return chapterId;
	}

	public void setChapterId(String chapterId) {
		this.chapterId = chapterId;
	}

	public int getIsSubjective() {
		return isSubjective;
	}

	public void setIsSubjective(int isSubjective) {
		this.isSubjective = isSubjective;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	
}