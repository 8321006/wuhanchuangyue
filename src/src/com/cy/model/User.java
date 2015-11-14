package com.cy.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_user
 * @since 2015-06-08 15:47:16
 */
@Alias("User")
public class User implements Serializable {
	/** user_id -- 用户表主键 */
	private String userId;
	
	/** user_QQ -- 绑定QQ号码 */
	private String userQQ;

	/** login_name -- 用户登录名 */
	private String loginName;

	/** login_password -- 登录密码 */
	private String loginPassword;

	/** real_name -- 真实姓名 */
	private String realName;

	/** nick_name -- 用户昵称 */
	private String nickName;

	/** university_id -- 学校ID */
	private String universityId;

	/** university_name -- 学校名称 */
	private String universityName;
	
	/** university_logo -- 学校logo */
	private String universityLogo;

	/** phone -- 联系方式 */
	private String phone;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((studentId == null) ? 0 : studentId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (studentId == null) {
			if (other.studentId != null)
				return false;
		} else if (!studentId.equals(other.studentId))
			return false;
		return true;
	}

	/** address -- 地址 */
	private String address;

	/** email -- 用户邮箱 */
	private String email;

	/** last_login_time -- 上次登录日期 */
	private Date lastLoginTime;

	/** login_count -- 总登录次数 */
	private Integer loginCount;

	/** create_user -- 数据创建人 */
	private String createUser;

	/** create_time -- 数据创建时间 */
	private Date createTime;

	/** update_user -- 数据更新人 */
	private String updateUser;

	/** update_time -- 数据更新日期 */
	private Date updateTime;

	/** student_id -- 学号 */
	private String studentId;

	/** college_name -- 学院名称 */
	private String collegeName;

	/** subject_name -- 专业名称 */
	private String subjectName;

	/** user_type -- 用户分类----> 0 学生  1 老师  2  学校  3 其它 */
	private Integer userType;

	private static final long serialVersionUID = 1L;

	/** extend -- 预留字段 */
	private String extend;

	/** user_introduction -- 用户简介 */
	private String userIntroduction;
	
	private String sex;
	
	private String  courseTerm;
	
	private String courseName;


	/** 获取预留字段 */
	public String getExtend() {
		return extend;
	}

	/** 设置预留字段 */
	public void setExtend(String extend) {
		this.extend = extend == null ? null : extend.trim();
	}

	/** 获取用户简介 */
	public String getUserIntroduction() {
		return userIntroduction;
	}

	/** 设置用户简介 */
	public void setUserIntroduction(String userIntroduction) {
		this.userIntroduction = userIntroduction == null ? null : userIntroduction.trim();
	}
	/** 获取用户表主键 */
	public String getUserId() {
		return userId;
	}

	/** 设置用户表主键 */
	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	/** 获取用户登录名 */
	public String getLoginName() {
		return loginName;
	}

	/** 设置用户登录名 */
	public void setLoginName(String loginName) {
		this.loginName = loginName == null ? null : loginName.trim();
	}

	/** 获取登录密码 */
	public String getLoginPassword() {
		return loginPassword;
	}

	/** 设置登录密码 */
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword == null ? null : loginPassword.trim();
	}

	/** 获取真实姓名 */
	public String getRealName() {
		return realName;
	}

	/** 设置真实姓名 */
	public void setRealName(String realName) {
		this.realName = realName == null ? null : realName.trim();
	}

	/** 获取用户昵称 */
	public String getNickName() {
		return nickName;
	}

	/** 设置用户昵称 */
	public void setNickName(String nickName) {
		this.nickName = nickName == null ? null : nickName.trim();
	}

	/** 获取学校ID */
	public String getUniversityId() {
		return universityId;
	}

	/** 设置学校ID */
	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}

	/** 获取学校名称 */
	public String getUniversityName() {
		return universityName;
	}

	/** 设置学校名称 */
	public void setUniversityName(String universityName) {
		this.universityName = universityName == null ? null : universityName.trim();
	}

	/** 获取联系方式 */
	public String getPhone() {
		return phone;
	}

	/** 设置联系方式 */
	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	/** 获取地址 */
	public String getAddress() {
		return address;
	}

	/** 设置地址 */
	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	/** 获取用户邮箱 */
	public String getEmail() {
		return email;
	}

	/** 设置用户邮箱 */
	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	/** 获取上次登录日期 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	/** 设置上次登录日期 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/** 获取总登录次数 */
	public Integer getLoginCount() {
		return loginCount;
	}

	/** 设置总登录次数 */
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	/** 获取数据创建人 */
	public String getCreateUser() {
		return createUser;
	}

	/** 设置数据创建人 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser == null ? null : createUser.trim();
	}

	/** 获取数据创建时间 */
	public Date getCreateTime() {
		return createTime;
	}

	/** 设置数据创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** 获取数据更新人 */
	public String getUpdateUser() {
		return updateUser;
	}

	/** 设置数据更新人 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser == null ? null : updateUser.trim();
	}

	/** 获取数据更新日期 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/** 设置数据更新日期 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/** 获取学号 */
	public String getStudentId() {
		return studentId;
	}

	/** 设置学号 */
	public void setStudentId(String studentId) {
		this.studentId = studentId == null ? null : studentId.trim();
	}

	/** 获取学院名称 */
	public String getCollegeName() {
		return collegeName;
	}

	/** 设置学院名称 */
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName == null ? null : collegeName.trim();
	}

	/** 获取专业名称 */
	public String getSubjectName() {
		return subjectName;
	}

	/** 设置专业名称 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName == null ? null : subjectName.trim();
	}

	/** 获取用户分类----> 0 学生  1 老师  2  学校  3 其它 */
	public Integer getUserType() {
		return userType;
	}

	/** 设置用户分类----> 0 学生  1 老师  2  学校  3 其它 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getUniversityLogo() {
		return universityLogo;
	}

	public void setUniversityLogo(String universityLogo) {
		this.universityLogo = universityLogo;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String  getCourseTerm() {
		return courseTerm;
	}

	public void setCourseTerm(String courseTerm) {
		this.courseTerm = courseTerm;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getUserQQ() {
		return userQQ;
	}

	public void setUserQQ(String userQQ) {
		this.userQQ = userQQ;
	}
	
}