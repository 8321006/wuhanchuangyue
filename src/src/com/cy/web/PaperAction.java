package com.cy.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.common.util.Message;
import com.cy.common.util.Page;
import com.cy.common.util.PagingUtil;
import com.cy.exception.ServiceException;
import com.cy.model.Paper;
import com.cy.model.Question;
import com.cy.service.PaperService;
import com.cy.service.QuestionService;
import com.mysql.jdbc.Field;

@Controller
@RequestMapping(value = "/paper")
public class PaperAction {
	private static final Logger logger = Logger.getLogger(PaperAction.class);


	@Autowired
	private PaperService paperService;
	@Autowired
	private  QuestionService questionService;
	
	/**
	 * 
	* @Title: paperList 
	* @Description: 查询试卷列表 
	* @param model
	* @param page
	* @param httpServletRequest
	* @return
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String paperList(Model model,
			HttpServletRequest httpServletRequest) throws ServiceException {
		Page<Paper> pageModel = new Page<Paper>();
		int page = 1;
		pageModel.setPageNo(page);
		pageModel.setPageSize(10);
		List<Paper> paper = paperService.getPaperList(pageModel);
		String pageStr = PagingUtil.getPageBtnlink(page,
				pageModel.getTotalPage());
		model.addAttribute("paper", paper);
		model.addAttribute("pageStr", pageStr);
		return "jsp/paper/list";
	}
	
	/**
	 * 
	* @Title: paperPreview 
	* @Description: 试卷预览
	* @param response
	* @param request
	* @param type
	* @param model
	* @return
	* @throws ServiceException
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	@RequestMapping(value = "/preview")
	public String preview(HttpServletResponse response,
			HttpServletRequest request, String paperId,String classId,String courseId,String chapter,String finish,ModelMap model) throws ServiceException, JsonGenerationException, JsonMappingException, IOException {
		Paper paper = paperService.findByPrimaryKey(paperId);
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
		model.addAttribute("classId", classId);
		model.addAttribute("courseId", courseId);
		model.addAttribute("chapter", chapter);
		model.addAttribute("finish", finish);
		return "jsp/paper/preview";
	}
  
	/**
	 * 
	* @Title: 
	* @Description: 上传试卷 
	* @param request
	* @return
	* Object
	 */
	@RequestMapping(value = "/paper-import",method = RequestMethod.GET)
	public String questionImportPage(ModelMap model) {	
		List<Field> fieldList = questionService.getAllField(null);
		model.addAttribute("fieldList", fieldList);
		return "jsp/paper/paper_import";
	}
	
	@RequestMapping(value = "/paper-import",method = RequestMethod.POST)
	public @ResponseBody Message courseImport(@RequestBody String filePath) {
		Message message = new Message();
		try{
			message.setMessageInfo(questionService.uploadQuestions(filePath, "yaly",2));
		}catch(RuntimeException e){
			message.setResult(e.getClass().getName() + ":" + e.getMessage());
//			message.setMessageInfo(e.getMessage());
		}
		System.out.print(message.getMessageInfo());
		return message;
	}


}