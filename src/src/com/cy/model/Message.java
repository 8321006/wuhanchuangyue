package com.cy.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_message
 * @since 2015-06-15 10:07:06
 */
@Alias("Message")
public class Message implements Serializable {
	/** message_id -- 消息主键ID */
	private String messageId;

	/** message_type -- 消息类型 */
	private Integer messageType;

	/** reader -- 是否读取标志 */
	private Integer reader;

	/** message -- 消息 */
	private String message;

	/** user -- 用户是否存在*/
	private String userId;

	/** user -- 用户 */
	private String user;

	/** fsruser --  */
	private String fsruser;
	private String messuserid;
	private String wthd;
	private String nickname;
	private Date messagetime;
	private String messagetimeString;
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getWthd() {
		return wthd;
	}

	public void setWthd(String wthd) {
		this.wthd = wthd;
	}

	
	public String getMessagetimeString() {
		return messagetimeString;
	}

	public void setMessagetimeString(String messagetimeString) {
		this.messagetimeString = messagetimeString;
	}

	

	public String getMessuserid() {
		return messuserid;
	}

	public void setMessuserid(String messuserid) {
		this.messuserid = messuserid;
	}

	public Date getMessagetime() {
		return messagetime;
	}

	public void setMessagetime(Date messagetime) {
		this.messagetime = messagetime;
	}

	private static final long serialVersionUID = 1L;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/** 获取消息类型 */
	public Integer getMessageType() {
		return messageType;
	}

	/** 设置消息类型 */
	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	/** 获取是否读取标志 */
	public Integer getReader() {
		return reader;
	}

	/** 设置是否读取标志 */
	public void setReader(Integer reader) {
		this.reader = reader;
	}

	/** 获取消息 */
	public String getMessage() {
		return message;
	}

	/** 设置消息 */
	public void setMessage(String message) {
		this.message = message == null ? null : message.trim();
	}

	/** 获取用户 */
	public String getUser() {
		return user;
	}

	/** 设置用户 */
	public void setUser(String user) {
		this.user = user == null ? null : user.trim();
	}

	/** 获取 */
	public String getFsruser() {
		return fsruser;
	}

	/** 设置 */
	public void setFsruser(String fsruser) {
		this.fsruser = fsruser == null ? null : fsruser.trim();
	}
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}