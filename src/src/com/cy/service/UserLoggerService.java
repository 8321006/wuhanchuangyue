package com.cy.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cy.exception.ServiceException;
import com.cy.mapper.UserLoggerMapper;
import com.cy.model.UserLogger;

/**
 * 业务实现层 - 表：t_user_logger
 * @since 2015-08-28 14:42:22
 */
@Service("userLoggerService")
public class UserLoggerService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Resource
	private UserLoggerMapper userLoggerMapper;

	public void insert(UserLogger entity) throws ServiceException {
		try {
			userLoggerMapper.insert(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void delete(UserLogger entity) throws ServiceException {
		try {
			userLoggerMapper.deleteByPrimaryKey(entity.getId());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void batchDelete(List<Integer> ids) throws ServiceException {
		try {
			for (Integer id : ids) {
				userLoggerMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void update(UserLogger entity) throws ServiceException {
		try {
			userLoggerMapper.updateByPrimaryKey(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void updateSelective(UserLogger entity) throws ServiceException {
		try {
			userLoggerMapper.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public UserLogger findByPrimaryKey(Integer id) throws ServiceException {
		try {
			return userLoggerMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public List<Map<String, Object>> getLogCurrMonth(String universityId) throws ServiceException {
		try {
			return userLoggerMapper.getLogCurrMonth(universityId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public List<Map<String, Object>> getAllLogCurrMonth() throws ServiceException {
		try {
			return userLoggerMapper.getAllLogCurrMonth();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
}