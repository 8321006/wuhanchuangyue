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

import com.cy.model.CourseExam;
import com.cy.service.CourseExamService;

/**
 * Spring MVC Controler - 表：t_course_exam
 * @since 2015-07-08 14:27:56
 */
@Controller
@RequestMapping(value = "/cy")
public class CourseExamAction {
	private static final Logger logger = LoggerFactory.getLogger(CourseExamAction.class);

	@Autowired
	private CourseExamService courseExamService;

	/**
	 * 列表页面
	 */
	@RequestMapping(value = "/courseexam", method=RequestMethod.GET)
	public Object listPage() {
		return "courseexam";
	}

	/**
	 * 列表数据
	 */
	@RequestMapping(value = "/courseexam", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			//Criteria<CourseExam> criteria = Criteria.create(CourseExam.class);
			//criteria.buildFromRequest(request);
			//criteria.sortIfEmpty(Sort.asc("examId"));
			//Pager<CourseExam> pager = courseExamService.findPage(criteria);
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
	@RequestMapping(value = "/courseexamadd", method=RequestMethod.GET)
	public Object addPage() {
		return "courseexam_add";
	}

	/**
	 * 新增保存
	 */
	@RequestMapping(value = "/courseexamadd", method=RequestMethod.POST)
	@ResponseBody
	public Object doAdd(CourseExam courseExam) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			ValidatorUtil.validate(courseExam);
			courseExamService.insert(courseExam);
			resultMap.put("result", 1);
		} catch (ValidatorException ve) {
			resultMap.put("result", 0);
			resultMap.put("message", ve.getMessage());
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		*/return resultMap;
	}

	/**
	 * 修改页面
	 */
	@RequestMapping(value = "/courseexamedit", method=RequestMethod.GET)
	public Object editPage(String examId) {
		ModelAndView model = new ModelAndView();
		/*try {
			CourseExam courseExam = courseExamService.findByPrimaryKey(examId);
			model.addObject("courseExam", courseExam);
			model.setViewName("courseexam_edit");
		} catch (Exception e) {
			model.addObject("exception", e.getMessage());
			model.setViewName("/error");
			logger.error(e.getMessage(), e);
		}
		*/return model;
	}

	/**
	 * 修改保存
	 */
	@RequestMapping(value = "/courseexamedit", method=RequestMethod.POST)
	@ResponseBody
	public Object doEdit(CourseExam courseExam) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			ValidatorUtil.validate(courseExam);
			courseExamService.update(courseExam);
			resultMap.put("result", 1);
		} catch (ValidatorException ve) {
			resultMap.put("result", 0);
			resultMap.put("message", ve.getMessage());
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		*/return resultMap;
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/courseexamdelete", method=RequestMethod.POST)
	@ResponseBody
	public Object doDelete(List<String> ids) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			courseExamService.batchDelete(ids);
			resultMap.put("result", 1);
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		*/return resultMap;
	}
}