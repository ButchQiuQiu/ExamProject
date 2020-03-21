package com.bdqn.data.bean;

import com.bdqn.data.Bean;

public class Result extends Bean{
	private Integer id;
	private Integer stuid;
	private String examname;
	private String option;
	private Double score;
	private String datetime;
	public final static String tablename="result";
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Result(Integer id, Integer stuid, String examname, String option, Double score, String datetime) {
		super();
		this.id = id;
		this.stuid = stuid;
		this.examname = examname;
		this.option = option;
		this.score = score;
		this.datetime = datetime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStuid() {
		return stuid;
	}
	public void setStuid(Integer stuid) {
		this.stuid = stuid;
	}
	public String getExamname() {
		return examname;
	}
	public void setExamname(String examname) {
		this.examname = examname;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
}
