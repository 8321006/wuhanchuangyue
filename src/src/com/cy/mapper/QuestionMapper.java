package com.cy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cy.common.util.Page;
import com.cy.model.Question;
import com.cy.model.QuestionFilter;
import com.cy.model.QuestionQueryResult;
import com.cy.model.QuestionType;

public interface QuestionMapper {

	public void insertQuestion(List<Question> questioncontentlist) throws Exception;

	public List<Question> getQuestionList(QuestionFilter qf,
			Page<Question> pageModel);

	List<QuestionType> getQuestionTypeList();

	public List<Question> getQuestionList(Question qf);
	Question getQuestionByQuestionId(@Param("questionId") String questionId);

	List<Question> getQuestionAnalysisListByIdList(
			@Param("array") List<Integer> idList);

//	public void addQuestionKnowledgePoint(@Param("questionId") int questionId,
//			@Param("pointId") int pointId) throws Exception;
	
}
