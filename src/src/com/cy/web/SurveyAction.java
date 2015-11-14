package com.cy.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.type.JavaType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cy.common.util.CommonFunc;
import com.cy.common.util.Constant;
import com.cy.common.util.MD5;
import com.cy.common.util.Message;
import com.cy.common.util.UID;
import com.cy.exception.ServiceException;
import com.cy.model.Course;
import com.cy.model.Notice;
import com.cy.model.Paper;
import com.cy.model.Question;
import com.cy.model.Survey;
import com.cy.model.Test;
import com.cy.model.TestQuestion;
import com.cy.service.CourseService;
import com.cy.service.PaperService;
import com.cy.service.QuestionService;
import com.cy.service.SurveyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value = "/survey")
public class SurveyAction {
	private static final Logger logger = Logger.getLogger(SurveyAction.class);
	@Autowired
	private SurveyService surveyService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private PaperService paperService;

	/**
	 * @Title: surveylist
	 * @Description: 调查列表
	 * @param model
	 * @param httpServletRequest
	 * @param response
	 * @author pear
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/surveylist", method = RequestMethod.GET)
	public Object surveylist(Model model,
			HttpServletRequest httpServletRequest, HttpSession session,
			HttpServletResponse response) throws ServiceException {
		// 查询调查列表
		Survey survey=new Survey();
		List<Survey> surveylist=new ArrayList<Survey>();
		List<Course> courselist=new ArrayList<Course>();
		// 统计所学课程未填写的调查问卷
		int totalwritesurvey;
		survey.setUserId(CommonFunc.getUserLogin(httpServletRequest)
				.getUserId());
		survey.setUniversityId(CommonFunc.getUserLogin(httpServletRequest)
				.getUniversityId());
		int userType =CommonFunc.getUserLogin(httpServletRequest)
				.getUserType();
		if (userType==0){
			// 学生平台
			surveylist=surveyService.findsurveylistByStudent(survey);
			// 统计该学生有几张调查问卷尚未填写
			totalwritesurvey = surveyService.findtotalwritesurvey(survey);
			model.addAttribute("totalwritesurvey", totalwritesurvey);
			// totalwritesurvey=surveyService.findtotalwritesurvey(survey);
			// 统计平台有几张调查问卷未填写
			int Platformsurvey = surveyService.findsurveyWrite(survey);
			session.setAttribute("totalwritesurvey", totalwritesurvey
					+ Platformsurvey);
			model.addAttribute("totalwritesurvey", totalwritesurvey
					+ Platformsurvey);
		}
		if (userType == 1) {
			// 老师平台
			surveylist = surveyService.findsurveylist(survey);
			// 查询本教师所负责的课程
			courselist = surveyService.findcourselist(survey);
			model.addAttribute("courselist", courselist);
		}
		// 查询平台调查问卷列表
		List<Survey> surveylistPlatform = surveyService.findPlatformsurvey(survey);
		model.addAttribute("surveylistPlatform", surveylistPlatform);
		model.addAttribute("userType", userType);
		model.addAttribute("surveylist", surveylist);
		return "jsp/survey/surveylist";
	}

	/**
	 * @Title: survey-import
	 * @Description: 调查问卷导入
	 * @param model
	 * @param file
	 * @param courseId
	 * @param httpServletRequest
	 * @param response
	 * @author pear
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/surveyimport", method = RequestMethod.POST)
	public Object surveyupload(MultipartFile file, String courseId,String courseName,Model model,RedirectAttributes attr,
			HttpServletRequest request) throws ServiceException , IOException{
		Message message = new Message();
		int type = 0;
		String path = request.getSession().getServletContext().getRealPath("upload");
		String fileName = file.getOriginalFilename();
		System.out.println(path);
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String filePath = path + File.separator + fileName;
		// 查询该课程是否已经上传调查问卷
		if ("0".equals(courseId) == false) {
			int count = surveyService.findSurveypost(courseId);
			if (count > 0) {
				// 该课程已经发送了调查问卷
				type=1;
			} else {
				message.setMessageInfo(questionService.uploadPaper(filePath,courseId, 2));
			}
		} else {
			message.setMessageInfo(questionService.uploadPaper(filePath,courseId, 2));
		}
		model.addAttribute("message", message.getMessageInfo());
		/*messages=new String(messages.getBytes("iso-8859-1"), "UTF-8");
		/*
		String bianma = null;
		try {
			byte[] jiema= messages.getBytes("UTF-8") ; //解码  
			bianma = new String(jiema,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		attr.addAttribute("type", type);
		attr.addAttribute("courseName", courseName);
		return "redirect:/survey/coursesurveylist.action";
	}

	/**
	 * @Title: survey-import
	 * @Description: 调查问卷导入页面---- 查询课程列表
	 * @param model
	 * @param httpServletRequest
	 * @param response
	 * @author pear
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/surveycourselist", method = RequestMethod.GET)
	public String surveyimport(Model model,
			HttpServletRequest httpServletRequest,
			HttpServletResponse response, HttpServletRequest request)
			throws ServiceException {
		List<Course> courses = courseService.findAllCourse();
		model.addAttribute("courses", courses);
		return "jsp/survey/survey_import";
	}
	/**
	 * @Title: survey-import
	 * @Description: 发送调查问卷的列表页面
	 * @param model
	 * @param httpServletRequest
	 * @param response
	 * @author pear
	 * @throws ServiceException
	 * @throws IOException
	 */
	@RequestMapping(value = "/sendsurveycourse", method = RequestMethod.POST)
	public void listData(Model model, @ModelAttribute("survey") Survey survey,
			HttpServletRequest httpServletRequest, HttpServletResponse response)
			throws ServiceException, IOException {
		survey.setUserId(CommonFunc.getUserLogin(httpServletRequest)
				.getUserId());
		survey.setUniversityId(CommonFunc.getUserLogin(httpServletRequest)
				.getUniversityId());
		List<Survey> coursesurveyList = surveyService.findsendSurvey(survey);
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter writer = mapper.viewWriter(Test.class);
		String jsonObj = writer.writeValueAsString(coursesurveyList);
		response.getWriter().write(jsonObj);
		response.getWriter().flush();
	}
	/**
	 * @Title: sendsurvey
	 * @Description: 向学生发送调查问卷
	 * @param model
	 * @param httpServletRequest
	 * @param response
	 * @author pear
	 * @throws ServiceException
	 * @throws IOException
	 */
	@RequestMapping(value = "/sendsurvey", method = RequestMethod.POST)
	public void submit(Model model, @ModelAttribute("survey") Survey survey,String content,
			HttpServletRequest httpServletRequest, HttpServletResponse response)
			throws ServiceException, IOException {
		survey.setUniversityId(CommonFunc.getUserLogin(httpServletRequest)
				.getUniversityId());
		surveyService.insertSurvey(content,survey.getUniversityId());
		response.getWriter().write("{\"success\":\"true\"}");

	}

