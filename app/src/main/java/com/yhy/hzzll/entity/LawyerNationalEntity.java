package com.yhy.hzzll.entity;

import java.util.List;

/**
 * 全国律师实体
 * 
 * @author Yang
 * 
 */
public class LawyerNationalEntity extends BaseEntity {

	/**
	 * code : 0
	 * message : 查询结果!
	 * data : {"pages":4,"total":21,"list":[{"users_id":413,"truename":"何虎","authentication_status_id":2,"exclusive_consult_isprice":0,"quoted_price":"0","legal_advice_rate":"0","handle_case_rate":"0","handle_case_num":0,"reply_consult_num":0,"headimg":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/413/20170721/2fd66bc3f9ecadb29351a8d275d1d2bb.jpg","base_region_id":{"simple_name":"北京","area_name":"北京市","whole_name":"北京,北京市,东城区"},"special":["遗嘱继承","婚姻家庭","合同纠纷"]},{"users_id":1,"truename":"","authentication_status_id":2,"exclusive_consult_isprice":0,"quoted_price":"0","legal_advice_rate":"0","handle_case_rate":"0","handle_case_num":0,"reply_consult_num":0,"headimg":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/1/20170725/31cf6da1cfcc3d7de302e03a9f701b0a.png","base_region_id":{"simple_name":"太原","area_name":"太原市","whole_name":"山西省,太原市,小店区"},"special":["建筑房产","公司事务","债权债务"]},{"users_id":408,"truename":"请码选填","authentication_status_id":2,"exclusive_consult_isprice":0,"quoted_price":"0","legal_advice_rate":"0","handle_case_rate":"0","handle_case_num":0,"reply_consult_num":0,"headimg":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/408/20170721/d7b91f2c0459cf3bd01d9654f04b2b63.jpg","base_region_id":{"simple_name":"长春","area_name":"长春市","whole_name":"吉林省,长春市,南关区"},"special":["遗嘱继承","婚姻家庭","合同纠纷"]},{"users_id":404,"truename":"剧粉丝讨","authentication_status_id":2,"exclusive_consult_isprice":0,"quoted_price":"0","legal_advice_rate":"0","handle_case_rate":"0","handle_case_num":0,"reply_consult_num":0,"headimg":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/404/20170720/c58488029dac4edbc0e9cf0429e80425.jpg","base_region_id":{"simple_name":"武汉","area_name":"武汉市","whole_name":"湖北省,武汉市,洪山区"},"special":["合同纠纷","交通保险"]},{"users_id":248,"truename":"是是是","authentication_status_id":2,"exclusive_consult_isprice":0,"quoted_price":"0","legal_advice_rate":"0","handle_case_rate":"0","handle_case_num":0,"reply_consult_num":0,"headimg":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/248/20170721/ed1869e72b9c3c0d11dae9c521d88321.jpg","base_region_id":{"simple_name":"太原","area_name":"太原市","whole_name":"山西省,太原市,小店区"},"special":["金融借贷"]},{"users_id":393,"truename":"方法","authentication_status_id":2,"exclusive_consult_isprice":0,"quoted_price":"0","legal_advice_rate":"0","handle_case_rate":"0","handle_case_num":0,"reply_consult_num":0,"headimg":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/393/20170718/71ccc9bca3c9bdfd0460224fda53574e.png","base_region_id":{"simple_name":"太原","area_name":"太原市","whole_name":"山西省,太原市,小店区"},"special":["遗嘱继承"]}]}
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
		 * pages : 4
		 * total : 21
		 * list : [{"users_id":413,"truename":"何虎","authentication_status_id":2,"exclusive_consult_isprice":0,"quoted_price":"0","legal_advice_rate":"0","handle_case_rate":"0","handle_case_num":0,"reply_consult_num":0,"headimg":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/413/20170721/2fd66bc3f9ecadb29351a8d275d1d2bb.jpg","base_region_id":{"simple_name":"北京","area_name":"北京市","whole_name":"北京,北京市,东城区"},"special":["遗嘱继承","婚姻家庭","合同纠纷"]},{"users_id":1,"truename":"","authentication_status_id":2,"exclusive_consult_isprice":0,"quoted_price":"0","legal_advice_rate":"0","handle_case_rate":"0","handle_case_num":0,"reply_consult_num":0,"headimg":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/1/20170725/31cf6da1cfcc3d7de302e03a9f701b0a.png","base_region_id":{"simple_name":"太原","area_name":"太原市","whole_name":"山西省,太原市,小店区"},"special":["建筑房产","公司事务","债权债务"]},{"users_id":408,"truename":"请码选填","authentication_status_id":2,"exclusive_consult_isprice":0,"quoted_price":"0","legal_advice_rate":"0","handle_case_rate":"0","handle_case_num":0,"reply_consult_num":0,"headimg":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/408/20170721/d7b91f2c0459cf3bd01d9654f04b2b63.jpg","base_region_id":{"simple_name":"长春","area_name":"长春市","whole_name":"吉林省,长春市,南关区"},"special":["遗嘱继承","婚姻家庭","合同纠纷"]},{"users_id":404,"truename":"剧粉丝讨","authentication_status_id":2,"exclusive_consult_isprice":0,"quoted_price":"0","legal_advice_rate":"0","handle_case_rate":"0","handle_case_num":0,"reply_consult_num":0,"headimg":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/404/20170720/c58488029dac4edbc0e9cf0429e80425.jpg","base_region_id":{"simple_name":"武汉","area_name":"武汉市","whole_name":"湖北省,武汉市,洪山区"},"special":["合同纠纷","交通保险"]},{"users_id":248,"truename":"是是是","authentication_status_id":2,"exclusive_consult_isprice":0,"quoted_price":"0","legal_advice_rate":"0","handle_case_rate":"0","handle_case_num":0,"reply_consult_num":0,"headimg":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/248/20170721/ed1869e72b9c3c0d11dae9c521d88321.jpg","base_region_id":{"simple_name":"太原","area_name":"太原市","whole_name":"山西省,太原市,小店区"},"special":["金融借贷"]},{"users_id":393,"truename":"方法","authentication_status_id":2,"exclusive_consult_isprice":0,"quoted_price":"0","legal_advice_rate":"0","handle_case_rate":"0","handle_case_num":0,"reply_consult_num":0,"headimg":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/393/20170718/71ccc9bca3c9bdfd0460224fda53574e.png","base_region_id":{"simple_name":"太原","area_name":"太原市","whole_name":"山西省,太原市,小店区"},"special":["遗嘱继承"]}]
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
			 * users_id : 413
			 * truename : 何虎
			 * authentication_status_id : 2
			 * exclusive_consult_isprice : 0
			 * quoted_price : 0
			 * legal_advice_rate : 0
			 * handle_case_rate : 0
			 * handle_case_num : 0
			 * reply_consult_num : 0
			 * lawyer_title
			 * headimg : http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/413/20170721/2fd66bc3f9ecadb29351a8d275d1d2bb.jpg
			 * base_region_id : {"simple_name":"北京","area_name":"北京市","whole_name":"北京,北京市,东城区"}
			 * special : ["遗嘱继承","婚姻家庭","合同纠纷"]
			 */

