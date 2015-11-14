package com.cy.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.ehcache.search.expression.Criteria;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cy.exception.ServiceException;
import com.cy.mapper.MyCourseTaskMapper;
import com.cy.model.MyCourseTask;

/**
 * 业务实现层 - 表：t_my_course_task
 * @since 2015-07-08 14:27:56
 */
@Service("myCourseTaskService")
public class MyCourseTaskService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Resource
	private MyCourseTaskMapper myCourseTaskMapper;

	public void insert(MyCourseTask entity) throws ServiceException {
		try {
			myCourseTaskMapper.insert(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void delete(MyCourseTask entity) throws ServiceException {
		try {
			myCourseTaskMapper.deleteByPrimaryKey(entity.getId());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void batchDelete(List<Integer> ids) throws ServiceException {
		try {
			for (Integer id : ids) {
				myCourseTaskMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void update(MyCourseTask entity) throws ServiceException {
		try {
			myCourseTaskMapper.updateByPrimaryKey(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void updateSelective(MyCourseTask entity) throws ServiceException {
		try {
			myCourseTaskMapper.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public MyCourseTask findByPrimaryKey(Integer id) throws ServiceException {
		try {
			return myCourseTaskMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	
	public List<Map<String, Object>> countTaskByCourse(int status) throws ServiceException {
		try {
			return myCourseTaskMapper.countTaskByCourse(status);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	/*public Pager<MyCourseTask> findPage(Criteria<MyCourseTask> criteria) throws ServiceException {
		try {
			if (criteria == null) {
				 criteria = Criteria.create(MyCourseTask.class);
			}
			criteria.pagination(true);
			List<MyCourseTask> list = myCourseTaskMapper.selectByCriteria(criteria);
			return new Pager<MyCourseTask>(criteria, list);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<MyCourseTask> findAll(Criteria<MyCourseTask> criteria) throws ServiceException {
		try {
			if (criteria == null) {
				 criteria = Criteria.create(MyCourseTask.class);
			}
			criteria.pagination(false);
			return myCourseTaskMapper.selectByCriteria(criteria);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}*/
}