package com.cy.mapper;

import java.util.List;
import java.util.Map;

import com.cy.model.CourseExamAnalysis;

/**
 * MyBatis Mapper 接口 - 表：t_course_exam_analysis
 * @since 2015-07-09 14:58:36
 */
public interface CourseExamAnalysisMapper {
	/**
	 * 按主键删除
	 * @since 2015-07-09 14:58:36
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 插入 - 全字段保存
	 * @since 2015-07-09 14:58:36
	 */
	int insert(CourseExamAnalysis record);

	/**
	 * 插入 - 仅保存给定实体类中非null的字段
	 * @since 2015-07-09 14:58:36
	 */
	int insertSelective(CourseExamAnalysis record);

	/**
	 * 按主键查询
	 * @since 2015-07-09 14:58:36
	 */
	CourseExamAnalysis selectByPrimaryKey(Integer id);

	/**
	 * 按主键更新 - 仅更新给定实体类中非null的字段
	 * @since 2015-07-09 14:58:36
	 */
	int updateByPrimaryKeySelective(CourseExamAnalysis record);

	/**
	 * 按主键更新 - 全更新
	 * @since 2015-07-09 14:58:36
	 */
	int updateByPrimaryKey(CourseExamAnalysis record);

	List<CourseExamAnalysis> selectPage(CourseExamAnalysis example);

	//统计总的记录数
	long selectCountByCriteria(CourseExamAnalysis example);
	
	int countPublishExamsByCourse(String courseId);
	int countExamsByCourse(Map<String, Object> args);
}