package com.yhy.hzzll.entity;

/**
 * 
 * {
        "code": "000000",
        "msg": "success",
        "data": {
          "order_info": {
            "orderno": "订单号",
            "amount": "订单金额",
            "title": "标题",
            "created_at": "下单时间",
            "pay_time": "支付时间",
            "lawyer": "律师姓名",
            "users": "购买者姓名",
            "mobile": "购买者电话",
            "status": "购买状态 1已付款",
            "lawyer_id" : "律师id",
            "is_commented": 是否已评论 0否 1是,
            "content" : 评论内容（订单完成后显示）,
              "level" : 评价分数（订单完成后显示）,
            "lawyer_mobile" : "律师电话"
          },
          "base_info": {
            "domain": "咨询领域,
            "meet_time": "见面时间",
            "meet_address": "见面地点",
            "description": "描述"
          }
        }
      }
}
 * 
 * @author 一合鱼
 *
 */


public class FaceOrderDetailEntity {
	


    private String code;
    private String msg;

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	private DataBean data;

   
    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        

        private OrderInfoBean order_info;
        

        private BaseInfoBean base_info;

        public OrderInfoBean getOrder_info() {
            return order_info;
        }

        public void setOrder_info(OrderInfoBean order_info) {
            this.order_info = order_info;
        }

        public BaseInfoBean getBase_info() {
            return base_info;
        }

        public void setBase_info(BaseInfoBean base_info) {
            this.base_info = base_info;
        }

        public static class OrderInfoBean {
            private String orderno;
            private String amount;
            private String title;
            private String created_at;
            private String pay_time;
            private String lawyer;
            private String users;
            private String mobile;
            private String status;
            private int lawyer_id;
            private String lawyer_mobile;
            private int is_commented;
            private String level ;
            private String content;
            
            public String getLevel() {
				return level;
			}

			public void setLevel(String level) {
				this.level = level;
			}


            public String getOrderno() {
                return orderno;
            }

            public void setOrderno(String orderno) {
                this.orderno = orderno;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getPay_time() {
                return pay_time;
            }

            public void setPay_time(String pay_time) {
                this.pay_time = pay_time;
            }

            public String getLawyer() {
                return lawyer;
            }

            public void setLawyer(String lawyer) {
                this.lawyer = lawyer;
            }

            public String getUsers() {
                return users;
            }

            public void setUsers(String users) {
                this.users = users;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getLawyer_id() {
                return lawyer_id;
            }

            public void setLawyer_id(int lawyer_id) {
                this.lawyer_id = lawyer_id;
            }

            public String getLawyer_mobile() {
                return lawyer_mobile;
            }

            public void setLawyer_mobile(String lawyer_mobile) {
                this.lawyer_mobile = lawyer_mobile;
            }

            public int getIs_commented() {
                return is_commented;
            }

            public void setIs_commented(int is_commented) {
                this.is_commented = is_commented;
            }

			public String getContent() {
				return content;
			}

			public void setContent(String content) {
				this.content = content;
			}

          
        }

        public static class BaseInfoBean {
            private String domain;
            private String meet_time;
            private String meet_address;
            private String description;

            public String getDomain() {
                return domain;
            }

            public void setDomain(String domain) {
                this.domain = domain;
            }

            public String getMeet_time() {
                return meet_time;
            }

            public void setMeet_time(String meet_time) {
                this.meet_time = meet_time;
            }

            public String getMeet_address() {
                return meet_address;
            }

            public void setMeet_address(String meet_address) {
                this.meet_address = meet_address;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }
    }
}

	
	

