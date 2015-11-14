package com.cy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cy.model.CourseTeacherAnalysis;

/**
 * MyBatis Mapper 接口 - 表：t_course_teacher_analysis
 * @since 2015-07-29 11:12:42
 */
public interface CourseTeacherAnalysisMapper {
	/**
	 * 按主键删除
	 * @since 2015-07-29 11:12:42
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 插入 - 全字段保存
	 * @since 2015-07-29 11:12:42
	 */
	int insert(CourseTeacherAnalysis record);

	/**
	 * 插入 - 仅保存给定实体类中非null的字段
	 * @since 2015-07-29 11:12:42
	 */
	int insertSelective(CourseTeacherAnalysis record);

	/**
	 * 按主键查询
	 * @since 2015-07-29 11:12:42
	 */
	CourseTeacherAnalysis selectByPrimaryKey(Integer id);

	/**
	 * 按主键更新 - 仅更新给定实体类中非null的字段
	 * @since 2015-07-29 11:12:42
	 */
	int updateByPrimaryKeySelective(CourseTeacherAnalysis record);

	/**
	 * 按主键更新 - 全更新
	 * @since 2015-07-29 11:12:42
	 */
	int updateByPrimaryKey(CourseTeacherAnalysis record);

	List<CourseTeacherAnalysis> selectPage(CourseTeacherAnalysis example);

	long selectCountByCriteria(CourseTeacherAnalysis example);
	
	CourseTeacherAnalysis getTotalByDay(String classId);
	
	List<String> findAllClass();
	
	CourseTeacherAnalysis findLastAnalysisByClass(String classId);
	
	String findClassByTeacherAndCourse(@Param("courseId") String courseId,@Param("teacherId") String teacherId);
	
	List<CourseTeacherAnalysis> findByTimebucket(@Param("classId") String classId,@Param("startTime") String startTime);
	
	Integer countStudentsLess(@Param("classId") String classId,@Param("less") int less);
	
	Integer countStudentsLessToMore(@Param("classId") String classId,@Param("less") int less,@Param("more") int more);
	
	Integer countStudentsMore(@Param("classId") String classId,@Param("more") int more);
	
	Integer countStudentInClass(@Param("classId") String classId);
	
	Map<String, Object> countStudentByScoure(@Param("classId") String classId,@Param("less") int less,@Param("more") int more);
	
	Map<String, Object> calRateByScore(@Param("classId") String classId,@Param("less") int less,@Param("more") int more);
	
	List<CourseTeacherAnalysis> findAllLastInClass();
	
	List<String> getTermsByUniversity(@Param("universityId")String universityId);
	
	List<Map<String, String>> getClassByTermAndUniversity(@Param("universityId")String universityId,@Param("courseTerm")String courseTerm);
	
	List<Map<String, String>> getTeacherAndClass();
	
	CourseTeacherAnalysis getCurrentDay(String classId);
	
	CourseTeacherAnalysis getTotalToInClass(String classId);
	
	Integer calAvgLearningRate(@Param("classId") String classId);
	
	Integer calAvgScoreInClass(@Param("classId") String classId);
	
	Integer countFinishInClass(@Param("classId") String classId);
	
	Integer countPassInClass(@Param("classId") String classId);
	
	Integer countSixInClass(@Param("classId") String classId);
	
	Integer countEightInClass(@Param("classId") String classId);
	
	Integer countTenInClass(@Param("classId") String classId);
}