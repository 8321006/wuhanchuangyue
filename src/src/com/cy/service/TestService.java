package com.cy.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.util.CommonFunc;
import com.cy.common.util.MD5;
import com.cy.common.util.UID;
import com.cy.exception.ServiceException;
import com.cy.mapper.SurveyMapper;
import com.cy.mapper.TestMapper;
import com.cy.mapper.TestQuestionMapper;
import com.cy.model.CourseScoreResult;
import com.cy.model.Marking;
import com.cy.model.Question;
import com.cy.model.Test;
import com.cy.model.TestQuestion;
import com.cy.model.TestResult;
import com.cy.model.UniversityTransaction;

/**
 * 业务实现层 - 表：t_test
 * @since 2015-07-15 09:27:26
 */
@Service("testService")
public class TestService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Resource
	private TestMapper testMapper;
	
	@Resource
	private TestQuestionMapper testQuestionMapper;
	@Resource
	private SurveyMapper surveyMapper;
	
	@Autowired
	private UniversityTransactionService universityTransactionService;
	
	public void insertTestRecord(Test test) throws ServiceException {
		try {
			testMapper.insertTestRecord(test);
			if(test.getTestType() == 2){
				// 如果是调查问卷，则需要插入数据到t_test_question
				ObjectMapper mapper = new ObjectMapper();  
				JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, TestQuestion.class); 
				@SuppressWarnings("unchecked")
				List<TestQuestion> list =  (List<TestQuestion>)mapper.readValue(test.getContent(), javaType);
				for(TestQuestion testQuestion : list){
					testQuestion.setId(MD5.toMd5(UID.nextUid()));
					testQuestion.setPaperId(test.getPaperId());
					testQuestion.setUserId(test.getUserId());
					testQuestion.setTestId(test.getTestId());
				}
				testQuestionMapper.insertRecordList(list);
				//将课程的平均满意度写入到t_survey表中
				/* 1、统计该调查问卷填写的人数 --从前台传入
				 * 2、统计用户选择课程的星级总和
				 */
				System.out.print(test.getUserWriteTotal());
				int usertoatal=testMapper.selectcountBypaperId(test.getPaperId());
				String surveyResult=String.valueOf(Math.round(usertoatal/(test.getUserWriteTotal()+1)));
				//判断当前调查问卷的类型：0：课程调查问卷   1：平台的调查问卷
				int surveyType=surveyMapper.findSurveyType(test.getPaperId());
				Map<String,String> Result = new HashMap<String,String>();
				Result.put("paperId", test.getPaperId());
				if(surveyType==0){
			    //课程的调查问卷要判断universityId
				Result.put("universityId", test.getUniversityId());
				}
				Result.put("surveyResult", surveyResult);
				surveyMapper.insertpaperDegree(Result);
				//System.out.print(userWritetoatal);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<Test> queryTestRecordByUserIdandPaperId(String userId,String paperId,String classId) throws ServiceException {
		try {
			Map<String,String> map = new HashMap<String,String>();
			map.put("userId", userId);
			map.put("paperId", paperId);
			map.put("classId", classId);
			return testMapper.queryTestRecordByUserIdandPaperId(map);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public Test findByPrimaryKey(String testId) throws ServiceException {
		try {
			return testMapper.selectByPrimaryKey(testId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	* @Title: getTestList 
	* @Description: 查询单个课程的学生对应的试卷、考试列表 
	* @param courseId
	* @param classId
	* @param userId
	* @return
	* @throws ServiceException
	 */
	public List<Test> getTestList(String courseId,String classId,String userId) throws ServiceException{
		try {
			Test test = new Test();
			test.setUserId(userId);
			test.setPaperId(courseId);
			test.setClassId(classId);
			List<Test> list= testMapper.getTestList(test);
			if(list != null && list.size()!=0){
				for(Test temp: list){
					if(temp.getTestType()==1){
						// 考试需要判断当前日期是否能参加考试
						if(new Date().before(temp.getStartAnswerTime())){
							// 时间未到，不能参加
							temp.setUserId("0");
						}else if(new Date().after(new Date(temp.getEndAnswerTime().getTime()+1*24*3600*1000))){
							// 时间过了，不能参加，未申请补考
							temp.setUserId("2");
							// 查询事务，查看当前是否申请了补考，以及申请状态
							UniversityTransaction transaction = new UniversityTransaction();
							transaction.setPaperId(temp.getPaperId());
							transaction.setUserId(userId);
							UniversityTransaction transactionTemp = universityTransactionService.queryTransactionByPaperId(transaction);
							if(transactionTemp != null){
								if(transactionTemp.getTransactionState()==2){
									// 补考申请通过
									temp.setUserId("3");
								}else if(transactionTemp.getTransactionState()==0){
									// 补考申请被拒绝
									temp.setUserId("4");
								}else{
									// 补考审核中
									temp.setUserId("5");
								}
							}
						}else{
							temp.setUserId("1");
						}
					}
				}
			}
			return list;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	* @Title: getTestListForTea 
	* @Description: 查询单个课程的老师对应的试卷、考试列表，包含批阅及交卷详情
	* @param courseId
	* @param classId
	* @return
	* @throws ServiceException
	 */
	public List<TestResult> getTestListForTea(String courseId,String classId) throws ServiceException{
		try {
			Test test = new Test();
			test.setClassId(classId);
			test.setPaperId(courseId);
			return testMapper.getTestListForTea(test);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/**
	 * 
	* @Title: getMarkingList 
	* @Description: 老师查询某班级，某次考试的批阅列表
	* @param classId
	* @param paperId
	* @return
	 * @throws ServiceException 
	 */
	public List<Marking> getMarkingList(String classId, String paperId) throws ServiceException {
		try {
			Test test = new Test();
			test.setClassId(classId);
			test.setPaperId(paperId);
			return testMapper.getMarkingList(test);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/**
	 * 
	* @Title: updateByPrimaryKeySelective 
	* @Description:  仅更新给定实体类中非null的字段
	* @param entity
	* @throws ServiceException
	 */
	public void updateByPrimaryKeySelective(Test entity) throws ServiceException {
		try {
			testMapper.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	/*
	public void insert(Test entity) throws ServiceException {
		try {
			testMapper.insert(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void delete(Test entity) throws ServiceException {
		try {
			testMapper.deleteByPrimaryKey(entity.getTestId());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void batchDelete(List<String> ids) throws ServiceException {
		try {
			for (String id : ids) {
				testMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void updateSelective(Test entity) throws ServiceException {
		try {
			testMapper.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public Test findByPrimaryKey(String testId) throws ServiceException {
		try {
			return testMapper.selectByPrimaryKey(testId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public Pager<Test> findPage(Criteria<Test> criteria) throws ServiceException {
		try {
			if (criteria == null) {
				 criteria = Criteria.create(Test.class);
			}
			criteria.pagination(true);
			List<Test> list = testMapper.selectByCriteria(criteria);
			return new Pager<Test>(criteria, list);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<Test> findAll(Criteria<Test> criteria) throws ServiceException {
		try {
			if (criteria == null) {
				 criteria = Criteria.create(Test.class);
			}
			criteria.pagination(false);
			return testMapper.selectByCriteria(criteria);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}*/

	/**
	 * 
	* @Title: getScoreByCourseAndUser 
	* @Description: 查询学生学习课程的成绩
	* @param courseId
	* @param classId
	* @param userId
	* @param universityId
	* @return
	* @throws ServiceException
	 */
	public CourseScoreResult getScoreByCourseAndUser (String courseId, String classId,
			String userId, String universityId) throws ServiceException {
		try {
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("courseId", courseId);
			args.put("classId", classId);
			args.put("userId", userId);
			args.put("universityId", universityId);
			CourseScoreResult result = testMapper.getScoreByCourseAndUser(args);
			if(result != null){
				result.setTotalScore(result.getVideoScore()*result.getVideoRatio()+result.getHomeWorkScore()*result.getWorkRatio()+result.getPaperScore()*result.getExamRatio());
			}
			return result;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	/**
	 * 
	* @Title:exportMarkingList
	* @Description: 导出学生的成绩
	* @param classId
	* @param paperId
	* @return
	* @throws ServiceException
	 */
	public List<Marking> exportMarkingList(String classId, String paperId) throws ServiceException {
		try {
			Test test = new Test();
			test.setClassId(classId);
			test.setPaperId(paperId);
			return testMapper.exportMarkingList(test);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
}