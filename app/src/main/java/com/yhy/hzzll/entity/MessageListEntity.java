package com.yhy.hzzll.entity;

import java.io.Serializable;
import java.util.List;
/**
 * 消息列表
 * @author 一合鱼
 *
 */
public class MessageListEntity implements Serializable{

	/**
	 * userid : 12946 nickname : 华债网用户 imgurl :
	 * /Uploads/users/12946/20160923/0d0251727e0faff064ae19342d33db69/file_c.png
	 * ctime : 1474452691 newmsg :
	 * [{"id":"778","userid":"12946","person":"10058","is_mine":"0","msg":"13246","ctime":"1474452691","type":"msg","coop_title":null,"coop_id":null},{"id":"718","userid":"12946","person":"10058","is_mine":"0","msg":"哈哈","ctime":"1474425710","type":"msg","coop_title":null,"coop_id":null},{"id":"716","userid":"12946","person":"10058","is_mine":"0","msg":"哈哈","ctime":"1474425567","type":"msg","coop_title":null,"coop_id":null},{"id":"703","userid":"12946","person":"10058","is_mine":"0","msg":"。。。","ctime":"1474380509","type":"msg","coop_title":null,"coop_id":null},{"id":"668","userid":"12946","person":"10058","is_mine":"0","msg":"在不啊","ctime":"1474366780","type":"msg","coop_title":null,"coop_id":null},{"id":"666","userid":"12946","person":"10058","is_mine":"0","msg":"。。。。。。。","ctime":"1474365004","type":"msg","coop_title":null,"coop_id":null},{"id":"664","userid":"12946","person":"10058","is_mine":"0","msg":"你。。。。。。","ctime":"1474365002","type":"msg","coop_title":null,"coop_id":null},{"id":"662","userid":"12946","person":"10058","is_mine":"0","msg":"人呢？？","ctime":"1474364990","type":"msg","coop_title":null,"coop_id":null},{"id":"660","userid":"12946","person":"10058","is_mine":"0","msg":"来啊
	 * 互相伤害啊","ctime":"1474364349","type":"msg","coop_title":null,"coop_id":null}]
	 */

	private String userid;
	private String nickname;
	private String imgurl;
	private String ctime;
	/**
	 * id : 778 userid : 12946 person : 10058 is_mine : 0 msg : 13246 ctime :
	 * 1474452691 type : msg coop_title : null coop_id : null
	 */

	private List<NewmsgBean> newmsg;

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

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public List<NewmsgBean> getNewmsg() {
		return newmsg;
	}

	public void setNewmsg(List<NewmsgBean> newmsg) {
		this.newmsg = newmsg;
	}

	public static class NewmsgBean implements Serializable{
		private String id;
		private String userid;
		private String person;
		private String is_mine;
		private String msg;
		private String ctime;
		private String type;
		private String coop_title;
		private String coop_id;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getUserid() {
			return userid;
		}

		public void setUserid(String userid) {
			this.userid = userid;
		}

		public String getPerson() {
			return person;
		}

		public void setPerson(String person) {
			this.person = person;
		}

		public String getIs_mine() {
			return is_mine;
		}

		public void setIs_mine(String is_mine) {
			this.is_mine = is_mine;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public String getCtime() {
			return ctime;
		}

		public void setCtime(String ctime) {
			this.ctime = ctime;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getCoop_title() {
			return coop_title;
		}

		public void setCoop_title(String coop_title) {
			this.coop_title = coop_title;
		}

		public String getCoop_id() {
			return coop_id;
		}

		public void setCoop_id(String coop_id) {
			this.coop_id = coop_id;
		}
	}

}
