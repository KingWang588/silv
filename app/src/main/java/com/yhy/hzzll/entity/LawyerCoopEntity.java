package com.yhy.hzzll.entity;

import java.util.List;

/**
 * 律师协作
 * 
 * @author Yang
 * 
 */
public class LawyerCoopEntity extends BaseEntity {

	/**
	 * code : 0
	 * message : 查询成功
	 * data : {"pages":2,"total":13,"list":[{"status":"待提交","order_no":"71718008908201094","create_time":"2018-08-20 13:53:11","type":"婚姻保","amount":"38.00","consult_id":354,"base_region_id":{"simple_name":"","area_name":"","whole_name":""},"number":0},{"status":"申请中","order_no":"71718007008208495","create_time":"2018-08-20 09:39:28","type":"婚姻保","amount":"38.00","consult_id":353,"base_region_id":{"simple_name":"武汉","area_name":"武汉市","whole_name":"湖北省-武汉市"},"number":0},{"status":"待提交","order_no":"71818004908174146","create_time":"2018-08-17 17:01:19","type":"债权保","amount":"38.00","consult_id":351,"base_region_id":{"simple_name":"","area_name":"","whole_name":""},"number":0},{"status":"待提交","order_no":"71718004908179001","create_time":"2018-08-17 16:41:11","type":"婚姻保","amount":"38.00","consult_id":350,"base_region_id":{"simple_name":"","area_name":"","whole_name":""},"number":0},{"status":"待提交","order_no":"31718006908144348","create_time":"2018-08-14 17:29:53","type":"婚姻保","amount":"38.00","consult_id":325,"base_region_id":{"simple_name":"","area_name":"","whole_name":""},"number":0},{"status":"申请中","order_no":"31718006908142674","create_time":"2018-08-14 16:49:33","type":"婚姻保","amount":"38.00","consult_id":323,"base_region_id":{"simple_name":"武汉","area_name":"武汉市","whole_name":"湖北省-武汉市"},"number":0},{"status":"申请中","order_no":"71818006908135686","create_time":"2018-08-13 16:11:52","type":"债权保","amount":"38.00","consult_id":303,"base_region_id":{"simple_name":"武汉","area_name":"武汉市","whole_name":"湖北省-武汉市"},"number":0},{"status":"申请中","order_no":"71718004908137596","create_time":"2018-08-13 16:05:08","type":"婚姻保","amount":"100.00","consult_id":302,"base_region_id":{"simple_name":"武汉","area_name":"武汉市","whole_name":"湖北省-武汉市"},"number":0},{"status":"申请中","order_no":"71818004908131327","create_time":"2018-08-13 16:05:04","type":"债权保","amount":"38.00","consult_id":301,"base_region_id":{"simple_name":"武汉","area_name":"武汉市","whole_name":"湖北省-武汉市"},"number":0},{"status":"申请中","order_no":"71818007008137279","create_time":"2018-08-13 14:43:55","type":"债权保","amount":"38.00","consult_id":298,"base_region_id":{"simple_name":"武汉","area_name":"武汉市","whole_name":"湖北省-武汉市"},"number":0}]}
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
		 * pages : 2
		 * total : 13
		 * list : [{"status":"待提交","order_no":"71718008908201094","create_time":"2018-08-20 13:53:11","type":"婚姻保","amount":"38.00","consult_id":354,"base_region_id":{"simple_name":"","area_name":"","whole_name":""},"number":0},{"status":"申请中","order_no":"71718007008208495","create_time":"2018-08-20 09:39:28","type":"婚姻保","amount":"38.00","consult_id":353,"base_region_id":{"simple_name":"武汉","area_name":"武汉市","whole_name":"湖北省-武汉市"},"number":0},{"status":"待提交","order_no":"71818004908174146","create_time":"2018-08-17 17:01:19","type":"债权保","amount":"38.00","consult_id":351,"base_region_id":{"simple_name":"","area_name":"","whole_name":""},"number":0},{"status":"待提交","order_no":"71718004908179001","create_time":"2018-08-17 16:41:11","type":"婚姻保","amount":"38.00","consult_id":350,"base_region_id":{"simple_name":"","area_name":"","whole_name":""},"number":0},{"status":"待提交","order_no":"31718006908144348","create_time":"2018-08-14 17:29:53","type":"婚姻保","amount":"38.00","consult_id":325,"base_region_id":{"simple_name":"","area_name":"","whole_name":""},"number":0},{"status":"申请中","order_no":"31718006908142674","create_time":"2018-08-14 16:49:33","type":"婚姻保","amount":"38.00","consult_id":323,"base_region_id":{"simple_name":"武汉","area_name":"武汉市","whole_name":"湖北省-武汉市"},"number":0},{"status":"申请中","order_no":"71818006908135686","create_time":"2018-08-13 16:11:52","type":"债权保","amount":"38.00","consult_id":303,"base_region_id":{"simple_name":"武汉","area_name":"武汉市","whole_name":"湖北省-武汉市"},"number":0},{"status":"申请中","order_no":"71718004908137596","create_time":"2018-08-13 16:05:08","type":"婚姻保","amount":"100.00","consult_id":302,"base_region_id":{"simple_name":"武汉","area_name":"武汉市","whole_name":"湖北省-武汉市"},"number":0},{"status":"申请中","order_no":"71818004908131327","create_time":"2018-08-13 16:05:04","type":"债权保","amount":"38.00","consult_id":301,"base_region_id":{"simple_name":"武汉","area_name":"武汉市","whole_name":"湖北省-武汉市"},"number":0},{"status":"申请中","order_no":"71818007008137279","create_time":"2018-08-13 14:43:55","type":"债权保","amount":"38.00","consult_id":298,"base_region_id":{"simple_name":"武汉","area_name":"武汉市","whole_name":"湖北省-武汉市"},"number":0}]
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
			 * status : 待提交
			 * order_no : 71718008908201094
			 * create_time : 2018-08-20 13:53:11
			 * type : 婚姻保
			 * amount : 38.00
			 * consult_id : 354
			 * base_region_id : {"simple_name":"","area_name":"","whole_name":""}
			 * number : 0
			 */

