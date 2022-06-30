package com.yhy.hzzll.entity;


/**
 * 所有状态合集
 * 
 * @author Yang
 * 
 */
public class AllStatusEntity extends BaseEntity {

	private String id;
	private String status_type;
	private String status_name;
	private String status_value;
	private String deleted;
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus_type() {
		return status_type;
	}

	public void setStatus_type(String status_type) {
		this.status_type = status_type;
	}

	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

	public String getStatus_value() {
		return status_value;
	}

	public void setStatus_value(String status_value) {
		this.status_value = status_value;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
