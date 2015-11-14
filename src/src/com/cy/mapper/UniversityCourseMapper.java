package com.cy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cy.model.Paper;
import com.cy.model.UniversityCourse;
import com.cy.model.User;

/**
 * MyBatis Mapper 接口 - 表：t_university_course
 * @since 2015-07-10 16:35:13
 */
public interface UniversityCourseMapper {
	/**
	 * 按主键删除
	 * @since 2015-07-10 16:35:13
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * 插入 - 全字段保存
	 * @since 2015-07-10 16:35:13
	 */
	int insert(UniversityCourse record);

	/**
	 * 插入 - 仅保存给定实体类中非null的字段
	 * @since 2015-07-10 16:35:13
	 */
	int insertSelective(UniversityCourse record);

	/**
	 * 按主键查询
	 * @since 2015-07-10 16:35:13
	 */
	List<UniversityCourse> selectByPrimaryKey(UniversityCourse universityCourse);

	/**
	 * 按主键更新 - 仅更新给定实体类中非null的字段
	 * @since 2015-07-10 16:35:13
	 */
	int updateByPrimaryKeySelective(UniversityCourse record);

	/**
	 * 按主键更新 - 全更新
	 * @since 2015-07-10 16:35:13
	 */
	int updateByPrimaryKey(UniversityCourse record);

	List<UniversityCourse> selectPage(UniversityCourse example);

	List<UniversityCourse> selectAll();
	
	UniversityCourse findLearningByCourse(String classId);
	
	//统计总的记录数
	long selectCountByCriteria(UniversityCourse example);
	//修改课程开始和结束的时间
	void updatecourseTimeByCourseId(UniversityCourse courseId);
	
	List<UniversityCourse> finduniversityCourseDate(List<String> courseIdlist);

	List<UniversityCourse> findcourseName(UniversityCourse universityCourse);

	List<UniversityCourse> findAllByUniversity(UniversityCourse universityId);

	//根据学期来搜索课程
	List<UniversityCourse> findtermtime(UniversityCourse termStartTime);
	
	
	//按照学期,课程名称,大学ID查询记录
	String findCourseUniversity(Map<String,Object> map);
	
	
	int insertForImport(Map<String,String> map);
	
	List<UniversityCourse> findAllBycourseTerm(UniversityCourse universityId);

	List<UniversityCourse> findThisBycourseTerm(UniversityCourse universityCourse);

	List<UniversityCourse> searchcourseName(@Param("courseTerm") String courseTerm,@Param("universityId")String universityId);

	List<UniversityCourse> searchCourseterm(String universityId);

	List<User> searchResult(@Param("courseTerm") String courseTerm,@Param("universityId") String universityId,@Param("courseId") String courseId,@Param("realName") String realName);

	List<Map<String, Object>> deletemsg(@Param("universityId") String universityId,@Param("courseTerm") String courseTerm,@Param("courseId") String courseId);

	List<UniversityCourse> searchTeachercoursename(String universityId);

	List<User> teacherSearch(@Param("universityId") String universityId,@Param("courseId") String courseName,@Param("realName") String realName);

	//课程设置查询该课程对应的试卷列表
		List<UniversityCourse> findpaperBycourseId(UniversityCourse universityCourse);
		//删除该课程已经选择的试卷
		void deletecoursePaper(UniversityCourse universityCourse);
		//设置该课程期末考试的试卷
		void insertcoursePaper(UniversityCourse universityCourse);
	
	String selectbyfile(@Param("universityName") String universityName,@Param("courseTerm") String courseTerm,@Param("courseName") String courseName);

	void deletebyterm(UniversityCourse universityCourse);

	UniversityCourse selectbyuniversityId(String universityId);
	
	String findCourseIdByCondition(Map<String,Object> map);

	int insertUniversitycourse(List<UniversityCourse> universityCourselist);

	//查询所有课程
	List<UniversityCourse> findAllCourse();
    //课程开课的状态
	int courseStartStatus(Map<String, Object> map);

	List<UniversityCourse> examlist(String userId);

}