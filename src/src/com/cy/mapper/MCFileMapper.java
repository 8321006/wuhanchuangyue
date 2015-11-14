package com.cy.mapper;

import java.util.List;

import com.cy.model.MCFile;

/**
 * MyBatis Mapper 接口 - 表：t_file
 * @since 2015-06-09 14:11:06
 */
public interface MCFileMapper {
	/**
	 * 按主键删除
	 * @since 2015-06-09 14:11:06
	 */
	int deleteByPrimaryKey(String fileId);

	/**
	 * 插入 - 全字段保存
	 * @since 2015-06-09 14:11:06
	 */
	int insert(MCFile record);

	/**
	 * 插入 - 仅保存给定实体类中非null的字段
	 * @since 2015-06-09 14:11:06
	 */
	int insertSelective(MCFile record);

	/**
	 * 按主键查询
	 * @since 2015-06-09 14:11:06
	 */
	MCFile selectByPrimaryKey(String fileId);

	/**
	 * 按主键更新 - 仅更新给定实体类中非null的字段
	 * @since 2015-06-09 14:11:06
	 */
	int updateByPrimaryKeySelective(MCFile record);

	/**
	 * 按主键更新 - 全更新
	 * @since 2015-06-09 14:11:06
	 */
	int updateByPrimaryKey(MCFile record);
	
	List<MCFile> listAll();
}