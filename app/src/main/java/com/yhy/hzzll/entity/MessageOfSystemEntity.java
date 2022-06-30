package com.yhy.hzzll.entity;

import java.util.List;

/**
 * 系统消息内容
 * 
 * @author Yang
 * 
 */
public class MessageOfSystemEntity extends BaseEntity {


	/**
	 * code : 0
	 * message : 查询成功
	 * data : {"pages":9,"total":45,"list":[{"id":"219","title":"专属咨询评价","content":"得得评价了您的回复，点击查看！","message_type_id":"16","send_time":"2017-09-26 16:19:32","relation_id":296,"params":{"params_id":"2117042909258642"}},{"id":"218","title":"回复追问","content":"私律用户律师邀请您进行1对1专属咨询，点击购买！","message_type_id":"14","send_time":"2017-09-26 16:12:08","relation_id":10,"params":{"params_id":54}},{"id":"215","title":"点赞","content":"得得觉得您的回复很赞，立即查看！","message_type_id":"18","send_time":"2017-09-26 15:15:57","relation_id":358,"params":{"params_id":1249}},{"id":"213","title":"回复追问","content":"恭喜您获得2元追问红包，点击查看！","message_type_id":"14","send_time":"2017-09-26 15:03:33","relation_id":527,"params":{"params_id":1249}},{"id":"212","title":"回复追问","content":"Tom发来2元追问红包，1小时内回复领取！","message_type_id":"14","send_time":"2017-09-26 14:56:33","relation_id":526,"params":{"params_id":1249}}],"count":45}
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
		 * pages : 9
		 * total : 45
		 * list : [{"id":"219","title":"专属咨询评价","content":"得得评价了您的回复，点击查看！","message_type_id":"16","send_time":"2017-09-26 16:19:32","relation_id":296,"params":{"params_id":"2117042909258642"}},{"id":"218","title":"回复追问","content":"私律用户律师邀请您进行1对1专属咨询，点击购买！","message_type_id":"14","send_time":"2017-09-26 16:12:08","relation_id":10,"params":{"params_id":54}},{"id":"215","title":"点赞","content":"得得觉得您的回复很赞，立即查看！","message_type_id":"18","send_time":"2017-09-26 15:15:57","relation_id":358,"params":{"params_id":1249}},{"id":"213","title":"回复追问","content":"恭喜您获得2元追问红包，点击查看！","message_type_id":"14","send_time":"2017-09-26 15:03:33","relation_id":527,"params":{"params_id":1249}},{"id":"212","title":"回复追问","content":"Tom发来2元追问红包，1小时内回复领取！","message_type_id":"14","send_time":"2017-09-26 14:56:33","relation_id":526,"params":{"params_id":1249}}]
		 * count : 45
		 */

		private int pages;
		private int total;
		private int count;
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

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public List<ListBean> getList() {
			return list;
		}

		public void setList(List<ListBean> list) {
			this.list = list;
		}

		public static class ListBean {
			/**
			 * id : 219
			 * title : 专属咨询评价
			 * content : 得得评价了您的回复，点击查看！
			 * message_type_id : 16
			 * send_time : 2017-09-26 16:19:32
			 * relation_id : 296
			 * params : {"params_id":"2117042909258642"}
			 */

			private String id;
			private String title;
			private String content;
			private String message_type_id;
			private String send_time;
			private int relation_id;
			private ParamsBean params;

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

			public String getContent() {
				return content;
			}

			public void setContent(String content) {
				this.content = content;
			}

			public String getMessage_type_id() {
				return message_type_id;
			}

			public void setMessage_type_id(String message_type_id) {
				this.message_type_id = message_type_id;
			}

			public String getSend_time() {
				return send_time;
			}

			public void setSend_time(String send_time) {
				this.send_time = send_time;
			}

			public int getRelation_id() {
				return relation_id;
			}

			public void setRelation_id(int relation_id) {
				this.relation_id = relation_id;
			}

			public ParamsBean getParams() {
				return params;
			}

			public void setParams(ParamsBean params) {
				this.params = params;
			}

			public static class ParamsBean {
				/**
				 * params_id : 2117042909258642
				 * order_no
				 * accid_id
				 */

				private String params_id;
				private String order_no;
				private String accid_id;
				private String message_id;

				public String getMessage_id() {
					return message_id;
				}

				public String getAccid_id() {
					return accid_id;
				}

				public String getReply_id() {
					return reply_id;
				}

				private String reply_id;

				public String getOrder_no() {
					return order_no;
				}

				public String getParams_id() {
					return params_id;
				}

				public void setParams_id(String params_id) {
					this.params_id = params_id;
				}
			}
		}
	}
}
