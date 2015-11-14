package com.cy.mapper;

import java.util.List;
import java.util.Map;

import com.cy.model.CourseScoreAnalysis;

/**
 * MyBatis Mapper 接口 - 表：t_course_score_analysis
 * @since 2015-07-23 15:10:16
 */
public interface CourseScoreAnalysisMapper {
	/**
	 * 按主键删除
	 * @since 2015-07-23 15:10:16
	 */
	int deleteByPrimaryKey(String scoreId);

	/**
	 * 插入 - 全字段保存
	 * @since 2015-07-23 15:10:16
	 */
	int insert(CourseScoreAnalysis record);

	/**
	 * 插入 - 仅保存给定实体类中非null的字段
	 * @since 2015-07-23 15:10:16
	 */
	int insertSelective(CourseScoreAnalysis record);

	/**
	 * 按主键查询
	 * @since 2015-07-23 15:10:16
	 */
	CourseScoreAnalysis selectByPrimaryKey(String scoreId);

	/**
	 * 按主键更新 - 仅更新给定实体类中非null的字段
	 * @since 2015-07-23 15:10:16
	 */
	int updateByPrimaryKeySelective(CourseScoreAnalysis record);

	/**
	 * 按主键更新 - 全更新
	 * @since 2015-07-23 15:10:16
	 */
	int updateByPrimaryKey(CourseScoreAnalysis record);

	List<CourseScoreAnalysis> selectPage(CourseScoreAnalysis example);

	long selectCountByCriteria(CourseScoreAnalysis example);
	
	int getScoreExamByClassAndUser(Map<String, Object> args);
}