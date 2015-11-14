package com.cy.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cy.common.util.CommonFunc;
import com.cy.common.util.Constant;
import com.cy.common.util.LetvCloudV1;
import com.cy.common.util.MD5;
import com.cy.common.util.StringUtil;
import com.cy.common.util.UID;
import com.cy.exception.ServiceException;
import com.cy.model.Course;
import com.cy.model.CourseChapter;
import com.cy.model.CourseClass;
import com.cy.model.CourseComment;
import com.cy.model.CourseCount;
import com.cy.model.CourseLearnAnalysis;
import com.cy.model.CourseNote;
import com.cy.model.MyCourseQuiz;
import com.cy.model.Paper;
import com.cy.model.Question;
import com.cy.model.Survey;
import com.cy.model.University;
import com.cy.model.User;
import com.cy.model.Video;
import com.cy.service.CourseChapterService;
import com.cy.service.CourseCommentService;
import com.cy.service.CourseLearnAnalysisService;
import com.cy.service.CourseService;
import com.cy.service.CourseTeacherAnalysisService;
import com.cy.service.MyCourseQuizService;
import com.cy.service.PaperService;
import com.cy.service.QuestionService;
import com.cy.service.SurveyService;
import com.cy.service.TestService;
import com.cy.service.UniversityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * Spring MVC Controler - 表：t_course
 * @since 2015-06-08 15:47:16
 */
@Controller
@RequestMapping(value = "/course")
public class CourseAction {
	private static final Logger logger = LoggerFactory.getLogger(CourseAction.class);

	@Autowired
	private CourseService courseService;
	@Autowired
	private TestService testService;
	@Autowired
	private CourseCommentService courseCommentService;
	@Autowired
	private CourseLearnAnalysisService courseLearnAnalysisService;
	@Autowired
	private CourseTeacherAnalysisService courseTeacherAnalysisService;
	@Autowired
	private CourseChapterService courseChapterService;
	@Autowired
	private MyCourseQuizService myCourseQuizService;
	@Autowired
	private UniversityService universityService;
	@Autowired
	private SurveyService surveyService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private PaperService paperService;
	
	public String getdirectory(){
		String os = System.getProperty("os.name");  
		if(os.toLowerCase().startsWith("win")){  
			return "E:/temp/";
		}else{
			return "/usr/local/temp/";
		}
	}
	
