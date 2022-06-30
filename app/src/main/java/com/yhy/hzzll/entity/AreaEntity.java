package com.yhy.hzzll.entity;


/**
 * 地区选择
 * 
 * @author Yang
 * 
 */
public class AreaEntity extends BaseEntity {

	private String id;
	private String city;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
