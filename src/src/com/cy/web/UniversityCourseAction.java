package com.cy.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.type.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cy.common.util.CommonFunc;
import com.cy.exception.ServiceException;
import com.cy.model.Course;
import com.cy.model.CourseClass;
import com.cy.model.Paper;
import com.cy.model.Question;
import com.cy.model.Test;
import com.cy.model.UniversityCourse;
import com.cy.model.User;
import com.cy.service.CourseChapterService;
import com.cy.service.CourseClassService;
import com.cy.service.CourseService;
import com.cy.service.UniversityCourseService;
import com.cy.service.UserService;

/**
 * Spring MVC Controler - 表：t_university_course
 * @since 2015-07-10 16:35:13
 */
@Controller
@RequestMapping(value = "/cy")
public class UniversityCourseAction {
	private static final Logger logger = LoggerFactory.getLogger(UniversityCourseAction.class);
	@Autowired
	private UniversityCourseService universityCourseService;
	@Autowired
	private UserService userService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private CourseChapterService courseChapterService;
	@Autowired
	private CourseClassService courseClassService;
	/**
	 * 课程设置列表页面
	 * @throws ServiceException 
	 * pear
	 */
	@RequestMapping(value = "/universitycourse", method=RequestMethod.GET)
	public Object listPage(Model model,HttpServletRequest httpServletRequest,HttpServletResponse response) throws ServiceException {
		UniversityCourse universityCourse = new UniversityCourse();
		universityCourse.setUniversityId(CommonFunc.getUserLogin(httpServletRequest).getUniversityId());	
		  List<UniversityCourse> universityCourselist=universityCourseService.findAllByUniversity(universityCourse);
	    model.addAttribute("universityCourselist", universityCourselist);
	    //查询学期
	    List<UniversityCourse> courseTermlist=universityCourseService.findAllBycourseTerm(universityCourse);
	    model.addAttribute("courseTermlist", courseTermlist);
		  return "jsp/admin/coursesetting";
	}
	/**
	 * 老师列表数据
	 */
	@RequestMapping(value = "/universitycourse", method=RequestMethod.POST)
	@ResponseBody
	public List<User> listData(Model model,@ModelAttribute("user") User user, @Param(value="realName") String realName,
			HttpServletRequest httpServletRequest,HttpServletResponse response) throws ServiceException, IOException {
		 System.out.print("user.getRealName()"+ user.getRealName());
		  user.getRealName();
		  user.setUniversityId(CommonFunc.getUserLogin(httpServletRequest).getUniversityId());
		 List<User> teacherList= userService.findByTeacherName(user);
		return teacherList;
	}
	/**
	 * 修改课程设置，提交修改
	 */
	@RequestMapping(value = "/universitycourseedit", method=RequestMethod.POST)
	@ResponseBody
	public void doEdit(Model model,@ModelAttribute("universityCourse") UniversityCourse universityCourse, 
			HttpServletRequest httpServletRequest,HttpServletResponse response) throws ServiceException, IOException {
		 universityCourse.getCourseId();
		 universityCourse.getCredit();
		 universityCourse.getTeacherId();
		 universityCourse.getTeacherName();
		 universityCourse.getWorkRatio();
		 universityCourse.getExamRatio();
		 universityCourse.getVideoRatio();
		 //获取登录的学校信息
		 universityCourse.setUniversityId(CommonFunc.getUserLogin(httpServletRequest).getUniversityId());
		 universityCourseService.update(universityCourse);
		 //将课程指定的期末考试的试卷插入到表中
		 universityCourseService.insertcoursePaper(universityCourse);
		response.getWriter().write("{\"success\":\"true\"}");
	}
	/**
	 * 删除
	 */
	@RequestMapping(value = "/universitycoursedelete", method=RequestMethod.POST)
	@ResponseBody
	public void doDelete(Model model,@ModelAttribute("universityCourse") UniversityCourse universityCourse, 
			HttpServletRequest httpServletRequest,HttpServletResponse response) throws ServiceException, IOException  {
		 universityCourse.getId(); 
		 System.out.print("universityCourse.getId()"+universityCourse.getId());
		 universityCourseService.delete(universityCourse);
		 response.getWriter().write("{\"success\":\"true\"}");
	}
	/**
	 * 后台管理课程开课
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/courseStart", method=RequestMethod.POST)
	public void StartCourse(Model model,@ModelAttribute("universityCourse") UniversityCourse universityCourse, 
			HttpServletRequest httpServletRequest,HttpServletResponse response) throws ServiceException, IOException  {
		   universityCourse.getCourseId();
		   Date date =new Date();
		   universityCourse.setCourseStartTime(date);
		   System.out.println(universityCourse.getCourseStartTime());
		   Calendar calendar=Calendar.getInstance();   
		   calendar.setTime(date); 
		   int plantotal= courseChapterService.findplantotalByCourseId(universityCourse.getCourseId());
		   calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+plantotal);
		   universityCourse.setCourseEndTime(calendar.getTime());
		   universityCourse.setUniversityId(CommonFunc.getUserLogin(httpServletRequest).getUniversityId());
		   System.out.println(universityCourse.getCourseEndTime());
		 //修改课程开始和结束的时间
		   try {  
			universityCourseService.updatecourseTimeByCourseId(universityCourse);
	    	CourseClass courseclass=new CourseClass();
	    	courseclass.setCourseStartTime(universityCourse.getCourseStartTime());
	    	courseclass.setCourseEndTime(universityCourse.getCourseEndTime());
	    	courseclass.setCourseId(universityCourse.getCourseId());
	    	courseclass.setUniversityId(CommonFunc.getUserLogin(httpServletRequest).getUniversityId());
			courseClassService.UpdateTime(courseclass);
			response.getWriter().write("{\"success\":\"true\"}");
           } catch (Exception e)  {  
        	   logger.error(e.getMessage(), e);
           }  
	}
	/*
	 * 课程搜索
	 */
	@RequestMapping(value = "/universitycoursesearch")
	public String searchcourse(HttpServletResponse response,@ModelAttribute("universityCourse") UniversityCourse universityCourse,
			HttpServletRequest request,HttpServletRequest httpServletRequest, String courseName,ModelMap model) throws ServiceException, JsonGenerationException, JsonMappingException, IOException {
		courseName=new String(courseName.getBytes("iso-8859-1"), "UTF-8");
		universityCourse.setUniversityId(CommonFunc.getUserLogin(httpServletRequest).getUniversityId());
		if("请输入要搜索的关键字".equals(courseName)){
			universityCourse.setCourseName(null);
		}else{
			universityCourse.setCourseName(courseName);
		}
		List<UniversityCourse> universityCourselist=universityCourseService.findcourseName(universityCourse);
		List<UniversityCourse> courseTermlist=universityCourseService.findAllBycourseTerm(universityCourse);
	   model.addAttribute("courseTermlist", courseTermlist);
	    model.addAttribute("universityCourselist", universityCourselist);
	    return "jsp/admin/coursesetting";
	}
	/*
	 * 根据学期搜素课程
	 */
	@RequestMapping(value = "/search")
	public String search(HttpServletResponse response,@ModelAttribute("universityCourse") UniversityCourse universityCourse,
			HttpServletRequest request,HttpServletRequest httpServletRequest, String courseTerm,ModelMap model) throws ServiceException, JsonGenerationException, JsonMappingException, IOException, Exception {		
		courseTerm=new String(courseTerm.getBytes("iso-8859-1"), "UTF-8");
		if("".equals(courseTerm)){
			universityCourse.setCourseTerm(null);
		}else{ 
			universityCourse.setCourseTerm(courseTerm);
		}
		universityCourse.setUniversityId(CommonFunc.getUserLogin(httpServletRequest).getUniversityId());
		List<UniversityCourse> universityCourselist=universityCourseService.findtermtime(universityCourse);
		 List<UniversityCourse> courseTermlist=universityCourseService.findAllBycourseTerm(universityCourse);
		model.addAttribute("courseTermlist", courseTermlist);
	    model.addAttribute("universityCourselist", universityCourselist);
	    
	    return "jsp/admin/coursesetting";
	}
	/*
	 * 课程设置查询信息
	 * 
	 */
	@RequestMapping(value = "/courseSet", method=RequestMethod.POST)
	@ResponseBody
	public void courseSet(HttpServletResponse response,@ModelAttribute("universityCourse") UniversityCourse universityCourse,
		HttpServletRequest request,HttpServletRequest httpServletRequest,String courseId,String universityId, String courseTerm,ModelMap model) throws ServiceException, IOException {		
		universityCourse.setUniversityId(CommonFunc.getUserLogin(httpServletRequest).getUniversityId());
		 List<UniversityCourse> courselist=universityCourseService.findThisBycourseTerm(universityCourse);
		   ObjectMapper mapper = new ObjectMapper();  
	       ObjectWriter writer = mapper.viewWriter(Test.class);  
	       String jsonObj = writer.writeValueAsString(courselist);  
			response.getWriter().write(jsonObj);
			response.getWriter().flush();
	
}
	/**
	 * @Title: paperlist
	 * @Description: 试卷列表
	 * @param model
	 * @param httpServletRequest
	 * @param response
	 * @author pear
	 * @throws ServiceException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/paperlist")
	@ResponseBody
	public void paperlist(HttpServletResponse response,@ModelAttribute("universityCourse") UniversityCourse universityCourse,
			HttpServletRequest request,HttpServletRequest httpServletRequest, String courseTerm,ModelMap model) throws ServiceException, JsonGenerationException, JsonMappingException, IOException, Exception {		
		List<UniversityCourse> paperlist=universityCourseService.findpaperBycourseId(universityCourse);
		ObjectMapper mapper = new ObjectMapper();  
	    ObjectWriter writer = mapper.viewWriter(Test.class);  
	    String jsonObj = writer.writeValueAsString(paperlist);  
		response.getWriter().write(jsonObj);
		response.getWriter().flush();
	}
}