	/**
	 * @Title: coursesurveylist
	 * @Description: 平台调查问卷列表
	 * @param model
	 * @param httpServletRequest
	 * @param response
	 * @author pear
	 * @throws ServiceException
	 * @throws UnsupportedEncodingException 
	 * @throws IOException
	 */
	@RequestMapping(value = "/coursesurveylist")
	public String surveylist(ModelMap model,String type,String courseName,Integer pageIndex,
			HttpServletRequest httpServletRequest,
			HttpServletResponse response, HttpServletRequest request)
			throws ServiceException, UnsupportedEncodingException {
		List<Course> courses = courseService.findAllCourse();
		 if(null!=courseName)
		{
		 courseName=new String(courseName.getBytes("iso-8859-1"), "UTF-8");
	    }
		 if(pageIndex == null){
				pageIndex = 1;
			}
		 PageHelper.startPage(pageIndex, Constant.pageSize);
		 List<Survey> surveylist = surveyService.findAdminsurvey();
		 PageInfo pager = new PageInfo(surveylist);
		 model.put("page", pager);
		// model.addAttribute("page", pager);
		 model.addAttribute("surveylist", surveylist);
		 model.addAttribute("courses", courses);
		 model.addAttribute("type", type);
		 model.addAttribute("courseName", courseName);
		 return "jsp/system/admin_addsurvey";
	}

