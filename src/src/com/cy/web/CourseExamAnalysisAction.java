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

import com.cy.model.CourseExamAnalysis;
import com.cy.service.CourseExamAnalysisService;

/**
 * Spring MVC Controler - 表：t_course_exam_analysis
 * @since 2015-07-09 14:17:10
 */
@Controller
@RequestMapping(value = "/cy")
public class CourseExamAnalysisAction {
	private static final Logger logger = LoggerFactory.getLogger(CourseExamAnalysisAction.class);

	@Autowired
	private CourseExamAnalysisService courseExamAnalysisService;

	/**
	 * 列表页面
	 */
	@RequestMapping(value = "/courseexamanalysis", method=RequestMethod.GET)
	public Object listPage() {
		return "courseexamanalysis";
	}

	/**
	 * 列表数据
	 */
	@RequestMapping(value = "/courseexamanalysis", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			Criteria<CourseExamAnalysis> criteria = Criteria.create(CourseExamAnalysis.class);
			criteria.buildFromRequest(request);
			criteria.sortIfEmpty(Sort.asc("id"));
			Pager<CourseExamAnalysis> pager = courseExamAnalysisService.findPage(criteria);
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
	@RequestMapping(value = "/courseexamanalysisadd", method=RequestMethod.GET)
	public Object addPage() {
		return "courseexamanalysis_add";
	}

	/**
	 * 新增保存
	 */
	@RequestMapping(value = "/courseexamanalysisadd", method=RequestMethod.POST)
	@ResponseBody
	public Object doAdd(CourseExamAnalysis courseExamAnalysis) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			ValidatorUtil.validate(courseExamAnalysis);
			courseExamAnalysisService.insert(courseExamAnalysis);
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
	@RequestMapping(value = "/courseexamanalysisedit", method=RequestMethod.GET)
	public Object editPage(Integer id) {
		ModelAndView model = new ModelAndView();
		/*try {
			CourseExamAnalysis courseExamAnalysis = courseExamAnalysisService.findByPrimaryKey(id);
			model.addObject("courseExamAnalysis", courseExamAnalysis);
			model.setViewName("courseexamanalysis_edit");
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
	@RequestMapping(value = "/courseexamanalysisedit", method=RequestMethod.POST)
	@ResponseBody
	public Object doEdit(CourseExamAnalysis courseExamAnalysis) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			ValidatorUtil.validate(courseExamAnalysis);
			courseExamAnalysisService.update(courseExamAnalysis);
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
	@RequestMapping(value = "/courseexamanalysisdelete", method=RequestMethod.POST)
	@ResponseBody
	public Object doDelete(List<Integer> ids) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			courseExamAnalysisService.batchDelete(ids);
			resultMap.put("result", 1);
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}*/
		return resultMap;
	}
}