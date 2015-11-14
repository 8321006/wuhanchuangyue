package com.cy.mapper;

import java.util.List;
import java.util.Map;

import com.cy.model.User;

/**
 * MyBatis Mapper 接口 - 表：t_user
 * @since 2015-06-08 15:47:16
 */
public interface UserMapper {
	/**
	 * 按主键删除
	 * @since 2015-06-08 15:47:16
	 */
	int deleteByPrimaryKey(String userId);

	/**
	 * 插入 - 全字段保存
	 * @since 2015-06-08 15:47:16
	 */
	int insert(User record);

	/**
	 * 插入 - 仅保存给定实体类中非null的字段
	 * @since 2015-06-08 15:47:16
	 */
	int insertSelective(User record);

	/**
	 * 按主键查询
	 * @since 2015-06-08 15:47:16
	 */
	User selectByPrimaryKey(String userId);

	/**
	 * 按主键更新 - 仅更新给定实体类中非null的字段
	 * @since 2015-06-08 15:47:16
	 */
	int updateByPrimaryKeySelective(User record);

	/**
	
	 * @since 2015-06-08 15:47:16
	 */
	int updateByPrimaryKeyWithBLOBs(User user);

	/**
	 * 按主键更新 - 全更新
	 * @since 2015-06-08 15:47:16
	 */
	int updateByPrimaryKey(User record);
	
	
	int insertUserList(List<User> userList);

	User selectUser(User user);

	User selectByphone(String phone);
	/*
	 * 通过教师名称来查询教师列表
	 */
	List<User> selectByTeacherName(User user);

	List<User> selectTecher(User user);
	
	List<User> findbyuserid(String realname);
	
	
	//按照角色查询学校所有用户
	List<User> selectUserListbySchool(Map<String,Object> map);
	
	//按课程查询学校所有学生
	List<User> selectUser(String courseId,String universityId);
	
	
	//按照角色查询学校所有用户总数
	String selectUserListCountbySchool(Map<String,Object> map);
	
	String findbyLoginName(String loginName);

	int insertTeacher(User user);
	
	List<User> findbyPhone(String phone);
	
	List<User> findBySchoolCode(User user);

	public String findbystudentId(String studentId);
	
	List<User> selectTeacherListbySchool(Map<String, Object> map);
	
	// 根据qq查询用户是否已经绑定 
	List<User> findByUserQQ(String qq);

	int updatebyPhone(User user);

	void deletebyClassId(String classId);
	//判断当前用户是否已经存在，避免重复导入
	String finduserBystudentId(User user);
	
}