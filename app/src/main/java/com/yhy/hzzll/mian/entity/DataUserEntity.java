package com.yhy.hzzll.mian.entity;

import com.yhy.hzzll.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 用户（个人/律师）个人信息
 * 
 * @author Yang
 * 
 */
public class DataUserEntity extends BaseEntity implements Serializable {

	/**
	 * code : 0
	 * message : 查询成功
	 * data : {"info":{"lawyer_intro":"","lawyer_type":"执业律师","lawyer_title":"律师","page_view":"10","count_befollowd":"0","count_reply":"1","count_case":"0","start_reply":"0.0","start_case":"0.0","lawyer_secpical":"婚姻家庭,债权债务,合同纠纷","lawyer_license_photo":[{"id":"1072","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/32/20180611/18d74f364b06695ee9b4280126c87087.png"},{"id":"1074","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/32/20180611/6af6171457e832a3e81d8da2cfc4cf0d.png"},{"id":"1075","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/32/20180611/029ec353e165091fb8a75e08055ecfb6.png"}],"offer_info":{"exclusive_consult_isprice":"1","price":"66.00"},"exclusive_lawyer_application":"1,2","nickname":"刘强武","truename":"刘强武","user_type":"个人用户","occupation":"lawyer","auth_status":"已认证","auth_status_code":"AUTHENTICATED","avatar":"","base_region":"江苏省,扬州市,高邮市","is_band":0,"im_token":{"users_id":32,"accid":"test_silv_32","token":"9b2f3eb7d75f340545aa6b9eb3a89b3a","is_delete":0},"id":32,"mobile":"15629037629","email":"","old_user_info":{"old_user_id":26258,"password":"e10adc3949ba59abbe56e057f20f883e","hash":"5f1b13207b976502eff47d76cce8ffa3","username":"15629037629"},"exist_paypassword":false},"finance":{"amt_balance":"10.00000","amt_freeze":"0.00000","amt_consult":"10.00000","amt_case":"0.00000","invite_income":"0.00000"}}
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
		 * info : {"lawyer_intro":"","lawyer_type":"执业律师","lawyer_title":"律师","page_view":"10","count_befollowd":"0","count_reply":"1","count_case":"0","start_reply":"0.0","start_case":"0.0","lawyer_secpical":"婚姻家庭,债权债务,合同纠纷","lawyer_license_photo":[{"id":"1072","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/32/20180611/18d74f364b06695ee9b4280126c87087.png"},{"id":"1074","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/32/20180611/6af6171457e832a3e81d8da2cfc4cf0d.png"},{"id":"1075","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/32/20180611/029ec353e165091fb8a75e08055ecfb6.png"}],"offer_info":{"exclusive_consult_isprice":"1","price":"66.00"},"exclusive_lawyer_application":"1,2","nickname":"刘强武","truename":"刘强武","user_type":"个人用户","occupation":"lawyer","auth_status":"已认证","auth_status_code":"AUTHENTICATED","avatar":"","base_region":"江苏省,扬州市,高邮市","is_band":0,"im_token":{"users_id":32,"accid":"test_silv_32","token":"9b2f3eb7d75f340545aa6b9eb3a89b3a","is_delete":0},"id":32,"mobile":"15629037629","email":"","old_user_info":{"old_user_id":26258,"password":"e10adc3949ba59abbe56e057f20f883e","hash":"5f1b13207b976502eff47d76cce8ffa3","username":"15629037629"},"exist_paypassword":false}
		 * finance : {"amt_balance":"10.00000","amt_freeze":"0.00000","amt_consult":"10.00000","amt_case":"0.00000","invite_income":"0.00000"}
		 */

		private InfoBean info;
		private FinanceBean finance;

		public InfoBean getInfo() {
			return info;
		}

		public void setInfo(InfoBean info) {
			this.info = info;
		}

		public FinanceBean getFinance() {
			return finance;
		}

		public void setFinance(FinanceBean finance) {
			this.finance = finance;
		}

		public static class InfoBean {
			/**
			 * lawyer_intro :
			 * lawyer_type : 执业律师
			 * lawyer_title : 律师
			 * page_view : 10
			 * count_befollowd : 0
			 * count_reply : 1
			 * count_case : 0
			 * start_reply : 0.0
			 * start_case : 0.0
			 * lawyer_secpical : 婚姻家庭,债权债务,合同纠纷
			 * lawyer_license_photo : [{"id":"1072","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/32/20180611/18d74f364b06695ee9b4280126c87087.png"},{"id":"1074","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/32/20180611/6af6171457e832a3e81d8da2cfc4cf0d.png"},{"id":"1075","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/32/20180611/029ec353e165091fb8a75e08055ecfb6.png"}]
			 * offer_info : {"exclusive_consult_isprice":"1","price":"66.00"}
			 * exclusive_lawyer_application : 1,2
			 * nickname : 刘强武
			 * truename : 刘强武
			 * user_type : 个人用户
			 * occupation : lawyer
			 * auth_status : 已认证
			 * auth_status_code : AUTHENTICATED
			 * avatar :
			 * base_region : 江苏省,扬州市,高邮市
			 * is_band : 0
			 * im_token : {"users_id":32,"accid":"test_silv_32","token":"9b2f3eb7d75f340545aa6b9eb3a89b3a","is_delete":0}
			 * id : 32
			 * mobile : 15629037629
			 * email :
			 * old_user_info : {"old_user_id":26258,"password":"e10adc3949ba59abbe56e057f20f883e","hash":"5f1b13207b976502eff47d76cce8ffa3","username":"15629037629"}
			 * exist_paypassword : false
			 */

