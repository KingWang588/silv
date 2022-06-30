package com.yhy.hzzll.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 省--全局
 * 
 * @author Yang
 * 
 */
public class ProvincesEntity extends BaseEntity {

	private String id;
	private String name;
	private List<CityEntity> cityList=new ArrayList<CityEntity>();

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

	public List<CityEntity> getCityList() {
		return cityList;
	}

}