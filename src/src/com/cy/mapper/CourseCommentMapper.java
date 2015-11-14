package com.cy.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cy.model.CourseComment;

/**
 * MyBatis Mapper 接口 - 表：t_course_comment
 * @since 2015-07-08 14:27:56
 */
public interface CourseCommentMapper {
	/**
	 * 按主键删除
	 * @since 2015-07-08 14:27:56
	 */
	int deleteByPrimaryKey(String commentId);

	/**
	 * 插入 - 全字段保存
	 * @since 2015-07-08 14:27:56
	 */
	int insert(CourseComment record);

	/**
	 * 插入 - 仅保存给定实体类中非null的字段
	 * @since 2015-07-08 14:27:56
	 */
	int insertSelective(CourseComment record);

	/**
	 * 按主键查询
	 * @since 2015-07-08 14:27:56
	 */
	CourseComment selectByPrimaryKey(String commentId);

	/**
	 * 按主键更新 - 仅更新给定实体类中非null的字段
	 * @since 2015-07-08 14:27:56
	 */
	int updateByPrimaryKeySelective(CourseComment record);

	/**
	 * 按主键更新 - 全更新
	 * @since 2015-07-08 14:27:56
	 */
	int updateByPrimaryKey(CourseComment record);

	List<CourseComment> selectPage(CourseComment example);

	/**
	 * 按 Criteria 条件查询, 支持分页
	 * @since 2015-07-08 14:27:56
	 */
	//List<CourseComment> selectByCriteria(Criteria<CourseComment> criteria);

	//统计总的记录数
	long selectCountByCriteria(CourseComment example);

	//List<CourseComment> getplList(String  courseId);
	List<Map<String, Object>> getplList(@Param("userId") String userId,@Param("courseId")String courseId);

	List<CourseComment> getuserMessage(CourseComment courseId);
	
	public int countComment(@Param("userId") String userId,@Param("courseId")String courseId);

	List<Map<String, Object>> getplwidList(String courseId);

	public int countwidComment(String courseId);

}