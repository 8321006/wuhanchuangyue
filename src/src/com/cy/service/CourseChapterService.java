package com.cy.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cy.exception.ServiceException;
import com.cy.mapper.CourseChapterMapper;
import com.cy.model.CourseChapter;
import com.cy.model.CourseClass;

/**
 * 业务实现层 - 表：t_course_chapter
 * @since 2015-07-07 09:52:28
 */
@Service("courseChapterService")
public class CourseChapterService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Resource
	private CourseChapterMapper courseChapterMapper;

	public void insert(CourseChapter entity) throws ServiceException {
		try {
			courseChapterMapper.insert(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void delete(CourseChapter entity) throws ServiceException {
		try {
			courseChapterMapper.deleteByPrimaryKey(entity.getChapterId());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void batchDelete(List<String> ids) throws ServiceException {
		try {
			for (String id : ids) {
				courseChapterMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void update(CourseChapter entity) throws ServiceException {
		try {
			courseChapterMapper.updateByPrimaryKey(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void updateSelective(CourseChapter entity) throws ServiceException {
		try {
			courseChapterMapper.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public CourseChapter findByPrimaryKey(String chapterId) throws ServiceException {
		try {
			return courseChapterMapper.selectByPrimaryKey(chapterId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public List<CourseChapter> findByCourseId(String courseId) throws ServiceException {
		try {
			return courseChapterMapper.findByCourseId(courseId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public CourseClass findPlan(Map<String,String> condition){
		return courseChapterMapper.findPlan(condition);
	}
	/*
	 * 每课用的总时间
	 */
	public int findplantotalByCourseId(String courseId) throws ServiceException {
		try {
			return courseChapterMapper.findplantotalByCourseId(courseId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
}