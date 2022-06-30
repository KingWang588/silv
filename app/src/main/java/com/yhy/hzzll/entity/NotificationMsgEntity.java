package com.yhy.hzzll.entity;

import java.io.Serializable;
import java.util.List;

public class NotificationMsgEntity implements Serializable {
	



    /**
     * type : admin
     * newmsg : {"admin":{"id":"838","userid":"10000","person":"10058","is_mine":"0","msg":"ask","ctime":"1474874544","msg_type":"admin","msg_subtype":null,"msg_title":null,"msg_id":"229"},"count":2}
     */

    private String type;
    /**
     * admin : {"id":"838","userid":"10000","person":"10058","is_mine":"0","msg":"ask","ctime":"1474874544","msg_type":"admin","msg_subtype":null,"msg_title":null,"msg_id":"229"}
     * count : 2
     */

    private NewmsgBean newmsg;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public NewmsgBean getNewmsg() {
        return newmsg;
    }

    public void setNewmsg(NewmsgBean newmsg) {
        this.newmsg = newmsg;
    }

    public static class NewmsgBean {
        /**
         * id : 838
         * userid : 10000
         * person : 10058
         * is_mine : 0
         * msg : ask
         * ctime : 1474874544
         * msg_type : admin
         * msg_subtype : null
         * msg_title : null
         * msg_id : 229
         */

        private AdminBean admin;
        private int count;

        public AdminBean getAdmin() {
            return admin;
        }

        public void setAdmin(AdminBean admin) {
            this.admin = admin;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public static class AdminBean {
            private String id;
            private String userid;
            private String person;
            private String is_mine;
            private String msg;
            private String ctime;
            private String msg_type;
            private Object msg_subtype;
            private Object msg_title;
            private String msg_id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getPerson() {
                return person;
            }

            public void setPerson(String person) {
                this.person = person;
            }

            public String getIs_mine() {
                return is_mine;
            }

            public void setIs_mine(String is_mine) {
                this.is_mine = is_mine;
            }

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getMsg_type() {
                return msg_type;
            }

            public void setMsg_type(String msg_type) {
                this.msg_type = msg_type;
            }

            public Object getMsg_subtype() {
                return msg_subtype;
            }

            public void setMsg_subtype(Object msg_subtype) {
                this.msg_subtype = msg_subtype;
            }

            public Object getMsg_title() {
                return msg_title;
            }

            public void setMsg_title(Object msg_title) {
                this.msg_title = msg_title;
            }

            public String getMsg_id() {
                return msg_id;
            }

            public void setMsg_id(String msg_id) {
                this.msg_id = msg_id;
            }
        }
    }

}
