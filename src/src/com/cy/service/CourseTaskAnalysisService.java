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
import com.cy.mapper.CourseTaskAnalysisMapper;
import com.cy.model.CourseTaskAnalysis;

/**
 * 业务实现层 - 表：t_course_task_analysis
 * @since 2015-07-09 14:17:10
 */
@Service("courseTaskAnalysisService")
public class CourseTaskAnalysisService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Resource
	private CourseTaskAnalysisMapper courseTaskAnalysisMapper;

	public void insert(CourseTaskAnalysis entity) throws ServiceException {
		try {
			courseTaskAnalysisMapper.insert(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void delete(CourseTaskAnalysis entity) throws ServiceException {
		try {
			courseTaskAnalysisMapper.deleteByPrimaryKey(entity.getId());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void batchDelete(List<Integer> ids) throws ServiceException {
		try {
			for (Integer id : ids) {
				courseTaskAnalysisMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void update(CourseTaskAnalysis entity) throws ServiceException {
		try {
			courseTaskAnalysisMapper.updateByPrimaryKey(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void updateSelective(CourseTaskAnalysis entity) throws ServiceException {
		try {
			courseTaskAnalysisMapper.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public CourseTaskAnalysis findByPrimaryKey(Integer id) throws ServiceException {
		try {
			return courseTaskAnalysisMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public int countPublishTasksByCourse(String courseId) throws ServiceException {
		try {
			if(StringUtil.isNotEmpty(courseId)){
				return courseTaskAnalysisMapper.countPublistTasksByCourse(courseId);
			}
			return 0;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public int countSubmitTasksByCourse(String courseId) throws ServiceException {
		try {
			Map<String, Object> args = new HashMap<String, Object>();
			if(StringUtil.isNotEmpty(courseId)){
				args.put("courseId", courseId);
			}
			args.put("testType", 0);
			args.put("markStatus", 0);
			return courseTaskAnalysisMapper.countLearnTasksByCourse(args);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public int countCorrectTasksByCourse(String courseId) throws ServiceException {
		try {
			Map<String, Object> args = new HashMap<String, Object>();
			if(StringUtil.isNotEmpty(courseId)){
				args.put("courseId", courseId);
			}
			args.put("testType", 0);
			args.put("markStatus", 1);
			return courseTaskAnalysisMapper.countLearnTasksByCourse(args);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	/*public Pager<CourseTaskAnalysis> findPage(Criteria<CourseTaskAnalysis> criteria) throws ServiceException {
		try {
			if (criteria == null) {
				 criteria = Criteria.create(CourseTaskAnalysis.class);
			}
			criteria.pagination(true);
			List<CourseTaskAnalysis> list = courseTaskAnalysisMapper.selectByCriteria(criteria);
			return new Pager<CourseTaskAnalysis>(criteria, list);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<CourseTaskAnalysis> findAll(Criteria<CourseTaskAnalysis> criteria) throws ServiceException {
		try {
			if (criteria == null) {
				 criteria = Criteria.create(CourseTaskAnalysis.class);
			}
			criteria.pagination(false);
			return courseTaskAnalysisMapper.selectByCriteria(criteria);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}*/
}