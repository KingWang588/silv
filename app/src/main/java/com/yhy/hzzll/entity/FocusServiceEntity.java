package com.yhy.hzzll.entity;

/**
 * 我的关注 --服务
 * 
 * @author Yang
 * 
 */
public class FocusServiceEntity extends BaseEntity {

	private String id;
	private String uid;
	private String cate;
	private String url;
	private String nid;
	private String addtime;
	private String truename;
	private String name;
	private String type_name;
	private String price;
	private String unit;
	private list list;

	public list getList() {
		return list;
	}

	public void setList(list list) {
		this.list = list;
	}

	public class list {
		private String truename;
		private String mobile;
		private String id;
		private String uid;
		private String shop_type;
		private String type;
		private String name;
		private String unit;
		private String price;
		private String intro;
		private String content;
		private String imgurl;
		private String thumb_list;
		private String ctime;
		private String deleted;
		private String hits;
		private String goods_type;
		private String activity_stime;
		private String activity_etime;
		private String type_name;

		public String getTruename() {
			return truename;
		}

		public void setTruename(String truename) {
			this.truename = truename;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

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

		public String getShop_type() {
			return shop_type;
		}

		public void setShop_type(String shop_type) {
			this.shop_type = shop_type;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public String getIntro() {
			return intro;
		}

		public void setIntro(String intro) {
			this.intro = intro;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getImgurl() {
			return imgurl;
		}

		public void setImgurl(String imgurl) {
			this.imgurl = imgurl;
		}

		public String getThumb_list() {
			return thumb_list;
		}

		public void setThumb_list(String thumb_list) {
			this.thumb_list = thumb_list;
		}

		public String getCtime() {
			return ctime;
		}

		public void setCtime(String ctime) {
			this.ctime = ctime;
		}

		public String getDeleted() {
			return deleted;
		}

		public void setDeleted(String deleted) {
			this.deleted = deleted;
		}

		public String getHits() {
			return hits;
		}

		public void setHits(String hits) {
			this.hits = hits;
		}

		public String getGoods_type() {
			return goods_type;
		}

		public void setGoods_type(String goods_type) {
			this.goods_type = goods_type;
		}

		public String getActivity_stime() {
			return activity_stime;
		}

		public void setActivity_stime(String activity_stime) {
			this.activity_stime = activity_stime;
		}

		public String getActivity_etime() {
			return activity_etime;
		}

		public void setActivity_etime(String activity_etime) {
			this.activity_etime = activity_etime;
		}

		public String getType_name() {
			return type_name;
		}

		public void setType_name(String type_name) {
			this.type_name = type_name;
		}

	}

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

	public String getCate() {
		return cate;
	}

	public void setCate(String cate) {
		this.cate = cate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