	/**
	 * @Title: coursesurveylist
	 * @Description: 平台调查问卷阅览
	 * @param model
	 * @param httpServletRequest
	 * @param response
	 * @author pear
	 * @throws ServiceException
	 * @throws IOException
	 */
	@RequestMapping(value = "/survey-preview")
	public void preview(HttpServletResponse response,
			HttpServletRequest request, String paperId, ModelMap model)
			throws ServiceException, JsonGenerationException,
			JsonMappingException, IOException {
		Paper paper = paperService.findByPrimaryKey(paperId);
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(
				ArrayList.class, Question.class);
		if (paper.getContent() != null && !paper.getContent().equals("")) {
			long jackStart = System.currentTimeMillis();
			@SuppressWarnings("unchecked")
			List<Question> questionList = (List<Question>) mapper.readValue(
					paper.getContent(), javaType);
			ObjectWriter writer = mapper.viewWriter(Test.class);
			String jsonObj = writer.writeValueAsString(questionList);
			response.getWriter().write(jsonObj);
			response.getWriter().flush();
		}
	}

	/**
	 * @Title: surveystudent-preview
	 * @Description: 调查问卷前台预览
	 * @param model
	 * @param httpServletRequest
	 * @param response
	 * @author pear
	 * @throws ServiceException
	 * @throws IOException
	 */
	@RequestMapping(value = "/surveystudent-preview")
	public Object surveywrite(HttpServletResponse response,
			HttpServletRequest request, String courseCoverUrl, String classId,
			int userTotal, int userWriteTotal,int state, String paperId, String surveyResult,ModelMap model)
			throws ServiceException, JsonGenerationException,
			JsonMappingException, IOException {
		Paper paper = paperService.findByPrimaryKey(paperId);
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(
				ArrayList.class, Question.class);
		if (paper.getContent() != null && !paper.getContent().equals("")) {
			long jackStart = System.currentTimeMillis();
			@SuppressWarnings("unchecked")
			List<Question> questionList = (List<Question>) mapper.readValue(
					paper.getContent(), javaType);
			logger.debug("Json2Obj=============>>"
					+ (System.currentTimeMillis() - jackStart) + "ms");
			for (int i = 0; i < questionList.size(); i++) {
				// String content= questionList.get(i).getContent();
				// System.out.print(questionList.get(i).getQuestionNo());
				if (questionList.get(i).getQuestionTypeId() == 1
						|| questionList.get(i).getQuestionTypeId() == 2) {
					// List<QuestionOption>
					// choiceList=questionList.get(i).getContent();
					questionList.get(i).setSelectATotal(
							surveyService.selectopationTotal(paperId, classId,
									questionList.get(i).getQuestionId(), "A"));
					questionList.get(i).setSelectBTotal(
							surveyService.selectopationTotal(paperId, classId,
									questionList.get(i).getQuestionId(), "B"));
					questionList.get(i).setSelectCTotal(
							surveyService.selectopationTotal(paperId, classId,
									questionList.get(i).getQuestionId(), "C"));
					questionList.get(i).setSelectDTotal(
							surveyService.selectopationTotal(paperId, classId,
									questionList.get(i).getQuestionId(), "D"));
				}
			}
			model.addAttribute("questionList", questionList);
		}
		model.addAttribute("paper", paper);
		model.addAttribute("state", state);
		model.addAttribute("userTotal", userTotal);
		model.addAttribute("userWriteTotal", userWriteTotal);
		model.addAttribute("surveyResult", surveyResult);
		model.addAttribute("courseCoverUrl", courseCoverUrl);
		return "jsp/survey/survey_write";
	}
}
