package com.cy.model;

import java.io.Serializable;

public class NoticeWithBLOBs extends Notice implements Serializable {
	/** notice_title -- 公告标题 */
	private String noticeTitle;

	/** notice_content -- 公告内容 */
	private String noticeContent;
	/**noticeCharacter-通知种类*/
	private Integer noticeCharacter;
	public Integer getNoticeCharacter() {
		return noticeCharacter;
	}

	public void setNoticeCharacter(Integer noticeCharacter) {
		this.noticeCharacter = noticeCharacter;
	}

	private static final long serialVersionUID = 1L;

	/** 获取公告标题 */
	public String getNoticeTitle() {
		return noticeTitle;
	}

	/** 设置公告标题 */
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle == null ? null : noticeTitle.trim();
	}

	/** 获取公告内容 */
	public String getNoticeContent() {
		return noticeContent;
	}

	/** 设置公告内容 */
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent == null ? null : noticeContent.trim();
	}

	/**
	
	 * @since 2015-07-27 11:56:21
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", noticeTitle=").append(noticeTitle);
		sb.append(", noticeContent=").append(noticeContent);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}