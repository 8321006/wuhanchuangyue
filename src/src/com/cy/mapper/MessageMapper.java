package com.cy.mapper;

import java.util.List;

import com.cy.model.Message;

/**
 * MyBatis Mapper 接口 - 表：t_message
 * @since 2015-06-08 16:37:51
 */
public interface MessageMapper {
	/**
	 * 按主键删除
	 * @since 2015-06-08 16:37:51
	 */
	int deleteByPrimaryKey(String messageId);

	/**
	 * 插入 - 全字段保存
	 * @since 2015-06-08 16:37:51
	 */
	int insert(Message record);
	
	int insertsend(Message record);

	/**
	 * 插入 - 仅保存给定实体类中非null的字段
	 * @since 2015-06-08 16:37:51
	 */
	int insertSelective(Message record);

	/**
	 * 按主键查询
	 * @since 2015-06-08 16:37:51
	 */
	Message selectByPrimaryKey(Integer messageId);

	/**
	 * 按主键更新 - 仅更新给定实体类中非null的字段
	 * @since 2015-06-08 16:37:51
	 */
	int updateByPrimaryKeySelective(Message record);

	/**
	 * 按主键更新 - 全更新
	 * @since 2015-06-08 16:37:51
	 */
	int updateByPrimaryKey(Message record);
	
	List<Message> getuserMessage(Message Message2);
	
	List<Message> getadminMessage(String  userId);
	
	List<Message> getMessageListId(String messageId);
	
    //查询评论消息
	List<Message> getplList();
	
	//查询个人私信详情
	List<Message> messpridetail(String messuserid);
	
	//查询
	List<Message> findmessprivesend(Message message2);
	
	List<Message> getnorederCount(Message message2);
	
	int updateReader(String  userId);
}