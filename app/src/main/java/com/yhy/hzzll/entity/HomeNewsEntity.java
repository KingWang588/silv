package com.yhy.hzzll.entity;

/**
 * 首页--新闻动态
 * 
 * @author wangyang
 * 
 */
public class HomeNewsEntity extends BaseEntity {

	private String id = "";

	private String content = "";

	private String date = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
