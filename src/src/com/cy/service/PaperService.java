package com.cy.service;
import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cy.common.util.Page;
import com.cy.exception.ServiceException;
import com.cy.mapper.PaperMapper;
import com.cy.model.Paper;
import com.cy.model.Question;

@Service("examService")
public class PaperService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Resource
	private PaperMapper examMapper;

	public Paper findByPrimaryKey(String paperId) throws ServiceException {
		try {
			return examMapper.selectByPrimaryKey(paperId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<Paper> getPaperList(Page<Paper> page) throws ServiceException {
		try {
			return examMapper.getPaperList(page);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
		public void addQuestion(Question question) {
			// TODO Auto-generated method stub
			try {
//				questionMapper.insertQuestion(question);

			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}

	/**
	 * 
	* @Title: getPaperListByCourseId 
	* @Description: 查询单个课程的试卷列表 
	* @param courseId
	* @return
	 */
	public List<Paper> getPaperListByCourseId(String courseId) throws ServiceException{
		try {
			return examMapper.getPaperListByCourseId(courseId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
//视频弹框，随机选择试卷
	public Paper findpaperBycourseId(String courseId) throws ServiceException {
		try {
			return examMapper.findpaperBycourseId(courseId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
}