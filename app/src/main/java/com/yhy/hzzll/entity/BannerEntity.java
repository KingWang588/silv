package com.yhy.hzzll.entity;

import java.util.List;

/**
 * banner
 * 
 * @author Yang
 * 
 */
public class BannerEntity extends BaseEntity {

	/**
	 * code : 0
	 * message : 查询成功
	 * data : [{"id":"5","title":"","target":"","imgurl":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/3/20170628/863b8a29841d5a0df3667987274cfde7.jpg"},{"id":"4","title":"","target":"","imgurl":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/3/20170628/c07d4afd72311a599f5eecd576d61d60.jpg"},{"id":"3","title":"","target":"","imgurl":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/3/20170628/e9e477c8d11d95282e497227a5101ab7.jpg"},{"id":"2","title":"","target":"","imgurl":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/1/20170621/c13c4158f2bd5591b6ccaea3038c7fc9.png"},{"id":"1","title":"测试Banner","target":"","imgurl":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/1/20170621/13ea427a3ce52cf1ad2cf451f1a3e750.jpg"}]
	 */

	private int code;
	private String message;
	private List<DataBean> data;

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

	public List<DataBean> getData() {
		return data;
	}

	public void setData(List<DataBean> data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 * id : 5
		 * title :
		 * target :
		 * imgurl : http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/3/20170628/863b8a29841d5a0df3667987274cfde7.jpg
		 */

		private String id;
		private String title;
		private String target;
		private String imgurl;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getTarget() {
			return target;
		}

		public void setTarget(String target) {
			this.target = target;
		}

		public String getImgurl() {
			return imgurl;
		}

		public void setImgurl(String imgurl) {
			this.imgurl = imgurl;
		}
	}
}
