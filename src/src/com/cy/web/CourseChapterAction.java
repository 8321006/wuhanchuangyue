package com.cy.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC Controler - 表：t_course_chapter
 * @since 2015-07-15 09:27:26
 */
@Controller
@RequestMapping(value = "/cy")
public class CourseChapterAction {
	private static final Logger logger = LoggerFactory.getLogger(CourseChapterAction.class);

	/*@Autowired
	private CourseChapterService courseChapterService;

	*//**
	 * 列表页面
	 *//*
	@RequestMapping(value = "/coursechapter", method=RequestMethod.GET)
	public Object listPage() {
		return "coursechapter";
	}

	*//**
	 * 列表数据
	 *//*
	@RequestMapping(value = "/coursechapter", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			Criteria<CourseChapter> criteria = Criteria.create(CourseChapter.class);
			criteria.buildFromRequest(request);
			criteria.sortIfEmpty(Sort.asc("chapterId"));
			Pager<CourseChapter> pager = courseChapterService.findPage(criteria);
			resultMap.put("result", 1);
			resultMap.put("total", pager.getTotalRecords());
			resultMap.put("rows", pager.getList());
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return resultMap;
	}

	*//**
	 * 新增页面
	 *//*
	@RequestMapping(value = "/coursechapteradd", method=RequestMethod.GET)
	public Object addPage() {
		return "coursechapter_add";
	}

	*//**
	 * 新增保存
	 *//*
	@RequestMapping(value = "/coursechapteradd", method=RequestMethod.POST)
	@ResponseBody
	public Object doAdd(@FormModel("courseChapter") CourseChapter courseChapter) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			ValidatorUtil.validate(courseChapter);
			courseChapterService.insert(courseChapter);
			resultMap.put("result", 1);
		} catch (ValidatorException ve) {
			resultMap.put("result", 0);
			resultMap.put("message", ve.getMessage());
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return resultMap;
	}

	*//**
	 * 修改页面
	 *//*
	@RequestMapping(value = "/coursechapteredit", method=RequestMethod.GET)
	public Object editPage(String chapterId) {
		ModelAndView model = new ModelAndView();
		try {
			CourseChapter courseChapter = courseChapterService.findByPrimaryKey(chapterId);
			model.addObject("courseChapter", courseChapter);
			model.setViewName("coursechapter_edit");
		} catch (Exception e) {
			model.addObject("exception", e.getMessage());
			model.setViewName("/error");
			logger.error(e.getMessage(), e);
		}
		return model;
	}

	*//**
	 * 修改保存
	 *//*
	@RequestMapping(value = "/coursechapteredit", method=RequestMethod.POST)
	@ResponseBody
	public Object doEdit(@FormModel("courseChapter") CourseChapter courseChapter) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			ValidatorUtil.validate(courseChapter);
			courseChapterService.update(courseChapter);
			resultMap.put("result", 1);
		} catch (ValidatorException ve) {
			resultMap.put("result", 0);
			resultMap.put("message", ve.getMessage());
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return resultMap;
	}

	*//**
	 * 删除
	 *//*
	@RequestMapping(value = "/coursechapterdelete", method=RequestMethod.POST)
	@ResponseBody
	public Object doDelete(@FormModel("ids") List<String> ids) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			courseChapterService.batchDelete(ids);
			resultMap.put("result", 1);
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return resultMap;
	}*/
}