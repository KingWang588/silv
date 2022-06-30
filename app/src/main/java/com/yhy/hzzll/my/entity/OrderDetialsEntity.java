package com.yhy.hzzll.my.entity;

import java.util.List;

/**
 * Created by chengying on 2017/8/14.
 */

public class OrderDetialsEntity  {

    /**
     * code : 0
     * message : 查询成功
     * data : {"order_info":{"order_no":"2117042909137997","amount":"48.00000","amount_pay":"48.00000","amount_discount":"0.00000","order_status":"已完成","nickname":"Tom","mobile":"13298181076","type_name":"专属咨询","data_time":"2017-09-13 17:56:03","accid":"13298181076"},"consult":{"id":920,"content":"iu和审批哦等候i撒旦金佛都怕是否破损的泼妇似的发破损的飓风破事都皮肤破损的佛山的的开放式的","file_attachment_id":"","speech_length":132},"evaluate":[{"id":215,"reply_id":870,"users_id":429,"evaluated_id":434,"content":"飒飒大苏打撒旦","start_rate":6,"type_id":2,"is_delete":0,"created_at":"2017-09-13 18:09:06","updated_at":"2017-09-13 18:09:06"}],"income_lists":[{"title":"用户咨询打赏红包 2017-09-13 19:41:34","amount":"6.00000"}],"income_total":0,"reply":{"content":"考虑考虑","file_attachment_id":"","speech_length":0}}
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
         * order_info : {"order_no":"2117042909137997","amount":"48.00000","amount_pay":"48.00000","amount_discount":"0.00000","order_status":"已完成","nickname":"Tom","mobile":"13298181076","type_name":"专属咨询","data_time":"2017-09-13 17:56:03","accid":"13298181076"}
         * consult : {"id":920,"content":"iu和审批哦等候i撒旦金佛都怕是否破损的泼妇似的发破损的飓风破事都皮肤破损的佛山的的开放式的","file_attachment_id":"","speech_length":132}
         * evaluate : [{"id":215,"reply_id":870,"users_id":429,"evaluated_id":434,"content":"飒飒大苏打撒旦","start_rate":6,"type_id":2,"is_delete":0,"created_at":"2017-09-13 18:09:06","updated_at":"2017-09-13 18:09:06"}]
         * income_lists : [{"title":"用户咨询打赏红包 2017-09-13 19:41:34","amount":"6.00000"}]
         * income_total : 0
         * reply : {"content":"考虑考虑","file_attachment_id":"","speech_length":0}
         */

        private Complaint complaint;
        private OrderInfoBean order_info;
        private ConsultBean consult;
        private String income_total;
        private ReplyBean reply;
        private List<EvaluateBean> evaluate;
        private List<IncomeListsBean> income_lists;

        public Complaint getComplaint() {
            return complaint;
        }

        public void setComplaint(Complaint complaint) {
            this.complaint = complaint;
        }

        public OrderInfoBean getOrder_info() {
            return order_info;
        }

        public void setOrder_info(OrderInfoBean order_info) {
            this.order_info = order_info;
        }

        public ConsultBean getConsult() {
            return consult;
        }

        public void setConsult(ConsultBean consult) {
            this.consult = consult;
        }

        public String getIncome_total() {
            return income_total;
        }

        public void setIncome_total(String income_total) {
            this.income_total = income_total;
        }

        public ReplyBean getReply() {
            return reply;
        }

        public void setReply(ReplyBean reply) {
            this.reply = reply;
        }

        public List<EvaluateBean> getEvaluate() {
            return evaluate;
        }

        public void setEvaluate(List<EvaluateBean> evaluate) {
            this.evaluate = evaluate;
        }

        public List<IncomeListsBean> getIncome_lists() {
            return income_lists;
        }

        public void setIncome_lists(List<IncomeListsBean> income_lists) {
            this.income_lists = income_lists;
        }

        public static class OrderInfoBean {
            /**
             * order_no : 2117042909137997
             * amount : 48.00000
             * amount_pay : 48.00000
             * amount_discount : 0.00000
             * order_status : 已完成
             * nickname : Tom
             * mobile : 13298181076
             * type_name : 专属咨询
             * data_time : 2017-09-13 17:56:03
             * accid : 13298181076
             */