			private String lawyer_intro;
			private String lawyer_type;
			private String lawyer_title;
			private String page_view;
			private String count_befollowd;
			private String count_reply;
			private String count_case;
			private String start_reply;
			private String start_case;
			private String lawyer_secpical;
			private OfferInfoBean offer_info;
			private String exclusive_lawyer_application;
			private String nickname;
			private String truename;
			private String user_type;
			private String occupation;
			private String auth_status;
			private String auth_status_code;
			private String avatar;
			private String base_region;
			private int is_band;
			private ImTokenBean im_token;
			private int id;
			private String mobile;
			private String email;
			private OldUserInfoBean old_user_info;
			private boolean exist_paypassword;
			private List<LawyerLicensePhotoBean> lawyer_license_photo;

			public String getLawyer_intro() {
				return lawyer_intro;
			}

			public void setLawyer_intro(String lawyer_intro) {
				this.lawyer_intro = lawyer_intro;
			}

			public String getLawyer_type() {
				return lawyer_type;
			}

			public void setLawyer_type(String lawyer_type) {
				this.lawyer_type = lawyer_type;
			}

			public String getLawyer_title() {
				return lawyer_title;
			}

			public void setLawyer_title(String lawyer_title) {
				this.lawyer_title = lawyer_title;
			}

			public String getPage_view() {
				return page_view;
			}

			public void setPage_view(String page_view) {
				this.page_view = page_view;
			}

			public String getCount_befollowd() {
				return count_befollowd;
			}

			public void setCount_befollowd(String count_befollowd) {
				this.count_befollowd = count_befollowd;
			}

			public String getCount_reply() {
				return count_reply;
			}

			public void setCount_reply(String count_reply) {
				this.count_reply = count_reply;
			}

			public String getCount_case() {
				return count_case;
			}

			public void setCount_case(String count_case) {
				this.count_case = count_case;
			}

			public String getStart_reply() {
				return start_reply;
			}

			public void setStart_reply(String start_reply) {
				this.start_reply = start_reply;
			}

			public String getStart_case() {
				return start_case;
			}

			public void setStart_case(String start_case) {
				this.start_case = start_case;
			}

			public String getLawyer_secpical() {
				return lawyer_secpical;
			}

			public void setLawyer_secpical(String lawyer_secpical) {
				this.lawyer_secpical = lawyer_secpical;
			}

			public OfferInfoBean getOffer_info() {
				return offer_info;
			}

			public void setOffer_info(OfferInfoBean offer_info) {
				this.offer_info = offer_info;
			}

			public String getExclusive_lawyer_application() {
				return exclusive_lawyer_application;
			}

			public void setExclusive_lawyer_application(String exclusive_lawyer_application) {
				this.exclusive_lawyer_application = exclusive_lawyer_application;
			}

			public String getNickname() {
				return nickname;
			}

			public void setNickname(String nickname) {
				this.nickname = nickname;
			}

			public String getTruename() {
				return truename;
			}

			public void setTruename(String truename) {
				this.truename = truename;
			}

			public String getUser_type() {
				return user_type;
			}

			public void setUser_type(String user_type) {
				this.user_type = user_type;
			}

			public String getOccupation() {
				return occupation;
			}

			public void setOccupation(String occupation) {
				this.occupation = occupation;
			}

			public String getAuth_status() {
				return auth_status;
			}

			public void setAuth_status(String auth_status) {
				this.auth_status = auth_status;
			}

			public String getAuth_status_code() {
				return auth_status_code;
			}

			public void setAuth_status_code(String auth_status_code) {
				this.auth_status_code = auth_status_code;
			}

			public String getAvatar() {
				return avatar;
			}

			public void setAvatar(String avatar) {
				this.avatar = avatar;
			}

			public String getBase_region() {
				return base_region;
			}

			public void setBase_region(String base_region) {
				this.base_region = base_region;
			}

			public int getIs_band() {
				return is_band;
			}

			public void setIs_band(int is_band) {
				this.is_band = is_band;
			}

			public ImTokenBean getIm_token() {
				return im_token;
			}

