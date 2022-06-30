package com.yhy.hzzll.entity;

/**
 * 个人信息
 * 
 * @author Yang
 * 
 */
public class PersonEntity extends BaseEntity {

	private String userid;
	private String nickname;
	private String imgurl;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

}
