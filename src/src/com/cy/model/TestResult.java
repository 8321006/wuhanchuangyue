package com.cy.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("TestResult")
public class TestResult implements Serializable {
	/** test_id -- 主鍵ID */
	private String testId;

	/** paper_id -- 试卷ID */
	private String paperId;
	
	/** paper_id -- 试卷NAME */
	private String paperName;

	/** class_id -- 班級 */
	private String classId;

	/** school_id -- 學校 */
	private Integer schoolId;

	/** startTime -- 開始答題時間 */
	private Date startTime;

	/** endTime -- 結束答題時間 */
	private Date endTime;
	
	/** TestTotal -- 已经交卷人数 */
	private Integer TestTotal;
	
	/** TestDelay -- 迟到交卷人数 */
	private Integer TestDelay;
	
	/** MarkTotal -- 已经批阅人数 */
	private Integer MarkTotal;
	
	/** total -- 应该交卷人数 */
	private Integer total;


	public String getTestId() {
		return testId;
	}


	public void setTestId(String testId) {
		this.testId = testId;
	}


	public String getPaperId() {
		return paperId;
	}


	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}


	public String getPaperName() {
		return paperName;
	}


	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}


	public String getClassId() {
		return classId;
	}


	public void setClassId(String classId) {
		this.classId = classId;
	}


	public Integer getSchoolId() {
		return schoolId;
	}


	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}


	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public Integer getTestTotal() {
		return TestTotal;
	}


	public void setTestTotal(Integer testTotal) {
		TestTotal = testTotal;
	}


	public Integer getTestDelay() {
		return TestDelay;
	}


	public void setTestDelay(Integer testDelay) {
		TestDelay = testDelay;
	}


	public Integer getMarkTotal() {
		return MarkTotal;
	}


	public void setMarkTotal(Integer markTotal) {
		MarkTotal = markTotal;
	}


	public Integer getTotal() {
		return total;
	}


	public void setTotal(Integer total) {
		this.total = total;
	}


	private static final long serialVersionUID = 1L;

}