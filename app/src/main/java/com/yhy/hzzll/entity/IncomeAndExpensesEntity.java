package com.yhy.hzzll.entity;

import java.util.List;

/**
 * 财富明细
 * 
 * @author 一合鱼
 * 
 */
public class IncomeAndExpensesEntity {


	/**
	 * code : 0
	 * message : 查询成功!
	 * data : {"pages":1,"total":2,"list":[{"created_at":"2017-08-09 18:59:23","amount":"0.00000","order_no":"25dsdsadsa","type":"见面咨询消费","direction":"支出"},{"created_at":"2017-08-09 18:58:42","amount":"0.00000","order_no":"AAdasdasdadw3592","type":"快速咨询回复收入","direction":"收入"}]}
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
		 * total : 2
		 * list : [{"created_at":"2017-08-09 18:59:23","amount":"0.00000","order_no":"25dsdsadsa","type":"见面咨询消费","direction":"支出"},{"created_at":"2017-08-09 18:58:42","amount":"0.00000","order_no":"AAdasdasdadw3592","type":"快速咨询回复收入","direction":"收入"}]
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
			 * created_at : 2017-08-09 18:59:23
			 * amount : 0.00000
			 * order_no : 25dsdsadsa
			 * type : 见面咨询消费
			 * direction : 支出
			 * payment
			 */

			private String created_at;
			private String amount;
			private String order_no;
			private String type;
			private String direction;
			private String payment;

			public String getPayment() {
				return payment;
			}

			public String getCreated_at() {
				return created_at;
			}

			public void setCreated_at(String created_at) {
				this.created_at = created_at;
			}

			public String getAmount() {
				return amount;
			}

			public void setAmount(String amount) {
				this.amount = amount;
			}

			public String getOrder_no() {
				return order_no;
			}

			public void setOrder_no(String order_no) {
				this.order_no = order_no;
			}

			public String getType() {
				return type;
			}

			public void setType(String type) {
				this.type = type;
			}

			public String getDirection() {
				return direction;
			}

			public void setDirection(String direction) {
				this.direction = direction;
			}
		}
	}
}
