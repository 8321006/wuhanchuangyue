package com.cy.model;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Question implements Serializable {
	private static final long serialVersionUID = 6335675770371435246L;
	private String questionId;
	private String subjectId;
	private String questionTypeName;
	private String content;
	private int questionTypeId;
	private float points;
	private String creator;
	private Date lastModify;
	private Date  createTime;
	private String answer;
	private String analysis;
	private String fieldName;
	private String name;
	private String keyword;
	private String  questionNo ;
	private  int  selectATotal;
	private  int  selectBTotal;
	private  int  selectCTotal;
	private  int  selectDTotal;
	private  int  selectETotal;
	private  int  selectFTotal;
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public float getPoints() {
		return points;
	}
	public void setPoints(float points) {
		this.points = points;
	}
	
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	
	public int getQuestionTypeId() {
		return questionTypeId;
	}
	public void setQuestionTypeId(int questionTypeId) {
		this.questionTypeId = questionTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getLastModify() {
		return lastModify;
	}
	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getQuestionTypeName() {
		return questionTypeName;
	}
	public void setQuestionTypeName(String questionTypeName) {
		this.questionTypeName = questionTypeName;
	}
	
	public String getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(String questionNo) {
		this.questionNo = questionNo;
	}
	public int getSelectATotal() {
		return selectATotal;
	}
	public void setSelectATotal(int selectATotal) {
		this.selectATotal = selectATotal;
	}
	public int getSelectBTotal() {
		return selectBTotal;
	}
	public void setSelectBTotal(int selectBTotal) {
		this.selectBTotal = selectBTotal;
	}
	public int getSelectCTotal() {
		return selectCTotal;
	}
	public void setSelectCTotal(int selectCTotal) {
		this.selectCTotal = selectCTotal;
	}
	public int getSelectDTotal() {
		return selectDTotal;
	}
	public void setSelectDTotal(int selectDTotal) {
		this.selectDTotal = selectDTotal;
	}
	public int getSelectETotal() {
		return selectETotal;
	}
	public void setSelectETotal(int selectETotal) {
		this.selectETotal = selectETotal;
	}
	public int getSelectFTotal() {
		return selectFTotal;
	}
	public void setSelectFTotal(int selectFTotal) {
		this.selectFTotal = selectFTotal;
	}

	
}
