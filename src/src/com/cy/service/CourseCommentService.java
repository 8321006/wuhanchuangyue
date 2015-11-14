package com.cy.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cy.exception.ServiceException;
import com.cy.mapper.CourseCommentMapper;
import com.cy.model.CourseComment;

/**
 * 业务实现层 - 表：t_course_comment
 * @since 2015-07-08 14:27:56
 */
@Service("courseCommentService")
public class CourseCommentService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Resource
	private CourseCommentMapper courseCommentMapper;

	public void insert(CourseComment entity) throws ServiceException {
		try {
			courseCommentMapper.insert(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void delete(CourseComment entity) throws ServiceException {
		try {
			courseCommentMapper.deleteByPrimaryKey(entity.getCommentId());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void batchDelete(List<String> ids) throws ServiceException {
		try {
			for (String id : ids) {
				courseCommentMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void update(CourseComment entity) throws ServiceException {
		try {
			courseCommentMapper.updateByPrimaryKey(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void updateSelective(CourseComment entity) throws ServiceException {
		try {
			courseCommentMapper.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public CourseComment findByPrimaryKey(String commentId) throws ServiceException {
		try {
			return courseCommentMapper.selectByPrimaryKey(commentId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/*public List<CourseComment> getplList(String courseId) throws ServiceException {

		try {
			return courseCommentMapper.getplList(courseId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}*/
public List<Map<String, Object>> getplList(String userId,String courseId) {
		
		return courseCommentMapper.getplList(userId,courseId);
	}

	public List<CourseComment> findMessageList(CourseComment courseId)throws ServiceException {
		try {
			return courseCommentMapper.getuserMessage(courseId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public int countComment(String userId,String courseId) {
		return courseCommentMapper.countComment(userId,courseId);
		
	}

	public List<Map<String, Object>> getwidList(String courseId) {
		
		return courseCommentMapper.getplwidList(courseId);
	}

	public int countwidComment(String courseId) {
		
		return courseCommentMapper.countwidComment(courseId);
	}

/*	public Pager<CourseComment> findPage(Criteria<CourseComment> criteria) throws ServiceException {
		try {
			if (criteria == null) {
				 criteria = Criteria.create(CourseComment.class);
			}
			criteria.pagination(true);
			List<CourseComment> list = courseCommentMapper.selectByCriteria(criteria);
			return new Pager<CourseComment>(criteria, list);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<CourseComment> findAll(Criteria<CourseComment> criteria) throws ServiceException {
		try {
			if (criteria == null) {
				 criteria = Criteria.create(CourseComment.class);
			}
			criteria.pagination(false);
			return courseCommentMapper.selectByCriteria(criteria);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}*/
	

}