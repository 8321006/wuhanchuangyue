package com.cy.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.cy.common.util.StringUtil;

import org.apache.catalina.connector.Request;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.record.UseSelFSRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cy.common.util.CommonFunc;
import com.cy.common.util.Constant;
import com.cy.common.util.HttpSend;
import com.cy.common.util.MD5;
import com.cy.common.util.UID;
import com.cy.common.util.Page;
import com.cy.exception.ServiceException;
import com.cy.model.Course;
import com.cy.model.CourseClass;
import com.cy.model.Marking;
import com.cy.model.Notice;
import com.cy.model.NoticeWithBLOBs;
import com.cy.model.Paper;
import com.cy.model.Survey;
import com.cy.model.University;
import com.cy.model.UniversityCourse;
import com.cy.model.User;
import com.cy.model.UserLogger;
import com.cy.service.CourseClassService;
import com.cy.service.CourseLearnAnalysisService;
import com.cy.service.CourseService;
import com.cy.service.CourseTeacherAnalysisService;
import com.cy.service.NoticeService;
import com.cy.service.PaperService;
import com.cy.service.SurveyService;
import com.cy.service.UniversityCourseService;
import com.cy.service.UniversityService;
import com.cy.service.UserLoggerService;
import com.cy.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * Spring MVC Controler - 表：t_user
 * 
 * @since 2015-06-08 15:47:16
 */
/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/cy")
public class UserAction {
	private static final Logger logger = LoggerFactory
			.getLogger(UserAction.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseClassService courseClassService;
	
	@Autowired
	private CourseLearnAnalysisService courseLearnAnalysisService;
	
	@Autowired
	private UniversityCourseService universityCourseService;
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private CourseTeacherAnalysisService courseTeacherAnalysisService;
	
	@Autowired
	private UniversityService universityService;
	
	@Autowired
	private SurveyService surveyService;
	@Autowired
	private UserLoggerService userLoggerService;
	
	@Autowired
	private PaperService paperService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
	}
	@RequestMapping(value = "/checklogin")
	@ResponseBody
	public Object checklogin(HttpSession session,HttpServletResponse response,
			HttpServletRequest request, User user) throws ServiceException, IOException {
			user.setLoginPassword(MD5.toMd5(user.getLoginPassword()));
			User u = userService.selectUser(user);
			Map<String , Object> map = new HashMap<String, Object>();
			response.setContentType("text/html;charset=UTF-8");
			if (u != null) {
				map.put("msg", "");
			} else {
				map.put("msg", "用户名或者密码错误");
			}
		return map;
	}
	
	/**
	 * 登录页面
	 * @param loginName 
	 * @param loginPassword 
	 * @param messageId 
	 * @throws ServiceException 
	 * @throws IOException 
	 */
	@RequestMapping(value = "/login")
	public String loginPage(Map<String , Object> map,HttpSession session,HttpServletResponse response,
			HttpServletRequest request, User user) throws ServiceException, IOException {
			System.out.println(MD5.toMd5(user.getLoginPassword()));
			String passwordString = user.getLoginPassword();
			user.setLoginPassword(MD5.toMd5(user.getLoginPassword()));
			User u = userService.selectUser(user);
			if (u != null) {
				map.put("user", u);
				session.setAttribute("user", u);
				userService.importOpenFire(u);
				UserLogger log = new UserLogger(u.getUserId(),u.getUniversityId(), new Date(), 0, getIp(request));
				userLoggerService.insert(log);
				//添加Cooike
				addCookie(user.getPhone(),passwordString, response, request);
				// 管理员身份直接跳转到后台管理页面
				if(u.getUserType() == 1 || u.getUserType() == 0){
					return "redirect:/cy/center.action";
				}
				if(u.getUserType() == 2){
					return "jsp/admin/bg_index";
				}
				if(u.getUserType() == 3){
					return "redirect:/university/listAll.action";
				}
				return "redirect:/index.action";
			} else {
				return "redirect:/index.action";
			}
	}
			/*user.setLoginPassword(MD5.toMd5(user.getLoginPassword()));
			user.setUserId("1");
			CommonFunc.login(request, user);
			User loginuser = CommonFunc.getUserLogin(request);
			System.out.println("remoteAddr : " + getIp(request));
			loginuser.hashCode();
		}*/
	public void addCookie(String loginName, String loginPassword,HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {  
	    if(StringUtils.isNotBlank(loginName)&&StringUtils.isNotBlank(loginPassword)){  
	        //创建Cookie  
	        Cookie nameCookie=new Cookie("name",URLEncoder.encode(loginName,"utf-8"));  
	        Cookie pswCookie=new Cookie("psw",loginPassword);  
	          
	        //设置Cookie的父路径  
	        nameCookie.setPath(request.getContextPath()+"/");  
	        pswCookie.setPath(request.getContextPath()+"/");  
	          
	        //获取是否保存Cookie  
	        if( request.getParameter("rememberMe").equals("0")){//不保存Cookie  
	            nameCookie.setMaxAge(0);  
	            pswCookie.setMaxAge(0);  
	        }else{//保存Cookie的时间长度，单位为秒  
	            nameCookie.setMaxAge(7*24*60*60);  
	            pswCookie.setMaxAge(7*24*60*60);  	          
	        }
	        response.addCookie(nameCookie);  
            response.addCookie(pswCookie); 
	    }  
	}  
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}
	
