package com.cy.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cy.common.util.CommonFunc;
import com.cy.common.util.Constant;
import com.cy.common.util.ExcelUtil;
import com.cy.common.util.MD5;
import com.cy.common.util.UID;
import com.cy.exception.ServiceException;
import com.cy.model.Marking;
import com.cy.model.Paper;
import com.cy.model.Question;
import com.cy.model.Test;
import com.cy.model.TestResult;
import com.cy.service.MyCourseService;
import com.cy.service.PaperService;
import com.cy.service.TestService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lowagie.text.pdf.codec.Base64.InputStream;

/**
 * 
* @ClassName: TestAction
* @Description: 作业、测试相关
* @author James Yang  
* @date 2015年7月20日 上午9:40:10
*
 */
@Controller
@RequestMapping(value = "/test")
public class TestAction {
	private static final Logger logger = Logger.getLogger(TestAction.class);


	@Autowired
	private TestService testService;
	
	@Autowired
	private PaperService paperService;
	
	@Autowired
	private MyCourseService myCourseService;
	
	/**
	 * 
	* @Title: markingList 
	* @Description: 老师查询某班级，某次考试的批阅列表
	* @param model
	* @param classId
	* @param httpServletRequest
	* @param response
	* @throws ServiceException
	* @throws IOException
	 */
	@RequestMapping(value = "/markingList")
	public String markingList(Model model,@ModelAttribute("classId") String classId,Integer pageIndex,
			@ModelAttribute("paperId") String paperId,
			HttpServletRequest httpServletRequest,HttpServletResponse response) throws ServiceException, IOException {
		 if(pageIndex == null){
				pageIndex = 1;
			}
		PageHelper.startPage(pageIndex, Constant.pageSize);
		List<Marking> markingList = testService.getMarkingList(classId,paperId);
		 PageInfo pager = new PageInfo(markingList);
		 model.addAttribute("page", pager);
		model.addAttribute("markingList", markingList);
		model.addAttribute("classId",classId);
		model.addAttribute("paperId",paperId);
		return "jsp/test/markingList";
	}
	/**
	 * 
	* @Title: listForStu 
	* @Description: 学生查看单个课程的试卷、作业列表，通过Ajax方式获取
	* @param model
	* @param courseId
	* @param classId
	* @param httpServletRequest
	* @param response
	* @throws ServiceException
	* @throws IOException
	 */
	@RequestMapping(value = "/listForStu", method = RequestMethod.GET)
	public void listForStu(Model model,@ModelAttribute("courseId") String courseId, @ModelAttribute("classId") String classId, 
			HttpServletRequest httpServletRequest,HttpServletResponse response) throws ServiceException, IOException {
		List<Test> testList = testService.getTestList(courseId,classId,CommonFunc.getUserLogin(httpServletRequest).getUserId());
		response.setCharacterEncoding("utf-8");
		// 对象转Json传递到前台
        ObjectMapper mapper = new ObjectMapper();  
        ObjectWriter writer = mapper.viewWriter(Test.class);  
        String jsonObj = writer.writeValueAsString(testList);  
		response.getWriter().write(jsonObj);
		response.getWriter().flush();
	}
	
	/**
	 * 
	* @Title: listForTea 
	* @Description: 查询单个课程的老师对应的试卷、考试列表，包含批阅及交卷详情
	* @param model
	* @param courseId
	* @param classId
	* @param httpServletRequest
	* @param response
	* @throws ServiceException
	* @throws IOException
	 */
	@RequestMapping(value = "/listForTea", method = RequestMethod.GET)
	public void listForTea(Model model,@ModelAttribute("courseId") String courseId, @ModelAttribute("classId") String classId, 
			HttpServletRequest httpServletRequest,HttpServletResponse response) throws ServiceException, IOException {
		List<TestResult> testList = testService.getTestListForTea(courseId,classId);
		response.setCharacterEncoding("utf-8");
		// 对象转Json传递到前台
        ObjectMapper mapper = new ObjectMapper();  
        ObjectWriter writer = mapper.viewWriter(Paper.class);  
        String jsonObj = writer.writeValueAsString(testList);  
		response.getWriter().write(jsonObj);
		response.getWriter().flush();
	}
	
