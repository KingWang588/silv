package com.yhy.hzzll.entity;

public class ServiceAndQuoteEntity {
	// "id": 86,
	// "price": "200元/10分钟以上",
	// "original_price": "200",
	// "type": 1,
	// "title": "电话咨询",
	// "case_type": 2,
	// "time_unit": "2",
	// "price_type": 0,
	// "price_unit": "1",
	// "charge_type": 0,
	// "remark": "让法律服务市场更加透明化，拉近律师与公众的距离"
	private String id;
	private String price;
	private String original_price;
	private String type;
	private String title;
	private String case_type;
	private String time_unit;
	private String price_type;
	private String price_unit;
	private String charge_type;
	private String remark;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getOriginal_price() {
		return original_price;
	}

	public void setOriginal_price(String original_price) {
		this.original_price = original_price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCase_type() {
		return case_type;
	}

	public void setCase_type(String case_type) {
		this.case_type = case_type;
	}

	public String getTime_unit() {
		return time_unit;
	}

	public void setTime_unit(String time_unit) {
		this.time_unit = time_unit;
	}

	public String getPrice_type() {
		return price_type;
	}

	public void setPrice_type(String price_type) {
		this.price_type = price_type;
	}

	public String getPrice_unit() {
		return price_unit;
	}

	public void setPrice_unit(String price_unit) {
		this.price_unit = price_unit;
	}

	public String getCharge_type() {
		return charge_type;
	}

	public void setCharge_type(String charge_type) {
		this.charge_type = charge_type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
