package com.cy.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import net.sf.ehcache.search.expression.Criteria;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cy.exception.ServiceException;
import com.cy.mapper.CourseExamMapper;
import com.cy.model.CourseExam;

/**
 * 业务实现层 - 表：t_course_exam
 * @since 2015-07-08 14:27:56
 */
@Service("courseExamService")
public class CourseExamService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Resource
	private CourseExamMapper courseExamMapper;

	public void insert(CourseExam entity) throws ServiceException {
		try {
			courseExamMapper.insert(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void delete(CourseExam entity) throws ServiceException {
		try {
			courseExamMapper.deleteByPrimaryKey(entity.getExamId());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void batchDelete(List<String> ids) throws ServiceException {
		try {
			for (String id : ids) {
				courseExamMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void update(CourseExam entity) throws ServiceException {
		try {
			courseExamMapper.updateByPrimaryKey(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void updateSelective(CourseExam entity) throws ServiceException {
		try {
			courseExamMapper.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public CourseExam findByPrimaryKey(String examId) throws ServiceException {
		try {
			return courseExamMapper.selectByPrimaryKey(examId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/*public Pager<CourseExam> findPage(Criteria<CourseExam> criteria) throws ServiceException {
		try {
			if (criteria == null) {
				 criteria = Criteria.create(CourseExam.class);
			}
			criteria.pagination(true);
			List<CourseExam> list = courseExamMapper.selectByCriteria(criteria);
			return new Pager<CourseExam>(criteria, list);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<CourseExam> findAll(Criteria<CourseExam> criteria) throws ServiceException {
		try {
			if (criteria == null) {
				 criteria = Criteria.create(CourseExam.class);
			}
			criteria.pagination(false);
			return courseExamMapper.selectByCriteria(criteria);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}*/
}