			private int users_id;
			private String lawyer_type;

			public String getLawyer_type() {
				return lawyer_type;
			}

			private String truename;
			private int authentication_status_id;
			private int exclusive_consult_isprice;
			private String quoted_price;
			private String legal_advice_rate;
			private String handle_case_rate;
			private int handle_case_num;
			private int reply_consult_num;
			private String headimg;
			private String lawyer_title;
			private BaseRegionIdBean base_region_id;
			private List<String> special;

			public String getLawyer_title() {
				return lawyer_title;
			}

			public int getUsers_id() {
				return users_id;
			}

			public void setUsers_id(int users_id) {
				this.users_id = users_id;
			}

			public String getTruename() {
				return truename;
			}

			public void setTruename(String truename) {
				this.truename = truename;
			}

			public int getAuthentication_status_id() {
				return authentication_status_id;
			}

			public void setAuthentication_status_id(int authentication_status_id) {
				this.authentication_status_id = authentication_status_id;
			}

			public int getExclusive_consult_isprice() {
				return exclusive_consult_isprice;
			}

			public void setExclusive_consult_isprice(int exclusive_consult_isprice) {
				this.exclusive_consult_isprice = exclusive_consult_isprice;
			}

			public String getQuoted_price() {
				return quoted_price;
			}

			public void setQuoted_price(String quoted_price) {
				this.quoted_price = quoted_price;
			}

			public String getLegal_advice_rate() {
				return legal_advice_rate;
			}

			public void setLegal_advice_rate(String legal_advice_rate) {
				this.legal_advice_rate = legal_advice_rate;
			}

			public String getHandle_case_rate() {
				return handle_case_rate;
			}

			public void setHandle_case_rate(String handle_case_rate) {
				this.handle_case_rate = handle_case_rate;
			}

			public int getHandle_case_num() {
				return handle_case_num;
			}

			public void setHandle_case_num(int handle_case_num) {
				this.handle_case_num = handle_case_num;
			}

			public int getReply_consult_num() {
				return reply_consult_num;
			}

			public void setReply_consult_num(int reply_consult_num) {
				this.reply_consult_num = reply_consult_num;
			}

			public String getHeadimg() {
				return headimg;
			}

			public void setHeadimg(String headimg) {
				this.headimg = headimg;
			}

			public BaseRegionIdBean getBase_region_id() {
				return base_region_id;
			}

			public void setBase_region_id(BaseRegionIdBean base_region_id) {
				this.base_region_id = base_region_id;
			}

			public List<String> getSpecial() {
				return special;
			}

			public void setSpecial(List<String> special) {
				this.special = special;
			}

			public static class BaseRegionIdBean {
				/**
				 * simple_name : 北京
				 * area_name : 北京市
				 * whole_name : 北京,北京市,东城区
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
