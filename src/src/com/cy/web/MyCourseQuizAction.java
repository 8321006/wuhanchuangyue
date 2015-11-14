package com.cy.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cy.common.util.CommonFunc;
import com.cy.common.util.Constant;
import com.cy.common.util.MD5;
import com.cy.common.util.UID;
import com.cy.exception.ServiceException;
import com.cy.model.Course;
import com.cy.model.CourseChapter;
import com.cy.model.CourseComment;
import com.cy.model.MyCourseQuiz;
import com.cy.service.CourseChapterService;
import com.cy.service.CourseService;
import com.cy.service.MyCourseQuizService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * Spring MVC Controler - 表：t_my_course_quiz
 * @since 2015-07-08 14:27:56
 */
@Controller
@RequestMapping(value = "/quiz")
public class MyCourseQuizAction {
	private static final Logger logger = LoggerFactory.getLogger(MyCourseQuizAction.class);

	@Autowired
	private MyCourseQuizService myCourseQuizService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseChapterService courseChapterService;
	
	@RequestMapping(value = "/questionAdd")
	public void  questionInsert(HttpServletResponse response,HttpSession session,HttpServletRequest request,String classId,
			ModelMap model,String courseId,String quizContent,String chapterId,String quizType) throws ServiceException, IOException {
		//JSONObject

		String teacherId =  myCourseQuizService.findteacherId(classId);
		MyCourseQuiz myCourseQuiz = new MyCourseQuiz();
		myCourseQuiz.setId(MD5.toMd5(UID.nextUid()));
		myCourseQuiz.setQuizContent(quizContent);
		myCourseQuiz.setClassId(classId);
		Date day = new Date();
		myCourseQuiz.setQuizTime(day);
		myCourseQuiz.setChapterId(chapterId);
		myCourseQuiz.setUserId(CommonFunc.getUserLogin(request).getUserId());
		myCourseQuiz.setTeacherId(teacherId);
		//myCourseQuiz.setQuestionType(Integer.parseInt(quizType));		
		if(quizType==null)
		{
			  myCourseQuiz.setQuestionType(new Integer(1));
		}else {	
		if(quizType.equals("0")){ 
			  	myCourseQuiz.setQuestionType(new Integer(0));
	      }
		else 
	      { 
	        	myCourseQuiz.setQuestionType(new Integer(1));
	      }
		}
		myCourseQuiz.setCourseId(courseId);
		myCourseQuizService.insert(myCourseQuiz);
		
		List<Map<String, Object>> listcomment = myCourseQuizService.getquwidList(chapterId);
		response.setContentType("text/html;charset=UTF-8");
		JSONObject obj = new JSONObject();	
		obj.put("list", listcomment);
		response.getWriter().print(obj.toString()) ;
		
		response.getWriter().flush();
		response.getWriter().close();
		

	}
	
	@RequestMapping(value = "/questionList")
	public void  questionList(HttpServletResponse response,HttpSession session,HttpServletRequest request,String classId,
			ModelMap model,String pageIndex,String courseId,String quizContent,String chapterId,String quizType) throws ServiceException, IOException {
		//ajax方式提交相应方法
		PageHelper.startPage(Integer.valueOf(pageIndex)+1,5);
		List<Map<String, Object>> listcomment = myCourseQuizService.getquwidList(chapterId);
		response.setContentType("text/html;charset=UTF-8");
		JSONObject obj = new JSONObject();
		
		obj.put("list", listcomment);
		//obj.put("num", quizcount);
		response.getWriter().print(obj.toString()) ;
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	
	@RequestMapping(value = "/updateAnwser",method=RequestMethod.POST)
	public void  updateAnwser(HttpServletResponse response,HttpSession session,HttpServletRequest request,String answer,
			String quizid,String courseId,String classId) throws ServiceException, IOException {		
		answer = URLDecoder.decode(answer,"UTF-8");
		Date day = new Date();
		myCourseQuizService.updateAnwser(quizid,answer,day);
		
		//ajax方式提交相应方法
		List<MyCourseQuiz> courseQuiz = myCourseQuizService.findMyCourseQuizTeacher(CommonFunc.getUserLogin(request).getUserId(),courseId,classId);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(JSONArray.fromObject(courseQuiz)) ;
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	
	
	
	/**
	 * 列表页面
	 */
	@RequestMapping(value = "/mycoursequiz", method=RequestMethod.GET)
	public Object listPage() {
		return "mycoursequiz";
	}

	/**
	 * 列表数据
	 */
	@RequestMapping(value = "/mycoursequiz", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			Criteria<MyCourseQuiz> criteria = Criteria.create(MyCourseQuiz.class);
			criteria.buildFromRequest(request);
			criteria.sortIfEmpty(Sort.asc("id"));
			Pager<MyCourseQuiz> pager = myCourseQuizService.findPage(criteria);
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
	@RequestMapping(value = "/mycoursequizadd", method=RequestMethod.GET)
	public Object addPage() {
		return "mycoursequiz_add";
	}

	/**
	 * 新增保存
	 */
	@RequestMapping(value = "/mycoursequizadd", method=RequestMethod.POST)
	@ResponseBody
	public Object doAdd(MyCourseQuiz myCourseQuiz) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			ValidatorUtil.validate(myCourseQuiz);
			myCourseQuizService.insert(myCourseQuiz);
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
	@RequestMapping(value = "/mycoursequizedit", method=RequestMethod.GET)
	public Object editPage(String id) {
		ModelAndView model = new ModelAndView();
		/*try {
			MyCourseQuiz myCourseQuiz = myCourseQuizService.findByPrimaryKey(id);
			model.addObject("myCourseQuiz", myCourseQuiz);
			model.setViewName("mycoursequiz_edit");
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
	@RequestMapping(value = "/mycoursequizedit", method=RequestMethod.POST)
	@ResponseBody
	public Object doEdit(MyCourseQuiz myCourseQuiz) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			ValidatorUtil.validate(myCourseQuiz);
			myCourseQuizService.update(myCourseQuiz);
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
	@RequestMapping(value = "/mycoursequizdelete", method=RequestMethod.POST)
	@ResponseBody
	public Object doDelete(List<String> ids) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		/*try {
			myCourseQuizService.batchDelete(ids);
			resultMap.put("result", 1);
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}*/
		return resultMap;
	}
}