			private String status;
			private String order_no;
			private String create_time;
			private String type;
			private String amount;
			private String title;
			private int consult_id;
			private BaseRegionIdBean base_region_id;
			private int number;

			public String getTitle() {
				return title;
			}

			public void setTitle(String title) {
				this.title = title;
			}

			public String getStatus() {
				return status;
			}

			public void setStatus(String status) {
				this.status = status;
			}

			public String getOrder_no() {
				return order_no;
			}

			public void setOrder_no(String order_no) {
				this.order_no = order_no;
			}

			public String getCreate_time() {
				return create_time;
			}

			public void setCreate_time(String create_time) {
				this.create_time = create_time;
			}

			public String getType() {
				return type;
			}

			public void setType(String type) {
				this.type = type;
			}

			public String getAmount() {
				return amount;
			}

			public void setAmount(String amount) {
				this.amount = amount;
			}

			public int getConsult_id() {
				return consult_id;
			}

			public void setConsult_id(int consult_id) {
				this.consult_id = consult_id;
			}

			public BaseRegionIdBean getBase_region_id() {
				return base_region_id;
			}

			public void setBase_region_id(BaseRegionIdBean base_region_id) {
				this.base_region_id = base_region_id;
			}

			public int getNumber() {
				return number;
			}

			public void setNumber(int number) {
				this.number = number;
			}

			public static class BaseRegionIdBean {
				/**
				 * simple_name :
				 * area_name :
				 * whole_name :
				 */

				private String simple_name;
				private String area_name;
				private String whole_name;

				public String getSimple_name() {
					return simple_name;
				}

				public void setSimple_name(String simple_name) {
					this.simple_name = simple_name;
				}

				public String getArea_name() {
					return area_name;
				}

				public void setArea_name(String area_name) {
					this.area_name = area_name;
				}

				public String getWhole_name() {
					return whole_name;
				}

				public void setWhole_name(String whole_name) {
					this.whole_name = whole_name;
				}
			}
		}
	}
}
