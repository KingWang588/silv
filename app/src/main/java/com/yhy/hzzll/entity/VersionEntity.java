package com.yhy.hzzll.entity;

/**
 * Created by 一合鱼 on 2017,3,6,0006.
 */

public class VersionEntity {


    /**
     * code : 000000
     * msg : 获取成功
     * data : {"version":"1.1.7","downurl":"http://oldapi.hzzll.com/Uploads/apk/HZZLLTest.apk","description":"测试版本更新"}
     */

    private String code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * version : 1.1.7
         * downurl : http://oldapi.hzzll.com/Uploads/apk/HZZLLTest.apk
         * description : 测试版本更新
         */

        private String version;
        private String downurl;
        private String description;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getDownurl() {
            return downurl;
        }

        public void setDownurl(String downurl) {
            this.downurl = downurl;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
