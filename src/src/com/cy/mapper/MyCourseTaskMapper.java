package com.cy.mapper;

import java.util.List;
import java.util.Map;

import com.cy.model.MyCourseTask;

/**
 * MyBatis Mapper 接口 - 表：t_my_course_task
 * @since 2015-07-08 14:27:56
 */
public interface MyCourseTaskMapper {
	/**
	 * 按主键删除
	 * @since 2015-07-08 14:27:56
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 插入 - 全字段保存
	 * @since 2015-07-08 14:27:56
	 */
	int insert(MyCourseTask record);

	/**
	 * 插入 - 仅保存给定实体类中非null的字段
	 * @since 2015-07-08 14:27:56
	 */
	int insertSelective(MyCourseTask record);

	/**
	 * 按主键查询
	 * @since 2015-07-08 14:27:56
	 */
	MyCourseTask selectByPrimaryKey(Integer id);

	/**
	 * 按主键更新 - 仅更新给定实体类中非null的字段
	 * @since 2015-07-08 14:27:56
	 */
	int updateByPrimaryKeySelective(MyCourseTask record);

	/**
	 * 按主键更新 - 全更新
	 * @since 2015-07-08 14:27:56
	 */
	int updateByPrimaryKey(MyCourseTask record);

	List<MyCourseTask> selectPage(MyCourseTask example);

	/**
	 * 按 Criteria 条件查询, 支持分页
	 * @since 2015-07-08 14:27:56
	 */
	//List<MyCourseTask> selectByCriteria(Criteria<MyCourseTask> criteria);

	//统计总的记录数
	long selectCountByCriteria(MyCourseTask example);
	
	List<Map<String, Object>> countTaskByCourse(int status);
}