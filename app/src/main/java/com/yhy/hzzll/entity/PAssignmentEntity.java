package com.yhy.hzzll.entity;

/**
 * 
 * 债权转让
 *
 */
public class PAssignmentEntity extends BaseEntity {
	/*
	 * "id": "305", "utime": "2016-06-01 09:12:34", "title": "阿斯顿",
	 * "zrprice":"2.00万元", "ctime": "2016-06-01", "zqprice": "1.00"
	 */

	private String id;
	private String utime;
	private String title;
	private String zrprice;
	private String ctime;
	private String zqprice;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUtime() {
		return utime;
	}

	public void setUtime(String utime) {
		this.utime = utime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getZrprice() {
		return zrprice;
	}

	public void setZrprice(String zrprice) {
		this.zrprice = zrprice;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getZqprice() {
		return zqprice;
	}

	public void setZqprice(String zqprice) {
		this.zqprice = zqprice;
	}

}
