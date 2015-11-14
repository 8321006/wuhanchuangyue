package com.cy.mapper;

import com.cy.model.MyCourse;
import com.cy.model.UniversityCourse;

import java.util.List;
import java.util.Map;

/**
 * MyBatis Mapper 接口 - 表：t_my_course
 * @since 2015-07-02 14:06:35
 */
public interface MyCourseMapper {
	/**
	 * 按主键删除
	 * @since 2015-07-02 14:06:35
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 插入 - 全字段保存
	 * @since 2015-07-02 14:06:35
	 */
	int insert(MyCourse record);

	/**
	 * 插入 - 仅保存给定实体类中非null的字段
	 * @since 2015-07-02 14:06:35
	 */
	int insertSelective(MyCourse record);

	/**
	 * 按主键查询
	 * @since 2015-07-02 14:06:35
	 */
	MyCourse selectByPrimaryKey(Integer id);

	/**
	 * 按主键更新 - 仅更新给定实体类中非null的字段
	 * @since 2015-07-02 14:06:35
	 */
	int updateByPrimaryKeySelective(MyCourse record);
	
	/**
	 * 按条件更新 - 仅更新给定实体类中非null的字段
	 * @since 2015-07-02 14:06:35
	 */
	int updateByUserIdSelective(MyCourse record);

	/**
	 * 按主键更新 - 全更新
	 * @since 2015-07-02 14:06:35
	 */
	int updateByPrimaryKey(MyCourse record);

	/**
	 * 按 Criteria 条件查询, 支持分页
	 * @since 2015-07-02 14:06:35
	 */
	
	int insertMyCourseList(List<MyCourse> myCourceList);
	
	List<Map<String, Object>> countStudents();
	
	
	List<String> findcourseid(String userId);
//删除t_my_course
	void deleteclassId(UniversityCourse universityCourse);

}