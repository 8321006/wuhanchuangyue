package com.cy.mapper;

import java.util.List;
import java.util.Map;

import com.cy.model.UserLogger;

/**
 * MyBatis Mapper 接口 - 表：t_user_logger
 * @since 2015-08-28 14:42:22
 */
public interface UserLoggerMapper {
	/**
	 * 按主键删除
	 * @since 2015-08-28 14:42:22
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 插入 - 全字段保存
	 * @since 2015-08-28 14:42:22
	 */
	int insert(UserLogger record);

	/**
	 * 插入 - 仅保存给定实体类中非null的字段
	 * @since 2015-08-28 14:42:22
	 */
	int insertSelective(UserLogger record);

	/**
	 * 按主键查询
	 * @since 2015-08-28 14:42:22
	 */
	UserLogger selectByPrimaryKey(Integer id);

	/**
	 * 按主键更新 - 仅更新给定实体类中非null的字段
	 * @since 2015-08-28 14:42:22
	 */
	int updateByPrimaryKeySelective(UserLogger record);

	/**
	 * 按主键更新 - 全更新
	 * @since 2015-08-28 14:42:22
	 */
	int updateByPrimaryKey(UserLogger record);

	List<UserLogger> selectPage(UserLogger example);

	//统计总的记录数
	long selectCountByCriteria(UserLogger example);
	
	long countUserOnline();
	List<Map<String, Object>> getLogCurrMonth(String universityId);
	List<Map<String, Object>> getAllLogCurrMonth();
	
	
}