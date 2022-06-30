package com.yhy.hzzll.my.entity;

import java.util.List;

public class CollOrderDetialsEntity {


    /**
     * code : 0
     * message : 查询成功
     * data : {"order_info":{"order_no":"71718004908082964","amount":"38.00","amount_pay":"38.00","amount_discount":"0.00","order_status":"待付款","status_id":1,"nickname":"uhslkv","mobile":"13000000007","type_name":"婚姻保","data_time":"2018-08-08 15:32:48"},"complaint":null}
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
         * order_info : {"order_no":"71718004908082964","amount":"38.00","amount_pay":"38.00","amount_discount":"0.00","order_status":"待付款","status_id":1,"nickname":"uhslkv","mobile":"13000000007","type_name":"婚姻保","data_time":"2018-08-08 15:32:48"}
         * complaint : null
         */

        private OrderInfoBean order_info;
        private Object complaint;
        private  List<IncomeListsBean> income_lists;
        private  String income_total;

        public String getIncome_total() {
            return income_total;
        }

        public void setIncome_total(String income_total) {
            this.income_total = income_total;
        }

        public List<IncomeListsBean> getIncome_lists() {
            return income_lists;
        }

        public void setIncome_lists(List<IncomeListsBean> income_lists) {
            this.income_lists = income_lists;
        }

        public OrderInfoBean getOrder_info() {
            return order_info;
        }

        public void setOrder_info(OrderInfoBean order_info) {
            this.order_info = order_info;
        }

        public Object getComplaint() {
            return complaint;
        }

        public void setComplaint(Object complaint) {
            this.complaint = complaint;
        }

        public static class OrderInfoBean {
            /**
             * order_no : 71718004908082964
             * amount : 38.00
             * amount_pay : 38.00
             * amount_discount : 0.00
             * order_status : 待付款
             * status_id : 1
             * nickname : uhslkv
             * mobile : 13000000007
             * type_name : 婚姻保
             * data_time : 2018-08-08 15:32:48
             */

            private String order_no;
            private int consult_id;
            private String amount;
            private String amount_pay;
            private String amount_discount;
            private String order_status;
            private int status_id;
            private String is_my;
            private String is_public;
            private String nickname;
            private String mobile;
            private String type_name;
            private String data_time;
            private String private_order_no;
            private String amount_employ;
            private String amount_d;
            private String amount_pay_employ;
            private String amount_discount_employ;


            public String getIs_public() {
                return is_public;
            }

            public void setIs_public(String is_public) {
                this.is_public = is_public;
            }

            public String getAmount_d() {
                return amount_d;
            }

            public void setAmount_d(String amount_d) {
                this.amount_d = amount_d;
            }

            public String getAmount_employ() {
                return amount_employ;
            }

            public void setAmount_employ(String amount_employ) {
                this.amount_employ = amount_employ;
            }

            public String getAmount_pay_employ() {
                return amount_pay_employ;
            }

            public void setAmount_pay_employ(String amount_pay_employ) {
                this.amount_pay_employ = amount_pay_employ;
            }

            public String getAmount_discount_employ() {
                return amount_discount_employ;
            }

            public void setAmount_discount_employ(String amount_discount_employ) {
                this.amount_discount_employ = amount_discount_employ;
            }

            public int getConsult_id() {
                return consult_id;
            }

            public void setConsult_id(int consult_id) {
                this.consult_id = consult_id;
            }

            public String getIs_my() {
                return is_my;
            }

            public void setIs_my(String is_my) {
                this.is_my = is_my;
            }

            public String getPrivate_order_no() {
                return private_order_no;
            }

            public void setPrivate_order_no(String private_order_no) {
                this.private_order_no = private_order_no;
            }

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getAmount_pay() {
                return amount_pay;
            }

            public void setAmount_pay(String amount_pay) {
                this.amount_pay = amount_pay;
            }

            public String getAmount_discount() {
                return amount_discount;
            }

            public void setAmount_discount(String amount_discount) {
                this.amount_discount = amount_discount;
            }

            public String getOrder_status() {
                return order_status;
            }

            public void setOrder_status(String order_status) {
                this.order_status = order_status;
            }

            public int getStatus_id() {
                return status_id;
            }

            public void setStatus_id(int status_id) {
                this.status_id = status_id;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }

            public String getData_time() {
                return data_time;
            }

            public void setData_time(String data_time) {
                this.data_time = data_time;
            }
        }

        public static class IncomeListsBean {
            /**
             * title : 回复咨询红包 2018-11-08 16:49:03
             * amount : 4,067.00
             */

            private String title;
            private String amount;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }
        }

    }
}
