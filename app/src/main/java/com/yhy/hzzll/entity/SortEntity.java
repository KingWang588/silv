package com.yhy.hzzll.entity;

import java.io.Serializable;

/**
 * 排序实体
 * 
 * @author Yang
 * 
 */
public class SortEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String id;
	private String tag;
	private boolean select;

	public SortEntity() {
		// TODO Auto-generated constructor stub
	}

	public SortEntity(String title, String id, boolean select) {
		this.title = title;
		this.id = id;
		this.select = select;
	}

	public SortEntity(String title, String id,String tag, boolean select) {
		this.id = id;
		this.title = title;
		this.tag = tag;
		this.select = select;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

}
