package com.cy.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cy.exception.ServiceException;
import com.cy.mapper.CourseClassMapper;
import com.cy.model.CourseClass;
import com.cy.model.UniversityCourse;

/**
 * 业务实现层 - 表：t_course_chapter
 * @since 2015-07-07 09:52:28
 */
@Service("courseClassService")
public class CourseClassService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Resource
	private CourseClassMapper courseClassMapper;

	public void insert(CourseClass entity) throws ServiceException {
		try {
			courseClassMapper.insert(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void delete(CourseClass entity) throws ServiceException {
		try {
			courseClassMapper.deleteByPrimaryKey(entity.getClassId());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void batchDelete(List<String> ids) throws ServiceException {
		try {
			for (String id : ids) {
				courseClassMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void update(CourseClass entity) throws ServiceException {
		try {
			courseClassMapper.updateByPrimaryKey(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void updateSelective(CourseClass entity) throws ServiceException {
		try {
			courseClassMapper.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public CourseClass findByPrimaryKey(String chapterId) throws ServiceException {
		try {
			return courseClassMapper.selectByPrimaryKey(chapterId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public void UpdateTime(CourseClass courseId) throws ServiceException {
		try {
			courseClassMapper.UpdateTime(courseId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public String selectbyid(String universityCourseId) {
		return courseClassMapper.selectbyid(universityCourseId);
	}
	//删除t_course_class
	public void deletebyid(UniversityCourse universityCourse) {
		courseClassMapper.deletebyid(universityCourse);
		
	}

}