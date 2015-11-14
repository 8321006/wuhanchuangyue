package com.cy.service;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.util.ExcelUtil;
import com.cy.common.util.MD5;
import com.cy.common.util.Page;
import com.cy.common.util.UID;
import com.cy.mapper.CourseChapterMapper;
import com.cy.mapper.PaperMapper;
import com.cy.mapper.QuestionMapper;
import com.cy.mapper.SurveyMapper;
import com.cy.model.CourseChapter;
import com.cy.model.Paper;
import com.cy.model.Question;
import com.cy.model.QuestionContent;
import com.cy.model.QuestionFilter;
import com.cy.model.QuestionOption;
import com.cy.model.Survey;
import com.mysql.jdbc.Field;

@Service("questionService")
public class QuestionService implements Serializable {
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private PaperMapper paperMapper;
	@Autowired
	private CourseChapterMapper courseChapterMapper;
	@Resource
	private SurveyMapper surveyMapper;

	private static final long serialVersionUID = 4992749228770291244L;

	public String uploadQuestions(String filePath, String courseId, int fieldId) {
		/*
		 * fieldId为1--试题插入 
		 * fieldId为2--试卷插入
		 * fieldId为3--调查问卷插入
		 */
		String strPath = ",webapps,files,question," + ",tmp";
		List<Question> questioncontentlist = new ArrayList<Question>();
		Paper paper = new Paper();
		int index = 2;
		String msg = "";
		int flagN = 0;// 记录数据为空项
		int flagS = 0;// 记录试题中是否存在主观题
		try {
			List<Map<String, String>> questionMapList = ExcelUtil
					.ExcelToList(System.getProperty("catalina.base")
							+ strPath.replace(',', File.separatorChar)
							+ File.separatorChar + filePath);
			for (Map<String, String> map : questionMapList) {
				String msg1 = "Excel中第" + index + "行";
				Question question = new Question();
				// 判断试题的题目是否为空
				if (null == map.get("题目") || "".equals(map.get("题目"))) {
					msg += msg1 + "题目列数据不能为空！" + "\n";
					flagN++;
					break;
				} else {
					question.setName(map.get("题目").length() > 10 ? map
							.get("题目").substring(0, 10) + "..." : map.get("题目"));
				}
				// 判断试题的类型是否为空
				if (null == map.get("类型") || "".equals(map.get("类型"))) {
					msg += msg1 + "类型列数据不能为空！";
					flagN++;
					break;
				} else {
					if (map.get("类型").equals("单选题")
							|| map.get("类型").equals("单项选择题"))
						question.setQuestionTypeId(1);
					else if (map.get("类型").equals("多选题")
							|| map.get("类型").equals("多项选择题"))
						question.setQuestionTypeId(2);
					else if (map.get("类型").equals("判断题"))
						question.setQuestionTypeId(3);
					else if (map.get("类型").equals("填空题"))
						question.setQuestionTypeId(4);
					else if (map.get("类型").equals("简答题"))
						question.setQuestionTypeId(5);
					else if (map.get("类型").equals("论述题"))
						question.setQuestionTypeId(6);
					else if (map.get("类型").equals("分析题"))
						question.setQuestionTypeId(7);
				}
				//判断是否存在主观题
				if(question.getQuestionTypeId() == 5||question.getQuestionTypeId() == 6||question.getQuestionTypeId() == 7){
					flagS++;
				}
				// 判断试题的答案不能为空
				if(question.getQuestionTypeId() == 1
				|| question.getQuestionTypeId() == 2||question.getQuestionTypeId() == 3||
				 question.getQuestionTypeId() == 4)
				{
				if (null == map.get("答案") || "".equals(map.get("答案"))) {
					msg += msg1 + "答案列数据不能为空！";
					flagN++;
					break;
				} 
				else {
					question.setAnswer(map.get("答案"));
				}
				}else
				{
					question.setAnswer(map.get("答案"));
				}
					question.setAnalysis(map.get("解析"));
				// 判断试题的分值是否为空
				if (null == map.get("分值") || "".equals(map.get("分值"))) {
					msg += msg1 + "分值列数据不能为空！";
					flagN++;
					break;
				} else {
					question.setPoints(map.get("分值").equals("") ? 0 : Float
							.parseFloat(map.get("分值")));
				}
				// 判断试题的关键点是否为空
					question.setKeyword(map.get("关键点"));
				// 科目编号从参数中获得
				question.setSubjectId("1");
				// question.setSubjectName(map.get("科目"));

				// 当题目类型为判断题和多选题时，判断选项是否为空
				QuestionContent qc = new QuestionContent();
				Iterator<String> it = map.keySet().iterator();
				List<String> keyStr = new ArrayList<String>();
				if (question.getQuestionTypeId() == 1
						|| question.getQuestionTypeId() == 2) {
					while (it.hasNext()) {
						String key = it.next();
						if (key.contains("选项"))
							keyStr.add(key.replace("选项", ""));
					}
					Collections.sort(keyStr);
					List<QuestionOption> choiceList = new ArrayList<QuestionOption>();
					int flag = 0;
					for (int i = 0; i < keyStr.size(); i++) {
						QuestionOption option = new QuestionOption();
						if (!map.get("选项" + keyStr.get(i)).trim().equals("")) {
							option.setAnswer(map.get("选项" + keyStr.get(i)));
							option.setOption(keyStr.get(i));
							choiceList.add(option);
						} else if (map.get("选项" + keyStr.get(i)).trim()
								.equals("")
								&& i < 4) {
							flag++;
							flagN++;
							msg += msg1 + "选项" + keyStr.get(i) + "的值为空！" + "\n";
							break;
						}
					}
					if (flag > 0) {
						break;
					}
					qc.setChoiceList(choiceList);
				}
				qc.setTitle(map.get("题目"));
				ObjectMapper mapper = new ObjectMapper();
				ObjectWriter writer = mapper.viewWriter(QuestionContent.class);
				String json1 = writer.writeValueAsString(qc);
				question.setContent(json1);
				// System.out.println("---json1--" + json1);
				// String jsonStr = Object2Xml.toXml(qc);
				question.setCreator("yaly");
				Date date = new Date();
				question.setCreateTime(date);
				// 插入试卷的时候需在试卷表中插入题号
				// paper = new Paper();
				if (fieldId == 2) {
					if (null == map.get("题号") || "".equals(map.get("题号"))) {
						msg += msg1 + "题号列的数据不能为空！";
						flagN++;
						break;
					} else {
						question.setQuestionNo(map.get("题号"));
					}

					if (index == 2) {
						// 判断插入试卷中的题目的题号是否为空
						// 判断试卷及格分数是否为空
						if (null == map.get("试卷及格分数")
								|| "".equals(map.get("试卷及格分数"))) {
							msg += msg1 + "试卷及格分数列的数据不能为空！";
							flagN++;
							break;
						} else {
							paper.setPassScore(Integer.parseInt(map
									.get("试卷及格分数")));
						}
						// 判断试卷答题时间是否为空
						if (null == map.get("试卷答题时间")
								|| "".equals(map.get("试卷答题时间"))) {
							msg += msg1 + "试卷答题时间列的数据不能为空！";
							flagN++;
							break;
						} else {
							paper.setAnswerCostTime(Integer.parseInt(map
									.get("试卷答题时间")));
						}
						// 判断试卷总分是否为空
						if (null == map.get("试卷总分")
								|| "".equals(map.get("试卷总分"))) {
							msg += msg1 + "试卷总分列的数据不能为空！";
							flagN++;
							break;
						} else {
							paper.setScore(Integer.parseInt(map.get("试卷总分")));
						}
						paper.setPaperName(filePath.split("\\.")[0]);
						// 科目编号从参数中获得
						paper.setSubjectId(1);
					}
				}
				question.setLastModify(date);
				questioncontentlist.add(question);
				question.setQuestionId(MD5.toMd5(UID.nextUid()));
				// this.addQuestion(question);
				index++;
			}
			if (flagN == 0) {
				this.addQuestion(questioncontentlist);
				ObjectMapper mapper = new ObjectMapper();
				ObjectWriter writer1 = mapper.viewWriter(Question.class);
				String json2 = writer1.writeValueAsString(questioncontentlist);
				System.out.print("---json2---" + json2);
				if (paper != null && fieldId == 2) {
					if(flagS>0){
						paper.setIsSubjective(0);
					}
					else{
						paper.setIsSubjective(1);
					}
					paper.setContent(json2);
					paper.setPaperId(MD5.toMd5(UID.nextUid()));
					this.addPaper(paper);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Excel表格中第" + index
					+ "行数据填写不完整，请检查您的文档！");
		}
		return msg;

	}

	/**
	 * 
	* @Title: uploadQuestions 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param filePath
	* @param fieldId
	* @param type 测试类型：0，作业，1：考试 2：调查问卷
	* @return
	 */
	public String uploadPaper(String filePath, String fieldId,int type) {
		String strPath = ",webapps,files,question," + ",tmp";
		List<Question> questioncontentlist = new ArrayList<Question>();
		Paper paper = new Paper();
		int index = 2;
		String msg = "";
		int flagN = 0;// 记录数据为空项
		int flagSubjective= 0;// 记录数据为空项
		int flagS = 0;// 记录试题中是否存在主观题
		try {
			List<Map<String, String>> questionMapList = ExcelUtil
					.ExcelToList(filePath);
			for (Map<String, String> map : questionMapList) {
				String msg1 = "Excel中第" + index + "行";
				Question question = new Question();
				// 判断试题的题目是否为空
				if (null == map.get("题目") || "".equals(map.get("题目"))) {
					msg += msg1 + "题目列数据不能为空！" + "\n";
					flagN++;
					break;
				} else {
					question.setName(map.get("题目").length() > 10 ? map
							.get("题目").substring(0, 10) + "..." : map.get("题目"));
				}
				// 判断试题的类型是否为空
				if (null == map.get("类型") || "".equals(map.get("类型"))) {
					msg += msg1 + "类型列数据不能为空！";
					flagN++;
					break;
				} else {
					if (map.get("类型").equals("单选题")
							|| map.get("类型").equals("单项选择题"))
						question.setQuestionTypeId(1);
					else if (map.get("类型").equals("多选题")
							|| map.get("类型").equals("多项选择题"))
						question.setQuestionTypeId(2);
					else if (map.get("类型").equals("判断题"))
						question.setQuestionTypeId(3);
					else if (map.get("类型").equals("填空题"))
						question.setQuestionTypeId(4);
					else if (map.get("类型").equals("简答题"))
						question.setQuestionTypeId(5);
					else if (map.get("类型").equals("论述题"))
						question.setQuestionTypeId(6);
					else if (map.get("类型").equals("分析题"))
						question.setQuestionTypeId(7);
				}
				//判断是否存在主观题
				if(question.getQuestionTypeId() == 5||question.getQuestionTypeId() == 6||question.getQuestionTypeId() == 7){
					flagS++;
				}
				// 判断试题的答案不能为空
				if(type!=2){
				if(question.getQuestionTypeId() == 1
				|| question.getQuestionTypeId() == 2||question.getQuestionTypeId() == 3||
				 question.getQuestionTypeId() == 4)
				{
					if (null == map.get("答案") || "".equals(map.get("答案"))) {
						msg += msg1 + "答案列数据不能为空！";
						flagN++;
						break;
					} 
					else {
						question.setAnswer(map.get("答案"));
					}
				}else
				{
					question.setAnswer(map.get("答案"));
				}
				}
					question.setAnalysis(map.get("解析"));
				// 判断试题的分值是否为空
				if (null == map.get("分值") || "".equals(map.get("分值"))) {
					msg += msg1 + "分值列数据不能为空！";
					flagN++;
					break;
				} else {
					question.setPoints(map.get("分值").equals("") ? 0 : Float
							.parseFloat(map.get("分值")));
				}
				// 判断试题的关键点是否为空
					question.setKeyword(map.get("关键点"));
				// 科目编号从参数中获得
				question.setSubjectId("1");
				// question.setSubjectName(map.get("科目"));

				// 当题目类型为判断题和多选题时，判断选项是否为空
				QuestionContent qc = new QuestionContent();
				Iterator<String> it = map.keySet().iterator();
				List<String> keyStr = new ArrayList<String>();
				if (question.getQuestionTypeId() == 1
						|| question.getQuestionTypeId() == 2) {
					while (it.hasNext()) {
						String key = it.next();
						if (key.contains("选项"))
							keyStr.add(key.replace("选项", ""));
					}
					Collections.sort(keyStr);
					List<QuestionOption> choiceList = new ArrayList<QuestionOption>();
					int flag = 0;
					for (int i = 0; i < keyStr.size(); i++) {
						QuestionOption option = new QuestionOption();
						if (!map.get("选项" + keyStr.get(i)).trim().equals("")) {
							option.setAnswer(map.get("选项" + keyStr.get(i)));
							option.setOption(keyStr.get(i));
							choiceList.add(option);
						} else if (map.get("选项" + keyStr.get(i)).trim()
								.equals("")
								&& i < 4) {
							flag++;
							flagN++;
							msg += msg1 + "选项" + keyStr.get(i) + "的值为空！" + "\n";
							break;
						}
					}
					if (flag > 0) {
						break;
					}
					qc.setChoiceList(choiceList);
				}
				qc.setTitle(map.get("题目"));
				ObjectMapper mapper = new ObjectMapper();
				ObjectWriter writer = mapper.viewWriter(QuestionContent.class);
				String json1 = writer.writeValueAsString(qc);
				question.setContent(json1);
				// System.out.println("---json1--" + json1);
				// String jsonStr = Object2Xml.toXml(qc);
				question.setCreator("yaly");
				Date date = new Date();
				question.setCreateTime(date);
				// 插入试卷的时候需在试卷表中插入题号
				// paper = new Paper();
					if (null == map.get("题号") || "".equals(map.get("题号"))) {
						msg += msg1 + "题号列的数据不能为空！";
						flagN++;
						break;
					} else {
						question.setQuestionNo(map.get("题号"));
					}
               if(type!=2){
					if (index == 2) {
						// 判断插入试卷中的题目的题号是否为空
						// 判断试卷及格分数是否为空
						if (null == map.get("试卷及格分数")
								|| "".equals(map.get("试卷及格分数"))) {
							msg += msg1 + "试卷及格分数列的数据不能为空！";
							flagN++;
							break;
						} else {
							paper.setPassScore(Integer.parseInt(map
									.get("试卷及格分数")));
						}
						// 判断试卷答题时间是否为空
						if (null == map.get("试卷答题时间")
								|| "".equals(map.get("试卷答题时间"))) {
							msg += msg1 + "试卷答题时间列的数据不能为空！";
							flagN++;
							break;
						} else {
							paper.setAnswerCostTime(Integer.parseInt(map
									.get("试卷答题时间")));
						}
						// 判断试卷总分是否为空
						if (null == map.get("试卷总分")
								|| "".equals(map.get("试卷总分"))) {
							msg += msg1 + "试卷总分列的数据不能为空！";
							flagN++;
							break;
						} else {
							paper.setScore(Integer.parseInt(map.get("试卷总分")));
						}
						
					}}
				
				question.setLastModify(date);
				questioncontentlist.add(question);
				question.setQuestionId(MD5.toMd5(UID.nextUid()));
				// this.addQuestion(question);
				index++;
			}
			if (flagN == 0) {
				if(type!=2){
				this.addQuestion(questioncontentlist);
				}
				ObjectMapper mapper = new ObjectMapper();
				ObjectWriter writer1 = mapper.viewWriter(Question.class);
				String json2 = writer1.writeValueAsString(questioncontentlist);
				System.out.print("---json2---" + json2);
				if (paper != null) {
					paper.setPaperName(new File(filePath).getName().split("\\.")[0]);
					// 科目编号从参数中获得
					paper.setSubjectId(1);
					paper.setPaperId(MD5.toMd5(UID.nextUid()));
					//当type为2（调查问卷时）
					if(type==2){
						Date date = new Date();
						if("0".equals(fieldId)==false){
							Survey survey=new Survey();
							survey.setId(MD5.toMd5(UID.nextUid()));
							survey.setChapterId(MD5.toMd5(UID.nextUid()));
							survey.setCourseId(fieldId);
							survey.setCreateTime(date);
							survey.setPaperId(paper.getPaperId());
							this.addcourseChapter(survey);
							paper.setChapterId(survey.getChapterId());
						}
						else{
							Survey survey=new Survey();
							survey.setId(MD5.toMd5(UID.nextUid()));
							survey.setPaperId(paper.getPaperId());
							//插入平台的调查问卷
							this.addsurveyPlatform(survey);
						}
						paper.setTestType(type);
						paper.setAnswerCostTime(60);
						paper.setContent(json2);
						paper.setCreateTime(date);
						this.addPapersurvey(paper);
					}
					else
					{
					paper.setChapterId(fieldId);
					
					//从fieldId中获取章节
					if(flagS>0){
						paper.setIsSubjective(0);
					}
					else{
						paper.setIsSubjective(1);
					}
					paper.setTestType(type);
					paper.setContent(json2);
					//paper.setPaperId(MD5.toMd5(UID.nextUid()));
					this.addPapercourse(paper);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Excel表格中第" + index
					+ "行数据填写不完整，请检查您的文档！");
		}
		return msg;					
	}
	private void addcourseChapter(Survey survey) {
		// TODO Auto-generated method stub
		try{
			surveyMapper.addcourseChapter(survey);
		}
		catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	//导入平台调查问卷，在t_survey插入该调查问卷
	private void addsurveyPlatform(Survey survey) {
		try {
			surveyMapper.addsurveyPlatform(survey);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	private void addPapersurvey(Paper paper) {
		try {
			paperMapper.addPapersurvey(paper);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	private void addPaper(Paper paper) {
		// TODO Auto-generated method stub
		try {
			paperMapper.insertPaper(paper);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private void addPapercourse(Paper paper) {
		// TODO Auto-generated method stub
		try {
			paperMapper.insertPapercourse(paper);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void addQuestion(List<Question> questioncontentlist) {
		// TODO Auto-generated method stub
		try {
			questionMapper.insertQuestion(questioncontentlist);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public List<Field> getAllField(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Question> getQuestionList(Question qf) {
		// TODO Auto-generated method stub
		return questionMapper.getQuestionList(qf);
	}

	public List<Question> getQuestionList(Page<Question> pageModel,
			QuestionFilter qf) {
		return questionMapper.getQuestionList(qf, pageModel);
	}

	public Question getQuestionByQuestionId(String questionId) {
		// TODO Auto-generated method stub
		return questionMapper.getQuestionByQuestionId(questionId);
	}

	public List<Question> getQuestionDescribeListByIdList(List<Integer> idList) {
		List<Question> questionList = questionMapper
				.getQuestionAnalysisListByIdList(idList);
		return questionList;
	}

}
