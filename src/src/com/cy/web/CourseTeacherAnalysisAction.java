package com.cy.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.common.util.DateUtil;
import com.cy.common.util.StringUtil;
import com.cy.exception.ServiceException;
import com.cy.model.Course;
import com.cy.model.CourseClass;
import com.cy.model.CourseLearnAnalysis;
import com.cy.model.CourseTeacherAnalysis;
import com.cy.model.User;
import com.cy.service.CourseClassService;
import com.cy.service.CourseLearnAnalysisService;
import com.cy.service.CourseService;
import com.cy.service.CourseTeacherAnalysisService;

/**
 * Spring MVC Controler - 表：t_course_teacher_analysis
 * @since 2015-07-29 11:12:42
 */
@Controller
@RequestMapping(value = "/teacherAnalysis")
public class CourseTeacherAnalysisAction {
	private static final Logger logger = LoggerFactory.getLogger(CourseTeacherAnalysisAction.class);

	@Autowired
	private CourseTeacherAnalysisService courseTeacherAnalysisService;
	@Autowired
	private CourseLearnAnalysisService courseLearnAnalysisService;
	@Autowired
	private CourseClassService courseClassService;
	@Autowired
	private CourseService courseService;
	
	@RequestMapping("/goReport")
	@ResponseBody
	public String goReport(String courseId,String classId, String timebucket, HttpSession session) throws ServiceException{
		try {
			User teacher = (User) session.getAttribute("user");
			String teacherId = null;
			if(teacher != null){
				teacherId = teacher.getUserId();
			}
			courseTeacherAnalysisService.updateClassJob(classId, courseId, teacherId);
			int studentCount = courseTeacherAnalysisService.countStudentsInClass(classId);
			CourseClass currClass = courseClassService.findByPrimaryKey(classId);
			Course currCourse = courseService.findByPrimaryKey(courseId);
			session.setAttribute("studentCount", studentCount);
			Map<String, String> months = DateUtil.getDatas(currClass.getCourseStartTime(), currClass.getCourseEndTime());
			session.setAttribute("months", months);
			System.out.println("months  == " + months);
			Map<String, Object> scoreRates = courseTeacherAnalysisService.calRateByScore(classId, 60, 80);
			session.setAttribute("scoreRates",scoreRates);
			session.setAttribute("timebucket",timebucket);
			session.setAttribute("currClass",currClass);
			session.setAttribute("currCourse",currCourse);
			session.setAttribute("classId",classId);
			session.setAttribute("courseId",courseId);
			List<CourseLearnAnalysis> details = courseLearnAnalysisService.getLastInClass(classId);		
			System.out.println("details = " + details);
			session.setAttribute("details",details);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "jsp/report/reportTeacher.jsp";
	}
	
	
	
	@RequestMapping("/report")
	@ResponseBody
	public Object doReport(String courseId,String classId, String timebucket, HttpSession session) throws ServiceException{
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isEmpty(timebucket)){
			timebucket = DateUtil.getCurrentYearAndMonth();
		}
		System.out.println("classId : " +classId);
		System.out.println("timebucket == " + timebucket);
		List<CourseTeacherAnalysis> analysis = courseTeacherAnalysisService.findByTimebucket(classId, timebucket);
		System.out.println("analysis ==== " + analysis);
		Map<String, Object> scoreRates = courseTeacherAnalysisService.calRateByScore(classId, 60, 80);
		List<Integer> rates = new ArrayList<Integer>();
		List<Integer> comments = new ArrayList<Integer>();
		List<Integer> tasks = new ArrayList<Integer>();
		List<Integer> quizs = new ArrayList<Integer>();
		List<Integer> videos = new ArrayList<Integer>();
		List<Integer> exams = new ArrayList<Integer>();
		List<String> days = new ArrayList<String>();
		for (CourseTeacherAnalysis an : analysis) {
			rates.add(an.getLearnRate());
			comments.add(an.getCommentCount());
			tasks.add(an.getTaskCount());
			quizs.add(an.getQuizCount());
			videos.add(an.getVideoCount());
			exams.add(an.getExamCount());
			days.add(DateUtil.getMonthAndDay(an.getAnalysisTime()));
		}
		map.put("rates", rates.toArray());
		map.put("comments", comments.toArray());
		map.put("tasks", tasks.toArray());
		map.put("quizs", quizs.toArray());
		map.put("videos", videos.toArray());
		map.put("exams", exams.toArray());
		map.put("days", days.toArray());
		map.put("scoreRates", scoreRates);
		System.out.println(rates);
		System.out.println(comments);
		System.out.println(quizs);
		System.out.println(videos);
		System.out.println(days);
		System.out.println("scoreRates = " + scoreRates);
		List<CourseLearnAnalysis> ds = courseLearnAnalysisService.getLastInClass(classId);
		System.out.println("ds = " + ds);
		System.out.println("map == " + map);
		return map;
	}
	
	@RequestMapping("/getDetails")
	@ResponseBody
	public Object getDetails(String courseId, String timebucket, HttpSession session) throws ServiceException{
		User user = (User) session.getAttribute("user");
		String teacherId = user.getUserId();
		String classId = courseTeacherAnalysisService.findClassByTeacherAndCourse(courseId, teacherId );
		List<CourseLearnAnalysis> details = courseLearnAnalysisService.getLastInClass(classId);
		System.out.println("details = " + details);
		return details;
	}
	@RequestMapping("/getStus")
	@ResponseBody
	public Object getStus(String courseTerm, HttpSession session) throws ServiceException{
		User user = (User) session.getAttribute("user");
		String universityId = user.getUniversityId();
		List<String> terms = courseTeacherAnalysisService.getTermsByUniversity(universityId);
		if(StringUtil.isEmpty(courseTerm)){
			courseTerm = terms.get(0);
		}
		List<Map<String, String>> classes = courseTeacherAnalysisService.getClassByTermAndUniversity(universityId, courseTerm);
		for (Map<String, String> map : classes) {
			String classId = map.get("classId");
			int stu = courseTeacherAnalysisService.countStudentsInClass(classId);
			map.put("stu", stu+"");
		}
		System.out.println(classes);
		return classes;
	}
}