package com.cy.service;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cy.exception.ServiceException;
import com.cy.mapper.MyCourseQuizMapper;
import com.cy.model.MyCourseQuiz;

/**
 * 业务实现层 - 表：t_my_course_quiz
 * @since 2015-07-08 14:27:56
 */
@Service("myCourseQuizService")
public class MyCourseQuizService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private MyCourseQuizMapper myCourseQuizMapper;

	public void insert(MyCourseQuiz entity) throws ServiceException {
		try {
			myCourseQuizMapper.insert(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void delete(MyCourseQuiz entity) throws ServiceException {
		try {
			myCourseQuizMapper.deleteByPrimaryKey(entity.getId());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void batchDelete(List<String> ids) throws ServiceException {
		try {
			for (String id : ids) {
				myCourseQuizMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void update(MyCourseQuiz entity) throws ServiceException {
		try {
			myCourseQuizMapper.updateByPrimaryKey(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void updateSelective(MyCourseQuiz entity) throws ServiceException {
		try {
			myCourseQuizMapper.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public MyCourseQuiz findByPrimaryKey(String courseId) throws ServiceException {
		try {
			return myCourseQuizMapper.selectByPrimaryKey(courseId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/*public Pager<MyCourseQuiz> findPage(Criteria<MyCourseQuiz> criteria) throws ServiceException {
		try {
			if (criteria == null) {
				 criteria = Criteria.create(MyCourseQuiz.class);
			}
			criteria.pagination(true);
			List<MyCourseQuiz> list = myCourseQuizMapper.selectByCriteria(criteria);
			return new Pager<MyCourseQuiz>(criteria, list);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<MyCourseQuiz> findAll(Criteria<MyCourseQuiz> criteria) throws ServiceException {
		try {
			if (criteria == null) {
				 criteria = Criteria.create(MyCourseQuiz.class);
			}
			criteria.pagination(false);
			return myCourseQuizMapper.selectByCriteria(criteria);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}*/
	
	public List<Map<String, Object>> countQuizsByCourse() throws ServiceException{
		try {
			return myCourseQuizMapper.countQuizsByCourse();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public List<Map<String, Object>> countReplysByCourse() throws ServiceException{
		try {
			return myCourseQuizMapper.countReplysByCourse();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<Map<String, Object>> getquList(String userId,String chapterId) {
		
		return myCourseQuizMapper.getquList(userId,chapterId);
	}

	public List<Map<String, Object>> getquwidList(String chapterId) {
		
		return myCourseQuizMapper.getquwidList(chapterId);
	}
	
	//获取提问的总条数
	public int getquizcount(String chapterId) {
		
		return myCourseQuizMapper.getquizcount(chapterId);
	}
	
	//查询学生的个人所有提问
	public List<MyCourseQuiz> findMyCourseQuiz(String userId,String courseId,String classId) {
		Map<String,String> condition = new HashMap<String,String>();
		condition.put("userId", userId);
		condition.put("courseId", courseId);
		condition.put("classId", classId);
		return myCourseQuizMapper.findMyCourseQuiz(condition);
	}
	//查询老师的个人所有问题
	public List<MyCourseQuiz> findMyCourseQuizTeacher(String userId,String courseId,String classId) {
		Map<String,String> condition = new HashMap<String,String>();
		condition.put("userId", userId);
		condition.put("courseId", courseId);
		condition.put("classId", classId);
		return myCourseQuizMapper.findMyCourseQuizTeacher(condition);
	}
	
	
	
	public void updateAnwser(String quizid,String answer,Date day) throws ServiceException {
		try {
			myCourseQuizMapper.updateAnwser(quizid,answer,day);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public String findteacherId(String classId) throws ServiceException {
		try {
		return myCourseQuizMapper.findteacherId(classId);
	} catch (Exception e) {
		throw new ServiceException(e.getMessage(), e);
	}
	}
	
}
