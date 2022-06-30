package com.yhy.hzzll.entity;

import java.util.List;

public class OrderListEntity {

	/**
	 * code : 0
	 * message : 查询成功
	 * data : {"pages":1,"total":53,"list":[{"reply_id":"285","consult_id":183,"status":"进行中","order_no":"2217043408222941","create_time":"2017-08-22 17:01:00","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"284","consult_id":194,"status":"进行中","order_no":"2217043408226029","create_time":"2017-08-22 16:51:25","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"283","consult_id":195,"status":"进行中","order_no":"2217043408226730","create_time":"2017-08-22 16:20:43","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"282","consult_id":186,"status":"待付款","order_no":"2217043408221278","create_time":"2017-08-22 15:03:06","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"281","consult_id":179,"status":"进行中","order_no":"2217043408222910","create_time":"2017-08-22 11:45:37","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"280","consult_id":164,"status":"进行中","order_no":"2217043408228489","create_time":"2017-08-22 10:09:01","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"279","consult_id":165,"status":"进行中","order_no":"2217043408222318","create_time":"2017-08-22 10:08:38","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"264","consult_id":166,"status":"进行中","order_no":"2217043408223930","create_time":"2017-08-22 09:40:00","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"263","consult_id":170,"status":"进行中","order_no":"2217043408224308","create_time":"2017-08-22 09:39:14","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"262","consult_id":171,"status":"待付款","order_no":"2217043408223053","create_time":"2017-08-22 09:35:48","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"261","consult_id":167,"status":"进行中","order_no":"2217043408222182","create_time":"2017-08-22 09:23:59","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"259","consult_id":168,"status":"进行中","order_no":"2217043408219024","create_time":"2017-08-21 20:37:30","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"256","consult_id":163,"status":"待付款","order_no":"2217043408219088","create_time":"2017-08-21 17:01:28","type":"快速咨询","amount":"38.00000","count_pursue":6},{"reply_id":"255","consult_id":148,"status":"待付款","order_no":"2217043408191009","create_time":"2017-08-19 08:11:55","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"254","consult_id":158,"status":"待付款","order_no":"2217043408181952","create_time":"2017-08-18 17:59:10","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"251","consult_id":155,"status":"待付款","order_no":"2217043408186703","create_time":"2017-08-18 16:58:05","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"248","consult_id":154,"status":"待付款","order_no":"2217043408182298","create_time":"2017-08-18 16:22:55","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"247","consult_id":152,"status":"待付款","order_no":"2217043408187832","create_time":"2017-08-18 16:12:26","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"245","consult_id":153,"status":"待付款","order_no":"2217043408182502","create_time":"2017-08-18 16:07:04","type":"快速咨询","amount":"38.00000","count_pursue":1},{"reply_id":"244","consult_id":103,"status":"待付款","order_no":"2217043408184328","create_time":"2017-08-18 15:57:55","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"243","consult_id":103,"status":"待付款","order_no":"2217043408188200","create_time":"2017-08-18 15:25:18","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"242","consult_id":103,"status":"待付款","order_no":"2217043408187941","create_time":"2017-08-18 15:25:17","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"241","consult_id":103,"status":"待付款","order_no":"2217043408182873","create_time":"2017-08-18 15:25:15","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"240","consult_id":103,"status":"待付款","order_no":"2217043408184982","create_time":"2017-08-18 15:24:49","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"209","consult_id":112,"status":"待付款","order_no":"220917043408154054","create_time":"2017-08-15 18:03:41","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"208","consult_id":111,"status":"待付款","order_no":"220817043408153298","create_time":"2017-08-15 18:01:20","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"207","consult_id":102,"status":"待付款","order_no":"220717043408151357","create_time":"2017-08-15 17:35:51","type":"专属咨询","amount":"38.00000","count_pursue":0},{"reply_id":"206","consult_id":110,"status":"待付款","order_no":"220617043408153366","create_time":"2017-08-15 16:24:12","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"205","consult_id":103,"status":"待付款","order_no":"220517043408153243","create_time":"2017-08-15 16:22:14","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"186","consult_id":94,"status":"待付款","order_no":"18617434014082534","create_time":"2017-08-14 16:22:24","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"185","consult_id":83,"status":"待付款","order_no":"18517434014083583","create_time":"2017-08-14 14:11:06","type":"专属咨询","amount":"38.00000","count_pursue":0},{"reply_id":"184","consult_id":81,"status":"待付款","order_no":"18417434014087927","create_time":"2017-08-14 14:05:33","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"179","consult_id":76,"status":"待付款","order_no":"17917434014082402","create_time":"2017-08-14 11:28:51","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"174","consult_id":72,"status":"已取消","order_no":"17417434014088703","create_time":"2017-08-14 10:18:58","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"173","consult_id":75,"status":"进行中","order_no":"17317434014082241","create_time":"2017-08-14 10:07:23","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"172","consult_id":71,"status":"待付款","order_no":"17217434014083933","create_time":"2017-08-14 10:03:06","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"170","consult_id":74,"status":"待付款","order_no":"17017434014081826","create_time":"2017-08-14 08:57:55","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"153","consult_id":66,"status":"待付款","order_no":"15317434011087533","create_time":"2017-08-11 11:40:06","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"151","consult_id":56,"status":"待付款","order_no":"15117434011086523","create_time":"2017-08-11 11:37:21","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"150","consult_id":65,"status":"待付款","order_no":"15017434011086938","create_time":"2017-08-11 11:04:41","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"147","consult_id":60,"status":"待付款","order_no":"14717434011089526","create_time":"2017-08-11 10:46:56","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"142","consult_id":69,"status":"待付款","order_no":"14217434011085547","create_time":"2017-08-11 10:39:47","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"141","consult_id":67,"status":"待付款","order_no":"14117434011089041","create_time":"2017-08-11 10:34:47","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"140","consult_id":70,"status":"待付款","order_no":"14017434011083313","create_time":"2017-08-11 09:50:32","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"125","consult_id":59,"status":"待付款","order_no":"12517434010089208","create_time":"2017-08-10 19:25:50","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"111","consult_id":61,"status":"待付款","order_no":"11117434010088466","create_time":"2017-08-10 18:15:38","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"100","consult_id":62,"status":"待付款","order_no":"10017434010081615","create_time":"2017-08-10 11:03:59","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"99","consult_id":63,"status":"待付款","order_no":"9917434010084725","create_time":"2017-08-10 11:01:07","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"97","consult_id":55,"status":"待付款","order_no":"9717434009086867","create_time":"2017-08-09 20:16:49","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"96","consult_id":58,"status":"待付款","order_no":"9617434009089860","create_time":"2017-08-09 18:38:24","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"95","consult_id":53,"status":"待付款","order_no":"9517434009082375","create_time":"2017-08-09 18:30:21","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"94","consult_id":57,"status":"待付款","order_no":"9417434009083766","create_time":"2017-08-09 18:01:58","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"90","consult_id":54,"status":"待付款","order_no":"9017434009081172","create_time":"2017-08-09 16:03:55","type":"快速咨询","amount":"38.00000","count_pursue":0}]}
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
		 * total : 53
		 * list : [{"reply_id":"285","consult_id":183,"status":"进行中","order_no":"2217043408222941","create_time":"2017-08-22 17:01:00","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"284","consult_id":194,"status":"进行中","order_no":"2217043408226029","create_time":"2017-08-22 16:51:25","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"283","consult_id":195,"status":"进行中","order_no":"2217043408226730","create_time":"2017-08-22 16:20:43","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"282","consult_id":186,"status":"待付款","order_no":"2217043408221278","create_time":"2017-08-22 15:03:06","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"281","consult_id":179,"status":"进行中","order_no":"2217043408222910","create_time":"2017-08-22 11:45:37","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"280","consult_id":164,"status":"进行中","order_no":"2217043408228489","create_time":"2017-08-22 10:09:01","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"279","consult_id":165,"status":"进行中","order_no":"2217043408222318","create_time":"2017-08-22 10:08:38","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"264","consult_id":166,"status":"进行中","order_no":"2217043408223930","create_time":"2017-08-22 09:40:00","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"263","consult_id":170,"status":"进行中","order_no":"2217043408224308","create_time":"2017-08-22 09:39:14","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"262","consult_id":171,"status":"待付款","order_no":"2217043408223053","create_time":"2017-08-22 09:35:48","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"261","consult_id":167,"status":"进行中","order_no":"2217043408222182","create_time":"2017-08-22 09:23:59","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"259","consult_id":168,"status":"进行中","order_no":"2217043408219024","create_time":"2017-08-21 20:37:30","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"256","consult_id":163,"status":"待付款","order_no":"2217043408219088","create_time":"2017-08-21 17:01:28","type":"快速咨询","amount":"38.00000","count_pursue":6},{"reply_id":"255","consult_id":148,"status":"待付款","order_no":"2217043408191009","create_time":"2017-08-19 08:11:55","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"254","consult_id":158,"status":"待付款","order_no":"2217043408181952","create_time":"2017-08-18 17:59:10","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"251","consult_id":155,"status":"待付款","order_no":"2217043408186703","create_time":"2017-08-18 16:58:05","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"248","consult_id":154,"status":"待付款","order_no":"2217043408182298","create_time":"2017-08-18 16:22:55","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"247","consult_id":152,"status":"待付款","order_no":"2217043408187832","create_time":"2017-08-18 16:12:26","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"245","consult_id":153,"status":"待付款","order_no":"2217043408182502","create_time":"2017-08-18 16:07:04","type":"快速咨询","amount":"38.00000","count_pursue":1},{"reply_id":"244","consult_id":103,"status":"待付款","order_no":"2217043408184328","create_time":"2017-08-18 15:57:55","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"243","consult_id":103,"status":"待付款","order_no":"2217043408188200","create_time":"2017-08-18 15:25:18","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"242","consult_id":103,"status":"待付款","order_no":"2217043408187941","create_time":"2017-08-18 15:25:17","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"241","consult_id":103,"status":"待付款","order_no":"2217043408182873","create_time":"2017-08-18 15:25:15","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"240","consult_id":103,"status":"待付款","order_no":"2217043408184982","create_time":"2017-08-18 15:24:49","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"209","consult_id":112,"status":"待付款","order_no":"220917043408154054","create_time":"2017-08-15 18:03:41","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"208","consult_id":111,"status":"待付款","order_no":"220817043408153298","create_time":"2017-08-15 18:01:20","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"207","consult_id":102,"status":"待付款","order_no":"220717043408151357","create_time":"2017-08-15 17:35:51","type":"专属咨询","amount":"38.00000","count_pursue":0},{"reply_id":"206","consult_id":110,"status":"待付款","order_no":"220617043408153366","create_time":"2017-08-15 16:24:12","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"205","consult_id":103,"status":"待付款","order_no":"220517043408153243","create_time":"2017-08-15 16:22:14","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"186","consult_id":94,"status":"待付款","order_no":"18617434014082534","create_time":"2017-08-14 16:22:24","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"185","consult_id":83,"status":"待付款","order_no":"18517434014083583","create_time":"2017-08-14 14:11:06","type":"专属咨询","amount":"38.00000","count_pursue":0},{"reply_id":"184","consult_id":81,"status":"待付款","order_no":"18417434014087927","create_time":"2017-08-14 14:05:33","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"179","consult_id":76,"status":"待付款","order_no":"17917434014082402","create_time":"2017-08-14 11:28:51","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"174","consult_id":72,"status":"已取消","order_no":"17417434014088703","create_time":"2017-08-14 10:18:58","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"173","consult_id":75,"status":"进行中","order_no":"17317434014082241","create_time":"2017-08-14 10:07:23","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"172","consult_id":71,"status":"待付款","order_no":"17217434014083933","create_time":"2017-08-14 10:03:06","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"170","consult_id":74,"status":"待付款","order_no":"17017434014081826","create_time":"2017-08-14 08:57:55","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"153","consult_id":66,"status":"待付款","order_no":"15317434011087533","create_time":"2017-08-11 11:40:06","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"151","consult_id":56,"status":"待付款","order_no":"15117434011086523","create_time":"2017-08-11 11:37:21","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"150","consult_id":65,"status":"待付款","order_no":"15017434011086938","create_time":"2017-08-11 11:04:41","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"147","consult_id":60,"status":"待付款","order_no":"14717434011089526","create_time":"2017-08-11 10:46:56","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"142","consult_id":69,"status":"待付款","order_no":"14217434011085547","create_time":"2017-08-11 10:39:47","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"141","consult_id":67,"status":"待付款","order_no":"14117434011089041","create_time":"2017-08-11 10:34:47","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"140","consult_id":70,"status":"待付款","order_no":"14017434011083313","create_time":"2017-08-11 09:50:32","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"125","consult_id":59,"status":"待付款","order_no":"12517434010089208","create_time":"2017-08-10 19:25:50","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"111","consult_id":61,"status":"待付款","order_no":"11117434010088466","create_time":"2017-08-10 18:15:38","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"100","consult_id":62,"status":"待付款","order_no":"10017434010081615","create_time":"2017-08-10 11:03:59","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"99","consult_id":63,"status":"待付款","order_no":"9917434010084725","create_time":"2017-08-10 11:01:07","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"97","consult_id":55,"status":"待付款","order_no":"9717434009086867","create_time":"2017-08-09 20:16:49","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"96","consult_id":58,"status":"待付款","order_no":"9617434009089860","create_time":"2017-08-09 18:38:24","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"95","consult_id":53,"status":"待付款","order_no":"9517434009082375","create_time":"2017-08-09 18:30:21","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"94","consult_id":57,"status":"待付款","order_no":"9417434009083766","create_time":"2017-08-09 18:01:58","type":"快速咨询","amount":"38.00000","count_pursue":0},{"reply_id":"90","consult_id":54,"status":"待付款","order_no":"9017434009081172","create_time":"2017-08-09 16:03:55","type":"快速咨询","amount":"38.00000","count_pursue":0}]
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
			 * reply_id : 285
			 * consult_id : 183
			 * status : 进行中
			 * order_no : 2217043408222941
			 * create_time : 2017-08-22 17:01:00
			 * type : 快速咨询
			 * amount : 38.00000
			 * count_pursue : 0
			 */

			private String reply_id;
			private int consult_id;
			private int message_id;

			public int getMessage_id() {
				return message_id;
			}

			private String status;
			private String order_no;
			private String create_time;
			private String type;
			private String amount;
			private int count_pursue;
			private String	accid;

			public String getAccid() {
				return accid;
			}

			public String getReply_id() {
				return reply_id;
			}

			public void setReply_id(String reply_id) {
				this.reply_id = reply_id;
			}

			public int getConsult_id() {
				return consult_id;
			}

			public void setConsult_id(int consult_id) {
				this.consult_id = consult_id;
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

			public int getCount_pursue() {
				return count_pursue;
			}

			public void setCount_pursue(int count_pursue) {
				this.count_pursue = count_pursue;
			}
		}
	}
}
