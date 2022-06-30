package com.yhy.hzzll.entity;

import java.util.List;

/**
 * 律师协作详情
 * 
 * @author Yang
 * 
 */
public class CooperateInfoEntity extends BaseEntity {

	private String id;
	private String lawcoop_number;
	private String uid;
	private String deleted;
	private String ctime;
	private String utime;
	private String title;
	private String zqlx;
	private String zqdb;
	private String province;
	private String city;
	private String area;
	private String address;
	private String zqprice;
	private String zqcontent;
	private String name;
	private String mobile;
	private String hash;
	private String thumb;
	private String hot;
	private String top;
	private String hopedate;
	private String source;
	private String hits;
	private String status;
	private String is_pay;
	private String is_giveup;
	private String is_service;
	private String is_reload;
	private String is_regiveup;
	private String service_reload;
	private String abandon_reason;
	private String suretime;
	private String paydone;
	private String real_price;
	private String coupon_list;
	private String is_due;
	private String places;
	private String status_text;
	private String catalog_text;
	private List<imgurl> imgurl;
	private String imgcount;
	private String collect_num;
	private workorderInfo workorderInfo;
	private String login_user_is_joinlawer;
	private String login_user_note;
	private coll_user coll_user;
	private collaboration collaboration;
	private String is_collected;

	public String getIs_collected() {
		return is_collected;
	}

	public void setIs_collected(String is_collected) {
		this.is_collected = is_collected;
	}

	public workorderInfo getWorkorderInfo() {
		return workorderInfo;
	}

	public void setWorkorderInfo(workorderInfo workorderInfo) {
		this.workorderInfo = workorderInfo;
	}

	public List<imgurl> getImgurlList() {
		return imgurl;
	}

	public class imgurl {
		private String id;
		private String uid;
		private String ctime;
		private String hash;
		private String filename;
		private String filetype;
		private String filesize;
		private String url;
		private String b_url;
		private String a_url;
		private String isimage;
		private String thumb;
		private String remote;
		private String aid;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getUid() {
			return uid;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}

		public String getCtime() {
			return ctime;
		}

		public void setCtime(String ctime) {
			this.ctime = ctime;
		}

		public String getHash() {
			return hash;
		}

		public void setHash(String hash) {
			this.hash = hash;
		}

		public String getFilename() {
			return filename;
		}

		public void setFilename(String filename) {
			this.filename = filename;
		}

		public String getFiletype() {
			return filetype;
		}

		public void setFiletype(String filetype) {
			this.filetype = filetype;
		}

		public String getFilesize() {
			return filesize;
		}

		public void setFilesize(String filesize) {
			this.filesize = filesize;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getB_url() {
			return b_url;
		}

		public void setB_url(String b_url) {
			this.b_url = b_url;
		}

		public String getA_url() {
			return a_url;
		}

		public void setA_url(String a_url) {
			this.a_url = a_url;
		}

		public String getIsimage() {
			return isimage;
		}

		public void setIsimage(String isimage) {
			this.isimage = isimage;
		}

		public String getThumb() {
			return thumb;
		}

		public void setThumb(String thumb) {
			this.thumb = thumb;
		}

		public String getRemote() {
			return remote;
		}

		public void setRemote(String remote) {
			this.remote = remote;
		}

		public String getAid() {
			return aid;
		}

		public void setAid(String aid) {
			this.aid = aid;
		}

	}

	public class workorderInfo {// 工单

		private String id;
		private String type;
		private String description;
		private String u_id;
		private String o_id;
		private String event_id;
		private String creator_id;
		private String handler_id;
		private String u_finally;
		private String o_finally;
		private String remark;
		private String ctime;
		private String utime;
		private String is_payed;
		private String publisher;
		private String joiner;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getU_id() {
			return u_id;
		}

		public void setU_id(String u_id) {
			this.u_id = u_id;
		}

		public String getO_id() {
			return o_id;
		}

		public void setO_id(String o_id) {
			this.o_id = o_id;
		}

		public String getEvent_id() {
			return event_id;
		}

		public void setEvent_id(String event_id) {
			this.event_id = event_id;
		}

		public String getCreator_id() {
			return creator_id;
		}

		public void setCreator_id(String creator_id) {
			this.creator_id = creator_id;
		}

		public String getHandler_id() {
			return handler_id;
		}

