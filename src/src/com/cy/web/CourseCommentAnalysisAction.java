package com.cy.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cy.model.CourseCommentAnalysis;
import com.cy.service.CourseCommentAnalysisService;

/**
 * Spring MVC Controler - 表：t_course_comment_analysis
 * @since 2015-07-09 14:17:10
 */
@Controller
@RequestMapping(value = "/cy")
public class CourseCommentAnalysisAction {
	private static final Logger logger = LoggerFactory.getLogger(MyCourseScoreAction.class);

	@Autowired
	private CourseCommentAnalysisService courseCommentAnalysisService;

	/**
	 * 列表页面
	 */
	@RequestMapping(value = "/coursecommentanalysis", method=RequestMethod.GET)
	public Object listPage() {
		return "coursecommentanalysis";
	}

	/**
	 * 列表数据
	 */
	@RequestMapping(value = "/coursecommentanalysis", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView listData(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		/*try {
			Criteria<CourseCommentAnalysis> criteria = Criteria.create(CourseCommentAnalysis.class);
			criteria.buildFromRequest(request);
			criteria.sortIfEmpty(Sort.asc("id"));
			Pager<CourseCommentAnalysis> pager = courseCommentAnalysisService.findPage(criteria);
			resultMap.put("result", 1);
			resultMap.put("total", pager.getTotalRecords());
			resultMap.put("rows", pager.getList());
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}*/
		return mav;
	}

	/**
	 * 新增页面
	 */
	@RequestMapping(value = "/coursecommentanalysisadd", method=RequestMethod.GET)
	public Object addPage() {
		return "coursecommentanalysis_add";
	}

	/**
	 * 新增保存
	 */
	@RequestMapping(value = "/coursecommentanalysisadd", method=RequestMethod.POST)
	@ResponseBody
	public Object doAdd(CourseCommentAnalysis courseCommentAnalysis) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			ValidatorUtil.validate(courseCommentAnalysis);
			courseCommentAnalysisService.insert(courseCommentAnalysis);
			resultMap.put("result", 1);
		} catch (ValidatorException ve) {
			resultMap.put("result", 0);
			resultMap.put("message", ve.getMessage());
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}*/
		return resultMap;
	}

	/**
	 * 修改页面
	 */
	@RequestMapping(value = "/coursecommentanalysisedit", method=RequestMethod.GET)
	public ModelAndView editPage(Integer id) {
		ModelAndView model = new ModelAndView();
		/*try {
			CourseCommentAnalysis courseCommentAnalysis = courseCommentAnalysisService.findByPrimaryKey(id);
			model.addObject("courseCommentAnalysis", courseCommentAnalysis);
			model.setViewName("coursecommentanalysis_edit");
		} catch (Exception e) {
			model.addObject("exception", e.getMessage());
			model.setViewName("/error");
			logger.error(e.getMessage(), e);
		}*/
		return model;
	}

	/**
	 * 修改保存
	 */
	@RequestMapping(value = "/coursecommentanalysisedit", method=RequestMethod.POST)
	@ResponseBody
	public Object doEdit(CourseCommentAnalysis courseCommentAnalysis) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			ValidatorUtil.validate(courseCommentAnalysis);
			courseCommentAnalysisService.update(courseCommentAnalysis);
			resultMap.put("result", 1);
		} catch (ValidatorException ve) {
			resultMap.put("result", 0);
			resultMap.put("message", ve.getMessage());
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}*/
		return resultMap;
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/coursecommentanalysisdelete", method=RequestMethod.POST)
	@ResponseBody
	public Object doDelete(List<Integer> ids) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			courseCommentAnalysisService.batchDelete(ids);
			resultMap.put("result", 1);
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}*/
		return resultMap;
	}
}