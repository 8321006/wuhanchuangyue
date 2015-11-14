package com.cy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cy.model.CourseClass;
import com.cy.model.CourseLearnAnalysis;

public interface CourseLearnAnalysisMapper {

	int deleteByPrimaryKey(Integer learnId);

	int insert(CourseLearnAnalysis record);

	int insertSelective(CourseLearnAnalysis record);

	CourseLearnAnalysis selectByPrimaryKey(Integer learnId);

	int updateByPrimaryKeySelective(CourseLearnAnalysis record);

	int updateByPrimaryKey(CourseLearnAnalysis record);

	List<CourseLearnAnalysis> selectByCriteria(CourseLearnAnalysis example);

	Map<String, Integer> countByCriteria(CourseLearnAnalysis example);

	Integer countChaptersByCourseId(String courseId);
	
	String findClassIdByCourseId(@Param("courseId") String courseId,@Param("userId") String userId,@Param("courseType") Integer courseType);
	
	Integer countTasksByClassAndUser(@Param("classId") String classId,@Param("userId") String userId);
	
	CourseClass findByCourseId(String courseId);
	
	Integer countPaperByCourse(String courseId);
	
	Integer countNoteByCourse(Map<String, Object> args);
	
	Integer countCommentByClassAndUser(@Param("userId") String userId,@Param("classId") String classId);
	
	Integer countCommentPostsByCourse(Map<String, Object> args);
	
	Integer countQuizsByClassAndUser(@Param("userId") String userId,@Param("classId") String classId);
	
	List<Map<String, Object>> countStudentAndClass();
	
	List<Map<String, Object>> countStudentGroup(String classId);

	Integer getRankLearningInClass(@Param("classId") String classId ,@Param("userId") String userId);
	
	Integer getRankCommentInClass(@Param("classId") String classId ,@Param("userId") String userId);
	
	Integer getRankQuizInClass(@Param("classId") String classId ,@Param("userId") String userId);
	
	Integer getRankTaskInClass(@Param("classId") String classId ,@Param("userId") String userId);
	
	Integer getRankVideoInClass(@Param("classId") String classId ,@Param("userId") String userId);
	
	Integer getRankExamInClass(@Param("classId") String classId ,@Param("userId") String userId);
	
	Integer getCourseStatusByUserAndClass(Map<String, Object> args);
	
	Integer countFinishByClass(String classId);
	
	List<CourseLearnAnalysis> findByTimebucket(@Param("studentId") String studentId,@Param("classId") String classId,@Param("startTime") String startTime);
	
	List<CourseLearnAnalysis> getLastInClass(String classId);
	
	CourseLearnAnalysis getLastAnalysisByCourseAndUser(@Param("studentId") String studentId,@Param("classId") String classId);
	
	Integer getExamScoreByUser(@Param("userId") String userId,@Param("classId") String classId);
	
	Integer countStduentByClass(String classId);
	
	String findClassIdByStudentAndCourse(@Param("userId") String userId,@Param("courseId") String courseId);
	
	Integer countVideoMins(@Param("userId") String userId,@Param("classId") String classId);
	
	List<Map<String, String>> findClassAndCourse(@Param("universityId") String universityId);
	
	CourseLearnAnalysis getCurrentDay(@Param("userId") String userId,@Param("classId") String classId);
	
	Integer countDayQuizByUserAndClass(@Param("userId") String userId,@Param("classId") String classId);
	Integer countDayTaskByUserAndClass(@Param("userId") String userId,@Param("classId") String classId);
	Integer countDayVideoByUserAndClass(@Param("userId") String userId,@Param("classId") String classId);
	Integer countDayExamByUserAndClass(@Param("userId") String userId,@Param("classId") String classId);
	
	CourseLearnAnalysis getTotalToByUserInClass(@Param("userId") String userId,@Param("classId") String classId,@Param("courseId") String courseId);
	
	Integer calPersonalLearningRate(@Param("userId") String userId,@Param("classId") String classId,@Param("courseId") String courseId);
	
	Integer updateQuizCountDay(@Param("userId") String userId,@Param("classId") String classId);
	Integer updateCommentCountDay(@Param("userId") String userId,@Param("classId") String classId);
	Integer updateTaskCountDay(@Param("userId") String userId,@Param("classId") String classId);
	Integer updateVideoCountDay(@Param("userId") String userId,@Param("classId") String classId,@Param("videoCount") Integer videoCount);
	
}