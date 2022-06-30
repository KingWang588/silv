package com.yhy.hzzll.entity;

/**
 * 省的实体
 * 
 * @author Yang
 * 
 */
public class ProvinceEntity extends BaseEntity {

	private String id;
	private String name;

	public ProvinceEntity() {
		// TODO Auto-generated constructor stub
	}

	public ProvinceEntity(String id, String name) {
		this.id = id;
		this.name = name;
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
