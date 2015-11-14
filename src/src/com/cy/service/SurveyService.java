package com.cy.service;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.type.JavaType;
import org.springframework.stereotype.Service;

import com.cy.common.util.CommonFunc;
import com.cy.common.util.ExcelUtil;
import com.cy.common.util.MD5;
import com.cy.common.util.UID;
import com.cy.exception.ServiceException;
import com.cy.mapper.PaperMapper;
import com.cy.mapper.SurveyMapper;
import com.cy.mapper.UniversityMapper;
import com.cy.model.Course;
import com.cy.model.Paper;
import com.cy.model.Question;
import com.cy.model.QuestionContent;
import com.cy.model.QuestionOption;
import com.cy.model.Survey;

@Service("SurveyService")
public class SurveyService implements Serializable {
	private static final long serialVersionUID = 1L;
	@Resource
	private SurveyMapper surveyMapper;
	@Resource
	private	PaperMapper paperMapper;
	/*
	 *  @Description: 调查问卷列表
	 *  @author pear 
	 *  @throws ServiceException
	 */
	public List<Survey> findsurveylist(Survey survey) throws ServiceException {
		try {
			return	surveyMapper.findsurveylist(survey);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<Course> findcourselist(Survey survey) throws ServiceException {
		try {
			return	surveyMapper.findcourselist(survey);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void uploadsurvey(String filePath) {
		String strPath = ",webapps,files,question," + ",tmp";
	//	  List<Survey> surveylist=new ArrayList<Survey>();
		try {
			List<Map<String, String>> surveyMapList = ExcelUtil
					.ExcelToList(filePath);
			for (Map<String, String> map : surveyMapList) {
				
				}
			}
		 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Excel表格中第"+ "行数据填写不完整，请检查您的文档！");
		}
	}

	public int findSurvey(Survey survey)throws ServiceException {
		try {
			return surveyMapper.findSurvey(survey);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
/*
  @Description: 调查问卷列表
	 *  @author pear 
	 *  @throws ServiceException
 */
	public List<Survey> findsendSurvey(Survey survey) {
		try{
			return surveyMapper.findsendSurvey(survey);
		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	/*@Description: 查询调查问卷
	  *@author pear 
	  *@throws ServiceException
	 */
	public String findSurveyPaperId(Survey survey) {
		try{
			return surveyMapper.findSurveyPaperId(survey);
		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
		
	}
	/*@Description: 向指定班级发送调查问卷
	  *@author pear 
	  *@throws ServiceException
	 */
	public void insertSurvey(String content,String universityId)throws ServiceException{
		try{
			// 发送调查问卷
			ObjectMapper mapper = new ObjectMapper();  
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Survey.class); 
			@SuppressWarnings("unchecked")
			List<Survey> list =  (List<Survey>)mapper.readValue(content, javaType);
			for(Survey sur : list){
				sur.setId(MD5.toMd5(UID.nextUid()));
				sur.setUniversityId(universityId);
			}
			 surveyMapper.insertSurvey(list);
		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
		
	}
	/*@Description: 学生查询自己班级的调查列表
	  *@author pear 
	  *@throws ServiceException
	 */
	public List<Survey> findsurveylistByStudent(Survey survey)throws ServiceException{
		try{
			return surveyMapper.findsurveylistByStudent(survey);
		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
	}
	/*@Description: 平台调查列表
	  *@author pear 
	  *@throws ServiceException
	 */
	public List<Survey> findAdminsurvey()throws ServiceException {
     try{
		return surveyMapper.findAdminsurvey();
       }catch(Exception e){
	throw new RuntimeException(e.getMessage());
       }
	}

	public int selectopationTotal(String paperId,String classId ,String questionId,String userAnswer) {
		try{
			Map<String,String> surveytotal = new HashMap<String,String>();
			surveytotal.put("paperId", paperId);
			surveytotal.put("classId", classId);
			surveytotal.put("questionId", questionId);
			surveytotal.put("userAnswer", userAnswer);
			return surveyMapper.selectopationTotal(surveytotal);
	       }catch(Exception e){
		throw new RuntimeException(e.getMessage());
	       }
		}
	//统计该学生有几张调查问卷尚未填写
	public int findtotalwritesurvey(Survey survey) {
		try{
			return surveyMapper.findtotalwritesurvey(survey);
	       }catch(Exception e){
		throw new RuntimeException(e.getMessage());
	       }
		}
	//查询平台调查问卷列表
	public List<Survey> findPlatformsurvey(Survey survey) {
		try{
		return surveyMapper.findPlatformsurvey(survey);
		}
		catch(Exception e){
			throw new RuntimeException(e.getMessage());
		       }
			}
	 //统计平台有几张调查问卷未填写
	public int findsurveyWrite(Survey survey) {
		try{
		return surveyMapper.findsurveyWrite(survey);
	}
		catch(Exception e){
			throw new RuntimeException(e.getMessage());
			}
		}
//查询该课程是否已经发送调查问卷
	public int findSurveypost(String courseId) {
		try{
			return surveyMapper.findSurveypost(courseId);
		}
			catch(Exception e){
				throw new RuntimeException(e.getMessage());
				}
			}
}