			public void setIm_token(ImTokenBean im_token) {
				this.im_token = im_token;
			}

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public String getMobile() {
				return mobile;
			}

			public void setMobile(String mobile) {
				this.mobile = mobile;
			}

			public String getEmail() {
				return email;
			}

			public void setEmail(String email) {
				this.email = email;
			}

			public OldUserInfoBean getOld_user_info() {
				return old_user_info;
			}

			public void setOld_user_info(OldUserInfoBean old_user_info) {
				this.old_user_info = old_user_info;
			}

			public boolean isExist_paypassword() {
				return exist_paypassword;
			}

			public void setExist_paypassword(boolean exist_paypassword) {
				this.exist_paypassword = exist_paypassword;
			}

			public List<LawyerLicensePhotoBean> getLawyer_license_photo() {
				return lawyer_license_photo;
			}

			public void setLawyer_license_photo(List<LawyerLicensePhotoBean> lawyer_license_photo) {
				this.lawyer_license_photo = lawyer_license_photo;
			}

			public static class OfferInfoBean {
				/**
				 * exclusive_consult_isprice : 1
				 * price : 66.00
				 */

				private String exclusive_consult_isprice;
				private String price;

				public String getExclusive_consult_isprice() {
					return exclusive_consult_isprice;
				}

				public void setExclusive_consult_isprice(String exclusive_consult_isprice) {
					this.exclusive_consult_isprice = exclusive_consult_isprice;
				}

				public String getPrice() {
					return price;
				}

				public void setPrice(String price) {
					this.price = price;
				}
			}

			public static class ImTokenBean {
				/**
				 * users_id : 32
				 * accid : test_silv_32
				 * token : 9b2f3eb7d75f340545aa6b9eb3a89b3a
				 * is_delete : 0
				 */

				private int users_id;
				private String accid;
				private String token;
				private int is_delete;

				public int getUsers_id() {
					return users_id;
				}

				public void setUsers_id(int users_id) {
					this.users_id = users_id;
				}

				public String getAccid() {
					return accid;
				}

				public void setAccid(String accid) {
					this.accid = accid;
				}

				public String getToken() {
					return token;
				}

				public void setToken(String token) {
					this.token = token;
				}

				public int getIs_delete() {
					return is_delete;
				}

				public void setIs_delete(int is_delete) {
					this.is_delete = is_delete;
				}
			}

			public static class OldUserInfoBean {
				/**
				 * old_user_id : 26258
				 * password : e10adc3949ba59abbe56e057f20f883e
				 * hash : 5f1b13207b976502eff47d76cce8ffa3
				 * username : 15629037629
				 */

				private int old_user_id;
				private String password;
				private String hash;
				private String username;

				public int getOld_user_id() {
					return old_user_id;
				}

				public void setOld_user_id(int old_user_id) {
					this.old_user_id = old_user_id;
				}

				public String getPassword() {
					return password;
				}

				public void setPassword(String password) {
					this.password = password;
				}

				public String getHash() {
					return hash;
				}

				public void setHash(String hash) {
					this.hash = hash;
				}

				public String getUsername() {
					return username;
				}

				public void setUsername(String username) {
					this.username = username;
				}
			}

			public static class LawyerLicensePhotoBean {
				/**
				 * id : 1072
				 * fileurl_full : http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/32/20180611/18d74f364b06695ee9b4280126c87087.png
				 */

				private String id;
				private String fileurl_full;

				public String getId() {
					return id;
				}

				public void setId(String id) {
					this.id = id;
				}

				public String getFileurl_full() {
					return fileurl_full;
				}

				public void setFileurl_full(String fileurl_full) {
					this.fileurl_full = fileurl_full;
				}
			}
		}

		public static class FinanceBean {
			/**
			 * amt_balance : 10.00000
			 * amt_freeze : 0.00000
			 * amt_consult : 10.00000
			 * amt_case : 0.00000
			 * invite_income : 0.00000
			 */

			private String amt_balance;
			private String amt_freeze;
			private String amt_consult;
			private String amt_case;
			private String invite_income;

			public String getAmt_balance() {
				return amt_balance;
			}

			public void setAmt_balance(String amt_balance) {
				this.amt_balance = amt_balance;
			}

			public String getAmt_freeze() {
				return amt_freeze;
			}

			public void setAmt_freeze(String amt_freeze) {
				this.amt_freeze = amt_freeze;
			}

			public String getAmt_consult() {
				return amt_consult;
			}

			public void setAmt_consult(String amt_consult) {
				this.amt_consult = amt_consult;
			}

			public String getAmt_case() {
				return amt_case;
			}

			public void setAmt_case(String amt_case) {
				this.amt_case = amt_case;
			}

			public String getInvite_income() {
				return invite_income;
			}

			public void setInvite_income(String invite_income) {
				this.invite_income = invite_income;
			}
		}
	}
}
