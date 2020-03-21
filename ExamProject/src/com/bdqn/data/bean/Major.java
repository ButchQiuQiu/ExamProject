package com.bdqn.data.bean;

import com.bdqn.data.Bean;

public class Major extends Bean{
	private Integer id;
	private String name;
	public final static String tablename="major";
	public Major() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Major(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	
}
