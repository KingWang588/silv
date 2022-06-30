package com.yhy.hzzll.entity;

import java.io.Serializable;

/**
 * 关联案件--当事人
 * 
 * @author Yang
 * 
 */
public class CaseManageConectEntity extends BaseEntity implements Serializable {

	/**
	 * customer_id : 30 is_member : 华债网会员 customer_name : 北京华 customer_contact :
	 * 15217127258 ctime : 2016-05-20 count : 2 member_status : 1
	 */

	private String customer_id;
	private String is_member;
	private String customer_name;
	private String customer_contact;
	private String ctime;
	private int count;
	private String member_status;
	private boolean isCheck;

	
	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getIs_member() {
		return is_member;
	}

	public void setIs_member(String is_member) {
		this.is_member = is_member;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_contact() {
		return customer_contact;
	}

	public void setCustomer_contact(String customer_contact) {
		this.customer_contact = customer_contact;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getMember_status() {
		return member_status;
	}

	public void setMember_status(String member_status) {
		this.member_status = member_status;
	}

}
