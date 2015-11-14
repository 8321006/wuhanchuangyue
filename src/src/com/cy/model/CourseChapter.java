package com.cy.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

/**
 * 瀹炰綋绫�- 琛細t_course_chapter
 * @since 2015-06-08 15:47:16
 */
@Alias("t_course_chapter")
public class CourseChapter implements Serializable {
	
	private String chapterId; 
	
	private String courseId;
	
	private String chapterName;
	
	private String chapterDesc;
	
	private String chapterVideoFile;
	
	private Date creatTime;
	
	private String creator;
	
	private Date updateTime;

	private String updator;
	
	private int index;
	
	private int plan;
	
	private int plantotal;
	
	private String outline;
	
	private String chapterIndex;
	
	private String status;

	public String getChapterId() {
		return chapterId;
	}

	public void setChapterId(String chapterId) {
		this.chapterId = chapterId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public String getChapterDesc() {
		return chapterDesc;
	}

	public void setChapterDesc(String chapterDesc) {
		this.chapterDesc = chapterDesc;
	}

	public String getChapterVideoFile() {
		return chapterVideoFile;
	}

	public void setChapterVideoFile(String chapterVideoFile) {
		this.chapterVideoFile = chapterVideoFile;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getChapterIndex() {
		return chapterIndex;
	}

	public void setChapterIndex(String chapterIndex) {
		this.chapterIndex = chapterIndex;
	}

	public int getPlan() {
		return plan;
	}

	public void setPlan(int plan) {
		this.plan = plan;
	}

	public int getPlantotal() {
		return plantotal;
	}

	public void setPlantotal(int plantotal) {
		this.plantotal = plantotal;
	}

	public String getOutline() {
		return outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}