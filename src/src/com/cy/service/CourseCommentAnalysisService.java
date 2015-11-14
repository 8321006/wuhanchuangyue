package com.cy.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cy.exception.ServiceException;
import com.cy.mapper.CourseCommentAnalysisMapper;
import com.cy.model.CourseCommentAnalysis;

/**
 * 业务实现层 - 表：t_course_comment_analysis
 * @since 2015-07-09 14:17:10
 */
@Service("courseCommentAnalysisService")
public class CourseCommentAnalysisService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Resource
	private CourseCommentAnalysisMapper courseCommentAnalysisMapper;

	public void insert(CourseCommentAnalysis entity) throws ServiceException {
		try {
			courseCommentAnalysisMapper.insert(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void delete(CourseCommentAnalysis entity) throws ServiceException {
		try {
			courseCommentAnalysisMapper.deleteByPrimaryKey(entity.getId());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void batchDelete(List<Integer> ids) throws ServiceException {
		try {
			for (Integer id : ids) {
				courseCommentAnalysisMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void update(CourseCommentAnalysis entity) throws ServiceException {
		try {
			courseCommentAnalysisMapper.updateByPrimaryKey(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void updateSelective(CourseCommentAnalysis entity) throws ServiceException {
		try {
			courseCommentAnalysisMapper.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public CourseCommentAnalysis findByPrimaryKey(Integer id) throws ServiceException {
		try {
			return courseCommentAnalysisMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/*public Pager<CourseCommentAnalysis> findPage(Criteria<CourseCommentAnalysis> criteria) throws ServiceException {
		try {
			if (criteria == null) {
				 criteria = Criteria.create(CourseCommentAnalysis.class);
			}
			criteria.pagination(true);
			List<CourseCommentAnalysis> list = courseCommentAnalysisMapper.selectByCriteria(criteria);
			return new Pager<CourseCommentAnalysis>(criteria, list);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<CourseCommentAnalysis> findAll(Criteria<CourseCommentAnalysis> criteria) throws ServiceException {
		try {
			if (criteria == null) {
				 criteria = Criteria.create(CourseCommentAnalysis.class);
			}
			criteria.pagination(false);
			return courseCommentAnalysisMapper.selectByCriteria(criteria);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}*/
	
	
	public List<Map<String, Object>> countCommentPostsByCourse() throws ServiceException{
		try {
			return courseCommentAnalysisMapper.countCommentPostsByCourse();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public List<Map<String, Object>> countCommentReplysByCourse() throws ServiceException{
		try {
			return courseCommentAnalysisMapper.countCommentReplysByCourse();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
}