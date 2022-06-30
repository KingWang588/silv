package com.yhy.hzzll.my.entity;

import java.util.List;

public class IncomeEntity {

    /**
     * code : 0
     * message : 查询成功
     * data : {"sum":2,"pages":1,"total":2,"list":[{"id":"193","inviter_id":"191","invitee_id":"193","nickname":"私律用户","avatar":"https://tapi.silvzone.com/img/avatar.jpg","mobile":"130****0050","created_at":"2018-03-23 15:21:42"},{"id":"192","inviter_id":"191","invitee_id":"192","nickname":"小胡","avatar":"https://tapi.silvzone.com/img/avatar.jpg","mobile":"130****0019","created_at":"2018-03-23 15:15:04"}]}
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
         * sum : 2
         * pages : 1
         * total : 2
         * list : [{"id":"193","inviter_id":"191","invitee_id":"193","nickname":"私律用户","avatar":"https://tapi.silvzone.com/img/avatar.jpg","mobile":"130****0050","created_at":"2018-03-23 15:21:42"},{"id":"192","inviter_id":"191","invitee_id":"192","nickname":"小胡","avatar":"https://tapi.silvzone.com/img/avatar.jpg","mobile":"130****0019","created_at":"2018-03-23 15:15:04"}]
         */

        private int sum;
        private int pages;
        private int total;
        private List<ListBean> list;

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }

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
            public ListBean(String id, String inviter_id, String invitee_id, String nickname, String avatar, String mobile, String created_at) {
                this.id = id;
                this.inviter_id = inviter_id;
                this.invitee_id = invitee_id;
                this.nickname = nickname;
                this.avatar = avatar;
                this.mobile = mobile;
                this.created_at = created_at;
            }

            /**
               id : 193
             * inviter_id : 191
             * invitee_id : 193
             * nickname : 私律用户
             * avatar : https://tapi.silvzone.com/img/avatar.jpg
             * mobile : 130****0050
             * created_at : 2018-03-23 15:21:42
             */




            private String id;
            private String inviter_id;
            private String invitee_id;
            private String nickname;
            private String avatar;
            private String mobile;
            private String created_at;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getInviter_id() {
                return inviter_id;
            }

            public void setInviter_id(String inviter_id) {
                this.inviter_id = inviter_id;
            }

            public String getInvitee_id() {
                return invitee_id;
            }

            public void setInvitee_id(String invitee_id) {
                this.invitee_id = invitee_id;
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

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }
        }
    }
}
