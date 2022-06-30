package com.yhy.hzzll.home.entity;

import java.util.List;

public class OrderListEntity {


    /**
     * code : 0
     * message : 查询成功
     * data : {"pages":1,"total":2,"list":[{"id":297,"amount":"38.00","order_status":"进行中","type_name":"债权保","order_no":"71818003108248942","status_id":4,"create_time":"2018-08-24 11:23:37"},{"id":5,"amount":"0.00","order_status":"待托管","type_name":"聘请律师","order_no":"","status_id":12,"create_time":"2018-08-27 15:48:09"}]}
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
         * list : [{"id":297,"amount":"38.00","order_status":"进行中","type_name":"债权保","order_no":"71818003108248942","status_id":4,"create_time":"2018-08-24 11:23:37"},{"id":5,"amount":"0.00","order_status":"待托管","type_name":"聘请律师","order_no":"","status_id":12,"create_time":"2018-08-27 15:48:09"}]
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
             * id : 297
             * amount : 38.00
             * order_status : 进行中
             * type_name : 债权保
             * order_no : 71818003108248942
             * status_id : 4
             * create_time : 2018-08-24 11:23:37
             */

            private int id;
            private String amount;
            private String order_status;
            private String type_name;
            private String order_no;
            private int status_id;
            private String create_time;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getOrder_status() {
                return order_status;
            }

            public void setOrder_status(String order_status) {
                this.order_status = order_status;
            }

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public int getStatus_id() {
                return status_id;
            }

            public void setStatus_id(int status_id) {
                this.status_id = status_id;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }
    }
}
