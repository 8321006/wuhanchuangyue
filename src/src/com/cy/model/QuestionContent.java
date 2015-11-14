package com.cy.model;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("QuestionContent")
public class QuestionContent {

	private String title;
	private String titleImg = "";
	private List<QuestionOption> choiceList;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitleImg() {
		return titleImg;
	}
	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}
	public List<QuestionOption> getChoiceList() {
		return choiceList;
	}
	public void setChoiceList(List<QuestionOption> choiceList) {
		this.choiceList = choiceList;
	}


}
