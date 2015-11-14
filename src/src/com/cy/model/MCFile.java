package com.cy.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_file
 * @since 2015-06-09 14:11:06
 */
@Alias("MCFile")
public class MCFile implements Serializable {
	/** file_id -- 文件id */
	private String fileId;

	/** file_name -- 文件名 */
	private String fileName;

	/** file_size -- 文件大小 */
	private Integer fileSize;

	/** file_time -- 文件上传时间 */
	private Date fileTime;

	/** file_suffix -- 文件后缀 */
	private String fileSuffix;

	/** file_path -- 文件路径 */
	private String filePath;

	/** file_upload -- 文件上传人 */
	private String fileUpload;

	/** file_remark -- 文件备注 */
	private String fileRemark;

	private static final long serialVersionUID = 1L;

	/** 获取文件id */
	public String getFileId() {
		return fileId;
	}

	/** 设置文件id */
	public void setFileId(String fileId) {
		this.fileId = fileId == null ? null : fileId.trim();
	}

	/** 获取文件名 */
	public String getFileName() {
		return fileName;
	}

	/** 设置文件名 */
	public void setFileName(String fileName) {
		this.fileName = fileName == null ? null : fileName.trim();
	}

	/** 获取文件大小 */
	public Integer getFileSize() {
		return fileSize;
	}

	/** 设置文件大小 */
	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	/** 获取文件上传时间 */
	public Date getFileTime() {
		return fileTime;
	}

	/** 设置文件上传时间 */
	public void setFileTime(Date fileTime) {
		this.fileTime = fileTime;
	}

	/** 获取文件后缀 */
	public String getFileSuffix() {
		return fileSuffix;
	}

	/** 设置文件后缀 */
	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix == null ? null : fileSuffix.trim();
	}

	/** 获取文件路径 */
	public String getFilePath() {
		return filePath;
	}

	/** 设置文件路径 */
	public void setFilePath(String filePath) {
		this.filePath = filePath == null ? null : filePath.trim();
	}

	/** 获取文件上传人 */
	public String getFileUpload() {
		return fileUpload;
	}

	/** 设置文件上传人 */
	public void setFileUpload(String fileUpload) {
		this.fileUpload = fileUpload == null ? null : fileUpload.trim();
	}

	/** 获取文件备注 */
	public String getFileRemark() {
		return fileRemark;
	}

	/** 设置文件备注 */
	public void setFileRemark(String fileRemark) {
		this.fileRemark = fileRemark == null ? null : fileRemark.trim();
	}

	public MCFile(String fileId, String fileName, Integer fileSize,
			Date fileTime, String fileSuffix, String filePath,
			String fileUpload, String fileRemark) {
		super();
		this.fileId = fileId;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileTime = fileTime;
		this.fileSuffix = fileSuffix;
		this.filePath = filePath;
		this.fileUpload = fileUpload;
		this.fileRemark = fileRemark;
	}

	public MCFile() {
		super();
	}
	
	
}