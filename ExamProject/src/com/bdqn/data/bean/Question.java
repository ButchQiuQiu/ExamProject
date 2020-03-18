package com.bdqn.data.bean;

import com.bdqn.data.Bean;

public class Question extends Bean{
	private Integer id;
	private String title;
	private String option;
	private String solution;
	private String analysis;
	private Integer chapid;
	public final static String tablename="question";
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Question(Integer id, String title, String option, String solution, String analysis, Integer chapid) {
		super();
		this.id = id;
		this.title = title;
		this.option = option;
		this.solution = solution;
		this.analysis = analysis;
		this.chapid = chapid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	public Integer getChapid() {
		return chapid;
	}
	public void setChapid(Integer chapid) {
		this.chapid = chapid;
	}
	
}
