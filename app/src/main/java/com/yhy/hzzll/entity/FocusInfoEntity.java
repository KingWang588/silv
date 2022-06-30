package com.yhy.hzzll.entity;

import java.util.List;

/**
 * 关注的信息
 * 
 * @author Yang
 * 
 */
public class FocusInfoEntity extends BaseEntity {
	/**
	 * code : 0
	 * message : 查找成功！
	 * data : {"pages":1,"total":6,"list":[{"id":30,"content":"你想怎么样。1554阿瓦打我","head_img":"http://silvapi.hzzll.com/img/avatar.jpg","updated_at":"2017-08-07 11:01:49"},{"id":41,"content":"来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三","head_img":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/1/20170621/13ea427a3ce52cf1ad2cf451f1a3e750.jpg","updated_at":"2017-08-08 16:31:15"},{"id":37,"content":"繁华大道多多多多多多多多阿达瓦阿瓦打我多","head_img":"http://silvapi.hzzll.com/img/avatar.jpg","updated_at":"2017-08-08 16:33:37"},{"id":45,"content":"多多多多多多多多阿达瓦阿瓦打我多","head_img":"http://silvapi.hzzll.com/img/avatar.jpg","updated_at":"2017-08-08 17:00:39"},{"id":24,"content":"你想怎么样。1554阿瓦打我","head_img":"http://silvapi.hzzll.com/img/avatar.jpg","updated_at":"2017-08-08 10:43:03"},{"id":50,"content":"3123大大实打实的dasd asdsa das das dadasd asd asdasdasdas d das das","head_img":"http://silvapi.hzzll.com/img/avatar.jpg","updated_at":"2017-08-08 19:49:46"}]}
	 */

	private int code;
	private String message;
	private DataBean data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 * pages : 1
		 * total : 6
		 * list : [{"id":30,"content":"你想怎么样。1554阿瓦打我","head_img":"http://silvapi.hzzll.com/img/avatar.jpg","updated_at":"2017-08-07 11:01:49"},{"id":41,"content":"来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三来来大撒大撒大撒大撒打算打算的撒打算的阿三","head_img":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/1/20170621/13ea427a3ce52cf1ad2cf451f1a3e750.jpg","updated_at":"2017-08-08 16:31:15"},{"id":37,"content":"繁华大道多多多多多多多多阿达瓦阿瓦打我多","head_img":"http://silvapi.hzzll.com/img/avatar.jpg","updated_at":"2017-08-08 16:33:37"},{"id":45,"content":"多多多多多多多多阿达瓦阿瓦打我多","head_img":"http://silvapi.hzzll.com/img/avatar.jpg","updated_at":"2017-08-08 17:00:39"},{"id":24,"content":"你想怎么样。1554阿瓦打我","head_img":"http://silvapi.hzzll.com/img/avatar.jpg","updated_at":"2017-08-08 10:43:03"},{"id":50,"content":"3123大大实打实的dasd asdsa das das dadasd asd asdasdasdas d das das","head_img":"http://silvapi.hzzll.com/img/avatar.jpg","updated_at":"2017-08-08 19:49:46"}]
		 */

		private int pages;
		private int total;
		private List<ListBean> list;

		public int getPages() {
			return pages;
		}

		public void setPages(int pages) {
			this.pages = pages;
		}

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}

		public List<ListBean> getList() {
			return list;
		}

		public void setList(List<ListBean> list) {
			this.list = list;
		}

		public static class ListBean {
			/**
			 * id : 30
			 * content : 你想怎么样。1554阿瓦打我
			 * head_img : http://silvapi.hzzll.com/img/avatar.jpg
			 * updated_at : 2017-08-07 11:01:49
			 */

			private int id;
			private String type_id;

			public String getType_id() {
				return type_id;
			}

			private String content;
			private String head_img;
			private String updated_at;
			private String nickname;

			public String getNickname() {
				return nickname;
			}

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public String getContent() {
				return content;
			}

			public void setContent(String content) {
				this.content = content;
			}

			public String getHead_img() {
				return head_img;
			}

			public void setHead_img(String head_img) {
				this.head_img = head_img;
			}

			public String getUpdated_at() {
				return updated_at;
			}

			public void setUpdated_at(String updated_at) {
				this.updated_at = updated_at;
			}
		}
	}


//	private String id;
//	private String uid;
//	private String cate;
//	private String url;
//	private String nid;
//	private String addtime;
//	private String title;
//	private String username;
//	private String imgurl;
//	private String cate_name;
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getImgurl() {
//		return imgurl;
//	}
//
//	public void setImgurl(String imgurl) {
//		this.imgurl = imgurl;
//	}
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public String getUid() {
//		return uid;
//	}
//
//	public void setUid(String uid) {
//		this.uid = uid;
//	}
//
//	public String getCate() {
//		return cate;
//	}
//
//	public void setCate(String cate) {
//		this.cate = cate;
//	}
//
//	public String getUrl() {
//		return url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}
//
//	public String getNid() {
//		return nid;
//	}
//
//	public void setNid(String nid) {
//		this.nid = nid;
//	}
//
//	public String getAddtime() {
//		return addtime;
//	}
//
//	public void setAddtime(String addtime) {
//		this.addtime = addtime;
//	}
//
//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getCate_name() {
//		return cate_name;
//	}
//
//	public void setCate_name(String cate_name) {
//		this.cate_name = cate_name;
//	}

}
