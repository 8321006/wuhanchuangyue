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

import com.cy.model.MyCourseScore;
import com.cy.service.MyCourseScoreService;

/**
 * Spring MVC Controler - 表：t_my_course_score
 * @since 2015-07-08 14:27:56
 */
@Controller
@RequestMapping(value = "/cy")
public class MyCourseScoreAction {
	private static final Logger logger = LoggerFactory.getLogger(MyCourseScoreAction.class);

	@Autowired
	private MyCourseScoreService myCourseScoreService;

	/**
	 * 列表页面
	 */
	@RequestMapping(value = "/mycoursescore", method=RequestMethod.GET)
	public Object listPage() {
		return "mycoursescore";
	}

	/**
	 * 列表数据
	 */
	@RequestMapping(value = "/mycoursescore", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			Criteria<MyCourseScore> criteria = Criteria.create(MyCourseScore.class);
			criteria.buildFromRequest(request);
			criteria.sortIfEmpty(Sort.asc("id"));
			Pager<MyCourseScore> pager = myCourseScoreService.findPage(criteria);
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
	@RequestMapping(value = "/mycoursescoreadd", method=RequestMethod.GET)
	public Object addPage() {
		return "mycoursescore_add";
	}

	/**
	 * 新增保存
	 */
	@RequestMapping(value = "/mycoursescoreadd", method=RequestMethod.POST)
	@ResponseBody
	public Object doAdd(MyCourseScore myCourseScore) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			ValidatorUtil.validate(myCourseScore);
			myCourseScoreService.insert(myCourseScore);
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
	@RequestMapping(value = "/mycoursescoreedit", method=RequestMethod.GET)
	public Object editPage(String id) {
		ModelAndView model = new ModelAndView();
		/*try {
			MyCourseScore myCourseScore = myCourseScoreService.findByPrimaryKey(id);
			model.addObject("myCourseScore", myCourseScore);
			model.setViewName("mycoursescore_edit");
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
	@RequestMapping(value = "/mycoursescoreedit", method=RequestMethod.POST)
	@ResponseBody
	public Object doEdit(MyCourseScore myCourseScore) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			ValidatorUtil.validate(myCourseScore);
			myCourseScoreService.update(myCourseScore);
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
	@RequestMapping(value = "/mycoursescoredelete", method=RequestMethod.POST)
	@ResponseBody
	public Object doDelete(List<String> ids) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			myCourseScoreService.batchDelete(ids);
			resultMap.put("result", 1);
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}*/
		return resultMap;
	}
}