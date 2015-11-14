package com.cy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cy.model.CourseUniversityAnalysis;

/**
 * MyBatis Mapper 接口 - 表：t_course_university_analysis
 * @since 2015-08-19 16:56:37
 */
public interface CourseUniversityAnalysisMapper {
	/**
	 * 按主键删除
	 * @since 2015-08-19 16:56:37
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 插入 - 全字段保存
	 * @since 2015-08-19 16:56:37
	 */
	int insert(CourseUniversityAnalysis record);

	/**
	 * 插入 - 仅保存给定实体类中非null的字段
	 * @since 2015-08-19 16:56:37
	 */
	int insertSelective(CourseUniversityAnalysis record);

	/**
	 * 按主键查询
	 * @since 2015-08-19 16:56:37
	 */
	CourseUniversityAnalysis selectByPrimaryKey(Integer id);

	/**
	 * 按主键更新 - 仅更新给定实体类中非null的字段
	 * @since 2015-08-19 16:56:37
	 */
	int updateByPrimaryKeySelective(CourseUniversityAnalysis record);

	/**
	 * 按主键更新 - 全更新
	 * @since 2015-08-19 16:56:37
	 */
	int updateByPrimaryKey(CourseUniversityAnalysis record);

	List<CourseUniversityAnalysis> selectPage(CourseUniversityAnalysis example);

	//统计总的记录数
	long selectCountByCriteria(CourseUniversityAnalysis example);
	
	List<CourseUniversityAnalysis> getAllGroup(@Param("universityId") String universityId);
	
	List<CourseUniversityAnalysis> getAnalysisByTerm(@Param("universityId") String universityId,@Param("courseTerm") String courseTerm);
	List<CourseUniversityAnalysis> getAllByTerm(String courseTerm);
	
	CourseUniversityAnalysis getOneByClass(@Param("universityId") String universityId,@Param("classId") String classId,@Param("courseTerm") String courseTerm);
	
	List<String> getAllTerm(@Param("universityId") String universityId);
	List<String> getTerms();
	
	List<Map<String, Object>> getTotalStuByGroup(@Param("universityId") String universityId);
	
	List<String> getAllUniversity();
}