package com.cy.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_user_logger
 * @since 2015-08-28 14:42:22
 */
@Alias("UserLogger")
public class UserLogger implements Serializable {
	/** id --  */
	private Integer id;

	/** user_id --  */
	private String userId;
	
	private String universityId;

	/** log_time --  */
	private Date logTime;

	/** log_type -- 0 登录 1 退出 */
	private Integer logType;

	/** log_ip -- 操作ip */
	private String logIp;

	private static final long serialVersionUID = 1L;

	public String getUniversityId() {
		return universityId;
	}

	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}

	/** 获取 */
	public Integer getId() {
		return id;
	}

	/** 设置 */
	public void setId(Integer id) {
		this.id = id;
	}

	/** 获取 */
	public String getUserId() {
		return userId;
	}

	/** 设置 */
	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	/** 获取 */
	public Date getLogTime() {
		return logTime;
	}

	/** 设置 */
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	/** 获取0 登录 1 退出 */
	public Integer getLogType() {
		return logType;
	}

	/** 设置0 登录 1 退出 */
	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	/** 获取操作ip */
	public String getLogIp() {
		return logIp;
	}

	/** 设置操作ip */
	public void setLogIp(String logIp) {
		this.logIp = logIp == null ? null : logIp.trim();
	}

	/**
	
	 * @since 2015-08-28 14:42:22
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", userId=").append(userId);
		sb.append(", logTime=").append(logTime);
		sb.append(", logType=").append(logType);
		sb.append(", logIp=").append(logIp);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}

	public UserLogger(String userId,String universityId, Date logTime, Integer logType, String logIp) {
		super();
		this.userId = userId;
		this.universityId = universityId;
		this.logTime = logTime;
		this.logType = logType;
		this.logIp = logIp;
	}

	public UserLogger() {
		super();
	}
	
	
}