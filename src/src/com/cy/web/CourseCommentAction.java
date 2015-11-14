package com.cy.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cy.common.util.MD5;
import com.cy.common.util.UID;
import com.cy.exception.ServiceException;
import com.cy.model.Course;
import com.cy.model.CourseChapter;
import com.cy.model.CourseComment;
import com.cy.model.Message;
import com.cy.service.CourseChapterService;
import com.cy.service.CourseCommentService;
import com.cy.service.CourseService;

/**
 * Spring MVC Controler - 表：t_course_comment
 * @since 2015-07-08 14:27:56
 */
@Controller
@RequestMapping(value = "/cy")
public class CourseCommentAction {
	private static final Logger logger = LoggerFactory.getLogger(CourseCommentAction.class);

	@Autowired
	private CourseCommentService courseCommentService;

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseChapterService courseChapterService;
//	/**
//	 * 评论
//	 * @throws ServiceException 
//	 */
//	@RequestMapping(value = "/comment")
//	public Object pinglun(CourseComment courseComment,HttpServletResponse response,
//			HttpServletRequest request,ModelMap model,String courseId) throws ServiceException {
//		List<CourseComment> listcomment=courseCommentService.getplList(courseId);
//		model.put("list", listcomment);
//		return "coursedetail";
//	}
	

	 
	//评论查询
	/*@RequestMapping(value="/commentlist")
    public String pinglunlist(HttpServletResponse response,CourseComment courseId,
			HttpServletRequest request, String type,ModelMap model) throws ServiceException
    {
		List<CourseComment> listcomment=courseCommentService.findMessageList(courseId);
		model.put("list", listcomment);
    	return "commentList";
    }*/
	/**
	 * 列表页面
	 */
	@RequestMapping(value = "/coursecomment", method=RequestMethod.GET)
	public Object listPage() {
		return "coursecomment";
	}

	/**
	 * 列表数据
	 */
	@RequestMapping(value = "/coursecomment", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			//Criteria<CourseComment> criteria = Criteria.create(CourseComment.class);
			//criteria.buildFromRequest(request);
			//criteria.sortIfEmpty(Sort.asc("commentId"));
			//Pager<CourseComment> pager = courseCommentService.findPage(criteria);
			resultMap.put("result", 1);
			//resultMap.put("total", pager.getTotalRecords());
			//resultMap.put("rows", pager.getList());
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return resultMap;
	}

	/**
	 * 新增页面
	 */
	@RequestMapping(value = "/coursecommentadd", method=RequestMethod.GET)
	public Object addPage() {
		return "coursecomment_add";
	}

	/**
	 * 新增保存
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/coursecommentadd", method=RequestMethod.POST)
	@ResponseBody
	public Object doAdd(CourseComment courseComment) throws ServiceException {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			//ValidatorUtil.validate(courseComment);
			courseCommentService.insert(courseComment);
			resultMap.put("result", 1);
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return resultMap;
	}

	/**
	 * 修改页面
	 */
	@RequestMapping(value = "/coursecommentedit", method=RequestMethod.GET)
	public Object editPage(String commentId) {
		ModelAndView model = new ModelAndView();
		try {
			//CourseComment courseComment = courseCommentService.findByPrimaryKey(commentId);
			//model.addObject("courseComment", courseComment);
			model.setViewName("coursecomment_edit");
		} catch (Exception e) {
			model.addObject("exception", e.getMessage());
			model.setViewName("/error");
			logger.error(e.getMessage(), e);
		}
		return model;
	}

	/**
	 * 修改保存
	 */
	@RequestMapping(value = "/coursecommentedit", method=RequestMethod.POST)
	@ResponseBody
	public Object doEdit(CourseComment courseComment) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			//ValidatorUtil.validate(courseComment);
			//courseCommentService.update(courseComment);
			resultMap.put("result", 1);
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return resultMap;
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/coursecommentdelete", method=RequestMethod.POST)
	@ResponseBody
	public Object doDelete(List<String> ids) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			//courseCommentService.batchDelete(ids);
			resultMap.put("result", 1);
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return resultMap;
	}
}