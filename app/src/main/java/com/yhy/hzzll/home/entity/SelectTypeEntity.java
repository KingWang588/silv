package com.yhy.hzzll.home.entity;

import java.util.List;

public class SelectTypeEntity  {


    /**
     * code : 0
     * message : 申请成功
     * data : [{"id":6,"name":"聘请律师","delegate_type":[{"id":0,"name":"不限"},{"id":2,"name":"代写文书"},{"id":3,"name":"律师函"},{"id":4,"name":"调查取证"},{"id":5,"name":"法律意见书"},{"id":6,"name":"法律顾问"},{"id":7,"name":"其他"}]},{"id":5,"name":"债权保"},{"id":4,"name":"婚姻保"}]
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
         * id : 6
         * name : 聘请律师
         * delegate_type : [{"id":0,"name":"不限"},{"id":2,"name":"代写文书"},{"id":3,"name":"律师函"},{"id":4,"name":"调查取证"},{"id":5,"name":"法律意见书"},{"id":6,"name":"法律顾问"},{"id":7,"name":"其他"}]
         */

        private int id;
        private String name;
        private List<DelegateTypeBean> delegate_type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<DelegateTypeBean> getDelegate_type() {
            return delegate_type;
        }

        public void setDelegate_type(List<DelegateTypeBean> delegate_type) {
            this.delegate_type = delegate_type;
        }

        public static class DelegateTypeBean {
            /**
             * id : 0
             * name : 不限
             */

            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
