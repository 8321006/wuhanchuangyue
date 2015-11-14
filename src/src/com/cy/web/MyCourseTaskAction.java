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

import com.cy.model.MyCourseTask;
import com.cy.service.MyCourseTaskService;

/**
 * Spring MVC Controler - 表：t_my_course_task
 * @since 2015-07-08 14:27:56
 */
@Controller
@RequestMapping(value = "/cy")
public class MyCourseTaskAction {
	private static final Logger logger = LoggerFactory.getLogger(MyCourseTaskAction.class);

	@Autowired
	private MyCourseTaskService myCourseTaskService;

	/**
	 * 列表页面
	 */
	@RequestMapping(value = "/mycoursetask", method=RequestMethod.GET)
	public Object listPage() {
		return "mycoursetask";
	}

	/**
	 * 列表数据
	 */
	@RequestMapping(value = "/mycoursetask", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			Criteria<MyCourseTask> criteria = Criteria.create(MyCourseTask.class);
			criteria.buildFromRequest(request);
			criteria.sortIfEmpty(Sort.asc("id"));
			Pager<MyCourseTask> pager = myCourseTaskService.findPage(criteria);
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
	@RequestMapping(value = "/mycoursetaskadd", method=RequestMethod.GET)
	public Object addPage() {
		return "mycoursetask_add";
	}

	/**
	 * 新增保存
	 */
	@RequestMapping(value = "/mycoursetaskadd", method=RequestMethod.POST)
	@ResponseBody
	public Object doAdd(MyCourseTask myCourseTask) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			ValidatorUtil.validate(myCourseTask);
			myCourseTaskService.insert(myCourseTask);
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
	@RequestMapping(value = "/mycoursetaskedit", method=RequestMethod.GET)
	public Object editPage(Integer id) {
		ModelAndView model = new ModelAndView();
		/*try {
			MyCourseTask myCourseTask = myCourseTaskService.findByPrimaryKey(id);
			model.addObject("myCourseTask", myCourseTask);
			model.setViewName("mycoursetask_edit");
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
	@RequestMapping(value = "/mycoursetaskedit", method=RequestMethod.POST)
	@ResponseBody
	public Object doEdit(MyCourseTask myCourseTask) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			ValidatorUtil.validate(myCourseTask);
			myCourseTaskService.update(myCourseTask);
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
	@RequestMapping(value = "/mycoursetaskdelete", method=RequestMethod.POST)
	@ResponseBody
	public Object doDelete(List<Integer> ids) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			myCourseTaskService.batchDelete(ids);
			resultMap.put("result", 1);
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}*/
		return resultMap;
	}
}