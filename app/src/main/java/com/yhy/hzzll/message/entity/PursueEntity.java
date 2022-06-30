package com.yhy.hzzll.message.entity;

import java.util.List;

/**
 * Created by chengying on 2017/9/6.
 */

public class PursueEntity {

    /**
     * code : 0
     * message : 查询成功
     * data : {"pages":1,"total":5,"list":[{"sender_id":456,"relation_id":104,"send_time":"2017-09-01 10:14:24","reply_id":314,"content":"读后感时间啊干活干活干活干活干活干活干活干活干活干活干活干活干活干活干活干活干活经理","consult_id":240,"nickname":"根深蒂固","avatar":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/456/20170822/571dfdd3bc79c3645e6a63ce0c349324.png"},{"sender_id":456,"relation_id":109,"send_time":"2017-09-04 09:42:58","reply_id":331,"content":"DHFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF","consult_id":259,"nickname":"根深蒂固","avatar":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/456/20170822/571dfdd3bc79c3645e6a63ce0c349324.png"},{"sender_id":13104,"relation_id":116,"send_time":"2017-09-05 10:53:16","reply_id":543,"content":"额u如题如题如题如题如题如题如题如题如题","consult_id":649,"nickname":"石全松","avatar":"http://silvapi.hzzll.com/img/avatar.jpg"},{"sender_id":442,"relation_id":128,"send_time":"2017-09-06 09:16:18","reply_id":562,"content":"这些刺激性","consult_id":668,"nickname":"私律用户","avatar":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/442/20170815/21d4461b5fd3f8e0c6fbdfb033b5996b.png"},{"sender_id":8919,"relation_id":131,"send_time":"2017-09-06 10:04:35","reply_id":577,"content":"考虑考虑了","consult_id":672,"nickname":"人好多人","avatar":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/8919/20170824/6142a0cc0bbabb6806e0eab86c28e81d.png"}]}
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
         * list : [{"sender_id":456,"relation_id":104,"send_time":"2017-09-01 10:14:24","reply_id":314,"content":"读后感时间啊干活干活干活干活干活干活干活干活干活干活干活干活干活干活干活干活干活经理","consult_id":240,"nickname":"根深蒂固","avatar":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/456/20170822/571dfdd3bc79c3645e6a63ce0c349324.png"},{"sender_id":456,"relation_id":109,"send_time":"2017-09-04 09:42:58","reply_id":331,"content":"DHFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF","consult_id":259,"nickname":"根深蒂固","avatar":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/456/20170822/571dfdd3bc79c3645e6a63ce0c349324.png"},{"sender_id":13104,"relation_id":116,"send_time":"2017-09-05 10:53:16","reply_id":543,"content":"额u如题如题如题如题如题如题如题如题如题","consult_id":649,"nickname":"石全松","avatar":"http://silvapi.hzzll.com/img/avatar.jpg"},{"sender_id":442,"relation_id":128,"send_time":"2017-09-06 09:16:18","reply_id":562,"content":"这些刺激性","consult_id":668,"nickname":"私律用户","avatar":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/442/20170815/21d4461b5fd3f8e0c6fbdfb033b5996b.png"},{"sender_id":8919,"relation_id":131,"send_time":"2017-09-06 10:04:35","reply_id":577,"content":"考虑考虑了","consult_id":672,"nickname":"人好多人","avatar":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/8919/20170824/6142a0cc0bbabb6806e0eab86c28e81d.png"}]
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
             * message_id
             * sender_id : 456
             * relation_id : 104
             * send_time : 2017-09-01 10:14:24
             * reply_id : 314
             * content : 读后感时间啊干活干活干活干活干活干活干活干活干活干活干活干活干活干活干活干活干活经理
             * consult_id : 240
             * nickname : 根深蒂固
             * avatar : http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/456/20170822/571dfdd3bc79c3645e6a63ce0c349324.png
             */

            private int sender_id;
            private int relation_id;
            private int message_id;

            public int getMessage_id() {
                return message_id;
            }

            private String send_time;
            private int reply_id;
            private String content;
            private int consult_id;

            public int getCount() {
                return count;
            }

            private int count;
            private String nickname;
            private String avatar;

            public int getSender_id() {
                return sender_id;
            }

            public void setSender_id(int sender_id) {
                this.sender_id = sender_id;
            }

            public int getRelation_id() {
                return relation_id;
            }

            public void setRelation_id(int relation_id) {
                this.relation_id = relation_id;
            }

            public String getSend_time() {
                return send_time;
            }

            public void setSend_time(String send_time) {
                this.send_time = send_time;
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

            public int getConsult_id() {
                return consult_id;
            }

            public void setConsult_id(int consult_id) {
                this.consult_id = consult_id;
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
        }
    }
}
