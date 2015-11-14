package com.cy.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.common.util.StringUtil;
import com.cy.exception.ServiceException;
import com.cy.model.CourseUniversityAnalysis;
import com.cy.model.University;
import com.cy.service.CourseLearnAnalysisService;
import com.cy.service.CourseTeacherAnalysisService;
import com.cy.service.CourseUniversityAnalysisService;
import com.cy.service.UniversityService;

/**
 * Spring MVC Controler - 表：t_course_university_analysis
 * @since 2015-08-19 16:56:37
 */
@Controller
@RequestMapping(value = "/universityanalysis")
public class CourseUniversityAnalysisAction {

	@Autowired
	private CourseUniversityAnalysisService courseUniversityAnalysisService;
	@Autowired
	private UniversityService universityService;

	@Autowired
	private CourseLearnAnalysisService courseLearnAnalysisService;
	@Autowired
	private CourseTeacherAnalysisService courseTeacherAnalysisService;
	/**
	 * 列表页面
	 */
	@RequestMapping(value = "/goReport")
	@ResponseBody
	public String listAllGroup(String universityId,HttpSession session) {
		try {
			courseUniversityAnalysisService.updateAllGroup(universityId);
			University university = universityService.findByPrimaryKey(universityId);
			session.setAttribute("university", university);
			List<String> terms = courseUniversityAnalysisService.getAllTerm(universityId);
			System.out.println("terms : " + terms);
			session.setAttribute("terms", terms);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "jsp/admin/reportUniversity.jsp";
	}

	/**
	 * 列表数据
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/report")
	@ResponseBody
	public Object report(String universityId,String courseTerm) throws ServiceException {
		List<CourseUniversityAnalysis> list = null;
		try {
			courseUniversityAnalysisService.updateAllJob();
			if(StringUtil.isEmpty(courseTerm)){
				List<String> terms = courseUniversityAnalysisService.getAllTerm(universityId);
				if(terms != null && terms.size() > 0){
					courseTerm = terms.get(0);				
				}
			}
			list = courseUniversityAnalysisService.getAnalysisByTerm(universityId, courseTerm);
			System.out.println("list == " + list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@RequestMapping(value = "/reportAll")
	@ResponseBody
	public Object reportAll(HttpServletRequest request, String courseTerm) throws ServiceException {
		List<CourseUniversityAnalysis> list = null;
		try {
			courseUniversityAnalysisService.updateAllJob();
			if(StringUtil.isEmpty(courseTerm)){
				List<String> terms = courseUniversityAnalysisService.getTerms();
				if(terms != null && terms.size() > 0){
					courseTerm = terms.get(0);				
				}
			}
			request.setAttribute("courseTerm", courseTerm);
			//查询t_course_university_analysis表的数据
			list = courseUniversityAnalysisService.getAllByTerm(courseTerm);
			System.out.println("list == " + list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}