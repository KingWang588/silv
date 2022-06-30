package com.yhy.hzzll.home.entity;

import java.util.List;

/**
 * Created by chengying on 2017/8/3.
 */

public class CommentsAndLikes {

    /**
     * code : 0
     * message : 查询结果!
     * data : {"sns_like_head_img_list":["http://silvapi.hzzll.com/img/avatar.jpg","http://silvapi.hzzll.com/img/avatar.jpg","http://silvapi.hzzll.com/img/avatar.jpg","http://silvapi.hzzll.com/img/avatar.jpg","http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/1/20170621/13ea427a3ce52cf1ad2cf451f1a3e750.jpg","http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/391/20170718/1ade44e91982ee3157d963ac79849c84.png","http://silvapi.hzzll.com/img/avatar.jpg"],"lawyer_evaluate_list":{"pages":1,"total":2,"list":[{"nickname":"blues.deng","content":"22222222","start_rate":2,"updated_at":"2017-08-01 14:44:40"},{"nickname":"blues.deng","content":"我","start_rate":6,"updated_at":"2017-07-24 16:12:06"}]}}
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
         * sns_like_head_img_list : ["http://silvapi.hzzll.com/img/avatar.jpg","http://silvapi.hzzll.com/img/avatar.jpg","http://silvapi.hzzll.com/img/avatar.jpg","http://silvapi.hzzll.com/img/avatar.jpg","http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/1/20170621/13ea427a3ce52cf1ad2cf451f1a3e750.jpg","http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/391/20170718/1ade44e91982ee3157d963ac79849c84.png","http://silvapi.hzzll.com/img/avatar.jpg"]
         * lawyer_evaluate_list : {"pages":1,"total":2,"list":[{"nickname":"blues.deng","content":"22222222","start_rate":2,"updated_at":"2017-08-01 14:44:40"},{"nickname":"blues.deng","content":"我","start_rate":6,"updated_at":"2017-07-24 16:12:06"}]}
         */

        private LawyerEvaluateListBean lawyer_evaluate_list;
        private List<String> sns_like_head_img_list;

        public LawyerEvaluateListBean getLawyer_evaluate_list() {
            return lawyer_evaluate_list;
        }

        public void setLawyer_evaluate_list(LawyerEvaluateListBean lawyer_evaluate_list) {
            this.lawyer_evaluate_list = lawyer_evaluate_list;
        }

        public List<String> getSns_like_head_img_list() {
            return sns_like_head_img_list;
        }

        public void setSns_like_head_img_list(List<String> sns_like_head_img_list) {
            this.sns_like_head_img_list = sns_like_head_img_list;
        }

        public static class LawyerEvaluateListBean {
            /**
             * pages : 1
             * total : 2
             * list : [{"nickname":"blues.deng","content":"22222222","start_rate":2,"updated_at":"2017-08-01 14:44:40"},{"nickname":"blues.deng","content":"我","start_rate":6,"updated_at":"2017-07-24 16:12:06"}]
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
                 * nickname : blues.deng
                 * content : 22222222
                 * start_rate : 2
                 * updated_at : 2017-08-01 14:44:40
                 */

                private String nickname;
                private String content;
                private int start_rate;
                private String updated_at;
                private String avatar;

                public String getAvatar() {
                    return avatar;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public int getStart_rate() {
                    return start_rate;
                }

                public void setStart_rate(int start_rate) {
                    this.start_rate = start_rate;
                }

                public String getUpdated_at() {
                    return updated_at;
                }

                public void setUpdated_at(String updated_at) {
                    this.updated_at = updated_at;
                }
            }
        }
    }
}