	/**
	 * 列表页面
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/list")
	public void courselist(HttpServletResponse response,
			HttpServletRequest request, String type,ModelMap model) throws ServiceException, IOException {
		Map<String,String> condition = new HashMap<String,String>();
		condition.put("type", type);
		condition.put("userId", CommonFunc.getUserLogin(request).getUserId());
		List<Course> courselist =courseService.findmyIncourse(condition);
		for(Course course:courselist){
			course.setNum(courseService.countcourseByCondition(course.getCourseId(),course.getClassId()));
			if(CommonFunc.getUserLogin(request).getUserType() == 0){
				course.setPercent(courseLearnAnalysisService.calPersonalLearningRate(course.getCourseId(),course.getClassId(),CommonFunc.getUserLogin(request).getUserId()));
			}
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(JSONArray.fromObject(courselist)) ;
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * 列表页面
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/detail")
	public String coursedetail(HttpServletResponse response,
			HttpServletRequest request,String courseId,ModelMap model) throws ServiceException {
		Course course = courseService.findByPrimaryKey(courseId);
		List<CourseChapter> coursechapter = courseChapterService.findByCourseId(courseId);
		if(coursechapter != null && coursechapter.size() > 1){
			model.put("chapterlength", coursechapter.size()-1);
		}else{
			model.put("chapterlength", 0);
		}
		model.put("num", courseCommentService.countwidComment(courseId));
		model.put("coursechapter", coursechapter);
		model.put("course", course);
		return "jsp/course/coursedetail";
	}
	
	
	/**
	 * 评论
	 * @throws ServiceException 
	 * @throws IOException 
	 */
	@RequestMapping(value = "/commentAdd")
	public void pinglunInsert(HttpServletResponse response,HttpSession session,
			HttpServletRequest request,ModelMap model,String courseId,String commentContent) throws ServiceException, IOException {
		CourseComment courseComment =new CourseComment();
		courseComment.setCommentContent(commentContent);
		courseComment.setCommentId(MD5.toMd5(UID.nextUid()));
		courseComment.setCourseId(courseId);
		courseComment.setUserId(CommonFunc.getUserLogin(request).getUserId());
		Date day=new Date(); 
		courseComment.setCommentTime(day);
		courseCommentService.insert(courseComment);
        int countcomment=courseCommentService.countwidComment(courseId);
		response.getWriter().print(countcomment) ;
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	/**
	 * 评论
	 * @throws ServiceException 
	 * @throws IOException 
	 */
	@RequestMapping(value = "/commentList")
	public void commentList(HttpServletResponse response,HttpSession session,
			HttpServletRequest request,ModelMap model,String pageIndex,String courseId,String commentContent) throws ServiceException, IOException {
		PageHelper.startPage(Integer.valueOf(pageIndex)+1,5);
		List<Map<String, Object>> listcomment=courseCommentService.getwidList(courseId);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(JSONArray.fromObject(listcomment)) ;
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * 列表页面
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/allcourse")
	public String allcourse(HttpServletResponse response,
			HttpServletRequest request,ModelMap model) throws ServiceException {
		List<Course> courses = courseService.findAllCourse();
		return "jsp/course/coursedetail";
	}
	
	/**
	 * 不同分类的列表页面
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/learn")
	public String learn(HttpServletResponse response,
			HttpServletRequest request,String courseId,String classId,ModelMap model) throws ServiceException {
		model.put("classId", classId);
		//课程详情
		Course course = courseService.findByPrimaryKey(courseId);
		course.setNum(courseService.countcourseByCondition(courseId,classId));
		model.put("course", course);
		
		//用户身份
		int userType = CommonFunc.getUserLogin(request).getUserType();
		String userId = CommonFunc.getUserLogin(request).getUserId();
		String universityId = CommonFunc.getUserLogin(request).getUniversityId();
		if(userType == 0){
			// 学生课程成绩分析
				model.put("courseScoreResult", testService.getScoreByCourseAndUser(courseId,classId,userId,universityId));
			courseLearnAnalysisService.updatePersonalJob(classId, courseId, userId);
			model.put("analysis", courseLearnAnalysisService.getLastAnalysisByCourseAndUser(classId, userId));
			model.put("learningrateRank", courseLearnAnalysisService.getRankLearningInClass(classId, userId));
			model.put("taskRank", courseLearnAnalysisService.getRankTaskInClass(classId, userId));
			model.put("commentRank", courseLearnAnalysisService.getRankCommentInClass(classId, userId));
			model.put("quizRank", courseLearnAnalysisService.getRankQuizInClass(classId, userId));
		}else{
			//老师负责课程的学生的学情分析
			courseTeacherAnalysisService.updateClassJob(classId, courseId, userId);
			model.put("analysis", courseTeacherAnalysisService.getCurrentDay(classId));
		}
		//章节列表
		List<CourseChapter> coursechapter = courseChapterService.findByCourseId(courseId);
		//教学计划
		Map<String,String> condition = new HashMap<String,String>();
		condition.put("userId", CommonFunc.getUserLogin(request).getUserId());
		condition.put("courseId", courseId);
		CourseClass courseclass =courseChapterService.findPlan(condition);
				
		Calendar cal = Calendar.getInstance();			
		if(coursechapter != null && coursechapter.size() > 1){
			if(courseclass != null && courseclass.getCourseStartTime() != null){
				cal.setTime(courseclass.getCourseStartTime());
				for(CourseChapter chapter : coursechapter){
					cal.add(Calendar.DAY_OF_MONTH, chapter.getPlan());
					chapter.setUpdateTime(cal.getTime());
				}
			}
		}else{
			model.put("chapterlength", 0);
		}
		model.put("chapterlength", coursechapter.size());
		model.put("coursechapter", coursechapter);
		model.put("courseclass", courseclass);
		
		
		//课程提问列表页面
		List<MyCourseQuiz> courseQuiz =null;
		List<MyCourseQuiz> discourseQuiz = new ArrayList<MyCourseQuiz>();
		if(userType == 0)
		{
			courseQuiz = myCourseQuizService.findMyCourseQuiz(CommonFunc.getUserLogin(request).getUserId(),courseId,classId);
		}
		else
		{
			courseQuiz = myCourseQuizService.findMyCourseQuizTeacher(CommonFunc.getUserLogin(request).getUserId(),courseId,classId);
		}
		if(!courseQuiz.isEmpty() && courseQuiz.size() >= 2){
			discourseQuiz.add(courseQuiz.get(0));
			discourseQuiz.add(courseQuiz.get(1));
		}else if(!courseQuiz.isEmpty() && courseQuiz.size() < 2){
			for(MyCourseQuiz mycourseQuiz : courseQuiz){
				discourseQuiz.add(mycourseQuiz);
			}}
		model.put("discourseQuiz", discourseQuiz);
		model.put("courseQuiz", courseQuiz);
		
		
		//课程笔记
		if(userType == Constant.studentrole){
		List<CourseNote> notes = courseService.findMyNotes(CommonFunc.getUserLogin(request).getUserId(), courseId);
		List<CourseNote> disnotes = new ArrayList<CourseNote>();
		Survey survey=new Survey();
		survey.setUniversityId(CommonFunc.getUserLogin(request).getUniversityId());
		survey.setCourseId(courseId);
		survey.setUserId(CommonFunc.getUserLogin(request).getUserId());
		int countsurvey=surveyService.findtotalwritesurvey(survey);
		if(!notes.isEmpty() && notes.size() >=2){
			disnotes.add(notes.get(0));
			disnotes.add(notes.get(1));
		}else if(!notes.isEmpty() && notes.size() < 2){
			for(CourseNote note : notes){
				disnotes.add(note);
			}
		}
		model.put("disnotes", disnotes);
		model.put("notes", notes);
		model.put("countsurvey", countsurvey);
		System.out.println(model);
		return "jsp/course/courselearn";
		}else{
			//老師頁面
			return "jsp/course/courseteacher";
		}
		
	}
	
	/**
	 * 不同分类的列表页面
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/coursetype")
	public String allcourse(HttpServletResponse response,
			HttpServletRequest request,String type,ModelMap model) throws ServiceException {
		List<Course> courses = courseService.findCourseByType(type);
		Course coursetype = courseService.findCourseTypeById(type);
		model.put("courses", courses);
		model.put("typeName", coursetype.getCourseTypeName());
		return "jsp/course/courseindex";
	}
	
	/**
	 * 新增保存
	 * 
	 * @throws ServiceException
	 * @throws Exception 
	 * @throws ZipException 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String doAdd(MultipartFile file, String desc,Model model,
			HttpServletRequest request) throws ServiceException, Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String tempfolder = String.valueOf(new Date().getTime());
		int courseflag=0;
		File folder = new File(getdirectory()+tempfolder+File.separator);
		if(!folder.exists()){
			folder.mkdir();
		}
		File temp = new File(getdirectory()+tempfolder+File.separator+file.getOriginalFilename());
		file.transferTo(temp);
		ZipFile zipFile = new ZipFile(getdirectory()+tempfolder+File.separator+file.getOriginalFilename(),"GB2312"); 
		Enumeration<ZipEntry> enu = (Enumeration<ZipEntry>) zipFile  
                 .getEntries();  
         while (enu.hasMoreElements()) {  
             ZipEntry zipElement = (ZipEntry) enu.nextElement();  
             InputStream read = zipFile.getInputStream(zipElement);
             String fileName = zipElement.getName();
             System.err.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
             System.err.println(fileName);
             if (fileName != null && fileName.indexOf(".") != -1) {// 是否为文件  
                 unZipFile(zipElement, read, getdirectory()+tempfolder+File.separator);  
             }
             read.close();
         }
         zipFile.close();
       String courseName=file.getOriginalFilename().split("\\.")[0].toString();
        int flag=courseService.courseSelect(courseName);
        //flag--0不存在该课程 flag--1存在该课程
        if(flag==0){
       	 courseService.insertCourses(getdirectory()+tempfolder+File.separator,file.getOriginalFilename(),CommonFunc.getUserLogin(request).getUserId(),request);
         deleteFile(getdirectory()+tempfolder+File.separator); 
        }else{
        	courseflag=1;
        }
         //导入课程
         model.addAttribute("courseflag", courseflag);
         return "redirect:/course/courseList.action";
	}
	/**
	 * 课程列表页面
	 * @throws ServiceException 
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value = "/courseList")
	public String courseAllList(HttpServletResponse response,String courseflag,
			HttpServletRequest request,ModelMap model,Integer pageIndex) throws ServiceException, NumberFormatException, Exception {
		if(pageIndex == null)
		{
			pageIndex = 1;
		}
	    PageHelper.startPage(pageIndex, Constant.pageSize);
	    //课程列表
	  	List<Course>courselist=courseService.findAllCourse();
	    PageInfo pager = new PageInfo(courselist);
	    model.put("page", pager);
		model.addAttribute("courselist", courselist);
		model.addAttribute("courseflag", courseflag);
		return "jsp/system/admin_addcourse";
	}
	
	/**
	 * 列表页面
	 * @throws ServiceException 
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value = "/video")
	public String video(HttpServletResponse response,
			HttpServletRequest request,String courseId,String chapterId,String classId,Integer pageIndex,ModelMap model) throws ServiceException, NumberFormatException, Exception {
		User user = CommonFunc.getUserLogin(request);
		if(user != null && user.getUserId() != null){
			Course course = courseService.findByPrimaryKey(courseId);
			course.setCourseTypeName(courseService.findCourseTypeById(course.getCourseType()).getCourseTypeName());
			//播放列表信息
			List<CourseChapter> coursechapter = courseChapterService.findByCourseId(courseId);
			List<CourseChapter> coursechapters = new ArrayList<CourseChapter>();
			for(CourseChapter chapter : coursechapter){
				if(!StringUtils.isEmpty(chapter.getChapterVideoFile())){
					coursechapters.add(chapter);
				}
			}
			model.put("coursechapter", coursechapters);
			//按chapterId来获取播放信息
			if(StringUtils.isEmpty(chapterId)){
				Video video = new Video();
				video.setCourseId(courseId);
				video.setUserId(user.getUserId());;
				Video temp = courseService.findLatestVideoHistory(video);
				if(temp!=null){
					//播放URL从历史记录取值
					model.put("vv", geturl(temp.getVideoId()));
					model.put("videoId",temp.getVideoId());
					model.put("chapterId",temp.getChapterId());
					chapterId=temp.getChapterId();
					int quizcount=myCourseQuizService.getquizcount(chapterId);
					model.put("num", quizcount);
					System.out.println("=================================================================:"+quizcount+chapterId);
				}else{
					//播放URL从第一个视频取值
					model.put("vv", geturl(coursechapter.get(0).getChapterVideoFile()));
					model.put("videoId",coursechapter.get(0).getChapterVideoFile());
					model.put("chapterId",coursechapter.get(0).getChapterId());
					chapterId=coursechapter.get(0).getChapterId();
					int quizcount=myCourseQuizService.getquizcount(chapterId);
					model.put("num", quizcount);
					System.out.println("=================================================================:"+quizcount+chapterId);
				}
			}else{
				//按章节ID来播放
				CourseChapter thechapter = courseChapterService.findByPrimaryKey(chapterId);
				model.put("vv", geturl(thechapter.getChapterVideoFile()));
				model.put("videoId",thechapter.getChapterVideoFile());
				model.put("chapterId",thechapter.getChapterId());
			}
			//提问列表
			if(pageIndex == null){
				pageIndex = 1;
			}
			//视频弹框
			Paper paper = paperService.findpaperBycourseId(courseId);
			ObjectMapper mapper = new ObjectMapper();  
	        JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Question.class);   
			if(paper.getContent() != null && !paper.getContent().equals("")){
				long jackStart = System.currentTimeMillis();
				@SuppressWarnings("unchecked")
				List<Question> questionList =  (List<Question>)mapper.readValue(paper.getContent(), javaType);
				logger.debug("Json2Obj=============>>"+(System.currentTimeMillis() - jackStart)+"ms");
				model.addAttribute("questionList",questionList);
			}
			//笔记
			List<CourseNote> notes = courseService.findMyNotes(CommonFunc.getUserLogin(request).getUserId(), courseId);
			model.put("notes", notes);
			PageHelper.startPage(pageIndex, Constant.pageSize);
			//List<Map<String, Object>> listcomment = myCourseQuizService.getquList(CommonFunc.getUserLogin(request).getUserId(),chapterId);
			List<Map<String, Object>> listcomment = myCourseQuizService.getquwidList(chapterId);
			PageInfo pager = new PageInfo(listcomment);
			model.put("classId", classId);
			model.put("page", pager);
			model.put("list", listcomment);
	        model.put("startTime", new Date());
			model.put("course", course);
			model.put("uu", Constant.USER_UNIQUE);
			return "jsp/course/video";
		}else{
			return "redirect:/index.action";
		}
	}
	
	/**
	 * 列表页面
	 * @throws ServiceException 
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value = "/addnote")
	public void addnote(HttpServletResponse response,
			HttpServletRequest request,CourseNote courseNote,ModelMap model) throws ServiceException, NumberFormatException, Exception {
		courseNote.setNoteTime(new Date());
		courseNote.setNoteId(MD5.toMd5(UID.nextUid()));
		courseService.addnote(courseNote);
		response.setContentType("text/html;charset=UTF-8");
		courseNote.setReserve(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(courseNote.getNoteTime()));
		response.getWriter().print(JSONObject.fromObject(courseNote)) ;
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	/**
	 * 记录播放时间
	 * @throws ServiceException 
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value = "/endplay")
	public void endplay(HttpServletResponse response,
			HttpServletRequest request,Video video,String beginTime,ModelMap model) throws ServiceException, NumberFormatException, Exception {
		video.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:s:ss").parse(beginTime));
		video.setEndTime(new Date());
		video.setViewTime(video.getEndTime().getTime()-video.getStartTime().getTime());
		video.setId(MD5.toMd5(UID.nextUid()));
		courseService.insertVideoHistory(video);
	}
	
	
	/**
	 * 列表页面
	 */
	@RequestMapping(value = "/course", method=RequestMethod.GET)
	public Object listPage() {
		return "course";
	}

	/**
	 * 列表数据
	 */
	@RequestMapping(value = "/course", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
//			Criteria<Course> criteria = Criteria.create(Course.class);
//			criteria.buildFromRequest(request);
//			criteria.sortIfEmpty(Sort.asc("courseId"));
//			Pager<Course> pager = courseService.findPage(criteria);
//			resultMap.put("result", 1);
//			resultMap.put("total", pager.getTotalRecords());
//			resultMap.put("rows", pager.getList());
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
	@RequestMapping(value = "/courseadd", method=RequestMethod.GET)
	public Object addPage() {
		return "course_add";
	}

	/**
	 * 新增保存
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/courseadd", method=RequestMethod.POST)
	@ResponseBody
	public Object doAdd( Course course) throws ServiceException {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			courseService.insert(course);
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
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/courseedit", method=RequestMethod.GET)
	public Object editPage(String courseId) throws ServiceException {
		ModelAndView model = new ModelAndView();
		try {
			Course course = courseService.findByPrimaryKey(courseId);
			model.addObject("course", course);
			model.setViewName("course_edit");
		} catch (Exception e) {
			model.addObject("exception", e.getMessage());
			model.setViewName("/error");
			logger.error(e.getMessage(), e);
		}
		return model;
	}

	/**
	 * 修改保存
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/courseedit", method=RequestMethod.POST)
	@ResponseBody
	public Object doEdit(Course course) throws ServiceException {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			courseService.update(course);
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
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/coursedelete", method=RequestMethod.POST)
	@ResponseBody
	public Object doDelete(List<String> ids) throws ServiceException {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			courseService.batchDelete(ids);
			resultMap.put("result", 1);
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	 /** 
     *  
     * @Description: TODO(找到文件并读取解压到指定目录) 
     * @param 设定文件 
     * @return void 返回类型 
     * @throws 
     */  
    public void unZipFile(ZipEntry ze, InputStream read,  
            String saveRootDirectory) throws FileNotFoundException, IOException {  
        // 如果只读取图片，自行判断就OK.  
        String fileName = ze.getName();
        System.err.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.err.println(fileName);
        // 判断文件是否符合要求或者是指定的某一类型  
//      if (fileName.equals("WebRoot/WEB-INF/web.xml")) {  
            // 指定要解压出来的文件格式（这些格式可抽取放置在集合或String数组通过参数传递进来，方法更通用）  
            File file = new File(saveRootDirectory + fileName);  
            if (!file.exists()) {  
                File rootDirectoryFile = new File(file.getParent());  
                // 创建目录  
                if (!rootDirectoryFile.exists()) {  
                    boolean ifSuccess = rootDirectoryFile.mkdirs();  
                    if (ifSuccess) {  
                        System.out.println("文件夹创建成功!");  
                    } else {  
                        System.out.println("文件创建失败!");  
                    }  
                }  
                // 创建文件  
                try {  
                    file.createNewFile();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
            // 写入文件  
            BufferedOutputStream write = new BufferedOutputStream(  
                    new FileOutputStream(file));  
            int cha = 0;  
            while ((cha = read.read()) != -1) {  
                write.write(cha);  
            }  
            // 要注意IO流关闭的先后顺序  
            write.flush();  
            write.close();  
            read.close();  
            // }  
//      }  
    }
    
    void deleteFile(String filepath){
    	File file = new File(filepath);
    	for(File temp : file.listFiles()){
    		temp.delete();
    	}
    	file.delete();
    }
    
    public String geturl(String videoId) throws NumberFormatException, Exception{
    	//乐视客户端代码
		LetvCloudV1 client = new LetvCloudV1(Constant.USER_UNIQUE,Constant.SECRET_KEY);
        String response4 = client.videoGet(Integer.parseInt(videoId));
        JSONObject json = JSONObject.fromObject(response4);
        json = JSONObject.fromObject(json.get("data"));
        return (String) json.get("video_unique");
       // return client.videoGetPlayinterface(Constant.USER_UNIQUE,unique,"html", "", -1, 880, 650);
    }
    
    /**
	 * 列表页面
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/countcourse")
	public void countcourse(HttpServletResponse response,
			HttpServletRequest request,ModelMap model) throws ServiceException, IOException {
		JSONObject json = new JSONObject();
		String universityId = CommonFunc.getUserLogin(request).getUniversityId();
		University university = universityService.findByPrimaryKey(universityId);
		json.put("universityname", university.getUniversityName());
		String userId = CommonFunc.getUserLogin(request).getUserId();
		CourseCount courseCount = courseService.countcourse(userId);
		json.put("courseCount", courseCount);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(json) ;
		response.getWriter().flush();
		response.getWriter().close();
	}
    
}