package com.yhy.hzzll.home.entity;

import java.util.List;

public class Questionnaire {


    /**
     * code : 0
     * message : 查询成功
     * data : [{"users_id":89,"type_id":1,"t_id":4,"d_name":"别别别","order_no":"71718008910258296","t_name":"女方是否111111"},{"users_id":89,"type_id":1,"t_id":2,"d_name":"未怀孕","order_no":"71718008910258296","t_name":"女方是否怀孕"},{"users_id":89,"type_id":1,"t_id":3,"d_name":"男方","order_no":"71718008910258296","t_name":"房产由谁购买"},{"users_id":89,"type_id":1,"t_id":5,"d_name":"哎哎哎","order_no":"71718008910258296","t_name":"男女方xxxxx"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * users_id : 89
         * type_id : 1
         * t_id : 4
         * d_name : 别别别
         * order_no : 71718008910258296
         * t_name : 女方是否111111
         */

        private int users_id;
        private int type_id;
        private int t_id;
        private String d_name;
        private String order_no;
        private String t_name;

        public int getUsers_id() {
            return users_id;
        }

        public void setUsers_id(int users_id) {
            this.users_id = users_id;
        }

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }

        public int getT_id() {
            return t_id;
        }

        public void setT_id(int t_id) {
            this.t_id = t_id;
        }

        public String getD_name() {
            return d_name;
        }

        public void setD_name(String d_name) {
            this.d_name = d_name;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getT_name() {
            return t_name;
        }

        public void setT_name(String t_name) {
            this.t_name = t_name;
        }
    }
}
