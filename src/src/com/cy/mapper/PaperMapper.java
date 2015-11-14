package com.cy.mapper;

import java.util.Date;
import java.util.List;

import com.cy.common.util.Page;
import com.cy.model.Course;
import com.cy.model.Paper;

/**
 * MyBatis Mapper 接口 - 表：t_paper
 * @since 2015-06-08 15:47:16
 */
public interface PaperMapper {

	/**
	 * 按主键查询
	 * @since 2015-06-08 15:47:16
	 */
	Paper selectByPrimaryKey(String paperId);

	/**
	 * 
	* @Title: getPaperList 
	* @Description: 按条件分页查询试卷列表
	* @param page
	* @return
	 */
	List<Paper> getPaperList(Page<Paper> page);
	List<Paper> examlist(Page<Paper> page);
	
	
	
	List<Paper> findpaper(Date datetime);

	public	void insertPaper(Paper paper)throws Exception;
	public	void insertPapercourse(Paper paper)throws Exception;

	/**
	 * 
	* @Title: getPaperListByCourseId 
	* @Description: 查询单个课程的试卷列表
	* @param courseId
	* @return
	 */
	List<Paper> getPaperListByCourseId(String courseId);
   //插入该调查问卷
	public void addPapersurvey(Paper paper);
   //视频弹框，随机选择试卷
	Paper findpaperBycourseId(String courseId);
	

}