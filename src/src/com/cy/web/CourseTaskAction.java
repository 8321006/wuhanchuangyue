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

import com.cy.model.CourseTask;
import com.cy.service.CourseTaskService;

/**
 * Spring MVC Controler - 表：t_course_task
 * @since 2015-07-08 14:27:56
 */
@Controller
@RequestMapping(value = "/cy")
public class CourseTaskAction {
	private static final Logger logger = LoggerFactory.getLogger(CourseTaskAction.class);

	@Autowired
	private CourseTaskService courseTaskService;

	/**
	 * 列表页面
	 */
	@RequestMapping(value = "/coursetask", method=RequestMethod.GET)
	public Object listPage() {
		return "coursetask";
	}

	/**
	 * 列表数据
	 */
	@RequestMapping(value = "/coursetask", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			Criteria<CourseTask> criteria = Criteria.create(CourseTask.class);
			criteria.buildFromRequest(request);
			criteria.sortIfEmpty(Sort.asc("taskId"));
			Pager<CourseTask> pager = courseTaskService.findPage(criteria);
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
	@RequestMapping(value = "/coursetaskadd", method=RequestMethod.GET)
	public Object addPage() {
		return "coursetask_add";
	}

	/**
	 * 新增保存
	 */
	@RequestMapping(value = "/coursetaskadd", method=RequestMethod.POST)
	@ResponseBody
	public Object doAdd(CourseTask courseTask) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			ValidatorUtil.validate(courseTask);
			courseTaskService.insert(courseTask);
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
	@RequestMapping(value = "/coursetaskedit", method=RequestMethod.GET)
	public Object editPage(Integer taskId) {
		ModelAndView model = new ModelAndView();
		/*try {
			CourseTask courseTask = courseTaskService.findByPrimaryKey(taskId);
			model.addObject("courseTask", courseTask);
			model.setViewName("coursetask_edit");
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
	@RequestMapping(value = "/coursetaskedit", method=RequestMethod.POST)
	@ResponseBody
	public Object doEdit(CourseTask courseTask) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			ValidatorUtil.validate(courseTask);
			courseTaskService.update(courseTask);
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
	@RequestMapping(value = "/coursetaskdelete", method=RequestMethod.POST)
	@ResponseBody
	public Object doDelete(List<Integer> ids) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			courseTaskService.batchDelete(ids);
			resultMap.put("result", 1);
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}*/
		return resultMap;
	}
}