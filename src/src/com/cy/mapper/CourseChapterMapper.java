package com.cy.mapper;

import java.util.List;
import java.util.Map;

import com.cy.model.CourseChapter;
import com.cy.model.CourseClass;

/**
 * MyBatis Mapper 接口 - 表：t_course_chapter
 * @since 2015-07-07 09:52:28
 */
public interface CourseChapterMapper {
	/**
	 * 按主键删除
	 * @since 2015-07-07 09:52:28
	 */
	int deleteByPrimaryKey(String chapterId);

	/**
	 * 插入 - 全字段保存
	 * @since 2015-07-07 09:52:28
	 */
	int insert(CourseChapter record);

	/**
	 * 插入 - 仅保存给定实体类中非null的字段
	 * @since 2015-07-07 09:52:28
	 */
	int insertSelective(CourseChapter record);

	/**
	 * 按主键查询
	 * @since 2015-07-07 09:52:28
	 */
	CourseChapter selectByPrimaryKey(String chapterId);

	/**
	 * 按主键更新 - 仅更新给定实体类中非null的字段
	 * @since 2015-07-07 09:52:28
	 */
	int updateByPrimaryKeySelective(CourseChapter record);

	/**
	
	 * @since 2015-07-07 09:52:28
	 */
	int updateByPrimaryKeyWithBLOBs(CourseChapter record);

	/**
	 * 按主键更新 - 全更新
	 * @since 2015-07-07 09:52:28
	 */
	int updateByPrimaryKey(CourseChapter record);

	List<CourseChapter> findByCourseId(String courseId);
	
	CourseClass findPlan(Map<String,String> condition);
	/*
 * 每章用的总时间
 */
	int findplantotalByCourseId(String courseId);
}