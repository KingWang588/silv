package com.yhy.hzzll.entity;

import java.util.List;

public class MemberCommentEntity {

	/**
	 * code : 0
	 * message : 查询成功!
	 * data : {"pages":1,"total":3,"list":[{"id":5,"reply_id":3,"users_id":1,"evaluated_id":308,"content":"","start_rate":4,"type_id":1,"created_at":"2017-07-18 19:43:52","updated_at":"2017-07-24 16:15:49","name":"blues.deng","head_img":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/1/20170621/13ea427a3ce52cf1ad2cf451f1a3e750.jpg","type":"快速咨询"},{"id":4,"reply_id":2,"users_id":2,"evaluated_id":308,"content":"2220","start_rate":6,"type_id":2,"created_at":"2017-07-18 19:43:45","updated_at":"2017-07-24 16:12:04","name":"1223","head_img":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/1/20170621/c13c4158f2bd5591b6ccaea3038c7fc9.png","type":"专属咨询"},{"id":3,"reply_id":1,"users_id":1,"evaluated_id":308,"content":"啊","start_rate":4,"type_id":2,"created_at":"2017-07-18 19:43:35","updated_at":"2017-07-24 16:12:02","name":"blues.deng","head_img":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/1/20170621/13ea427a3ce52cf1ad2cf451f1a3e750.jpg","type":"专属咨询"}]}
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
		 * list : [{"id":5,"reply_id":3,"users_id":1,"evaluated_id":308,"content":"","start_rate":4,"type_id":1,"created_at":"2017-07-18 19:43:52","updated_at":"2017-07-24 16:15:49","name":"blues.deng","head_img":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/1/20170621/13ea427a3ce52cf1ad2cf451f1a3e750.jpg","type":"快速咨询"},{"id":4,"reply_id":2,"users_id":2,"evaluated_id":308,"content":"2220","start_rate":6,"type_id":2,"created_at":"2017-07-18 19:43:45","updated_at":"2017-07-24 16:12:04","name":"1223","head_img":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/1/20170621/c13c4158f2bd5591b6ccaea3038c7fc9.png","type":"专属咨询"},{"id":3,"reply_id":1,"users_id":1,"evaluated_id":308,"content":"啊","start_rate":4,"type_id":2,"created_at":"2017-07-18 19:43:35","updated_at":"2017-07-24 16:12:02","name":"blues.deng","head_img":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/1/20170621/13ea427a3ce52cf1ad2cf451f1a3e750.jpg","type":"专属咨询"}]
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
			 * id : 5
			 * reply_id : 3
			 * users_id : 1
			 * evaluated_id : 308
			 * content :
			 * start_rate : 4
			 * type_id : 1
			 * created_at : 2017-07-18 19:43:52
			 * updated_at : 2017-07-24 16:15:49
			 * name : blues.deng
			 * head_img : http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/1/20170621/13ea427a3ce52cf1ad2cf451f1a3e750.jpg
			 * type : 快速咨询
			 */

			private int id;
			private int reply_id;
			private int users_id;
			private int evaluated_id;
			private String content;
			private int start_rate;
			private int type_id;
			private String created_at;
			private String updated_at;
			private String name;
			private String head_img;
			private String type;

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public int getReply_id() {
				return reply_id;
			}

			public void setReply_id(int reply_id) {
				this.reply_id = reply_id;
			}

			public int getUsers_id() {
				return users_id;
			}

			public void setUsers_id(int users_id) {
				this.users_id = users_id;
			}

			public int getEvaluated_id() {
				return evaluated_id;
			}

			public void setEvaluated_id(int evaluated_id) {
				this.evaluated_id = evaluated_id;
			}

			public String getContent() {
				return content;
			}

			public void setContent(String content) {
				this.content = content;
			}

			public int getStart_rate() {
				return start_rate;
			}

			public void setStart_rate(int start_rate) {
				this.start_rate = start_rate;
			}

			public int getType_id() {
				return type_id;
			}

			public void setType_id(int type_id) {
				this.type_id = type_id;
			}

			public String getCreated_at() {
				return created_at;
			}

			public void setCreated_at(String created_at) {
				this.created_at = created_at;
			}

			public String getUpdated_at() {
				return updated_at;
			}

			public void setUpdated_at(String updated_at) {
				this.updated_at = updated_at;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getHead_img() {
				return head_img;
			}

			public void setHead_img(String head_img) {
				this.head_img = head_img;
			}

			public String getType() {
				return type;
			}

			public void setType(String type) {
				this.type = type;
			}
		}
	}
}