	/**
	 * 列表页面，查看系统通知的页面列表
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/center")
	public String gocenter(Map<String, Object> map,HttpServletResponse response,HttpServletRequest httpServletRequest,String userId,HttpSession session,
			HttpServletRequest request,ModelMap model) throws ServiceException {
		Map<String,String> condition = new HashMap<String,String>();
		condition.put("type", "0");
		int totalwritesurvey=0;
		int Platformsurvey=0;
		condition.put("userId", CommonFunc.getUserLogin(request).getUserId());
		List<Course> courselist =courseService.findmyIncourse(condition);
		for(Course course:courselist){
			course.setNum(courseService.countcourseByCondition(course.getCourseId(),course.getClassId()));
			course.setVideonum(courseService.countcoursevideos(course.getCourseId()));
			CourseClass courseclass = courseClassService.findByPrimaryKey(course.getClassId());
			course.setStudystarttime(courseclass.getCourseStartTime());
			course.setStudyendtime(courseclass.getCourseEndTime());
			if(0 ==CommonFunc.getUserLogin(request).getUserType()){
				course.setExampercent(courseService.getpercent(CommonFunc.getUserLogin(request).getUserId(), course.getCourseId(), course.getClassId()));
			}
		}
		model.put("courselist", courselist);
		if(CommonFunc.getUserLogin(httpServletRequest).getUserType()==0){
		Survey survey=new Survey();
		survey.setUniversityId(CommonFunc.getUserLogin(httpServletRequest).getUniversityId());
		survey.setUserId(CommonFunc.getUserLogin(httpServletRequest).getUserId());
		 totalwritesurvey=surveyService.findtotalwritesurvey(survey);
		//统计平台有几张调查问卷未填写
		 Platformsurvey=surveyService.findsurveyWrite(survey);
		}
		//统计当前学校新闻通知的个数
		List<Notice> noticeAlllist=noticeService.findNoticeAll(CommonFunc.getUserLogin(request).getUniversityId());
		int  noticetotal=noticeAlllist.size();
		session.setAttribute("noticetotal",noticetotal);
		model.addAttribute("noticetotal", noticetotal);
		session.setAttribute("totalwritesurvey",totalwritesurvey+Platformsurvey);
		model.addAttribute("totalwritesurvey", totalwritesurvey+Platformsurvey);
		if(CommonFunc.getUserLogin(httpServletRequest).getUserType()!=2){
			return "jsp/user/personalcenter";
		}else{
			return "jsp/admin/bg_index";
		}
		
	}
	
	/**
	 * 列表页面，查看系统通知的页面列表
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/endcourse")
	public String endcourse(Map<String, Object> map,HttpServletResponse response,String userId,HttpSession session,
			HttpServletRequest request,ModelMap model) throws ServiceException {
		Map<String,String> condition = new HashMap<String,String>();
		condition.put("type", "2");
		condition.put("userId", CommonFunc.getUserLogin(request).getUserId());
		List<Course> courselist =courseService.findmyIncourse(condition);
		for(Course course:courselist){
			course.setNum(courseService.countcourseByCondition(course.getCourseId(),course.getClassId()));
			course.setVideonum(courseService.countcoursevideos(course.getCourseId()));
			CourseClass courseclass = courseClassService.findByPrimaryKey(course.getClassId());
			course.setStudystarttime(courseclass.getCourseStartTime());
			course.setStudyendtime(courseclass.getCourseEndTime());
			if(0 ==CommonFunc.getUserLogin(request).getUserType()){
				course.setExampercent(courseService.getpercent(CommonFunc.getUserLogin(request).getUserId(), course.getCourseId(), course.getClassId()));
			}
		}
		model.put("courselist", courselist);
		return "jsp/user/endcourse";
	}
	/**
	 * 新闻列表--个人中心
	 * @param userId
	 * @param pageIndex 
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/centerNotice")
	public String gocenterNotice(Map<String, Object> map,HttpServletResponse response,String userId,HttpSession session,
			HttpServletRequest request,ModelMap model,NoticeWithBLOBs noticewithblobs,Integer pageIndex,Integer pageIndex1,Integer pageIndex2,Integer pageIndex3) throws ServiceException {
		//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		int  noticetype=0;
		if(("".equals(request.getParameter("noticetype")))||(request.getParameter("noticetype"))==null){
			  noticetype=0;
		  }else{
			  noticetype = Integer.parseInt(request.getParameter("noticetype"));
		}		
		if(pageIndex == null){
			pageIndex = 1;
		}
		 pageIndex1=StringUtil.pageIndexvalue(noticetype,pageIndex,1);
		 pageIndex2=StringUtil.pageIndexvalue(noticetype,pageIndex,2);
		 pageIndex3=StringUtil.pageIndexvalue(noticetype, pageIndex,3);
		PageHelper.startPage(pageIndex3, Constant.pageSize);
		List<Notice> noticeAlllist=noticeService.findNoticeAll(CommonFunc.getUserLogin(request).getUniversityId());
		PageHelper.startPage(pageIndex1, Constant.pageSize);
		List<Notice> noticeCourselist=noticeService.findNoticeCourse(CommonFunc.getUserLogin(request).getUniversityId(),1);
		PageHelper.startPage(pageIndex2, Constant.pageSize);
		List<Notice> noticeSystemlist=noticeService.findcyadminNoticeAll(CommonFunc.getUserLogin(request).getUniversityId(),2);
		PageInfo	pager = new PageInfo(noticeAlllist);
		model.put("page", pager);
		PageInfo pager1 = new PageInfo(noticeCourselist);
		model.put("page1", pager1);
		PageInfo pager2 = new PageInfo(noticeSystemlist);
		model.put("page2", pager2);
//		int  noticetotal=noticeAlllist.size();
//		session.setAttribute("noticetotal",noticetotal);
//		model.put("noticetotal", noticetotal);
		model.put("noticeAlllist", noticeAlllist);
		model.put("noticeCourselist", noticeCourselist);
		model.put("noticeSystemlist", noticeSystemlist);
		model.put("noticetype", noticetype);
		model.put("noticeCourselistsize",noticeCourselist.size());	
		return "jsp/user/personalNoticeson";
	}
	/**
	 * 列表页面，查看系统通知的页面列表
	 * @throws ServiceException 
	 * @throws IOException 
	 */
	@RequestMapping(value = "/noticedetail")
	public String noticedetail(HttpServletResponse response,
			HttpServletRequest request,ModelMap model) throws ServiceException, IOException {
		System.out.println("=============================================================");
		String noticeid=request.getParameter("noticeid");
		List<Notice> findBynoticedetail=noticeService.noticedetail(noticeid);
		System.out.println(findBynoticedetail.size()); 
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(JSONArray.fromObject(findBynoticedetail)) ;
		response.getWriter().flush();
		response.getWriter().close();
			
		return "jsp/user/personalcenter";
	}
	
