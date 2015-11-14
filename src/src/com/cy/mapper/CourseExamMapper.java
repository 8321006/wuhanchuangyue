package com.cy.mapper;

import java.util.List;

import com.cy.model.CourseExam;

/**
 * MyBatis Mapper 接口 - 表：t_course_exam
 * @since 2015-07-08 14:27:56
 */
public interface CourseExamMapper {
	/**
	 * 按主键删除
	 * @since 2015-07-08 14:27:56
	 */
	int deleteByPrimaryKey(String examId);

	/**
	 * 插入 - 全字段保存
	 * @since 2015-07-08 14:27:56
	 */
	int insert(CourseExam record);

	/**
	 * 插入 - 仅保存给定实体类中非null的字段
	 * @since 2015-07-08 14:27:56
	 */
	int insertSelective(CourseExam record);

	/**
	 * 按主键查询
	 * @since 2015-07-08 14:27:56
	 */
	CourseExam selectByPrimaryKey(String examId);

	/**
	 * 按主键更新 - 仅更新给定实体类中非null的字段
	 * @since 2015-07-08 14:27:56
	 */
	int updateByPrimaryKeySelective(CourseExam record);

	/**
	 * 按主键更新 - 全更新
	 * @since 2015-07-08 14:27:56
	 */
	int updateByPrimaryKey(CourseExam record);

	List<CourseExam> selectPage(CourseExam example);

	/**
	 * 按 Criteria 条件查询, 支持分页
	 * @since 2015-07-08 14:27:56
	 */
	//List<CourseExam> selectByCriteria(Criteria<CourseComment> criteria);

	//统计总的记录数
	long selectCountByCriteria(CourseExam example);
}