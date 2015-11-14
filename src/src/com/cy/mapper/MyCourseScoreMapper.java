package com.cy.mapper;

import java.util.List;

import com.cy.model.MyCourseScore;

/**
 * MyBatis Mapper 接口 - 表：t_my_course_score
 * @since 2015-07-08 14:27:56
 */
public interface MyCourseScoreMapper {
	/**
	 * 按主键删除
	 * @since 2015-07-08 14:27:56
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * 插入 - 全字段保存
	 * @since 2015-07-08 14:27:56
	 */
	int insert(MyCourseScore record);

	/**
	 * 插入 - 仅保存给定实体类中非null的字段
	 * @since 2015-07-08 14:27:56
	 */
	int insertSelective(MyCourseScore record);

	/**
	 * 按主键查询
	 * @since 2015-07-08 14:27:56
	 */
	MyCourseScore selectByPrimaryKey(String id);

	/**
	 * 按主键更新 - 仅更新给定实体类中非null的字段
	 * @since 2015-07-08 14:27:56
	 */
	int updateByPrimaryKeySelective(MyCourseScore record);

	/**
	 * 按主键更新 - 全更新
	 * @since 2015-07-08 14:27:56
	 */
	int updateByPrimaryKey(MyCourseScore record);

	List<MyCourseScore> selectPage(MyCourseScore example);

	/**
	 * 按 Criteria 条件查询, 支持分页
	 * @since 2015-07-08 14:27:56
	 */
	//List<MyCourseScore> selectByCriteria(Criteria<MyCourseScore> criteria);

	//统计总的记录数
	long selectCountByCriteria(MyCourseScore example);
}