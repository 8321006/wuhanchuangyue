package com.cy.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("CourseScoreResult")
public class CourseScoreResult implements Serializable {
	
	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	
	private static final long serialVersionUID = 7891016763256623038L;

	private Float videoScore;
	
	private Float homeWorkScore;
	
	private Float paperScore;
	
	private Float totalScore;

	private Float videoRatio;
	
	private Float workRatio;
	
	private Float examRatio;

	public Float getVideoScore() {
		return videoScore;
	}

	public void setVideoScore(Float videoScore) {
		this.videoScore = videoScore;
	}

	public Float getHomeWorkScore() {
		return homeWorkScore;
	}

	public void setHomeWorkScore(Float homeWorkScore) {
		this.homeWorkScore = homeWorkScore;
	}

	public Float getPaperScore() {
		return paperScore;
	}

	public void setPaperScore(Float paperScore) {
		this.paperScore = paperScore;
	}

	public Float getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Float totalScore) {
		this.totalScore = totalScore;
	}

	public Float getVideoRatio() {
		return videoRatio;
	}

	public void setVideoRatio(Float videoRatio) {
		this.videoRatio = videoRatio;
	}

	public Float getWorkRatio() {
		return workRatio;
	}

	public void setWorkRatio(Float workRatio) {
		this.workRatio = workRatio;
	}

	public Float getExamRatio() {
		return examRatio;
	}

	public void setExamRatio(Float examRatio) {
		this.examRatio = examRatio;
	}
	
}