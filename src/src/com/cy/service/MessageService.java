package com.cy.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cy.exception.ServiceException;
import com.cy.mapper.MessageMapper;
import com.cy.model.Message;

/**
 * 业务实现层 - 表：t_message
 * @since 2015-06-08 16:37:51
 */
@Service("messageService")
public class MessageService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Resource
	private MessageMapper messageMapper;

	public void insert(Message entity) throws ServiceException {
		try {
			messageMapper.insert(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public void insertsend(Message entity) throws ServiceException {
		try {
			messageMapper.insertsend(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void delete(Message entity) throws ServiceException {
		try {
			messageMapper.deleteByPrimaryKey(entity.getMessageId());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void batchDelete(List<Integer> ids) throws ServiceException {
		try {
			for (Integer id : ids) {
		//		messageMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void update(Message entity) throws ServiceException {
		try {
			messageMapper.updateByPrimaryKey(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void updateSelective(Message entity) throws ServiceException {
		try {
			messageMapper.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public Message findByPrimaryKey(Integer messageId) throws ServiceException {
		try {
			return messageMapper.selectByPrimaryKey(messageId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	
	//查询方法，查询私信
	public List<Message> findMessageList(Message Message2) throws ServiceException 
	{
		try {
			return messageMapper.getuserMessage(Message2);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	//查询方法，查询评论消息
	public List<Message> getplList() throws ServiceException 
	{
		try {
			return messageMapper.getplList();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	//查询方法，查询系统消息
	public List<Message> getadminMessage(String userId) throws ServiceException 
	{
		try {
			return messageMapper.getadminMessage(userId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	
	
	public List<Message> getMessageListId(String messageId) throws ServiceException {
		try {
			return messageMapper.getMessageListId(messageId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	//查询个人私信的详情列表
	public List<Message> messdetail(String messuserid) throws ServiceException {
		try {
			return messageMapper.messpridetail(messuserid);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	
	//查询方法,查询个人私信列表
	public List<Message> findmessprivesend(Message Message2) throws ServiceException 
	{
		try {
			return messageMapper.findmessprivesend(Message2);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	//查询方法，查询出没有读取的消息有多少条
	public List<Message> getnorederCount(Message Message2) throws ServiceException 
	{
		try {
			return messageMapper.getnorederCount(Message2);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	//修改的方法，修改已读标志
	public void updateReader(String userId) throws ServiceException 
	{
		try {
			 messageMapper.updateReader(userId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	
}