package com.cy.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.util.StringUtil;
import com.cy.exception.ServiceException;
import com.cy.mapper.CourseScoreAnalysisMapper;
import com.cy.model.CourseScoreAnalysis;

/**
 * 业务实现层 - 表：t_course_score_analysis
 * @since 2015-07-09 14:17:10
 */
@Service("courseScoreAnalysisService")
public class CourseScoreAnalysisService implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int TEST_TASK = 0;
	private static final int TEST_EXAM = 1;

	
	@Resource
	private CourseScoreAnalysisMapper courseScoreAnalysisMapper;

	public void insert(CourseScoreAnalysis entity) throws ServiceException {
		try {
			courseScoreAnalysisMapper.insert(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void delete(CourseScoreAnalysis entity) throws ServiceException {
		try {
			courseScoreAnalysisMapper.deleteByPrimaryKey(entity.getScoreId());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void batchDelete(List<String> ids) throws ServiceException {
		try {
			for (String id : ids) {
				courseScoreAnalysisMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void update(CourseScoreAnalysis entity) throws ServiceException {
		try {
			courseScoreAnalysisMapper.updateByPrimaryKey(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void updateSelective(CourseScoreAnalysis entity) throws ServiceException {
		try {
			courseScoreAnalysisMapper.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public CourseScoreAnalysis findByPrimaryKey(String id) throws ServiceException {
		try {
			return courseScoreAnalysisMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	private int getScoreByClassAndUser(String classId,String userId,int testType) throws ServiceException {
		try {
			Map<String, Object> args = new HashMap<String, Object>();
			if(StringUtil.isNotEmpty(userId)){
				args.put("userId", userId);
			}
			if(StringUtil.isNotEmpty(classId)){
				args.put("classId", classId);
			}
				args.put("testType", testType);
			return courseScoreAnalysisMapper.getScoreExamByClassAndUser(args);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public int getScoreExamByClassAndUser(String classId,String userId) throws ServiceException {
		return getScoreByClassAndUser(classId,userId,TEST_EXAM);
	}
	public int getScoreTaskByClassAndUser(String classId,String userId) throws ServiceException {
		return getScoreByClassAndUser(classId,userId,TEST_TASK);
	}
}