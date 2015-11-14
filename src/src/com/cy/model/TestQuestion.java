package com.cy.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_test_question
 * @since 2015-07-15 09:27:26
 */
@Alias("TestQuestion")
public class TestQuestion implements Serializable {
	
	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	
	private static final long serialVersionUID = -1618876498394562506L;

	/** id -- 主鍵ID */
	private String id;
	
	/** test_id -- testId */
	private String testId;

	/** paper_id -- 试卷ID */
	private String paperId;
	
	/** question_Id -- questionId */
	private String questionId;

	/** user_id -- 学生ID */
	private String userId;
	
	/** user_Answer -- userAnswer */
	private String userAnswer;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

}