		public void setHandler_id(String handler_id) {
			this.handler_id = handler_id;
		}

		public String getU_finally() {
			return u_finally;
		}

		public void setU_finally(String u_finally) {
			this.u_finally = u_finally;
		}

		public String getO_finally() {
			return o_finally;
		}

		public void setO_finally(String o_finally) {
			this.o_finally = o_finally;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getCtime() {
			return ctime;
		}

		public void setCtime(String ctime) {
			this.ctime = ctime;
		}

		public String getUtime() {
			return utime;
		}

		public void setUtime(String utime) {
			this.utime = utime;
		}

		public String getIs_payed() {
			return is_payed;
		}

		public void setIs_payed(String is_payed) {
			this.is_payed = is_payed;
		}

		public String getPublisher() {
			return publisher;
		}

		public void setPublisher(String publisher) {
			this.publisher = publisher;
		}

		public String getJoiner() {
			return joiner;
		}

		public void setJoiner(String joiner) {
			this.joiner = joiner;
		}

	}

	public class coll_user {
		private String status;
		private String userid;
		private String name;
		private String mobile;

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getUserid() {
			return userid;
		}

		public void setUserid(String userid) {
			this.userid = userid;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

	}

	public class collaboration {
		private List<list> list;
		private String count;

		public List<list> getList() {
			return list;
		}

		public String getCount() {
			return count;
		}

		public void setCount(String count) {
			this.count = count;
		}

		public class list {
			private String userid;
			private String mobile;
			private String truename;
			private String vemail;
			private String vmobile;
			private String vtruename;
			private String lawfirm;
			private String imgurl;
			private String province;
			private String city;
			private String area;
			private String id;
			private String status;
			private String note;
			private String msgid;
			private String lawid;
			private String is_anonymity;
			private String jointime;
			private String places;

			public String getUserid() {
				return userid;
			}

			public void setUserid(String userid) {
				this.userid = userid;
			}

			public String getMobile() {
				return mobile;
			}

			public void setMobile(String mobile) {
				this.mobile = mobile;
			}

			public String getTruename() {
				return truename;
			}

			public void setTruename(String truename) {
				this.truename = truename;
			}

			public String getVemail() {
				return vemail;
			}

			public void setVemail(String vemail) {
				this.vemail = vemail;
			}

			public String getVmobile() {
				return vmobile;
			}

			public void setVmobile(String vmobile) {
				this.vmobile = vmobile;
			}

			public String getVtruename() {
				return vtruename;
			}

			public void setVtruename(String vtruename) {
				this.vtruename = vtruename;
			}

			public String getLawfirm() {
				return lawfirm;
			}

			public void setLawfirm(String lawfirm) {
				this.lawfirm = lawfirm;
			}

			public String getImgurl() {
				return imgurl;
			}

			public void setImgurl(String imgurl) {
				this.imgurl = imgurl;
			}

			public String getProvince() {
				return province;
			}

			public void setProvince(String province) {
				this.province = province;
			}

			public String getCity() {
				return city;
			}

			public void setCity(String city) {
				this.city = city;
			}

			public String getArea() {
				return area;
			}

			public void setArea(String area) {
				this.area = area;
			}

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public String getStatus() {
				return status;
			}

			public void setStatus(String status) {
				this.status = status;
			}

			public String getNote() {
				return note;
			}

			public void setNote(String note) {
				this.note = note;
			}

			public String getMsgid() {
				return msgid;
			}

			public void setMsgid(String msgid) {
				this.msgid = msgid;
			}

			public String getLawid() {
				return lawid;
			}

			public void setLawid(String lawid) {
				this.lawid = lawid;
			}

			public String getIs_anonymity() {
				return is_anonymity;
			}

			public void setIs_anonymity(String is_anonymity) {
				this.is_anonymity = is_anonymity;
			}

			public String getJointime() {
				return jointime;
			}

			public void setJointime(String jointime) {
				this.jointime = jointime;
			}

			public String getPlaces() {
				return places;
			}

			public void setPlaces(String places) {
				this.places = places;
			}
		}
	}

	private String is_comment;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLawcoop_number() {
		return lawcoop_number;
	}

