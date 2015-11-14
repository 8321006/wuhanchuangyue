package com.cy.mapper;

import java.util.List;
import java.util.Map;

import com.cy.model.CourseTask;

/**
 * MyBatis Mapper 接口 - 表：t_course_task
 * @since 2015-07-08 14:27:56
 */
public interface CourseTaskMapper {
	/**
	 * 按主键删除
	 * @since 2015-07-08 14:27:56
	 */
	int deleteByPrimaryKey(Integer taskId);

	/**
	 * 插入 - 全字段保存
	 * @since 2015-07-08 14:27:56
	 */
	int insert(CourseTask record);

	/**
	 * 插入 - 仅保存给定实体类中非null的字段
	 * @since 2015-07-08 14:27:56
	 */
	int insertSelective(CourseTask record);

	/**
	 * 按主键查询
	 * @since 2015-07-08 14:27:56
	 */
	CourseTask selectByPrimaryKey(Integer taskId);

	/**
	 * 按主键更新 - 仅更新给定实体类中非null的字段
	 * @since 2015-07-08 14:27:56
	 */
	int updateByPrimaryKeySelective(CourseTask record);

	/**
	 * 按主键更新 - 全更新
	 * @since 2015-07-08 14:27:56
	 */
	int updateByPrimaryKey(CourseTask record);

	List<CourseTask> selectPage(CourseTask example);

	/**
	 * 按 Criteria 条件查询, 支持分页
	 * @since 2015-07-08 14:27:56
	 */
	//List<CourseTask> selectByCriteria(Criteria<CourseTask> criteria);

	//统计总的记录数
	long selectCountByCriteria(CourseTask example);
	List<CourseTask> selectAll();
	
	List<Map<String, Object>> countTaskByCourse();
}