	/**
	 * 
	* @Title: submit 
	* @Description: 学生交卷
	* @param model
	* @param httpServletRequest
	* @throws ServiceException
	 * @throws IOException 
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public void submit(Model model,@ModelAttribute("test") Test test, String finish,
			HttpServletRequest httpServletRequest,HttpServletResponse response) throws ServiceException, IOException {
		// 判断本次考试是否已经参加过，不允许二次提交
		List<Test> list = testService.queryTestRecordByUserIdandPaperId(CommonFunc.getUserLogin(httpServletRequest).getUserId(), test.getPaperId(), test.getClassId());
		if(list != null && list.size() != 0){
			// 未经查看过答案的作业允许重复提交
			if(list.get(0).getTestType() == 0 && list.get(0).getCheckStatus() == 0 ){
				test.setStartAnswerTime(null);
				test.setEndAnswerTime(null);
				test.setTestTimes(null);
				test.setUserAnswerCostTime(null);
				test.setTestId(list.get(0).getTestId());
				testService.updateByPrimaryKeySelective(test);
			}else{
				// 考试和已经查看过答案的作业不允许重复提交
				response.setCharacterEncoding("utf-8");
				response.getWriter().write("{\"success\":\"false\" ,\"testId\":\""+test.getTestId()+" \"}");
				response.getWriter().flush();
			}
		}else{
			test.setTestId(MD5.toMd5(UID.nextUid()));
			// 全为客观题的试卷自动批阅，在sql中判断
			//test.setMarkStatus(1);
			test.setTestTimes(1);
			test.setUniversityId(CommonFunc.getUserLogin(httpServletRequest).getUniversityId());
			test.setSubmitTime(new Date());
			test.setUserId(CommonFunc.getUserLogin(httpServletRequest).getUserId());
			testService.insertTestRecord(test);
			// 如果是参加了最后一次考试，默认结束此门课程
			if(finish != null && "1".equals(finish)){
				myCourseService.finishMyCourse(CommonFunc.getUserLogin(httpServletRequest).getUserId(), test.getClassId());
			}
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("{\"success\":\"true\" ,\"testId\":\""+test.getTestId()+" \"}");
			response.getWriter().flush();
		}
	}
	
	/**
	 * 
	* @Title: marking 
	* @Description: 老师批阅试卷
	* @param model
	* @param test
	* @param httpServletRequest
	* @param response
	* @throws ServiceException
	* @throws IOException
	 */
	@RequestMapping(value = "/marking", method = RequestMethod.POST)
	public void marking(Model model,@ModelAttribute("test") Test test, 
			HttpServletRequest httpServletRequest,HttpServletResponse response) throws ServiceException, IOException {
		// 老师手动批阅后试卷状态为已批阅
		test.setMarkStatus(1);
		test.setStartAnswerTime(null);
		test.setEndAnswerTime(null);
		test.setTestTimes(null);
		test.setUserAnswerCostTime(null);
		testService.updateByPrimaryKeySelective(test);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write("{\"success\":\"true\" ,\"testId\":\""+test.getTestId()+" \"}");
		response.getWriter().flush();
	}
	
