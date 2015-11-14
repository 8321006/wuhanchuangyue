package com.cy.model;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_course_chapter
 * @since 2015-06-08 15:47:16
 */
@Alias("t_course_resource")
public class CourseResource implements Serializable {
	
	private String courseId;
	
	private String resourceId;

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	
}