	/**
	 * 退出登录
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/exit")
		public String exit(HttpSession session,HttpServletRequest request){
		User user = (User) session.getAttribute("user");
		UserLogger log = new UserLogger(user.getUserId(),user.getUniversityId(), new Date(), 1, getIp(request));
		try {
			userLoggerService.insert(log);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
			session.invalidate();
			return "redirect:/index.action";
		}

	/**
	 * 列表页面
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Object listPage() {
		return "user";
	}

	/**
	 * 列表数据
	 */
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// Criteria<User> criteria = Criteria.create(User.class);
			// criteria.buildFromRequest(request);
			// criteria.sortIfEmpty(Sort.asc("userId"));
			// Pager<User> pager = userService.findPage(criteria);
			// resultMap.put("result", 1);
			// resultMap.put("total", pager.getTotalRecords());
			// resultMap.put("rows", pager.getList());
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return resultMap;
	}

	/**
	 * 新增页面
	 */
	@RequestMapping(value = "/useradd", method = RequestMethod.GET)
	public Object addPage() {
		return "user_add";
	}

	/**
	 * 新增保存
	 * 
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/useradd", method = RequestMethod.POST)
	@ResponseBody
	public Object doAdd(User user) throws ServiceException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			userService.insert(user);
			resultMap.put("result", 1);
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return resultMap;
	}

	/**
	 * 修改页面
	 * 
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/useredit", method = RequestMethod.GET)
	public Object editPage(String userId) throws ServiceException {
		ModelAndView model = new ModelAndView();
		try {
			User user = userService.findByPrimaryKey(userId);
			model.addObject("user", user);
			model.setViewName("user_edit");
		} catch (Exception e) {
			model.addObject("exception", e.getMessage());
			model.setViewName("/error");
			logger.error(e.getMessage(), e);
		}
		return model;
	}

	/**
	 * 修改保存
	 * 
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/useredit", method = RequestMethod.POST)
	@ResponseBody
	public Object doEdit(User user) throws ServiceException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			userService.update(user);
			resultMap.put("result", 1);
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return resultMap;
	}

	/**
	 * 删除
	 * 
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/userdelete", method = RequestMethod.POST)
	@ResponseBody
	public Object doDelete(List<String> ids) throws ServiceException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			userService.batchDelete(ids);
			resultMap.put("result", 1);
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	/**
	 * 查询学生列表
	 * 
	 * @throws ServiceException
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/userlist")
	public String getUserList(HttpServletResponse response,
			HttpServletRequest request, Integer pageIndex,ModelMap model,String term,String courseId,String courseName,String searcharea) throws ServiceException, UnsupportedEncodingException {		
		String universityId=CommonFunc.getUserLogin(request).getUniversityId().toString();
		String role=CommonFunc.getUserLogin(request).getUserType().toString();
		if(pageIndex == null){
			pageIndex = 1;
		}
		List<UniversityCourse> termlist =  universityCourseService.searchCourseterm(universityId);
		model.put("termlist", termlist);
		if(StringUtils.isEmpty(term) && StringUtils.isEmpty(courseId) && StringUtils.isEmpty(searcharea)){
			PageHelper.startPage(pageIndex, Constant.pageSize);
			List<User> users = userService.selectUserBySchool(universityId,"0");
			PageInfo pager = new PageInfo(users);
			model.put("page", pager);
			model.put("list",users);
		}else{
			if(!StringUtils.isEmpty(term)){
				List<UniversityCourse> courselist=universityCourseService.searchcourseName(term, universityId);
				model.put("courselist", courselist);
			}
			PageHelper.startPage(pageIndex, Constant.pageSize);
			List<User> users = universityCourseService.searchResult(term,universityId,courseId,"%"+searcharea+"%");
			//List<User> users = userService.selectUserBySchool(universityId,"0",courseId,"%"+searcharea+"%",term);
			PageInfo pager = new PageInfo(users);
			model.put("page", pager);
			model.put("list",users);
			model.put("courseId",courseId);
			model.put("courseName",courseName);		
			model.put("term",term);
			model.put("searcharea",searcharea);
		}
		
		model.put("universityId", universityId);
//		if(courseTerm != null){
//			String term = URLDecoder.decode(courseTerm,"UTF-8");
//			String realname = URLDecoder.decode(realName,"utf-8");
//			//System.out.println("realname = " + realname);
//			PageHelper.startPage(pageIndex, Constant.pageSize);
//			
//			PageInfo pager = new PageInfo(list);
//			model.put("page", pager);
//			model.put("list",list);
//		}else{
//			PageHelper.startPage(pageIndex, Constant.pageSize); 
//			
//			PageInfo pager = new PageInfo(users);
//			model.put("page", pager);
//			model.put("list", users);
//		}
//		//sList<UniversityCourse> list = universityCourseService.findlist();
//		model.put("universityId", universityId);
//		//System.out.println(universityId);
//		
//		model.put("num", userService.selectUserCountBySchool(universityId,"0"));
			
		return "jsp/admin/bg_index_user_list";
	
	}
	
	@RequestMapping("/getCourseList")
	public void getCourseList(HttpServletResponse response,String universityId,String courseTerm) throws IOException {
		List<UniversityCourse> courselist=universityCourseService.searchcourseName(courseTerm, universityId);
		JSONArray jsonArray = JSONArray.fromObject(courselist);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jsonArray) ;
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	/*@RequestMapping("/dosearch")
	public String getSearch(HttpServletRequest request,HttpServletResponse response,ModelMap model,String universityId,String courseTerm,String courseId) throws UnsupportedEncodingException, ServiceException{
		//System.out.println(courseTerm);
		String term = URLDecoder.decode(courseTerm,"UTF-8");
		//System.out.println(term);		
		List<User> list = universityCourseService.findSearch(term,universityId,courseId);	
		model.put("list",list);
		//System.out.println(list);
		//return getUserList(response,request,null,model);
		return "jsp/admin/bg_index_user_list";
	}
*/		
/*	@RequestMapping("/doupdate")
	public String getupdate(HttpServletResponse response,HttpServletRequest request, Integer pageIndex,ModelMap model) throws ServiceException{
		//return "jsp/admin/bg_index_user_list";
		//List<Map<String , Object>> listMaps = universityCourseService.update();
		return null;
	}
	
	@RequestMapping("/dodelete")
	public String getdelete(HttpServletResponse response,HttpServletRequest request, 
			Integer pageIndex,ModelMap model,String universityId,String courseTerm,String courseId) throws ServiceException{
		universityCourseService.deletemsg(courseTerm,universityId,courseId);
		return null;
	}*/
	
	@RequestMapping(value = "/teacherlist")
	public String getTeacherList(HttpServletResponse response,String courseId,String courseName,String searcharea,
			HttpServletRequest request, Integer pageIndex,ModelMap model) throws ServiceException {
		
		String universityId=CommonFunc.getUserLogin(request).getUniversityId().toString();
		//String role=CommonFunc.getUserLogin(request).getUserType().toString();
		
		if(pageIndex == null){
			pageIndex = 1;
		}
		List<UniversityCourse> tcourselist = universityCourseService.searchTeachercoursename(universityId);
		model.put("tcourselist", tcourselist);
		if(StringUtils.isEmpty(courseId) && StringUtils.isEmpty(searcharea)){
			PageHelper.startPage(pageIndex, Constant.pageSize);
			List<User> users = userService.selectTeacherBySchool(universityId,"1",null,null,null);
			PageInfo pager = new PageInfo(users);
			model.put("page", pager);
			//model.put("num", userService.selectUserCountBySchool(universityId,"0"));
			model.put("list", users);
		}else{
			PageHelper.startPage(pageIndex, Constant.pageSize);
			List<User> users = universityCourseService.teacherSearch(universityId,courseId,"%"+searcharea+"%");
			//List<User> users = userService.selectTeacherBySchool(universityId,"1",courseId,"%"+searcharea+"%",null);
			PageInfo pager = new PageInfo(users);
			model.put("page", pager);
			model.put("list",users);
			model.put("courseId",courseId);
			model.put("courseName",courseName);
			model.put("searcharea",searcharea);
		}
		model.put("universityId", universityId);		
		return "jsp/admin/bg_index_teacher_list";
	
	}
	
	@RequestMapping(value="/teacherInsert")
	@ResponseBody
	public Object getteacherInsert(ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession session,String realName,String sex,String phone) throws IOException, ServiceException{	
		Map<String, Object> map = new HashMap<String, Object>();
		User u = userService.selectByphone(phone);
		User user = new User();
		if( u == null){
			String universityId = CommonFunc.getUserLogin(request).getUniversityId();
			user.setUserId(MD5.toMd5(UID.nextUid()));
			user.setLoginName(phone);
			user.setPhone(phone);
			user.setRealName(realName);
			user.setSex(sex);
			user.setLoginPassword("e10adc3949ba59abbe56e057f20f883e");
			user.setUserType(new Integer(1));
			user.setUniversityId(universityId);
			System.out.println(user);
			int res = userService.insertTeacher(user);
			map.put("result", res == 1 ? 1:0);
			
		}else{
			map.put("result", 2);
		}
		return map;
	}
	
	@RequestMapping(value = "/findpwd")
	public void sendpwd(Map<String , Object> map,HttpSession session,HttpServletResponse response,
			HttpServletRequest request,User user,String phone) throws ServiceException, IOException {
				JSONObject json = new JSONObject();
				String code="";
				for(int i=0;i<6;i++){
					code+=String.valueOf((int)(Math.random()*10));
				}
					String strContent = HttpSend.paraTo16("尊敬的用户:欢迎使用易启学。您修改密码的短信验证码为:"+code+",请及时填写，如非本人操作，请不要理会。【易启学】");
					//验证码放入SESSION
					request.getSession().setAttribute(phone, code);
					String strSmsParam = "reg=" + Constant.strReg + "&pwd=" + Constant.strPwd + "&sourceadd=" + Constant.strSourceAdd + "&phone=" + phone + "&content=" + strContent;
					String strRes = HttpSend.postSend(Constant.strSmsUrl, strSmsParam);
					response.setContentType("text/html;charset=UTF-8");
					response.getWriter().print(json);
					response.getWriter().flush();
					response.getWriter().close();
			}
	
	@RequestMapping(value = "/checkpwdcode")
	public void checkpwdcode(Map<String , Object> map,HttpSession session,HttpServletResponse response,
			HttpServletRequest request,String phone,String code) throws ServiceException, IOException {
			JSONObject json = new JSONObject();
			String type = "";
			List<User> users = userService.findbyPhone(phone);
			if(users==null || users.size()<1){
				type = "error";
				json.put("msg", "该账号不存在");
			}else if(code.equals(request.getSession().getAttribute(phone))){
				type = "success";
			}else{
				type = "error";
				json.put("msg", "验证码失效或者错误");
			}
			json.put("type", type);
			json.put("phone", phone);
			json.put("users",users);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(json.toString());
			response.getWriter().flush();
			response.getWriter().close();
	}
	
	@RequestMapping(value = "/updatepwd")
	public void updatepwd(Map<String , Object> map,HttpSession session,HttpServletResponse response,
			HttpServletRequest request,User user) throws ServiceException, IOException {
			JSONObject json = new JSONObject();
			user.setLoginPassword(MD5.toMd5(user.getLoginPassword()));
			userService.updatebyPhone(user);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(json.toString());
			response.getWriter().flush();
			response.getWriter().close();
	}
		
	@RequestMapping(value = "/sendmes")
	public void sendmes(Map<String , Object> map,HttpSession session,HttpServletResponse response,
			HttpServletRequest request,String phone) throws ServiceException, IOException {
			JSONObject json = new JSONObject();
			String code="";
			for(int i=0;i<6;i++){
				code+=String.valueOf((int)(Math.random()*10));
			}
			String strContent = HttpSend.paraTo16("尊敬的用户:欢迎使用易启学。您的短信验证码为:"+code+",请及时填写，如非本人操作，请不要理会。【易启学】");
			//验证码放入SESSION
			request.getSession().setAttribute(phone, code);
			String strSmsParam = "reg=" + Constant.strReg + "&pwd=" + Constant.strPwd + "&sourceadd=" + Constant.strSourceAdd + "&phone=" + phone + "&content=" + strContent;
			String strRes = HttpSend.postSend(Constant.strSmsUrl, strSmsParam);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(json);
			response.getWriter().flush();
			response.getWriter().close();
	}
	@RequestMapping(value = "/checkcode")
	public void checkcode(Map<String , Object> map,HttpSession session,HttpServletResponse response,
			HttpServletRequest request,String phone,String code) throws ServiceException, IOException {
			JSONObject json = new JSONObject();
			String type = "";
			List<User> users = userService.findbyPhone(phone);
			if(users!=null && users.size()>0){
				type = "error";
				json.put("msg", "该账号已注册");
			}else if(code.equals(request.getSession().getAttribute(phone))){
				type = "success";
			}else{
				type = "error";
				json.put("msg", "验证码失效或者错误");
			}
			json.put("type", type);
			json.put("phone", phone);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(json.toString());
			response.getWriter().flush();
			response.getWriter().close();
	}
	
	@RequestMapping(value = "/checkschoolId")
	public void checkschoolId(Map<String , Object> map,HttpSession session,HttpServletResponse response,
			HttpServletRequest request,User user) throws ServiceException, IOException {
			JSONObject json = new JSONObject();
			String type = "";
			List<User> users = userService.findBySchoolCode(user);
			if(users == null || users.size() < 1){
				type = "error";
				json.put("msg", "不存在该学号信息");
			}else if(users.get(0).getPhone() != null && !"".equals(users.get(0).getPhone())){
				type = "error";
				json.put("msg", "该学号已绑定账号");
			}else{
				type = "success";
			}
			json.put("type", type);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(json.toString());
			response.getWriter().flush();
			response.getWriter().close();
	}
	
	@RequestMapping(value = "/bindschool")
	public String bindschool(HttpServletResponse response,HttpServletRequest request,
			String phone,String login_password,ModelMap model) throws ServiceException {
		List<University> universitys = universityService.selectAll();
		model.put("universitys",universitys);
		model.put("phone",phone);
		model.put("login_password",login_password);
		return "jsp/user/bindschool";
	}
	
	@RequestMapping(value = "/addcount")
	public String addcount(HttpServletResponse response,HttpServletRequest request,
			User user,String resuniversityId,ModelMap model) throws ServiceException {
		user.setUniversityId(resuniversityId);
		List<User> users = userService.findBySchoolCode(user);
		User insertuser = users.get(0);
		String openId = (String) request.getSession().getAttribute("demo_openid");
		if(openId != null && !"".equals(openId)){
			insertuser.setUserQQ(openId);
		}else{
			insertuser.setLoginPassword(MD5.toMd5(user.getLoginPassword()));
			insertuser.setPhone(user.getPhone());
		}
		userService.update(insertuser);
		CommonFunc.login(request, insertuser);
		return "redirect:/index.action";
	}
	
	@RequestMapping(value = "/teacherMsg")
	public void sendTeachermsg(Map<String , Object> map,HttpSession session,HttpServletResponse response,
			HttpServletRequest request,String phone) throws ServiceException, IOException {
			JSONObject json = new JSONObject();
			request.getSession().setAttribute(phone, request);
			String strContent = HttpSend.paraTo16("尊敬的用户:欢迎使用易启学。您的登录名是:"+phone+",密码为123456，如非本人操作，请不要理会。【易启学】");
			String strSmsParam = "reg=" + Constant.strReg + "&pwd=" + Constant.strPwd + "&sourceadd=" + Constant.strSourceAdd + "&phone=" + phone + "&content=" + strContent;
			String strRes = HttpSend.postSend(Constant.strSmsUrl, strSmsParam);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(json);
			response.getWriter().flush();
			response.getWriter().close();
	}
	/* @Description: 在线考试列表
	 * @title：onlineExamlist
	 * @author pear
	 * @param httpServletRequest
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/onlineExamlist")
	public String onlineExamlist(Model model,HttpServletRequest request,HttpServletRequest httpServletRequest, HttpSession session,
			HttpServletResponse response)throws ServiceException,IOException{
	    String  userId=CommonFunc.getUserLogin(httpServletRequest).getUserId();
	    List<UniversityCourse> courselist=universityCourseService.examlist(userId);
       model.addAttribute("courselist", courselist);
		return "jsp/user/personalcenterexam";
	}
}