	@RequestMapping(value = "/preview")
	public String preview(HttpServletResponse response,
			HttpServletRequest request, String testId,String chapter,ModelMap model) throws ServiceException, JsonGenerationException, JsonMappingException, IOException {
//		String strUrl = "http://" + request.getServerName()
//				+ ":" + request.getServerPort() + "/";
		long start1 = System.currentTimeMillis();
		Test test = testService.findByPrimaryKey(testId);
		test.setCheckStatus(1);
		testService.updateByPrimaryKeySelective(test);
		Paper paper = paperService.findByPrimaryKey(test.getPaperId());
		logger.debug("findByPrimaryKey=============>>"+(System.currentTimeMillis() - start1)+"ms");
	    ObjectMapper mapper = new ObjectMapper();  
	    JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Question.class);   
		if(paper.getContent() != null && !paper.getContent().equals("")){
			long jackStart = System.currentTimeMillis();
			@SuppressWarnings("unchecked")
			List<Question> questionList =  (List<Question>)mapper.readValue(paper.getContent(), javaType);
			logger.debug("Json2Obj=============>>"+(System.currentTimeMillis() - jackStart)+"ms");
			model.addAttribute("questionList",questionList);
		}
		model.addAttribute("paper", paper);
		model.addAttribute("chapter", chapter);
		model.addAttribute("test", test);
		model.addAttribute("classId", test.getClassId());
		return "jsp/paper/preview";
	}
	/**
	 * 
	* @Title: exportExcel 
	* @Description: 导出学生成绩
	* @param model
	* @param test
	* @param httpServletRequest
	* @param response
	* @throws ServiceException
	* @throws IOException
	 */
	@RequestMapping(value = "/exportExcel")
	public String exportExcel(HttpServletResponse response,
			HttpServletRequest request, String paperId,String classId,Integer pageIndex,ModelMap model) throws ServiceException, JsonGenerationException, JsonMappingException, IOException {
		 String fileName="excel文件";
		List<Marking> markingList = testService.exportMarkingList(classId,paperId);
		List<Map<String,Object>> list=createExcelRecord(markingList);
		 String columnNames[]={"学生姓名","试卷名称","学生分数","班级名称","班级导师",};//列名
	        String keys[]  = {"userName","paperName","userScore","className","teacherName"};//map中的key
	        ByteArrayOutputStream os = new ByteArrayOutputStream();
	        try {
	            ExcelUtil.createWorkBook(list,keys,columnNames).write(os);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        byte[] content = os.toByteArray();
	        ByteArrayInputStream is = new ByteArrayInputStream(content);
	        // 设置response参数，可以打开下载页面
	        response.reset();
	        response.setContentType("application/vnd.ms-excel;charset=utf-8");
	        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
	        ServletOutputStream out = response.getOutputStream();
	        BufferedInputStream bis = null;
	        BufferedOutputStream bos = null;
	        try {
	            bis = new BufferedInputStream(is);
	            bos = new BufferedOutputStream(out);
	            byte[] buff = new byte[2048];
	            int bytesRead;
	            // Simple read/write loop.
	            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	                bos.write(buff, 0, bytesRead);
	            }
	        } catch (final IOException e) {
	            throw e;
	        } finally {
	            if (bis != null)
	                bis.close();
	            if (bos != null)
	                bos.close();
	        }
	        model.addAttribute("markingList",markingList);
	        model.addAttribute("classId",classId);
			model.addAttribute("paperId",paperId);
			return "jsp/test/markingList";
	}
	private List<Map<String, Object>> createExcelRecord(
			List<Marking> markingList) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName", "sheet1");
		 Marking  mrking=null;
		 for (int j = 0; j < markingList.size(); j++) {
			 mrking=markingList.get(j);
			 Map<String, Object> mapValue = new HashMap<String, Object>();
			 mapValue.put("paperName", mrking.getPaperName());
			 mapValue.put("className", mrking.getClassName());
			 mapValue.put("teacherName", mrking.getTeacherName());
			 mapValue.put("userId", mrking.getUserId());
			 mapValue.put("studentId",mrking.getStudentId());
			 mapValue.put("userName", mrking.getUserName());
			 mapValue.put("userScore", mrking.getUserScore());
			 listmap.add(mapValue);
		 }	 
		 return listmap;
		 }
	/**
	 * 
	* @Title: startexam 
	* @Description: 老师查询某班级，某次考试的批阅列表
	* @param model
	* @param classId
	* @param httpServletRequest
	* @param response
	* @throws ServiceException
	* @throws IOException
	 */
	@RequestMapping(value = "/startexam")
	public String startexam(HttpServletResponse response,HttpServletRequest request,String classId,String courseId,ModelMap model) throws ServiceException, JsonGenerationException, JsonMappingException, IOException {
		
	 return "1";		
		}
}