package com.yhy.hzzll.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 市 --地区
 * 
 * @author Yang
 * 
 */
public class CityEntity extends BaseEntity {

	private String id;
	private String province;
	private String name;
	private List<AreaEntity> areaList = new ArrayList<AreaEntity>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AreaEntity> getAreaList() {
		return areaList;
	}

}
