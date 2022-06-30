package com.yhy.hzzll.message.entity;

import java.util.List;

/**
 * Created by chengying on 2017/8/31.
 */

public class FileEntity {

    /**
     * code : 0
     * message : 查询结果！
     * data : {"pages":1,"total":5,"list":[{"content":"{\"size\":4669,\"ext\":\"png\",\"url\":\"https:\\/\\/nos.netease.com\\/nim\\/NDMwNTc2Nw==\\/bmltYV81NzA4NzExNTRfMTUwNDA4MjQ3MTkzMV84NTA2NWM2MS00YWQwLTRiZmQtOTkxZS1kMzc4NTZhNWUwNjE=\",\"md5\":\"9ef1e9feb00ff3fded56ebb20a317bc5\",\"name\":\"AppIcon40x40@2x.png\"}","from_id":"15629037629","message_type_id":6,"send_time":"1504161362844"},{"content":"{\"md5\":\"7c907d327e9a368d5bb81a5f17fc626c\",\"name\":\"temp_image_2f36aeaa-6284-4442-b3ff-40da26fbd8d8.gif\",\"url\":\"https:\\/\\/nos.netease.com\\/nim\\/NDMwNTc2Nw==\\/bmltYV81NzA4NzExNTRfMTUwNDE0MzgwMDk3MF9mZmVmZTNkYy05N2RiLTQ5NDktYTY2Ni1iODM1MDM0NTUxYWY=\",\"size\":8840,\"ext\":\"gif\",\"w\":286,\"h\":287}","from_id":"15629037629","message_type_id":1,"send_time":"1504161341686"},{"content":"{\"md5\":\"b026324c6904b2a9cb4b88d6d61c81d1\",\"name\":\"dctp\",\"url\":\"https:\\/\\/nos.netease.com\\/nim\\/NDMwNTc2Nw==\\/bmltYV81NzA4NzExNTRfMTUwNDE0MzgwMDk3MV83NGNlNGNhMy0xNGIxLTRkYmItODFmOS04MWE0YjYxN2I5N2I=\",\"size\":2}","from_id":"15629037629","message_type_id":6,"send_time":"1504161336491"},{"content":"{\"md5\":\"ba0dc68709fefc56ad21e349d59cc188\",\"name\":\".tcookieid\",\"url\":\"https:\\/\\/nos.netease.com\\/nim\\/NDMwNTc2Nw==\\/bmltYV81NzA4NzExNTRfMTUwNDE0MzgwMDk3MV8xNmZmMTgzOC0yMDkxLTQxNDYtYTBkMy1kM2NjMDQxMmNjMGE=\",\"size\":33,\"ext\":\"tcookieid\"}","from_id":"15629037629","message_type_id":6,"send_time":"1504161325690"},{"content":"{\"md5\":\"c88c67d202d2b464107116cb614e54f5\",\"name\":\"temp_image_bdb40897-99e6-4e57-9c7f-7f662192eca8.jpg\",\"url\":\"https:\\/\\/nos.netease.com\\/nim\\/NDMwNTc2Nw==\\/bmltYV81NzA4NzExNTRfMTUwNDE0MzgwMDk3MV84YjVmMmI1Yi00NTMwLTQwYTUtOTJlMC1jMGNlODU4MDMyMWU=\",\"size\":45723,\"ext\":\"jpg\",\"w\":624,\"h\":831}","from_id":"15629037629","message_type_id":1,"send_time":"1504159448262"}]}
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
         * list : [{"content":"{\"size\":4669,\"ext\":\"png\",\"url\":\"https:\\/\\/nos.netease.com\\/nim\\/NDMwNTc2Nw==\\/bmltYV81NzA4NzExNTRfMTUwNDA4MjQ3MTkzMV84NTA2NWM2MS00YWQwLTRiZmQtOTkxZS1kMzc4NTZhNWUwNjE=\",\"md5\":\"9ef1e9feb00ff3fded56ebb20a317bc5\",\"name\":\"AppIcon40x40@2x.png\"}","from_id":"15629037629","message_type_id":6,"send_time":"1504161362844"},{"content":"{\"md5\":\"7c907d327e9a368d5bb81a5f17fc626c\",\"name\":\"temp_image_2f36aeaa-6284-4442-b3ff-40da26fbd8d8.gif\",\"url\":\"https:\\/\\/nos.netease.com\\/nim\\/NDMwNTc2Nw==\\/bmltYV81NzA4NzExNTRfMTUwNDE0MzgwMDk3MF9mZmVmZTNkYy05N2RiLTQ5NDktYTY2Ni1iODM1MDM0NTUxYWY=\",\"size\":8840,\"ext\":\"gif\",\"w\":286,\"h\":287}","from_id":"15629037629","message_type_id":1,"send_time":"1504161341686"},{"content":"{\"md5\":\"b026324c6904b2a9cb4b88d6d61c81d1\",\"name\":\"dctp\",\"url\":\"https:\\/\\/nos.netease.com\\/nim\\/NDMwNTc2Nw==\\/bmltYV81NzA4NzExNTRfMTUwNDE0MzgwMDk3MV83NGNlNGNhMy0xNGIxLTRkYmItODFmOS04MWE0YjYxN2I5N2I=\",\"size\":2}","from_id":"15629037629","message_type_id":6,"send_time":"1504161336491"},{"content":"{\"md5\":\"ba0dc68709fefc56ad21e349d59cc188\",\"name\":\".tcookieid\",\"url\":\"https:\\/\\/nos.netease.com\\/nim\\/NDMwNTc2Nw==\\/bmltYV81NzA4NzExNTRfMTUwNDE0MzgwMDk3MV8xNmZmMTgzOC0yMDkxLTQxNDYtYTBkMy1kM2NjMDQxMmNjMGE=\",\"size\":33,\"ext\":\"tcookieid\"}","from_id":"15629037629","message_type_id":6,"send_time":"1504161325690"},{"content":"{\"md5\":\"c88c67d202d2b464107116cb614e54f5\",\"name\":\"temp_image_bdb40897-99e6-4e57-9c7f-7f662192eca8.jpg\",\"url\":\"https:\\/\\/nos.netease.com\\/nim\\/NDMwNTc2Nw==\\/bmltYV81NzA4NzExNTRfMTUwNDE0MzgwMDk3MV84YjVmMmI1Yi00NTMwLTQwYTUtOTJlMC1jMGNlODU4MDMyMWU=\",\"size\":45723,\"ext\":\"jpg\",\"w\":624,\"h\":831}","from_id":"15629037629","message_type_id":1,"send_time":"1504159448262"}]
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
             * content : {"size":4669,"ext":"png","url":"https:\/\/nos.netease.com\/nim\/NDMwNTc2Nw==\/bmltYV81NzA4NzExNTRfMTUwNDA4MjQ3MTkzMV84NTA2NWM2MS00YWQwLTRiZmQtOTkxZS1kMzc4NTZhNWUwNjE=","md5":"9ef1e9feb00ff3fded56ebb20a317bc5","name":"AppIcon40x40@2x.png"}
             * from_id : 15629037629
             * message_type_id : 6
             * send_time : 1504161362844
             */

            private String content;
            private String from_id;
            private int message_type_id;
            private String send_time;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getFrom_id() {
                return from_id;
            }

            public void setFrom_id(String from_id) {
                this.from_id = from_id;
            }

            public int getMessage_type_id() {
                return message_type_id;
            }

            public void setMessage_type_id(int message_type_id) {
                this.message_type_id = message_type_id;
            }

            public String getSend_time() {
                return send_time;
            }

            public void setSend_time(String send_time) {
                this.send_time = send_time;
            }
        }
    }
}
