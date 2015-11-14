package com.cy.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cy.common.util.CommonFunc;
import com.cy.exception.ServiceException;
import com.cy.mapper.UniversityTransactionMapper;
import com.cy.model.UniversityTransaction;
@Service("UniversityTransactionService")
public class UniversityTransactionService {
	@Resource
	private UniversityTransactionMapper universityTransactionMapper;
	
	public List<UniversityTransaction> findAll(UniversityTransaction universityTransaction) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return universityTransactionMapper.selectAll(universityTransaction);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}	
	}
	
	/**
	 * 
	* @Title: queryTransactionByPaperId 
	* @Description: 查询自己某次考试的补考事务
	* @param paperId
	* @return
	* @throws ServiceException
	 */
	public UniversityTransaction queryTransactionByPaperId(UniversityTransaction universityTransaction) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return universityTransactionMapper.queryTransactionByPaperId(universityTransaction);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}	
	}
	
	/**
	 * 
	* @Title: insertTransaction 
	* @Description: 申请事务
	* @param universityTransaction
	* @throws ServiceException
	 */
	public void insert(UniversityTransaction universityTransaction) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			universityTransactionMapper.insert(universityTransaction);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}	
	}

	/**
	 * 
	* @Title: update 
	* @Description: 更新事务非空字段
	* @param universityTransaction
	* @throws ServiceException
	 */
	public void update(UniversityTransaction universityTransaction) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			universityTransactionMapper.updateByPrimaryKeySelective(universityTransaction);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}	
		
	}

	public List<UniversityTransaction> findAllteacherTransactionList(UniversityTransaction universityTransaction) throws ServiceException {
		try {
			return universityTransactionMapper.findAllteacherTransactionList(universityTransaction);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}	
	}

	public void updateByuserId(UniversityTransaction universityTransaction) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			universityTransactionMapper.updateByuserId(universityTransaction);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}	
		
	}

	

}
