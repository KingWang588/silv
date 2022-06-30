package com.yhy.hzzll.entity;

/**
 * 通话套餐 购买记录
 * 
 * @author Yang
 * 
 */
public class EvidenceTelephoneMealEntity extends BaseEntity {

	private String combo_name;
	private String paid;
	private String created_at;

	public String getCombo_name() {
		return combo_name;
	}

	public void setCombo_name(String combo_name) {
		this.combo_name = combo_name;
	}

	public String getPaid() {
		return paid;
	}

	public void setPaid(String paid) {
		this.paid = paid;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

}
