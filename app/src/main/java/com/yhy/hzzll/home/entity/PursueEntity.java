package com.yhy.hzzll.home.entity;

import java.util.List;

/**
 * Created by chengying on 2017/8/8.
 */

public class PursueEntity {

    /**
     * code : 0
     * message : 查询结果!
     * data : {"pages":1,"total":5,"list":[{"id":"7","consult_id":15,"users_id":213,"reply_id":42,"content":"总呢看啊","created_at":"2017-08-07 09:16:14","nickname":"wen","avatar":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/413/20170721/0ad2d9feffda69fd4afafe38eaa682a4.jpg","date_time":"2017-08-07 09:16:14","type":"职业律师"},{"id":"6","consult_id":15,"users_id":57,"reply_id":42,"content":"总呢看啊","created_at":"2017-08-04 16:59:21","nickname":"何虎2","avatar":"http://silvapi.hzzll.com/img/avatar.jpg","date_time":"2017-08-04 16:59:21","type":"用户"},{"id":"5","consult_id":15,"users_id":213,"reply_id":42,"content":"咯我到家我地","created_at":"2017-08-04 16:21:45","nickname":"wen","avatar":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/413/20170721/0ad2d9feffda69fd4afafe38eaa682a4.jpg","date_time":"2017-08-04 16:21:45","type":"职业律师"},{"id":"4","consult_id":15,"users_id":213,"reply_id":42,"content":"咯我到家我地","created_at":"2017-08-04 16:21:06","nickname":"wen","avatar":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/413/20170721/0ad2d9feffda69fd4afafe38eaa682a4.jpg","date_time":"2017-08-04 16:21:06","type":"职业律师"},{"id":"3","consult_id":15,"users_id":57,"reply_id":42,"content":"咯我到家我地","created_at":"2017-08-04 16:19:32","nickname":"何虎2","avatar":"http://silvapi.hzzll.com/img/avatar.jpg","date_time":"2017-08-04 16:19:32","type":"用户"}]}
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
         * total : 5
         * list : [{"id":"7","consult_id":15,"users_id":213,"reply_id":42,"content":"总呢看啊","created_at":"2017-08-07 09:16:14","nickname":"wen","avatar":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/413/20170721/0ad2d9feffda69fd4afafe38eaa682a4.jpg","date_time":"2017-08-07 09:16:14","type":"职业律师"},{"id":"6","consult_id":15,"users_id":57,"reply_id":42,"content":"总呢看啊","created_at":"2017-08-04 16:59:21","nickname":"何虎2","avatar":"http://silvapi.hzzll.com/img/avatar.jpg","date_time":"2017-08-04 16:59:21","type":"用户"},{"id":"5","consult_id":15,"users_id":213,"reply_id":42,"content":"咯我到家我地","created_at":"2017-08-04 16:21:45","nickname":"wen","avatar":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/413/20170721/0ad2d9feffda69fd4afafe38eaa682a4.jpg","date_time":"2017-08-04 16:21:45","type":"职业律师"},{"id":"4","consult_id":15,"users_id":213,"reply_id":42,"content":"咯我到家我地","created_at":"2017-08-04 16:21:06","nickname":"wen","avatar":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/413/20170721/0ad2d9feffda69fd4afafe38eaa682a4.jpg","date_time":"2017-08-04 16:21:06","type":"职业律师"},{"id":"3","consult_id":15,"users_id":57,"reply_id":42,"content":"咯我到家我地","created_at":"2017-08-04 16:19:32","nickname":"何虎2","avatar":"http://silvapi.hzzll.com/img/avatar.jpg","date_time":"2017-08-04 16:19:32","type":"用户"}]
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
             * id : 7
             * consult_id : 15
             * users_id : 213
             * reply_id : 42
             * content : 总呢看啊
             * created_at : 2017-08-07 09:16:14
             * nickname : wen
             * avatar : http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/413/20170721/0ad2d9feffda69fd4afafe38eaa682a4.jpg
             * date_time : 2017-08-07 09:16:14
             * type : 职业律师
             */

            private String id;
            private int consult_id;
            private int users_id;
            private int reply_id;
            private String content;
            private String created_at;
            private String nickname;
            private String avatar;
            private String date_time;
            private String type;

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

            public int getReply_id() {
                return reply_id;
            }

            public void setReply_id(int reply_id) {
                this.reply_id = reply_id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
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

            public String getDate_time() {
                return date_time;
            }

            public void setDate_time(String date_time) {
                this.date_time = date_time;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
