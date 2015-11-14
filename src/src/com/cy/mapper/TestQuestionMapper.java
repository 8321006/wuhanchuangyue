package com.cy.mapper;

import java.util.List;

import com.cy.model.TestQuestion;

/**
 * MyBatis Mapper 接口 - 表：t_test_question
 * @since 2015-07-15 09:27:26
 */
public interface TestQuestionMapper {
	/**
	 * 
	* @Title: insertTestRecord 
	* @Description: 插入答卷记录 
	* @param test
	 * @return 
	* @return
	 */
	void insertRecordList(List<TestQuestion> list);

}