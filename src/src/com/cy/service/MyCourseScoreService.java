package com.cy.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import net.sf.ehcache.search.expression.Criteria;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cy.exception.ServiceException;
import com.cy.mapper.MyCourseScoreMapper;
import com.cy.model.MyCourseScore;

/**
 * 业务实现层 - 表：t_my_course_score
 * @since 2015-07-08 14:27:56
 */
@Service("myCourseScoreService")
public class MyCourseScoreService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Resource
	private MyCourseScoreMapper myCourseScoreMapper;

	public void insert(MyCourseScore entity) throws ServiceException {
		try {
			myCourseScoreMapper.insert(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void delete(MyCourseScore entity) throws ServiceException {
		try {
			myCourseScoreMapper.deleteByPrimaryKey(entity.getId());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void batchDelete(List<String> ids) throws ServiceException {
		try {
			for (String id : ids) {
				myCourseScoreMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void update(MyCourseScore entity) throws ServiceException {
		try {
			myCourseScoreMapper.updateByPrimaryKey(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void updateSelective(MyCourseScore entity) throws ServiceException {
		try {
			myCourseScoreMapper.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public MyCourseScore findByPrimaryKey(String id) throws ServiceException {
		try {
			return myCourseScoreMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/*public Pager<MyCourseScore> findPage(Criteria<MyCourseScore> criteria) throws ServiceException {
		try {
			if (criteria == null) {
				 criteria = Criteria.create(MyCourseScore.class);
			}
			criteria.pagination(true);
			List<MyCourseScore> list = myCourseScoreMapper.selectByCriteria(criteria);
			return new Pager<MyCourseScore>(criteria, list);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<MyCourseScore> findAll(Criteria<MyCourseScore> criteria) throws ServiceException {
		try {
			if (criteria == null) {
				 criteria = Criteria.create(MyCourseScore.class);
			}
			criteria.pagination(false);
			return myCourseScoreMapper.selectByCriteria(criteria);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}*/
}