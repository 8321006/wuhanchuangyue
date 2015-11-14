package com.cy.mapper;

import com.cy.model.Message;
import com.cy.model.Notice;
import com.cy.model.NoticeWithBLOBs;

import java.util.List;
import java.util.Map;

/**
 * MyBatis Mapper 接口 - 表：t_notice
 * @since 2015-07-27 11:56:21
 */
public interface NoticeMapper {
	/**
	 * 按主键删除
	 * @since 2015-07-27 11:56:21
	 */
	int deleteByPrimaryKey(Integer noticeId);

	/**
	 * 插入 - 全字段保存
	 * @since 2015-07-27 11:56:21
	 */
	int insert(NoticeWithBLOBs record);

	/**
	 * 插入 - 仅保存给定实体类中非null的字段
	 * @since 2015-07-27 11:56:21
	 */
	int insertSelective(NoticeWithBLOBs record);

	/**
	 * 按主键查询
	 * @since 2015-07-27 11:56:21
	 */
	NoticeWithBLOBs selectByPrimaryKey(Integer noticeId);

	/**
	 * 按主键更新 - 仅更新给定实体类中非null的字段
	 * @since 2015-07-27 11:56:21
	 */
	int updateByPrimaryKeySelective(NoticeWithBLOBs record);

	/**
	
	 * @since 2015-07-27 11:56:21
	 */
	int updateByPrimaryKeyWithBLOBs(NoticeWithBLOBs record);

	/**
	 * 按主键更新 - 全更新
	 * @since 2015-07-27 11:56:21
	 */
	int updateByPrimaryKey(Notice record);
	
	List<Notice> findNoticeAll(String universityId);  //查询全部通知
	List<Notice> findNoticeCourse(Map<String,Object>notice);  //查询学校通知
	List<Notice> findNoticeSystem();  //查询系统的通知
	List<Notice> noticedetail(String noticeid);

	int insertNotice(NoticeWithBLOBs noticewithblobs);
	
	List<Notice> findlikenotice(String noticetitle,String universityId);
	
	int delNotice(String noticeid);

	//查询平台的系统管理员的通知方法
	List<Notice> findcyadminNoticeAll(Map<String, Object> notice);
	
	//平台添加系统公告的方法
	int insertsysNotice(NoticeWithBLOBs noticewithblobs);

	List<Notice> findlikesystemnotice(String noticetitle,String universityId);
	
	
}