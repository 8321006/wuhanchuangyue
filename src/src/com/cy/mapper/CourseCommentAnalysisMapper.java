package com.cy.mapper;

import java.util.List;
import java.util.Map;

import com.cy.model.CourseCommentAnalysis;

/**
 * MyBatis Mapper 接口 - 表：t_course_comment_analysis
 * @since 2015-07-09 14:58:36
 */
public interface CourseCommentAnalysisMapper {
	/**
	 * 按主键删除
	 * @since 2015-07-09 14:58:36
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 插入 - 全字段保存
	 * @since 2015-07-09 14:58:36
	 */
	int insert(CourseCommentAnalysis record);

	/**
	 * 插入 - 仅保存给定实体类中非null的字段
	 * @since 2015-07-09 14:58:36
	 */
	int insertSelective(CourseCommentAnalysis record);

	/**
	 * 按主键查询
	 * @since 2015-07-09 14:58:36
	 */
	CourseCommentAnalysis selectByPrimaryKey(Integer id);

	/**
	 * 按主键更新 - 仅更新给定实体类中非null的字段
	 * @since 2015-07-09 14:58:36
	 */
	int updateByPrimaryKeySelective(CourseCommentAnalysis record);

	/**
	 * 按主键更新 - 全更新
	 * @since 2015-07-09 14:58:36
	 */
	int updateByPrimaryKey(CourseCommentAnalysis record);

	List<CourseCommentAnalysis> selectPage(CourseCommentAnalysis example);

	//统计总的记录数
	long selectCountByCriteria(CourseCommentAnalysis example);
	
	
	List<Map<String, Object>> countCommentPostsByCourse();
	List<Map<String, Object>> countCommentReplysByCourse();
}