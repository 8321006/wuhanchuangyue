package com.cy.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.exception.ServiceException;
import com.cy.mapper.MyCourseMapper;
import com.cy.model.MyCourse;
import com.cy.model.UniversityCourse;

@Service("myCourseService")
public class MyCourseService {

	@Autowired
	private MyCourseMapper myCourseMapper;
	
	public List<Map<String, Object>> countStudents() throws ServiceException{
		try {		
			return myCourseMapper.countStudents();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	
	public List<String> findcourseid(String userId) throws ServiceException{
		try {		
			return myCourseMapper.findcourseid(userId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	* @Title: finishMyCourse 
	* @Description: 结束我的课程
	* @param userId
	* @param classId
	* @throws ServiceException
	 */
	public void finishMyCourse(String userId,String classId)throws ServiceException{
		try {		
			MyCourse myCourse = new MyCourse();
			myCourse.setClassId(classId);
			myCourse.setUserId(userId);
			// 2表示已经完成课程
			myCourse.setType("2");
			myCourseMapper.updateByUserIdSelective(myCourse);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	//删除t_my_course
	public void deleteclassId(UniversityCourse universityCourse) {
		myCourseMapper.deleteclassId(universityCourse);
		
	}



	
	
	
	
	
	
	
}
