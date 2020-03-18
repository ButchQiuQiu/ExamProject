package com.bdqn.data.bean;

import com.bdqn.data.Bean;

public class Exampaper extends Bean{
	private Integer id;
	private String name;
	private String qids;
	private Double score;
	private Integer count;
	private Integer time;
	private Integer classid;
	private String date;
	public final static String tablename="exampaper";
	public Exampaper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Exampaper(Integer id, String name, String qids, double score, Integer count, Integer time, Integer classid,
			String date) {
		super();
		this.id = id;
		this.name = name;
		this.qids = qids;
		this.score = score;
		this.count = count;
		this.time = time;
		this.classid = classid;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQids() {
		return qids;
	}

	public void setQids(String qids) {
		this.qids = qids;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Integer getClassid() {
		return classid;
	}

	public void setClassid(Integer classid) {
		this.classid = classid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	
	
}
