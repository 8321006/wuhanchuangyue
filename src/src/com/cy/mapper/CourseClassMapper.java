package com.cy.mapper;

import java.util.List;

import com.cy.model.CourseClass;
import com.cy.model.UniversityCourse;

/**
 * MyBatis Mapper 接口 - 表：t_course_class
 * @since 2015-07-02 15:44:18
 */
public interface CourseClassMapper {
	/**
	 * 按主键删除
	 * @since 2015-07-02 15:44:18
	 */
	int deleteByPrimaryKey(String classId);

	/**
	 * 插入 - 全字段保存
	 * @since 2015-07-02 15:44:18
	 */
	int insert(CourseClass record);

	/**
	 * 插入 - 仅保存给定实体类中非null的字段
	 * @since 2015-07-02 15:44:18
	 */
	int insertSelective(CourseClass record);

	/**
	 * 按主键查询
	 * @since 2015-07-02 15:44:18
	 */
	CourseClass selectByPrimaryKey(String classId);

	/**
	 * 按主键更新 - 仅更新给定实体类中非null的字段
	 * @since 2015-07-02 15:44:18
	 */
	int updateByPrimaryKeySelective(CourseClass record);

	/**
	 * 按主键更新 - 全更新
	 * @since 2015-07-02 15:44:18
	 */
	int updateByPrimaryKey(CourseClass record);

	
	int insertCourseClassList(List<CourseClass> courseClassList);

	int  UpdateTime(CourseClass courseId);
   //更新t_courseclass中课程负责人的信息
	int updateByClassId(CourseClass courseClass);

	String selectbyid(String universityCourseId);
   //删除t_courseclass
	void deletebyid(UniversityCourse universityCourse);

}