package com.cy.mapper;

import java.util.List;

import com.cy.model.CourseData;
import com.cy.model.User;

public interface CourseDataMapper {
	//查询courseId
	List<CourseData> findAllCourseIdByuserId(User user);
	
	//查询课程资料
	List<CourseData> findCourseDatasByCourseId(String courseId);
	
	//查询管理员有关courseId
	List<CourseData>findAllCourseIdByUniversityId(String userId);
}