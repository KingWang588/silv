package com.yhy.hzzll.entity;

public class HomeLawyerEntity extends BaseEntity {

	/** 律师ID */
	private String id = "11";

	/** 律师头像 */
	private String headUrl = "http://rescdn.qqmail.com/dyimg/20140409/72B8663B7F23.jpg";

	/** 律师姓名 */
	private String name = "2safsdf";

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
