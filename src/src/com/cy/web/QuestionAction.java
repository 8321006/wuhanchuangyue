package com.cy.web;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.common.util.FileUploadUtil;
import com.cy.common.util.Message;
import com.cy.model.Question;
import com.cy.model.User;
import com.cy.service.QuestionService;
import com.mysql.jdbc.Field;
@RequestMapping(value = "/question")
@Controller
public class QuestionAction {

	@Autowired
	private QuestionService questionService;
	
	@RequestMapping(value = "/upload-uploadify", method = RequestMethod.POST)
	public @ResponseBody String uploadFile(HttpServletRequest request, HttpServletResponse response){
//		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext()
//			    .getAuthentication()
//			    .getPrincipal();
		List<String> filePathList = new ArrayList<String>();
		try {
			filePathList = FileUploadUtil.uploadFile(request, response, "yaly");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception ex){
			ex.printStackTrace(); 
		}
		
		if(filePathList.size() == 0){
			return "系统错误";
		}
				
		return filePathList.get(0);
	}
	
	//文件上传
		@RequestMapping(value = "/question-import",method = RequestMethod.POST)
		public @ResponseBody Message courseImport(@RequestBody String filePath) {
			Message message = new Message();
			System.out.print("yaly");
//			UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext()
//					.getAuthentication().getPrincipal();
//			if(id == 0){
//				message.setResult("error");
//				message.setMessageInfo("请选择题库");
//				return message;
//			}
			try{
				message.setMessageInfo(	questionService.uploadQuestions(filePath, "yaly",1));
			
			}catch(RuntimeException e){
				message.setResult(e.getClass().getName() + ":" + e.getMessage());
//				message.setMessageInfo(e.getMessage());
			}
			System.out.print("message.getResult()"+message.getResult());
			System.out.print(message.getMessageInfo());
			return message;
		}
	
	/**
	 * 试题导入页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/question-import",method = RequestMethod.GET)
	public String questionImportPage(ModelMap model) {	
		System.out.print("yaly1");
		List<Field> fieldList = questionService.getAllField(null);
		model.addAttribute("fieldList", fieldList);
		return "jsp/question/question_import";
	}
	@RequestMapping(value = "/question")
	public String downloadFile(HttpServletResponse response,
			HttpServletRequest request, User user) {		
		//questionService.downloadFile();
		return "jsp/question/question_import";
	}


	@RequestMapping(value = "/question-list", method = RequestMethod.GET)
	public String questionListFilterPage(Model model)
			{
//		int questionType=0;
//		int fieldId=0;
	Question qf = new Question();
////		qf.setFieldId(fieldId);
//	  qf.setQuestionType(questionType);
	    System.out.print("121rt3111");
//	    Page<Question> pageModel = new Page<Question>();
	    List<Question> questionList = questionService.getQuestionList(
			qf);
	    model.addAttribute("questionList", questionList);
	    model.addAttribute("fieldList", questionService.getAllField(null));
	return "jsp/question/question-list";
 }
	

	//试题展览
	@RequestMapping(value = "/question-preview/{questionId}", method = RequestMethod.GET)
	public String questionPreviewPage(Model model,
			@PathVariable("questionId") String questionId, HttpServletRequest request){
//		String strUrl = "http://" + request.getServerName() //服务器地址  
//                + ":"   
//                + request.getServerPort() + "/";
		Question question = questionService.getQuestionByQuestionId(questionId);

		//		List<Integer> idList = new ArrayList<Integer>();
//		idList.add(questionId);
//		List<Question> questionQueryList = questionService.getQuestionDescribeListByIdList(idList);
//		HashMap<Integer, Question> questionMap = new HashMap<Integer, Question>();
//		for (Question qqr : questionQueryList) {
//			if (questionMap.containsKey(qqr.getQuestionId())) {
//				Question a = questionMap.get(qqr.getQuestionId());
//				questionMap.put(qqr.getQuestionId(), a);
//			} else {
//				questionMap.put(qqr.getQuestionId(), qqr);
//			}
//		}
//		QuestionAdapter adapter = new QuestionAdapter(question,null,questionMap.get(questionId),strUrl);
//		String strHtml = adapter.getStringFromXML(true, false, true);
//		model.addAttribute("strHtml", strHtml);
		model.addAttribute("question", question);
		return "jsp/question/question-preview";
	}


}

