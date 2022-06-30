package com.yhy.hzzll.entity;

import java.util.List;

/**
 * 我的关注--我的私粉
 * 
 * @author wangyang
 * 
 */
public class FocusListEntity extends BaseEntity {

	/**
	 * code : 0
	 * message : 查找成功！
	 * data : {"pages":1,"total":3,"list":[{"user_id":386,"user_type":"0","nickname":"6598","head_img":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/386/20170720/60124f401a8ba26c7e53d9c5c9e5341c.png","base_region":"辽宁省,沈阳市,和平区"},{"user_id":387,"user_type":"0","nickname":"safsafdasf","head_img":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/387/20170718/db5881fa0f0c2b1fa12ffd2e8a16380a.jpg","base_region":"山西省,太原市,小店区"},{"user_id":248,"user_type":"0","nickname":"何虎","head_img":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/248/20170726/2287ceb0fbfde0e5953c244e8da133c9.jpg","base_region":"浙江省,杭州市,上城区"}]}
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
		 * total : 3
		 * list : [{"user_id":386,"user_type":"0","nickname":"6598","head_img":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/386/20170720/60124f401a8ba26c7e53d9c5c9e5341c.png","base_region":"辽宁省,沈阳市,和平区"},{"user_id":387,"user_type":"0","nickname":"safsafdasf","head_img":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/387/20170718/db5881fa0f0c2b1fa12ffd2e8a16380a.jpg","base_region":"山西省,太原市,小店区"},{"user_id":248,"user_type":"0","nickname":"何虎","head_img":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/248/20170726/2287ceb0fbfde0e5953c244e8da133c9.jpg","base_region":"浙江省,杭州市,上城区"}]
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
			 *
			 * followed_id
			 * user_id : 386
			 * user_type : 0
			 * nickname : 6598
			 * head_img : http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/386/20170720/60124f401a8ba26c7e53d9c5c9e5341c.png
			 * base_region : 辽宁省,沈阳市,和平区
			 */

			private int user_id;
			private int followed_id;

			public int getFollowed_id() {
				return followed_id;
			}

			public void setFollowed_id(int followed_id) {
				this.followed_id = followed_id;
			}

			private String user_type;
			private String nickname;
			private String head_img;
			private String base_region;

			public int getUser_id() {
				return user_id;
			}

			public void setUser_id(int user_id) {
				this.user_id = user_id;
			}

			public String getUser_type() {
				return user_type;
			}

			public void setUser_type(String user_type) {
				this.user_type = user_type;
			}

			public String getNickname() {
				return nickname;
			}

			public void setNickname(String nickname) {
				this.nickname = nickname;
			}

			public String getHead_img() {
				return head_img;
			}

			public void setHead_img(String head_img) {
				this.head_img = head_img;
			}

			public String getBase_region() {
				return base_region;
			}

			public void setBase_region(String base_region) {
				this.base_region = base_region;
			}
		}
	}
}