            private String order_no;
            private String amount;
            private String amount_pay;
            private String amount_discount;
            private String order_status;
            private String nickname;
            private String mobile;
            private String type_name;
            private String data_time;
            private String order_status_id;

            public String getOrder_status_id() {
                return order_status_id;
            }

            private String accid;
            private int success_pay_time;

            public int getSuccess_pay_time() {
                return success_pay_time;
            }

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getAmount_pay() {
                return amount_pay;
            }

            public void setAmount_pay(String amount_pay) {
                this.amount_pay = amount_pay;
            }

            public String getAmount_discount() {
                return amount_discount;
            }

            public void setAmount_discount(String amount_discount) {
                this.amount_discount = amount_discount;
            }

            public String getOrder_status() {
                return order_status;
            }

            public void setOrder_status(String order_status) {
                this.order_status = order_status;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }

            public String getData_time() {
                return data_time;
            }

            public void setData_time(String data_time) {
                this.data_time = data_time;
            }

            public String getAccid() {
                return accid;
            }

            public void setAccid(String accid) {
                this.accid = accid;
            }
        }

        public static class ConsultBean {
            /**
             * id : 920
             * content : iu和审批哦等候i撒旦金佛都怕是否破损的泼妇似的发破损的飓风破事都皮肤破损的佛山的的开放式的
             * file_attachment_id :
             * speech_length : 132
             */

            private int id;
            private String content;
            private String file_attachment_id;
            private int speech_length;

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
        }

        public static class ReplyBean {
            /**
             * content : 考虑考虑
             * file_attachment_id :
             * speech_length : 0
             */

            private String content;
            private String reply_id;
            private int reply_time;

            public String getReply_id() {
                return reply_id;
            }

            private String file_attachment_id;
            private int speech_length;

            public int getReply_time() {
                return reply_time;
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
        }

        public static class EvaluateBean {
            /**
             * id : 215
             * reply_id : 870
             * users_id : 429
             * evaluated_id : 434
             * content : 飒飒大苏打撒旦
             * start_rate : 6
             * type_id : 2
             * is_delete : 0
             * created_at : 2017-09-13 18:09:06
             * updated_at : 2017-09-13 18:09:06
             */

            private int id;
            private int reply_id;
            private int users_id;
            private int evaluated_id;
            private String content;
            private int start_rate;
            private int type_id;
            private int is_delete;
            private String created_at;
            private String updated_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getReply_id() {
                return reply_id;
            }

            public void setReply_id(int reply_id) {
                this.reply_id = reply_id;
            }

            public int getUsers_id() {
                return users_id;
            }

            public void setUsers_id(int users_id) {
                this.users_id = users_id;
            }

            public int getEvaluated_id() {
                return evaluated_id;
            }

            public void setEvaluated_id(int evaluated_id) {
                this.evaluated_id = evaluated_id;
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

            public int getType_id() {
                return type_id;
            }

            public void setType_id(int type_id) {
                this.type_id = type_id;
            }

            public int getIs_delete() {
                return is_delete;
            }

            public void setIs_delete(int is_delete) {
                this.is_delete = is_delete;
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
        }

        public static class IncomeListsBean {
            /**
             * title : 用户咨询打赏红包 2017-09-13 19:41:34
             * amount : 6.00000
             */

            private String title;
            private String amount;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }
        }

        public static class Complaint{
            private String status_id;
            private String handle_result;
            private String handle_opinion;

            public String getStatus_id() {
                return status_id;
            }

            public void setStatus_id(String status_id) {
                this.status_id = status_id;
            }

            public String getHandle_result() {
                return handle_result;
            }

            public void setHandle_result(String handle_result) {
                this.handle_result = handle_result;
            }

            public String getHandle_opinion() {
                return handle_opinion;
            }

            public void setHandle_opinion(String handle_opinion) {
                this.handle_opinion = handle_opinion;
            }
        }


    }
}
