package com.yhy.hzzll.entity;

import java.util.List;

/**
 * 咨询  回复
 * 
 * @author Yang
 * 
 */
public class ConsultReplysEntity extends BaseEntity {

    /**
     * code : 0
     * message : 查询结果!
     * data : {"pages":1,"total":2,"list":[{"id":"1","consult_id":1,"users_id":57,"content":"asdasdfasdfasdfasdf222","file_attachment_id":"","speech_length":0,"base_region_id":{"simple_name":"武汉","area_name":"武汉市","whole_name":"湖北省,武汉市"},"count_pursue":0,"count_pursue_reply":0,"count_like":3,"count_attend":0,"count_reward":0,"is_adopt":0,"is_delete":0,"order_no":"100","created_at":"2017-07-20 10:47:14","updated_at":"2017-08-01 10:25:30","username":"252","head_img":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/227/20170717/84fabe8b5e29f97c3c642febacc3eb22.png","lawyer_type":"实习律师","time":"1天前"},{"id":"2","consult_id":1,"users_id":1,"content":"25522423e","file_attachment_id":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/52/20170705/a68b72c2843adfbcb4b3ac0383b4a84a.jpg","speech_length":0,"base_region_id":{"simple_name":"黄石","area_name":"黄石市","whole_name":"湖北省,黄石市"},"count_pursue":0,"count_pursue_reply":0,"count_like":2,"count_attend":3,"count_reward":3,"is_adopt":0,"is_delete":0,"order_no":"12560","created_at":"2017-07-26 10:20:43","updated_at":"2017-08-01 15:47:32","username":"axxd21","head_img":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/1/20170621/13ea427a3ce52cf1ad2cf451f1a3e750.jpg","lawyer_type":"执业律师","time":"19小时前"}]}
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
         * list : [{"id":"1","consult_id":1,"users_id":57,"content":"asdasdfasdfasdfasdf222","file_attachment_id":"","speech_length":0,"base_region_id":{"simple_name":"武汉","area_name":"武汉市","whole_name":"湖北省,武汉市"},"count_pursue":0,"count_pursue_reply":0,"count_like":3,"count_attend":0,"count_reward":0,"is_adopt":0,"is_delete":0,"order_no":"100","created_at":"2017-07-20 10:47:14","updated_at":"2017-08-01 10:25:30","username":"252","head_img":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/227/20170717/84fabe8b5e29f97c3c642febacc3eb22.png","lawyer_type":"实习律师","time":"1天前"},{"id":"2","consult_id":1,"users_id":1,"content":"25522423e","file_attachment_id":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/52/20170705/a68b72c2843adfbcb4b3ac0383b4a84a.jpg","speech_length":0,"base_region_id":{"simple_name":"黄石","area_name":"黄石市","whole_name":"湖北省,黄石市"},"count_pursue":0,"count_pursue_reply":0,"count_like":2,"count_attend":3,"count_reward":3,"is_adopt":0,"is_delete":0,"order_no":"12560","created_at":"2017-07-26 10:20:43","updated_at":"2017-08-01 15:47:32","username":"axxd21","head_img":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/1/20170621/13ea427a3ce52cf1ad2cf451f1a3e750.jpg","lawyer_type":"执业律师","time":"19小时前"}]
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
             * id : 1
             * consult_id : 1
             * users_id : 57
             * content : asdasdfasdfasdfasdf222
             * file_attachment_id :
             * speech_length : 0
             * base_region_id : {"simple_name":"武汉","area_name":"武汉市","whole_name":"湖北省,武汉市"}
             * count_pursue : 0
             * count_pursue_reply : 0
             * count_like : 3
             * count_attend : 0
             * count_reward : 0
             * is_adopt : 0
             * is_delete : 0
             * order_no : 100
             * created_at : 2017-07-20 10:47:14
             * updated_at : 2017-08-01 10:25:30
             * username : 252
             * head_img : http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/227/20170717/84fabe8b5e29f97c3c642febacc3eb22.png
             * lawyer_type : 实习律师
             * time : 1天前
             * is_sns_like : flase
             */

