package com.cy.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import net.sf.ehcache.search.expression.Criteria;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cy.exception.ServiceException;
import com.cy.mapper.UniversityMapper;
import com.cy.model.University;
import com.cy.model.UniversityCourse;

/**
 * 业务实现层 - 表：t_university
 * @since 2015-07-10 16:35:13
 */
@Service("universityService")
public class UniversityService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Resource
	private UniversityMapper universityMapper;


	/**
	 * 根据学校名称查询Id
	 */
	public UniversityCourse findByUniversityName(String name) throws ServiceException {
		try {
			return universityMapper.findByUniversityName(name);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void insert(University entity) throws ServiceException {
		try {
			universityMapper.insert(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void delete(String universityId) throws ServiceException {
		try {
			universityMapper.deleteByPrimaryKey(universityId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void batchDelete(List<String> ids) throws ServiceException {
		try {
			for (String id : ids) {
				universityMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void update(University entity) throws ServiceException {
		try {
			universityMapper.updateByPrimaryKey(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void updateSelective(University entity) throws ServiceException {
		try {
			universityMapper.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public University findByPrimaryKey(String universityId) throws ServiceException {
		try {
			return universityMapper.selectByPrimaryKey(universityId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public List<University> selectAll() throws ServiceException {
		try {
			return universityMapper.selectAll();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	
}