	public void setLawcoop_number(String lawcoop_number) {
		this.lawcoop_number = lawcoop_number;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
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

	public String getZqlx() {
		return zqlx;
	}

	public void setZqlx(String zqlx) {
		this.zqlx = zqlx;
	}

	public String getZqdb() {
		return zqdb;
	}

	public void setZqdb(String zqdb) {
		this.zqdb = zqdb;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZqprice() {
		return zqprice;
	}

	public void setZqprice(String zqprice) {
		this.zqprice = zqprice;
	}

	public String getZqcontent() {
		return zqcontent;
	}

	public void setZqcontent(String zqcontent) {
		this.zqcontent = zqcontent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getHot() {
		return hot;
	}

	public void setHot(String hot) {
		this.hot = hot;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getHopedate() {
		return hopedate;
	}

	public void setHopedate(String hopedate) {
		this.hopedate = hopedate;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getHits() {
		return hits;
	}

	public void setHits(String hits) {
		this.hits = hits;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIs_pay() {
		return is_pay;
	}

	public void setIs_pay(String is_pay) {
		this.is_pay = is_pay;
	}

	public String getIs_giveup() {
		return is_giveup;
	}

	public void setIs_giveup(String is_giveup) {
		this.is_giveup = is_giveup;
	}

	public String getIs_service() {
		return is_service;
	}

	public void setIs_service(String is_service) {
		this.is_service = is_service;
	}

	public String getIs_reload() {
		return is_reload;
	}

	public void setIs_reload(String is_reload) {
		this.is_reload = is_reload;
	}

	public String getIs_regiveup() {
		return is_regiveup;
	}

	public void setIs_regiveup(String is_regiveup) {
		this.is_regiveup = is_regiveup;
	}

	public String getService_reload() {
		return service_reload;
	}

	public void setService_reload(String service_reload) {
		this.service_reload = service_reload;
	}

	public String getAbandon_reason() {
		return abandon_reason;
	}

	public void setAbandon_reason(String abandon_reason) {
		this.abandon_reason = abandon_reason;
	}

	public String getSuretime() {
		return suretime;
	}

	public void setSuretime(String suretime) {
		this.suretime = suretime;
	}

	public String getPaydone() {
		return paydone;
	}

	public void setPaydone(String paydone) {
		this.paydone = paydone;
	}

	public String getReal_price() {
		return real_price;
	}

	public void setReal_price(String real_price) {
		this.real_price = real_price;
	}

	public String getCoupon_list() {
		return coupon_list;
	}

	public void setCoupon_list(String coupon_list) {
		this.coupon_list = coupon_list;
	}

	public String getIs_due() {
		return is_due;
	}

	public void setIs_due(String is_due) {
		this.is_due = is_due;
	}

	public String getPlaces() {
		return places;
	}

	public void setPlaces(String places) {
		this.places = places;
	}

	public String getStatus_text() {
		return status_text;
	}

	public void setStatus_text(String status_text) {
		this.status_text = status_text;
	}

	public String getCatalog_text() {
		return catalog_text;
	}

	public void setCatalog_text(String catalog_text) {
		this.catalog_text = catalog_text;
	}

	// public String getImgurl() {
	// return imgurl;
	// }
	//
	// public void setImgurl(String imgurl) {
	// this.imgurl = imgurl;
	// }

	public String getImgcount() {
		return imgcount;
	}

	public void setImgcount(String imgcount) {
		this.imgcount = imgcount;
	}

	public String getCollect_num() {
		return collect_num;
	}

	public void setCollect_num(String collect_num) {
		this.collect_num = collect_num;
	}

	public String getLogin_user_is_joinlawer() {
		return login_user_is_joinlawer;
	}

	public void setLogin_user_is_joinlawer(String login_user_is_joinlawer) {
		this.login_user_is_joinlawer = login_user_is_joinlawer;
	}

	public String getLogin_user_note() {
		return login_user_note;
	}

	public void setLogin_user_note(String login_user_note) {
		this.login_user_note = login_user_note;
	}

	public coll_user getColl_user() {
		return coll_user;
	}

	public void setColl_user(coll_user coll_user) {
		this.coll_user = coll_user;
	}

	public collaboration getCollaboration() {
		return collaboration;
	}

	public void setCollaboration(collaboration collaboration) {
		this.collaboration = collaboration;
	}

	public String getIs_comment() {
		return is_comment;
	}

	public void setIs_comment(String is_comment) {
		this.is_comment = is_comment;
	}

}
