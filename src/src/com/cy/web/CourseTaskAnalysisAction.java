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

import com.cy.model.CourseTaskAnalysis;
import com.cy.service.CourseTaskAnalysisService;

/**
 * Spring MVC Controler - 表：t_course_task_analysis
 * @since 2015-07-09 14:17:10
 */
@Controller
@RequestMapping(value = "/cy")
public class CourseTaskAnalysisAction {
	private static final Logger logger = LoggerFactory.getLogger(CourseTaskAnalysisAction.class);

	@Autowired
	private CourseTaskAnalysisService courseTaskAnalysisService;

	/**
	 * 列表页面
	 */
	@RequestMapping(value = "/coursetaskanalysis", method=RequestMethod.GET)
	public Object listPage() {
		return "coursetaskanalysis";
	}

	/**
	 * 列表数据
	 */
	@RequestMapping(value = "/coursetaskanalysis", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			Criteria<CourseTaskAnalysis> criteria = Criteria.create(CourseTaskAnalysis.class);
			criteria.buildFromRequest(request);
			criteria.sortIfEmpty(Sort.asc("id"));
			Pager<CourseTaskAnalysis> pager = courseTaskAnalysisService.findPage(criteria);
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
	@RequestMapping(value = "/coursetaskanalysisadd", method=RequestMethod.GET)
	public Object addPage() {
		return "coursetaskanalysis_add";
	}

	/**
	 * 新增保存
	 */
	@RequestMapping(value = "/coursetaskanalysisadd", method=RequestMethod.POST)
	@ResponseBody
	public Object doAdd(CourseTaskAnalysis courseTaskAnalysis) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			ValidatorUtil.validate(courseTaskAnalysis);
			courseTaskAnalysisService.insert(courseTaskAnalysis);
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
	@RequestMapping(value = "/coursetaskanalysisedit", method=RequestMethod.GET)
	public Object editPage(Integer id) {
		ModelAndView model = new ModelAndView();
		/*try {
			CourseTaskAnalysis courseTaskAnalysis = courseTaskAnalysisService.findByPrimaryKey(id);
			model.addObject("courseTaskAnalysis", courseTaskAnalysis);
			model.setViewName("coursetaskanalysis_edit");
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
	@RequestMapping(value = "/coursetaskanalysisedit", method=RequestMethod.POST)
	@ResponseBody
	public Object doEdit(CourseTaskAnalysis courseTaskAnalysis) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			ValidatorUtil.validate(courseTaskAnalysis);
			courseTaskAnalysisService.update(courseTaskAnalysis);
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
	@RequestMapping(value = "/coursetaskanalysisdelete", method=RequestMethod.POST)
	@ResponseBody
	public Object doDelete(List<Integer> ids) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			courseTaskAnalysisService.batchDelete(ids);
			resultMap.put("result", 1);
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}*/
		return resultMap;
	}
}