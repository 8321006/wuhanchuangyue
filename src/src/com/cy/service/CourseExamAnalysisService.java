package com.cy.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.ehcache.search.expression.Criteria;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.util.StringUtil;
import com.cy.exception.ServiceException;
import com.cy.mapper.CourseExamAnalysisMapper;
import com.cy.model.CourseExamAnalysis;

/**
 * 业务实现层 - 表：t_course_exam_analysis
 * @since 2015-07-09 14:17:10
 */
@Service("courseExamAnalysisService")
public class CourseExamAnalysisService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Resource
	private CourseExamAnalysisMapper courseExamAnalysisMapper;

	public void insert(CourseExamAnalysis entity) throws ServiceException {
		try {
			courseExamAnalysisMapper.insert(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void delete(CourseExamAnalysis entity) throws ServiceException {
		try {
			courseExamAnalysisMapper.deleteByPrimaryKey(entity.getId());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void batchDelete(List<Integer> ids) throws ServiceException {
		try {
			for (Integer id : ids) {
				courseExamAnalysisMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void update(CourseExamAnalysis entity) throws ServiceException {
		try {
			courseExamAnalysisMapper.updateByPrimaryKey(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void updateSelective(CourseExamAnalysis entity) throws ServiceException {
		try {
			courseExamAnalysisMapper.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public CourseExamAnalysis findByPrimaryKey(Integer id) throws ServiceException {
		try {
			return courseExamAnalysisMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public int countPublishExamsByCourse(String courseId) throws ServiceException {
		try {
			if(StringUtil.isNotEmpty(courseId)){
				return courseExamAnalysisMapper.countPublishExamsByCourse(courseId);
			}
			return 0;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public int countSubmitExamsByCourse(String courseId) throws ServiceException {
		try {
			Map<String, Object> args = new HashMap<String, Object>();
			if(StringUtil.isNotEmpty(courseId)){
				args.put("courseId", courseId);
			}
			args.put("testType", 1);
			args.put("markStatus", 0);
			return courseExamAnalysisMapper.countExamsByCourse(args);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public int countCorrectExamsByCourse(String courseId) throws ServiceException {
		try {
			Map<String, Object> args = new HashMap<String, Object>();
			if(StringUtil.isNotEmpty(courseId)){
				args.put("courseId", courseId);
			}
			args.put("testType", 1);
			args.put("markStatus", 1);
			return courseExamAnalysisMapper.countExamsByCourse(args);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/*public Pager<CourseExamAnalysis> findPage(Criteria<CourseExamAnalysis> criteria) throws ServiceException {
		try {
			if (criteria == null) {
				 criteria = Criteria.create(CourseExamAnalysis.class);
			}
			criteria.pagination(true);
			List<CourseExamAnalysis> list = courseExamAnalysisMapper.selectByCriteria(criteria);
			return new Pager<CourseExamAnalysis>(criteria, list);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<CourseExamAnalysis> findAll(Criteria<CourseExamAnalysis> criteria) throws ServiceException {
		try {
			if (criteria == null) {
				 criteria = Criteria.create(CourseExamAnalysis.class);
			}
			criteria.pagination(false);
			return courseExamAnalysisMapper.selectByCriteria(criteria);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}*/
	
}