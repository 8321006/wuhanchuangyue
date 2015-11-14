package com.cy.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cy.exception.ServiceException;
import com.cy.mapper.CourseTaskMapper;
import com.cy.model.CourseTask;

/**
 * 业务实现层 - 表：t_course_task
 * @since 2015-07-08 14:27:56
 */
@Service("courseTaskService")
public class CourseTaskService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Resource
	private CourseTaskMapper courseTaskMapper;

	public void insert(CourseTask entity) throws ServiceException {
		try {
			courseTaskMapper.insert(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void delete(CourseTask entity) throws ServiceException {
		try {
			courseTaskMapper.deleteByPrimaryKey(entity.getTaskId());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void batchDelete(List<Integer> ids) throws ServiceException {
		try {
			for (Integer id : ids) {
				courseTaskMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void update(CourseTask entity) throws ServiceException {
		try {
			courseTaskMapper.updateByPrimaryKey(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void updateSelective(CourseTask entity) throws ServiceException {
		try {
			courseTaskMapper.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public CourseTask findByPrimaryKey(Integer taskId) throws ServiceException {
		try {
			return courseTaskMapper.selectByPrimaryKey(taskId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public List<Map<String, Object>> countTaskByCourse() throws ServiceException {
		try {
			return courseTaskMapper.countTaskByCourse();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	
	
	/*public Pager<CourseTask> findPage(Criteria<CourseTask> criteria) throws ServiceException {
		try {
			if (criteria == null) {
				 criteria = Criteria.create(CourseTask.class);
			}
			criteria.pagination(true);
			List<CourseTask> list = courseTaskMapper.selectByCriteria(criteria);
			return new Pager<CourseTask>(criteria, list);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<CourseTask> findAll(Criteria<CourseTask> criteria) throws ServiceException {
		try {
			if (criteria == null) {
				 criteria = Criteria.create(CourseTask.class);
			}
			criteria.pagination(false);
			return courseTaskMapper.selectByCriteria(criteria);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}*/
}