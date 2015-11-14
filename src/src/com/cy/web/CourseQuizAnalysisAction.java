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

import com.cy.model.CourseQuizAnalysis;
import com.cy.service.CourseQuizAnalysisService;

/**
 * Spring MVC Controler - 表：t_course_quiz_analysis
 * @since 2015-07-09 14:17:10
 */
@Controller
@RequestMapping(value = "/cy")
public class CourseQuizAnalysisAction {
	private static final Logger logger = LoggerFactory.getLogger(CourseQuizAnalysisAction.class);

	@Autowired
	private CourseQuizAnalysisService courseQuizAnalysisService;

	/**
	 * 列表页面
	 */
	@RequestMapping(value = "/coursequizanalysis", method=RequestMethod.GET)
	public Object listPage() {
		return "coursequizanalysis";
	}

	/**
	 * 列表数据
	 */
	@RequestMapping(value = "/coursequizanalysis", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			Criteria<CourseQuizAnalysis> criteria = Criteria.create(CourseQuizAnalysis.class);
			criteria.buildFromRequest(request);
			criteria.sortIfEmpty(Sort.asc("id"));
			Pager<CourseQuizAnalysis> pager = courseQuizAnalysisService.findPage(criteria);
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
	@RequestMapping(value = "/coursequizanalysisadd", method=RequestMethod.GET)
	public Object addPage() {
		return "coursequizanalysis_add";
	}

	/**
	 * 新增保存
	 */
	@RequestMapping(value = "/coursequizanalysisadd", method=RequestMethod.POST)
	@ResponseBody
	public Object doAdd(CourseQuizAnalysis courseQuizAnalysis) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			ValidatorUtil.validate(courseQuizAnalysis);
			courseQuizAnalysisService.insert(courseQuizAnalysis);
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
	@RequestMapping(value = "/coursequizanalysisedit", method=RequestMethod.GET)
	public Object editPage(Integer id) {
		ModelAndView model = new ModelAndView();
		/*try {
			CourseQuizAnalysis courseQuizAnalysis = courseQuizAnalysisService.findByPrimaryKey(id);
			model.addObject("courseQuizAnalysis", courseQuizAnalysis);
			model.setViewName("coursequizanalysis_edit");
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
	@RequestMapping(value = "/coursequizanalysisedit", method=RequestMethod.POST)
	@ResponseBody
	public Object doEdit(CourseQuizAnalysis courseQuizAnalysis) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			ValidatorUtil.validate(courseQuizAnalysis);
			courseQuizAnalysisService.update(courseQuizAnalysis);
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
	@RequestMapping(value = "/coursequizanalysisdelete", method=RequestMethod.POST)
	@ResponseBody
	public Object doDelete(List<Integer> ids) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			courseQuizAnalysisService.batchDelete(ids);
			resultMap.put("result", 1);
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}*/
		return resultMap;
	}
}