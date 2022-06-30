package com.yhy.hzzll.common.entity;

import java.util.List;

/**
 * Created by chengying on 2017/7/13.
 */

public class CaseTypeEntity {

    /**
     * code : 0
     * message : 查询成功
     * data : [{"id":"1","name":"金融借贷"},{"id":"2","name":"建筑房产"},{"id":"3","name":"投资证券"},{"id":"4","name":"并购重组"},{"id":"5","name":"遗嘱继承"},{"id":"6","name":"公司事务"},{"id":"7","name":"婚姻家庭"},{"id":"8","name":"债权债务"},{"id":"9","name":"合同纠纷"},{"id":"10","name":"人身损害"},{"id":"11","name":"交通保险"},{"id":"12","name":"医疗事故"},{"id":"13","name":"劳动纠纷"},{"id":"14","name":"环境保护"},{"id":"15","name":"海事海商"},{"id":"16","name":"知识产权"},{"id":"17","name":"征地拆产"},{"id":"18","name":"涉外事务"},{"id":"19","name":"行政诉讼"},{"id":"20","name":"行政复议"},{"id":"21","name":"刑事诉讼"},{"id":"22","name":"法律顾问"},{"id":"23","name":"国家赔偿"},{"id":"24","name":"公司上市"},{"id":"25","name":"消费权益"},{"id":"26","name":"其它"}]
     */

    private int code;
    private String message;
    private List<CaseType> data;

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

    public List<CaseType> getData() {
        return data;
    }

    public void setData(List<CaseType> data) {
        this.data = data;
    }

    public static class CaseType {
        /**
         * id : 1
         * name : 金融借贷
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
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
