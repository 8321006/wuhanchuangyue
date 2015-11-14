package com.cy.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.common.util.MD5;
import com.cy.common.util.UID;
import com.cy.exception.ServiceException;
import com.cy.model.University;
import com.cy.service.CourseTeacherAnalysisService;
import com.cy.service.UniversityService;

/**
 * Spring MVC Controler - 表：t_university
 * @since 2015-08-04 14:17:10
 */
@Controller
@RequestMapping(value = "/university")
public class UniversityAction {
	private static final Logger logger = LoggerFactory.getLogger(UniversityAction.class);

	@Autowired
	private UniversityService universityService;
	@Autowired
	private CourseTeacherAnalysisService courseTeacherAnalysisService;

	
	@RequestMapping(value = "/listAll")
	public String dolistAll(HttpSession session) throws ServiceException {
		List<University> universityList = null;
		try {
			universityList = universityService.selectAll();
			session.setAttribute("universityList", universityList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "jsp/system/admin_addschool";
	}

	/**
	 * 新增保存
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public Object doAdd(University university) throws ServiceException {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			university.setUniversityId(MD5.toMd5(UID.nextUid()));
			universityService.insert(university);
			resultMap.put("result", 1);
		}catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return "redirect:/university/listAll.action";
	}

	/**
	 * 修改页面
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/edit", method=RequestMethod.GET)
	@ResponseBody
	public Object editPage(String universityId) throws ServiceException {
		University university = null;
		try {
			university = universityService.findByPrimaryKey(universityId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return university;
	}

	/**
	 * 修改保存
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/edit", method=RequestMethod.POST)
	public Object doEdit(University university) throws ServiceException {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			universityService.update(university);
			resultMap.put("result", 1);
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return "redirect:/university/listAll.action";
	}

	/**
	 * 删除
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object doDelete(String universityId) throws ServiceException {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			universityService.delete(universityId);
			resultMap.put("result", 1);
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return resultMap;
	}
	@RequestMapping(value = "/goAnalysis")
	public String goAnalysis(Map<String, Object> map) throws ServiceException{
		List<String> terms = courseTeacherAnalysisService.getTermsByUniversity("1");
		map.put("terms", terms);
		return "jsp/system/admin_studyanalysis";
	}
}