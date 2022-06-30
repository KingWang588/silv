package com.yhy.hzzll.mian.entity;

import java.util.List;

/**
 * Created by chengying on 2017/9/7.
 */

public class RankEntity {

    /**
     * code : 0
     * message : 查询成功
     * data : [{"id":"27","code":"1","name":"10"},{"id":"28","code":"2","name":"6"},{"id":"29","code":"3","name":"4"}]
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
         * id : 27
         * code : 1
         * name : 10
         */

        private String id;
        private String code;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
