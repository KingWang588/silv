package com.yhy.hzzll.entity;

public class PhoneOrderDetailEntity {
	

	/**
	 * orderno : OL2016091710210250 
	 * amount : 200.00 
	 * title : 电话咨询复杂案件
	 *  created_at: 2016-09-17 19:20:15
	 *   pay_time : 1970-01-01 08:00:00 
	 *   lawyer : 肖筱 users :
	 * 华债网用户 status : 0 p_orderid :
	 */

	private String orderno;
	private String amount;
	private String title;
	private String created_at;
	private String pay_time;
	private String lawyer;
	private String users;
	private String mobile;
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	private String status;
	private String p_orderid;

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getPay_time() {
		return pay_time;
	}

	public void setPay_time(String pay_time) {
		this.pay_time = pay_time;
	}

	public String getLawyer() {
		return lawyer;
	}

	public void setLawyer(String lawyer) {
		this.lawyer = lawyer;
	}

	public String getUsers() {
		return users;
	}

	public void setUsers(String users) {
		this.users = users;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getP_orderid() {
		return p_orderid;
	}

	public void setP_orderid(String p_orderid) {
		this.p_orderid = p_orderid;
	}

}
