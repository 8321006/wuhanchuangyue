package com.cy.mapper;

import java.util.List;
import java.util.Map;

import com.cy.model.CourseScoreResult;
import com.cy.model.Marking;
import com.cy.model.Test;
import com.cy.model.TestResult;

/**
 * MyBatis Mapper 接口 - 表：t_test
 * @since 2015-07-15 09:27:26
 */
public interface TestMapper {
	/**
	 * 
	* @Title: insertTestRecord 
	* @Description: 插入答卷记录 
	* @param test
	 * @return 
	* @return
	 */
	void insertTestRecord(Test test);

	/**
	 *  查询个人测试记录
	 */
	List<Test> queryTestRecordByUserIdandPaperId(Map<String, String> args);

	/**
	 * 按主键删除
	 * @since 2015-07-15 09:27:26
	 */
	int deleteByPrimaryKey(String testId);

	/**
	 * 插入 - 全字段保存
	 * @since 2015-07-15 09:27:26
	 */
	int insert(Test record);

	/**
	 * 插入 - 仅保存给定实体类中非null的字段
	 * @since 2015-07-15 09:27:26
	 */
	int insertSelective(Test record);

	/**
	 * 按主键查询
	 * @since 2015-07-15 09:27:26
	 */
	Test selectByPrimaryKey(String testId);

	/**
	 * 按主键更新 - 仅更新给定实体类中非null的字段
	 * @since 2015-07-15 09:27:26
	 */
	int updateByPrimaryKeySelective(Test record);

	/**
	
	 * @since 2015-07-15 09:27:26
	 */
	int updateByPrimaryKeyWithBLOBs(Test record);

	/**
	 * 按主键更新 - 全更新
	 * @since 2015-07-15 09:27:26
	 */
	int updateByPrimaryKey(Test record);

	List<Test> selectPage(Test example);

	//统计总的记录数
	long selectCountByCriteria(Test example);
	
	//查询单个课程的学生对应的试卷、考试列表 
	List<Test> getTestList(Test test);

	//查询单个课程的老师对应的试卷、考试列表，包含批阅及交卷详情
	List<TestResult> getTestListForTea(Test test);

	//老师查询某班级，某次考试的批阅列表
	List<Marking> getMarkingList(Test test);

	CourseScoreResult getScoreByCourseAndUser(Map<String, Object> args);
	//统计用户选择课程的星级总和
	int selectcountBypaperId(String paperId);
    //查询要导出的数据
	List<Marking> exportMarkingList(Test test);
}