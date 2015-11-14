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
import com.cy.model.User;
import com.cy.service.CourseClassService;
import com.cy.service.CourseLearnAnalysisService;
import com.cy.service.CourseService;
import com.cy.service.CourseTeacherAnalysisService;

/**
 * Spring MVC Controler - 表：t_course_learn_analysis
 * 
 * @since 2015-07-20 16:33:23
 */
@Controller
@RequestMapping(value = "/analysis")
public class CourseLearnAnalysisAction {
	private static final Logger logger = LoggerFactory
			.getLogger(CourseLearnAnalysisAction.class);

	@Autowired
	private CourseLearnAnalysisService courseLearnAnalysisService;
	@Autowired
	private CourseTeacherAnalysisService courseTeacherAnalysisService;

	@Autowired
	private CourseClassService courseClassService;
	@Autowired
	private CourseService courseService;

	@RequestMapping("/goReport")
	@ResponseBody
	public String goReport(String courseId,String classId, HttpSession session) throws ServiceException{
		User user = (User) session.getAttribute("user");
		String userId = user.getUserId();
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			courseLearnAnalysisService.updatePersonalJob(classId, courseId, userId);
			result.put("courseId", courseId);
			result.put("classId", classId);
			String studentId = user.getUserId();
			CourseClass currClass = courseClassService.findByPrimaryKey(classId);
			Course currCourse = courseService.findByPrimaryKey(courseId);
			result.put("currClass", currClass.getClassName());
			result.put("currCourse", currCourse.getCourseName());
			int studentCount = courseLearnAnalysisService.countStudentsByClass(classId);
			result.put("studentCount", studentCount);
			int rank = courseLearnAnalysisService.getRankLearningInClass(classId, studentId);
			result.put("rank", rank);
			Map<String, String> months = DateUtil.getDatas(currClass.getCourseStartTime(), currClass.getCourseEndTime());
			session.setAttribute("months", months);
			System.out.println(months);
			int myscore = courseLearnAnalysisService.getExamScoreByUser(classId, studentId);
			result.put("myscore",myscore);
			int examRank = courseLearnAnalysisService.getRankExamInClass(classId, studentId);
			result.put("examRank", examRank);
			session.setAttribute("result", result);
			System.out.println("result  " +result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "jsp/report/reportStudent.jsp";
	}

	@RequestMapping("/report")
	@ResponseBody
	public Object doReport(String classId, String timebucket, HttpSession session) throws ServiceException{
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) session.getAttribute("user");
		if(StringUtil.isEmpty(timebucket)){
			timebucket = DateUtil.getCurrentYearAndMonth();
		}
		String studentId = user.getUserId();
		System.out.println("classId == " + classId);
		List<CourseLearnAnalysis> analysis = courseLearnAnalysisService.findByTimebucket(studentId, classId, timebucket);
		Map<String, Object> scoreRates = courseTeacherAnalysisService.calRateByScore(classId, 60, 80);
		System.out.println("analysis : =====" +analysis);
		List<Integer> rates = new ArrayList<Integer>();
		List<Integer> comments = new ArrayList<Integer>();
		List<Integer> tasks = new ArrayList<Integer>();
		List<Integer> quizs = new ArrayList<Integer>();
		List<Integer> videos = new ArrayList<Integer>();
		List<Integer> exams = new ArrayList<Integer>();
		List<String> days = new ArrayList<String>();
		for (CourseLearnAnalysis an : analysis) {
			rates.add(an.getLearnRate());
			comments.add(an.getCommentCount());
			tasks.add(an.getTaskCount());
			quizs.add(an.getQuizCount());
			videos.add(an.getVideoCount());
			exams.add(an.getExamCount());
			days.add(DateUtil.getMonthAndDay(an.getAnalysisTime()));
		}
		map.put("analysis", analysis);
		map.put("rates", rates.toArray());
		map.put("comments", comments.toArray());
		map.put("tasks", tasks.toArray());
		map.put("quizs", quizs.toArray());
		map.put("videos", videos.toArray());
		map.put("exams", exams.toArray());
		map.put("days", days.toArray());
		map.put("scoreRates", scoreRates);
		return map;
	}
	
}