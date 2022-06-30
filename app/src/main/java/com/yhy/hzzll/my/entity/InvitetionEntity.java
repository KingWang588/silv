package com.yhy.hzzll.my.entity;

import java.util.List;

/**
 * Created by chengying on 2017/7/21.
 */

public class InvitetionEntity {


    /**
     * code : 0
     * message : 查询成功
     * data : {"general":{"lists":[{"users_id":"408","nickname":"私律用户","avatar":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/408/20170721/d7b91f2c0459cf3bd01d9654f04b2b63.jpg","user_personal_type_id":"2"},{"users_id":"408","nickname":"私律用户","avatar":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/408/20170721/d7b91f2c0459cf3bd01d9654f04b2b63.jpg","user_personal_type_id":"2"}],"num":0},"lawyer":{"lists":[{"users_id":"408","nickname":"私律用户","avatar":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/408/20170721/d7b91f2c0459cf3bd01d9654f04b2b63.jpg","user_personal_type_id":"2"},{"users_id":"408","nickname":"私律用户","avatar":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/408/20170721/d7b91f2c0459cf3bd01d9654f04b2b63.jpg","user_personal_type_id":"2"}],"num":"1"}}
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
         * general : {"lists":[{"users_id":"408","nickname":"私律用户","avatar":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/408/20170721/d7b91f2c0459cf3bd01d9654f04b2b63.jpg","user_personal_type_id":"2"},{"users_id":"408","nickname":"私律用户","avatar":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/408/20170721/d7b91f2c0459cf3bd01d9654f04b2b63.jpg","user_personal_type_id":"2"}],"num":0}
         * lawyer : {"lists":[{"users_id":"408","nickname":"私律用户","avatar":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/408/20170721/d7b91f2c0459cf3bd01d9654f04b2b63.jpg","user_personal_type_id":"2"},{"users_id":"408","nickname":"私律用户","avatar":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/408/20170721/d7b91f2c0459cf3bd01d9654f04b2b63.jpg","user_personal_type_id":"2"}],"num":"1"}
         */

        private GeneralBean general;
        private LawyerBean lawyer;

        public GeneralBean getGeneral() {
            return general;
        }

        public void setGeneral(GeneralBean general) {
            this.general = general;
        }

        public LawyerBean getLawyer() {
            return lawyer;
        }

        public void setLawyer(LawyerBean lawyer) {
            this.lawyer = lawyer;
        }

        public static class GeneralBean {
            /**
             * lists : [{"users_id":"408","nickname":"私律用户","avatar":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/408/20170721/d7b91f2c0459cf3bd01d9654f04b2b63.jpg","user_personal_type_id":"2"},{"users_id":"408","nickname":"私律用户","avatar":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/408/20170721/d7b91f2c0459cf3bd01d9654f04b2b63.jpg","user_personal_type_id":"2"}]
             * num : 0
             */

            private int num;
            private List<ListsBean> lists;

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public List<ListsBean> getLists() {
                return lists;
            }

            public void setLists(List<ListsBean> lists) {
                this.lists = lists;
            }

            public static class ListsBean {
                /**
                 * users_id : 408
                 * nickname : 私律用户
                 * avatar : http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/408/20170721/d7b91f2c0459cf3bd01d9654f04b2b63.jpg
                 * user_personal_type_id : 2
                 */

                private String users_id;
                private String nickname;
                private String avatar;
                private String user_personal_type_id;

                public String getUsers_id() {
                    return users_id;
                }

                public void setUsers_id(String users_id) {
                    this.users_id = users_id;
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

                public String getUser_personal_type_id() {
                    return user_personal_type_id;
                }

                public void setUser_personal_type_id(String user_personal_type_id) {
                    this.user_personal_type_id = user_personal_type_id;
                }
            }
        }

        public static class LawyerBean {
            /**
             * lists : [{"users_id":"408","nickname":"私律用户","avatar":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/408/20170721/d7b91f2c0459cf3bd01d9654f04b2b63.jpg","user_personal_type_id":"2"},{"users_id":"408","nickname":"私律用户","avatar":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/408/20170721/d7b91f2c0459cf3bd01d9654f04b2b63.jpg","user_personal_type_id":"2"}]
             * num : 1
             */

            private String num;
            private List<ListsBeanX> lists;

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public List<ListsBeanX> getLists() {
                return lists;
            }

            public void setLists(List<ListsBeanX> lists) {
                this.lists = lists;
            }

            public static class ListsBeanX {
                /**
                 * users_id : 408
                 * nickname : 私律用户
                 * avatar : http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/408/20170721/d7b91f2c0459cf3bd01d9654f04b2b63.jpg
                 * user_personal_type_id : 2
                 */

                private String users_id;
                private String nickname;
                private String truename;

                public String getTruename() {
                    return truename;
                }

                private String avatar;
                private String user_personal_type_id;

                public String getUsers_id() {
                    return users_id;
                }

                public void setUsers_id(String users_id) {
                    this.users_id = users_id;
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

                public String getUser_personal_type_id() {
                    return user_personal_type_id;
                }

                public void setUser_personal_type_id(String user_personal_type_id) {
                    this.user_personal_type_id = user_personal_type_id;
                }
            }
        }
    }
}
