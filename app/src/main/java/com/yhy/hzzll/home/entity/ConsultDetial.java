package com.yhy.hzzll.home.entity;

/**
 * Created by chengying on 2017/9/21.
 */

public class ConsultDetial {

    /**
     * code : 0
     * message : 查询成功!
     * data : {"id":356,"content":"交通保险小孩没成年时被判决由父亲扶养，户口归属父亲名下，但实际小孩是跟随母亲生活，成年后小孩认为父亲没尽过责任，想将户口改到母亲名下。问：成年后该父亲是否仍然有扶养权监护权？如有，应该如何变更归属于母亲？户口是否可以一起变更？所需手续是成年小孩自己名义去办，还是必须由母亲名义办理？ ","count_view":8,"count_pursue":0,"speech_length":"","count_follow":0,"count_reply":0,"mobile":"","fileurl":"","nickname":"刁冬梅","avatar":"http://silvapi.hzzll.com/img/avatar.jpg","whole_name":{"simple_name":"黔东南","area_name":"黔东南苗族侗族自治州","whole_name":"贵州省-黔东南苗族侗族自治州"},"legal_name":"交通保险","date_time":"02/18","created_at":"2017-02-18 00:30:02","is_self":false,"is_adopt":false,"consult_follower":"1","count":0}
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
         * id : 356
         * content : 交通保险小孩没成年时被判决由父亲扶养，户口归属父亲名下，但实际小孩是跟随母亲生活，成年后小孩认为父亲没尽过责任，想将户口改到母亲名下。问：成年后该父亲是否仍然有扶养权监护权？如有，应该如何变更归属于母亲？户口是否可以一起变更？所需手续是成年小孩自己名义去办，还是必须由母亲名义办理？
         * count_view : 8
         * count_pursue : 0
         * speech_length :
         * count_follow : 0
         * count_reply : 0
         * mobile :
         * fileurl :
         * nickname : 刁冬梅
         * avatar : http://silvapi.hzzll.com/img/avatar.jpg
         * whole_name : {"simple_name":"黔东南","area_name":"黔东南苗族侗族自治州","whole_name":"贵州省-黔东南苗族侗族自治州"}
         * legal_name : 交通保险
         * date_time : 02/18
         * created_at : 2017-02-18 00:30:02
         * is_self : false
         * is_adopt : false
         * consult_follower : 1
         * count : 0
         */

        private int id;
        private String content;
        private int count_view;
        private int count_pursue;
        private String speech_length;
        private int count_follow;
        private int count_reply;
        private String mobile;
        private String fileurl;
        private String nickname;
        private String avatar;
        private WholeNameBean whole_name;
        private String legal_name;
        private String date_time;
        private String created_at;
        private boolean is_self;
        private boolean is_adopt;
        private String consult_follower;
        private int count;

        public int getIs_closed() {
            return is_closed;
        }

        private int is_closed;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getCount_view() {
            return count_view;
        }

        public void setCount_view(int count_view) {
            this.count_view = count_view;
        }

        public int getCount_pursue() {
            return count_pursue;
        }

        public void setCount_pursue(int count_pursue) {
            this.count_pursue = count_pursue;
        }

        public String getSpeech_length() {
            return speech_length;
        }

        public void setSpeech_length(String speech_length) {
            this.speech_length = speech_length;
        }

        public int getCount_follow() {
            return count_follow;
        }

        public void setCount_follow(int count_follow) {
            this.count_follow = count_follow;
        }

        public int getCount_reply() {
            return count_reply;
        }

        public void setCount_reply(int count_reply) {
            this.count_reply = count_reply;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getFileurl() {
            return fileurl;
        }

        public void setFileurl(String fileurl) {
            this.fileurl = fileurl;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public WholeNameBean getWhole_name() {
            return whole_name;
        }

        public void setWhole_name(WholeNameBean whole_name) {
            this.whole_name = whole_name;
        }

        public String getLegal_name() {
            return legal_name;
        }

        public void setLegal_name(String legal_name) {
            this.legal_name = legal_name;
        }

        public String getDate_time() {
            return date_time;
        }

        public void setDate_time(String date_time) {
            this.date_time = date_time;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public boolean isIs_self() {
            return is_self;
        }

        public void setIs_self(boolean is_self) {
            this.is_self = is_self;
        }

        public boolean isIs_adopt() {
            return is_adopt;
        }

        public void setIs_adopt(boolean is_adopt) {
            this.is_adopt = is_adopt;
        }

        public String getConsult_follower() {
            return consult_follower;
        }

        public void setConsult_follower(String consult_follower) {
            this.consult_follower = consult_follower;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public static class WholeNameBean {
            /**
             * simple_name : 黔东南
             * area_name : 黔东南苗族侗族自治州
             * whole_name : 贵州省-黔东南苗族侗族自治州
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
