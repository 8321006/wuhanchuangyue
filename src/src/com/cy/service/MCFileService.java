package com.cy.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cy.exception.ServiceException;
import com.cy.mapper.MCFileMapper;
import com.cy.model.MCFile;

/**
 * 业务实现层 - 表：t_file
 * @since 2015-06-08 15:47:16
 */
@Service("fileService")
public class MCFileService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Resource
	private MCFileMapper fileMapper;

	public void insert(MCFile entity) throws ServiceException {
		try {
			fileMapper.insert(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void delete(String fileId) throws ServiceException {
		try {
			fileMapper.deleteByPrimaryKey(fileId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void batchDelete(List<String> ids) throws ServiceException {
		try {
			for (String id : ids) {
				fileMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void update(MCFile entity) throws ServiceException {
		try {
			fileMapper.updateByPrimaryKey(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void updateSelective(MCFile entity) throws ServiceException {
		try {
			fileMapper.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public MCFile findByPrimaryKey(String fileId) throws ServiceException {
		try {
			return fileMapper.selectByPrimaryKey(fileId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public List<MCFile> listAll() throws ServiceException {
		try {
			return fileMapper.listAll();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	
}