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
import com.cy.mapper.CourseQuizAnalysisMapper;
import com.cy.model.CourseQuizAnalysis;

/**
 * 业务实现层 - 表：t_course_quiz_analysis
 * @since 2015-07-09 14:17:10
 */
@Service("courseQuizAnalysisService")
public class CourseQuizAnalysisService implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int QUIZ_COMMIT = 3;
	private static final int QUIZ_REPLY = 5;

	
	
	@Resource
	private CourseQuizAnalysisMapper courseQuizAnalysisMapper;

	public void insert(CourseQuizAnalysis entity) throws ServiceException {
		try {
			courseQuizAnalysisMapper.insert(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void delete(CourseQuizAnalysis entity) throws ServiceException {
		try {
			courseQuizAnalysisMapper.deleteByPrimaryKey(entity.getId());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void batchDelete(List<Integer> ids) throws ServiceException {
		try {
			for (Integer id : ids) {
				courseQuizAnalysisMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void update(CourseQuizAnalysis entity) throws ServiceException {
		try {
			courseQuizAnalysisMapper.updateByPrimaryKey(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void updateSelective(CourseQuizAnalysis entity) throws ServiceException {
		try {
			courseQuizAnalysisMapper.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public CourseQuizAnalysis findByPrimaryKey(Integer id) throws ServiceException {
		try {
			return courseQuizAnalysisMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	private int countQuizs(String courseId,String userId,String universityId,int messageType) throws ServiceException{
		try {
			Map<String, Object> args = new HashMap<String, Object>();
			if(StringUtil.isNotEmpty(courseId)){
				args.put("courseId", courseId);
			}
			if(StringUtil.isNotEmpty(userId)){
				args.put("userId", userId);
			}
			if(StringUtil.isNotEmpty(universityId)){
				args.put("universityId", universityId);
			}
			args.put("messageType", messageType);
			return courseQuizAnalysisMapper.countQuizsByCourse(args);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public int countQuizsCommitByCourseId(String courseId) throws ServiceException {
		return countQuizs(courseId, null, null, QUIZ_COMMIT);
	}
	
	public int countQuizsCommitByCourseAndUser(String courseId,String userId) throws ServiceException {
		return countQuizs(courseId, userId, null, QUIZ_COMMIT);
	}
	public int countQuizsCommitByCourseAndUniversity(String courseId,String universityId) throws ServiceException {
		return countQuizs(courseId, null, universityId, QUIZ_COMMIT);
	}
	public int countQuizsReplyByCourseId(String courseId) throws ServiceException {
		return countQuizs(courseId, null, null, QUIZ_REPLY);
	}
	
	public int countQuizsReplyByCourseAndUser(String courseId,String userId) throws ServiceException {
		return countQuizs(courseId, userId, null, QUIZ_REPLY);
	}
	public int countQuizsReplyByCourseAndUniversity(String courseId,String universityId) throws ServiceException {
		return countQuizs(courseId, null, universityId, QUIZ_REPLY);
	}

	
}