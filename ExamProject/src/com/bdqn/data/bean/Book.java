package com.bdqn.data.bean;

import com.bdqn.data.Bean;

public class Book extends Bean{
	private Integer id;
	private String name;
	public final static String tablename="book";
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(Integer id, String name) {
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
