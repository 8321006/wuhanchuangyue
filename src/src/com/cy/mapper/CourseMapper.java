package com.cy.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cy.model.Course;
import com.cy.model.CourseChapter;
import com.cy.model.CourseCount;
import com.cy.model.CourseNote;
import com.cy.model.CourseResource;
import com.cy.model.MyCourse;
import com.cy.model.Video;

/**
 * MyBatis Mapper 接口 - 表：t_course
 * @since 2015-06-08 15:47:16
 */
public interface CourseMapper {
	/**
	 * 按主键删除
	 * @since 2015-06-08 15:47:16
	 */
	int deleteByPrimaryKey(String courseId);

	/**
	 * 插入 - 全字段保存
	 * @since 2015-06-08 15:47:16
	 */
	int insert(Course record);

	/**
	 * 插入 - 仅保存给定实体类中非null的字段
	 * @since 2015-06-08 15:47:16
	 */
	int insertSelective(Course record);

	/**
	 * 按主键查询
	 * @since 2015-06-08 15:47:16
	 */
	Course selectByPrimaryKey(String courseId);

	/**
	 * 按主键更新 - 仅更新给定实体类中非null的字段
	 * @since 2015-06-08 15:47:16
	 */
	int updateByPrimaryKeySelective(Course record);

	/**
	
	 * @since 2015-06-08 15:47:16
	 */
	int updateByPrimaryKeyWithBLOBs(Course record);

	/**
	 * 按主键更新 - 全更新
	 * @since 2015-06-08 15:47:16
	 */
	int updateByPrimaryKey(Course record);
	
	List<Course> findmyIncourse(Map<String,String> condition);
	
	
	
	List<Course> findByxsl(Date studyendtime);
	
	int insertCourseChapters(List<CourseChapter> coursechapter);
	
	int insertCourseResource(List<CourseResource> courseResource);
	
	Course findCourseType(String typename);
	
	List<Course> findAllCourses();
	
	List<Course> findCourseByType(String type);
	
	Course findCourseTypeById(String typeId);
	
	List<CourseNote> findMyNotes(Map<String,String> condition);

	List<Course> searchCourse(String searchname);

	Course findCourseByName(String courseName);
	
	int insertVideoHistory(Video video);
	
	Video findLatestVideoHistory(Video video);
	
	int addnote(CourseNote courseNote);
	
	int countcourseById(String courseId);
	
	int countcourseByCondition(Map<String,String> condition);
	
	int addmycourse(MyCourse mycourse);
   /*
    * 删除mycourse的老师的信息
    */
	int  deleteCourseId(MyCourse mycourse);
	
	CourseCount countcourse(String userId);
	
	int countcoursevideos(String courseId);
//判断该课程是否在数据库中已经存在
	int courseSelect(String courseName);
	
}