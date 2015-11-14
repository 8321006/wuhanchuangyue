
package com.cy.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cy.exception.ServiceException;
import com.cy.model.Course;
import com.cy.model.Message;
import com.cy.service.CourseChapterService;
import com.cy.service.CourseService;
import com.cy.service.MessageService;
import com.cy.service.UniversityService;
import com.cy.service.UserService;

/**
 * Spring MVC Controler - 表：t_course
 * @since 2015-06-08 15:47:16
 */
@Controller
@RequestMapping(value = "/index")
public class IndexAction {
	private static final Logger logger = LoggerFactory.getLogger(IndexAction.class);

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseChapterService courseChapterService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UniversityService universityService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 列表页面
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "")
	public String index(HttpServletResponse response,Message userId,
			HttpServletRequest request,ModelMap model) throws ServiceException {
		List<Course> courses = courseService.findAllCourse();
		List<Course> courseType = new ArrayList<Course>();
		String id ="";
		String first = "";
		for(Course course :courses){
			if(!id.equals(course.getCourseType())){
				Course temp = new Course();
				temp.setCourseType(course.getCourseType());
				temp.setCourseTypeName(course.getCourseTypeName());
				courseType.add(temp);
				if(id.equals("")){
					first=course.getCourseType();
				}
				id = course.getCourseType();
			}
		}
		List<Course> thecourses = courseService.findCourseByType(first);
		for(Course course : thecourses){
			course.setNum(courseService.countcourseById(course.getCourseId()));
		}
	//	List<Message> listmessage=messageService.getadminMessage(userId);
	//	List<Message> listmes=messageService.findMessageList(userId);
	//	model.put("msgnum", listmessage.size()+listmes.size());
	//	model.put("listmessage", listmessage.size());
	//	model.put("listmes", listmes.size());
		if(thecourses.size() <6){
			model.put("size", 6-thecourses.size());
		}
		model.put("courseType", courseType);
		model.put("courses", thecourses);
		
		// qq登录跳转处理,没有绑定qq时则跳转到绑定页面
		if(!userService.loginOrBindByQQ(request)){
			return "redirect:/cy/bindschool.action";
		}
		return "jsp/index";
	}
	
	/**
	 * 列表页面
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/search")
	public String search(HttpServletResponse response,
			HttpServletRequest request,String name,ModelMap model) throws ServiceException {
		if(name == null){
			name = "";
		}
		List<Course> dourselist = courseService.searchCourse(name);
		if(dourselist.size()==0){
			dourselist = courseService.searchCourse("");
		}
		for(Course course : dourselist){
			course.setNum(courseService.countcourseById(course.getCourseId()));
		}
		model.put("dourselist", dourselist);
		model.put("name", name);
		return "jsp/course/courselist";
	}
	
	/**
	 * 列表页面
	 * @throws ServiceException 
	 * @throws IOException 
	 */
	@RequestMapping(value = "/coursetype")
	public void coursetype(HttpServletResponse response,
			HttpServletRequest request,String type,ModelMap model) throws ServiceException, IOException {
		List<Course> courses = courseService.findCourseByType(type);
		for(Course course : courses){
			course.setNum(courseService.countcourseById(course.getCourseId()));
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(JSONArray.fromObject(courses)) ;
		response.getWriter().flush();
		response.getWriter().close();
	}
    
}