package com.yhy.hzzll.entity;

/**
 * 首页最新信息 数据
 * 
 * @author wangyang
 * 
 */
public class HomeMessageEntity extends BaseEntity {

	private String id = "";
	private String title = "";
	private String content = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
