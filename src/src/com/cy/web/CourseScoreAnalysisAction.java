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

import com.cy.model.CourseScoreAnalysis;
import com.cy.service.CourseScoreAnalysisService;

/**
 * Spring MVC Controler - 表：t_course_score_analysis
 * @since 2015-07-09 14:17:10
 */
@Controller
@RequestMapping(value = "/cy")
public class CourseScoreAnalysisAction {
	private static final Logger logger = LoggerFactory.getLogger(CourseScoreAnalysisAction.class);

	@Autowired
	private CourseScoreAnalysisService courseScoreAnalysisService;

	/**
	 * 列表页面
	 */
	@RequestMapping(value = "/coursescoreanalysis", method=RequestMethod.GET)
	public Object listPage() {
		return "coursescoreanalysis";
	}

	/**
	 * 列表数据
	 */
	@RequestMapping(value = "/coursescoreanalysis", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			Criteria<CourseScoreAnalysis> criteria = Criteria.create(CourseScoreAnalysis.class);
			criteria.buildFromRequest(request);
			criteria.sortIfEmpty(Sort.asc("id"));
			Pager<CourseScoreAnalysis> pager = courseScoreAnalysisService.findPage(criteria);
			resultMap.put("result", 1);
			resultMap.put("total", pager.getTotalRecords());
			resultMap.put("rows", pager.getList());
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}*/
		return resultMap;
	}

	/**
	 * 新增页面
	 */
	@RequestMapping(value = "/coursescoreanalysisadd", method=RequestMethod.GET)
	public Object addPage() {
		return "coursescoreanalysis_add";
	}

	/**
	 * 新增保存
	 */
	@RequestMapping(value = "/coursescoreanalysisadd", method=RequestMethod.POST)
	@ResponseBody
	public Object doAdd(CourseScoreAnalysis courseScoreAnalysis) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			ValidatorUtil.validate(courseScoreAnalysis);
			courseScoreAnalysisService.insert(courseScoreAnalysis);
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
	@RequestMapping(value = "/coursescoreanalysisedit", method=RequestMethod.GET)
	public Object editPage(Integer id) {
		ModelAndView model = new ModelAndView();
		/*try {
			CourseScoreAnalysis courseScoreAnalysis = courseScoreAnalysisService.findByPrimaryKey(id);
			model.addObject("courseScoreAnalysis", courseScoreAnalysis);
			model.setViewName("coursescoreanalysis_edit");
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
	@RequestMapping(value = "/coursescoreanalysisedit", method=RequestMethod.POST)
	@ResponseBody
	public Object doEdit(CourseScoreAnalysis courseScoreAnalysis) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			ValidatorUtil.validate(courseScoreAnalysis);
			courseScoreAnalysisService.update(courseScoreAnalysis);
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
	@RequestMapping(value = "/coursescoreanalysisdelete", method=RequestMethod.POST)
	@ResponseBody
	public Object doDelete(List<Integer> ids) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			courseScoreAnalysisService.batchDelete(ids);
			resultMap.put("result", 1);
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}*/
		return resultMap;
	}
}