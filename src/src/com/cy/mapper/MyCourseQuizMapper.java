package com.cy.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cy.model.MyCourseQuiz;

/**
 * MyBatis Mapper 接口 - 表：t_my_course_quiz
 * @since 2015-07-08 14:27:56
 */
public interface MyCourseQuizMapper {
	/**
	 * 按主键删除
	 * @since 2015-07-08 14:27:56
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * 插入 - 全字段保存
	 * @since 2015-07-08 14:27:56
	 */
	int insert(MyCourseQuiz record);

	/**
	 * 插入 - 仅保存给定实体类中非null的字段
	 * @since 2015-07-08 14:27:56
	 */
	int insertSelective(MyCourseQuiz record);

	/**
	 * 按主键查询
	 * @since 2015-07-08 14:27:56
	 */
	MyCourseQuiz selectByPrimaryKey(String courseId);

	/**
	 * 按主键更新 - 仅更新给定实体类中非null的字段
	 * @since 2015-07-08 14:27:56
	 */
	int updateByPrimaryKeySelective(MyCourseQuiz record);

	/**
	 * 按主键更新 - 全更新
	 * @since 2015-07-08 14:27:56
	 */
	int updateByPrimaryKey(MyCourseQuiz record);

	List<MyCourseQuiz> selectPage(MyCourseQuiz example);

	/**
	 * 按 Criteria 条件查询, 支持分页
	 * @since 2015-07-08 14:27:56
	 */
	//List<MyCourseQuiz> selectByCriteria(Criteria<MyCourseQuiz> criteria);

	//统计总的记录数
	long selectCountByCriteria(MyCourseQuiz example);
	List<Map<String, Object>> countQuizsByCourse();
	List<Map<String, Object>> countReplysByCourse();
	List<Map<String, Object>> getquList(@Param("userId") String userId,@Param("chapterId")String chapterId);

	List<Map<String, Object>> getquwidList(String chapterId);
	
	//获取当前视频提问的总条数
	public int getquizcount(String chapterId); 
	
	//查询学生问题列表
	List<MyCourseQuiz> findMyCourseQuiz(Map<String,String> condition);
	
	//查询老师所有的问题列表
	List<MyCourseQuiz> findMyCourseQuizTeacher(Map<String,String> condition);
	
	
	int updateAnwser(String quizid,String answer,Date day);
	
	String findteacherId(String classId);
}
