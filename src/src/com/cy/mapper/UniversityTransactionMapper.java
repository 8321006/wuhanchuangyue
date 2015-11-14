package com.cy.mapper;

import java.util.List;

import com.cy.model.UniversityTransaction;

public interface UniversityTransactionMapper {

	List<UniversityTransaction> selectAll(UniversityTransaction universityTransaction);

	/**
	 * 
	* @Title: queryTransactionByPaperId 
	* @Description: 查询自己某次考试的补考事务
	* @param universityTransaction
	* @return
	 */
	UniversityTransaction queryTransactionByPaperId(
			UniversityTransaction universityTransaction);

	/**
	 * 
	* @Title: insertTransaction 
	* @Description: 申请事务
	* @param universityTransaction
	 */
	void insert(UniversityTransaction universityTransaction);

	/**
	 * 
	* @Title: update 
	* @Description:  更新事务非空字段
	* @param universityTransaction
	 */
	void updateByPrimaryKeySelective(UniversityTransaction universityTransaction);

	List<UniversityTransaction> findAllteacherTransactionList(UniversityTransaction universityTransaction);

	void updateByuserId(UniversityTransaction universityTransaction);

}
