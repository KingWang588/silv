package com.yhy.hzzll.entity;

/**
 * 首页--专业服务商店数据
 * 
 * @author wangyang
 * 
 */
public class HomeServiceEntity extends BaseEntity {

	/** 服务商ID */
	private String id = "";

	/** 服务商家的logo */
	private String imgUrl = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
