package com.bdqn.data.bean;

import com.bdqn.data.Bean;

public class Classes extends Bean{
	private Integer id;
	private String name;
	private Integer mid;
	public final static String tablename="classes";
	public Classes() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Classes(Integer id, String name, Integer mid) {
		super();
		this.id = id;
		this.name = name;
		this.mid = mid;
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
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	
}
