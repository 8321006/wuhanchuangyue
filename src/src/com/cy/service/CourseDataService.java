package com.cy.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cy.mapper.CourseDataMapper;
import com.cy.model.CourseData;
import com.cy.model.User;

//业务实现层
@Service("courseDataService")
public class CourseDataService implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Resource
	private CourseDataMapper courseDataMapper;
	//根据userID 查询所有的courseID
	
	public List<CourseData> findAllCourseIdByuserId(User user){
		return courseDataMapper.findAllCourseIdByuserId(user);
	}
	
	//根据courseId查询课程资料信息
	public List<CourseData> findCourseDataByCourseId(String courseId){
		return courseDataMapper.findCourseDatasByCourseId(courseId);
	}
	//根据universityId查询课程资料信息
	public List<CourseData> findAllCourseIdByUniversityId(String userId){
		return courseDataMapper.findAllCourseIdByUniversityId(userId);
	}
}
