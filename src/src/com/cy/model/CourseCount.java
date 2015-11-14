package com.cy.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_course
 * @since 2015-06-08 15:47:16
 */
@Alias("t_course_count")
public class CourseCount implements Serializable {
	
	public Integer learncount;
	
	public Integer collectcount;
	
	public Integer overcount;

	public Integer getLearncount() {
		return learncount;
	}

	public void setLearncount(Integer learncount) {
		this.learncount = learncount;
	}

	public Integer getCollectcount() {
		return collectcount;
	}

	public void setCollectcount(Integer collectcount) {
		this.collectcount = collectcount;
	}

	public Integer getOvercount() {
		return overcount;
	}

	public void setOvercount(Integer overcount) {
		this.overcount = overcount;
	}
	
}