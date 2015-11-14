package com.cy.mapper;

import java.util.List;

import com.cy.model.University;
import com.cy.model.UniversityCourse;

/**
 * MyBatis Mapper 接口 - 表：t_university
 * @since 2015-07-10 16:35:13
 */
public interface UniversityMapper {

	// 根据学校名称查询Id
	UniversityCourse findByUniversityName(String universityName);
	
	/**
	 * 按主键删除
	 * @since 2015-08-04 14:17:10
	 */
	int deleteByPrimaryKey(String universityId);

	/**
	 * 插入 - 全字段保存
	 * @since 2015-08-04 14:17:10
	 */
	int insert(University record);

	/**
	 * 插入 - 仅保存给定实体类中非null的字段
	 * @since 2015-08-04 14:17:10
	 */
	int insertSelective(University record);

	/**
	 * 按主键查询
	 * @since 2015-08-04 14:17:10
	 */
	University selectByPrimaryKey(String universityId);

	/**
	 * 按主键更新 - 仅更新给定实体类中非null的字段
	 * @since 2015-08-04 14:17:10
	 */
	int updateByPrimaryKeySelective(University record);

	/**
	 * 按主键更新 - 全更新
	 * @since 2015-08-04 14:17:10
	 */
	int updateByPrimaryKey(University record);

	List<University> selectPage(University example);
	List<University> selectAll();

	//统计总的记录数
	long selectCountByCriteria(University example);
}