            private String id;
            private int consult_id;
            private int users_id;
            private String content;
            private String file_attachment_id;
            private int speech_length;
            private BaseRegionIdBean base_region_id;
            private int count_pursue;
            private int count_pursue_reply;
            private int count_like;
            private int count_attend;
            private int count_reward;
            private int is_adopt;
            private int is_delete;

            public int getMessage_id() {
                return message_id;
            }

            private int message_id;
            private String order_no;
            private String created_at;
            private String updated_at;
            private String username;
            private String head_img;
            private String lawyer_type;
            private String time;

            public String getIs_sns_like() {
                return is_sns_like;
            }

            public void setIs_sns_like(String is_sns_like) {
                this.is_sns_like = is_sns_like;
            }

            private String is_sns_like;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getConsult_id() {
                return consult_id;
            }

            public void setConsult_id(int consult_id) {
                this.consult_id = consult_id;
            }

            public int getUsers_id() {
                return users_id;
            }

            public void setUsers_id(int users_id) {
                this.users_id = users_id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getFile_attachment_id() {
                return file_attachment_id;
            }

            public void setFile_attachment_id(String file_attachment_id) {
                this.file_attachment_id = file_attachment_id;
            }

            public int getSpeech_length() {
                return speech_length;
            }

            public void setSpeech_length(int speech_length) {
                this.speech_length = speech_length;
            }

            public BaseRegionIdBean getBase_region_id() {
                return base_region_id;
            }

            public void setBase_region_id(BaseRegionIdBean base_region_id) {
                this.base_region_id = base_region_id;
            }

            public int getCount_pursue() {
                return count_pursue;
            }

            public void setCount_pursue(int count_pursue) {
                this.count_pursue = count_pursue;
            }

            public int getCount_pursue_reply() {
                return count_pursue_reply;
            }

            public void setCount_pursue_reply(int count_pursue_reply) {
                this.count_pursue_reply = count_pursue_reply;
            }

            public int getCount_like() {
                return count_like;
            }

            public void setCount_like(int count_like) {
                this.count_like = count_like;
            }

            public int getCount_attend() {
                return count_attend;
            }

            public void setCount_attend(int count_attend) {
                this.count_attend = count_attend;
            }

            public int getCount_reward() {
                return count_reward;
            }

            public void setCount_reward(int count_reward) {
                this.count_reward = count_reward;
            }

            public int getIs_adopt() {
                return is_adopt;
            }

            public void setIs_adopt(int is_adopt) {
                this.is_adopt = is_adopt;
            }

            public int getIs_delete() {
                return is_delete;
            }

            public void setIs_delete(int is_delete) {
                this.is_delete = is_delete;
            }

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getHead_img() {
                return head_img;
            }

            public void setHead_img(String head_img) {
                this.head_img = head_img;
            }

            public String getLawyer_type() {
                return lawyer_type;
            }

            public void setLawyer_type(String lawyer_type) {
                this.lawyer_type = lawyer_type;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public static class BaseRegionIdBean {
                /**
                 * simple_name : 武汉
                 * area_name : 武汉市
                 * whole_name : 湖北省,武汉市
                 */

                private String simple_name;
                private String area_name;
                private String whole_name;

                public String getSimple_name() {
                    return simple_name;
                }

                public void setSimple_name(String simple_name) {
                    this.simple_name = simple_name;
                }

                public String getArea_name() {
                    return area_name;
                }

                public void setArea_name(String area_name) {
                    this.area_name = area_name;
                }

                public String getWhole_name() {
                    return whole_name;
                }

                public void setWhole_name(String whole_name) {
                    this.whole_name = whole_name;
                }
            }
        }
    }
}
