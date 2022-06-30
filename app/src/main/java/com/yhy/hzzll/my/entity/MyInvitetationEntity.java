package com.yhy.hzzll.my.entity;

import java.util.List;

public class MyInvitetationEntity {


    /**
     * code : 0
     * message : 查询成功
     * data : {"pages":1,"total":2,"list":[{"payee_id":191,"amount_pay":"2.00000","order_type_id":14,"payment_time":"2018-03-23 15:41:26","users_id":null,"mobile":null,"nickname":"私律用户","type_name":"注册"},{"payee_id":191,"amount_pay":"2.00000","order_type_id":14,"payment_time":"2018-03-23 15:15:06","users_id":null,"mobile":null,"nickname":"私律用户","type_name":"注册"}]}
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
         * list : [{"payee_id":191,"amount_pay":"2.00000","order_type_id":14,"payment_time":"2018-03-23 15:41:26","users_id":null,"mobile":null,"nickname":"私律用户","type_name":"注册"},{"payee_id":191,"amount_pay":"2.00000","order_type_id":14,"payment_time":"2018-03-23 15:15:06","users_id":null,"mobile":null,"nickname":"私律用户","type_name":"注册"}]
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

            public ListBean(int payee_id, String amount_pay, int order_type_id, String payment_time, String users_id, String mobile, String nickname, String type_name) {
                this.payee_id = payee_id;
                this.amount_pay = amount_pay;
                this.order_type_id = order_type_id;
                this.payment_time = payment_time;
                this.users_id = users_id;
                this.mobile = mobile;
                this.nickname = nickname;
                this.type_name = type_name;
            }

            /**
             * payee_id : 191
             * amount_pay : 2.00000
             * order_type_id : 14
             * payment_time : 2018-03-23 15:41:26
             * users_id : null
             * mobile : null
             * nickname : 私律用户
             * type_name : 注册
             */



            private int payee_id;
            private String amount_pay;
            private int order_type_id;
            private String payment_time;
            private String users_id;
            private String mobile;
            private String nickname;
            private String type_name;

            public int getPayee_id() {
                return payee_id;
            }

            public void setPayee_id(int payee_id) {
                this.payee_id = payee_id;
            }

            public String getAmount_pay() {
                return amount_pay;
            }

            public void setAmount_pay(String amount_pay) {
                this.amount_pay = amount_pay;
            }

            public int getOrder_type_id() {
                return order_type_id;
            }

            public void setOrder_type_id(int order_type_id) {
                this.order_type_id = order_type_id;
            }

            public String getPayment_time() {
                return payment_time;
            }

            public void setPayment_time(String payment_time) {
                this.payment_time = payment_time;
            }

            public String getUsers_id() {
                return users_id;
            }

            public void setUsers_id(String users_id) {
                this.users_id = users_id;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }
        }
    }
}
