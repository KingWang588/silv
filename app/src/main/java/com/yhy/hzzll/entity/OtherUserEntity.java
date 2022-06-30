package com.yhy.hzzll.entity;

/**
 * 其他用户的个人信息
 * 
 * @author Yang
 * 
 */
public class OtherUserEntity extends BaseEntity {

	// "userid": "10058",
	// "nickname": "肖筱 律师",
	// "utype": "1",
	// "vtruename": "1",
	// "imgurl":
	// "/Uploads/users/10058/20161017/6c77340ae2bf33b6aab3d0743613095b/file_c.png",
	// "collectiong ": {
	// "code": "000000",
	// "msg": "查询成功",
	// "data": "1"

	private String userid = "";
	private String nickname = "";
	private String truename = "";
	private String utype = "";
	private String vtruename = "";
	private String imgurl = "";

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

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

	public String getUtype() {
		return utype;
	}

	public void setUtype(String utype) {
		this.utype = utype;
	}

	public String getVtruename() {
		return vtruename;
	}

	public void setVtruename(String vtruename) {
		this.vtruename = vtruename;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

}
