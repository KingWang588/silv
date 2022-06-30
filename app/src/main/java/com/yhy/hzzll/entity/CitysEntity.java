package com.yhy.hzzll.entity;

/**
 * 市 的实体
 * 
 * @author Yang
 * 
 */
public class CitysEntity extends BaseEntity {

	private String id;
	private String name;
	private String province;

	public CitysEntity() {
		// TODO Auto-generated constructor stub
	}

	public CitysEntity(String id, String name, String province) {
		this.id = id;
		this.name = name;
		this.province = province;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

}
