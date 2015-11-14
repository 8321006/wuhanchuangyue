package com.cy.service;

import com.cy.exception.ServiceException;
import com.cy.mapper.NoticeMapper;
import com.cy.model.Message;
import com.cy.model.Notice;
import com.cy.model.NoticeWithBLOBs;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 业务实现层 - 表：t_notice
 * @since 2015-07-27 11:56:21
 */
@Service("noticeService")
public class NoticeService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Resource
	private NoticeMapper noticeMapper;

	//查询系统全部通知
	public List<Notice> findNoticeAll(String universityId) throws ServiceException 
	{
		try {
			return noticeMapper.findNoticeAll(universityId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	
	//查询学校通知
	public List<Notice> findNoticeCourse(String universityId,int noticeType) throws ServiceException 
	{
		try {
			Map<String, Object> notice = new HashMap<String, Object>();
			notice.put("universityId", universityId);
			notice.put("noticeType", noticeType);
			return noticeMapper.findNoticeCourse(notice);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	//查询系统的通知
	public List<Notice> findNoticeSystem() throws ServiceException 
	{
		try {
			return noticeMapper.findNoticeSystem();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	//查询通知详情
	public List<Notice> noticedetail(String noticeid) throws ServiceException 
	{
		try {
			return noticeMapper.noticedetail(noticeid);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public void insertNotice(NoticeWithBLOBs noticewithblobs) throws ServiceException
	{
		try {
			noticeMapper.insertNotice(noticewithblobs);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<Notice> findlikenotice(String noticetitle,String universityId) throws ServiceException 
	{
		try {
			return noticeMapper.findlikenotice(noticetitle,universityId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public void delNotice(String noticeid) throws ServiceException {
		try {
			noticeMapper.delNotice(noticeid);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	
	//平台管理员系统方法查询平台所有数据
	public List<Notice> findcyadminNoticeAll(String universityId,int noticeType) throws ServiceException 
	{
		try {
			Map<String, Object> notice = new HashMap<String, Object>();
			notice.put("universityId", universityId);
			notice.put("noticeType", noticeType);
			return noticeMapper.findcyadminNoticeAll(notice);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	//平台添加公告新闻的方法
	public void insertsysNotice(NoticeWithBLOBs noticewithblobs) throws ServiceException
	{
		try {
			noticeMapper.insertsysNotice(noticewithblobs);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	//平台管理员模糊查询
	public List<Notice> findlikesystemnotice(String noticetitle,String universityId) throws ServiceException 
	{
		try {
			return noticeMapper.findlikesystemnotice(noticetitle,universityId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	//编辑新闻通知
	public void editNotice(NoticeWithBLOBs  noticeWithBLOBs) throws ServiceException
	{
		try {
			noticeMapper.updateByPrimaryKeySelective(noticeWithBLOBs);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public	List<Notice> findtnoticeBynoticeId(String noticeid) throws ServiceException{
		try {
			return noticeMapper.noticedetail(noticeid);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
}