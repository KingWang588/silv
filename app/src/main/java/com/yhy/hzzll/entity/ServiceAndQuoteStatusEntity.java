package com.yhy.hzzll.entity;

public class ServiceAndQuoteStatusEntity {

	private String id;
	private String name;

	public ServiceAndQuoteStatusEntity() {

	}

	public ServiceAndQuoteStatusEntity(String id, String name) {
		if (id != null) {
			this.id = id;
		} else {
			this.id = "";
		}
		if (name != null) {
			this.name = name;
		} else {
			this.name = "";
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
