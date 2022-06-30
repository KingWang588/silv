package com.yhy.hzzll.entity;

import java.util.List;

/**
 * 
 * @author chengying1216
 *
 */
public class CaseRemainEntity {

	
	    /**
	     * code : 000000
	     * msg : success
	     * data : {"month":[{"case_id":24,"title":"收案","remind_time":"2016-05-11 16:49:00","is_start":"0"},{"case_id":28,"title":"立案","remind_time":"2016-05-12 17:27:00","is_start":"1"},{"case_id":28,"title":"收案","remind_time":"2016-05-28 11:21:00","is_start":"1"},{"case_id":28,"title":"收案","remind_time":"2016-05-31 11:21:00","is_start":"0"},{"case_id":28,"title":"收案","remind_time":"2016-05-09 12:00:00","is_start":"1"},{"case_id":28,"title":"立案","remind_time":"2016-05-19 18:00:00","is_start":"0"},{"case_id":28,"title":"收案","remind_time":"2016-05-19 17:00:00","is_start":"0"},{"case_id":28,"title":"收案","remind_time":"2016-05-14 08:45:00","is_start":"0"},{"case_id":28,"title":"收案","remind_time":"2016-05-12 11:31:00","is_start":"0"},{"case_id":32,"title":"收案","remind_time":"2016-05-20 19:00:00","is_start":"0"},{"case_id":32,"title":"调查取证","remind_time":"2016-05-14 13:00:00","is_start":"1"},{"case_id":32,"title":"收案","remind_time":"2016-05-20 11:38:00","is_start":"0"},{"case_id":32,"title":"收案","remind_time":"2016-05-09 11:39:00","is_start":"1"}],"today":[{"case_id":28,"title":"收案","remind_time":"2016-05-14 08:45:00","is_start":"0"},{"case_id":32,"title":"调查取证","remind_time":"2016-05-14 13:00:00","is_start":"1"}]}
	     */

	    private String code;
	    private String msg;
	    private DataBean data;

	    public String getCode() {
	        return code;
	    }

	    public void setCode(String code) {
	        this.code = code;
	    }

	    public String getMsg() {
	        return msg;
	    }

	    public void setMsg(String msg) {
	        this.msg = msg;
	    }

	    public DataBean getData() {
	        return data;
	    }

	    public void setData(DataBean data) {
	        this.data = data;
	    }

	    public static class DataBean {
	        /**
	         * id :47
	         * case_id : 24
	         * title : 收案
	         * remind_time : 2016-05-11 16:49:00
	         * is_start : 0
	         */

	        private List<MonthBean> month;
	        /**
	         * id:47
	         * case_id : 28
	         * title : 收案
	         * remind_time : 2016-05-14 08:45:00
	         * is_start : 0
	         */

	        private List<TodayBean> today;

	        public List<MonthBean> getMonth() {
	            return month;
	        }

	        public void setMonth(List<MonthBean> month) {
	            this.month = month;
	        }

	        public List<TodayBean> getToday() {
	            return today;
	        }

	        public void setToday(List<TodayBean> today) {
	            this.today = today;
	        }

	        public static class MonthBean {
	        	private int id;
	            public int getId() {
					return id;
				}

				public void setId(int id) {
					this.id = id;
				}

				private int case_id;
	            private String title;
	            private String remind_time;
	            private String is_start;

	            public int getCase_id() {
	                return case_id;
	            }

	            public void setCase_id(int case_id) {
	                this.case_id = case_id;
	            }

	            public String getTitle() {
	                return title;
	            }

	            public void setTitle(String title) {
	                this.title = title;
	            }

	            public String getRemind_time() {
	                return remind_time;
	            }

	            public void setRemind_time(String remind_time) {
	                this.remind_time = remind_time;
	            }

	            public String getIs_start() {
	                return is_start;
	            }

	            public void setIs_start(String is_start) {
	                this.is_start = is_start;
	            }
	        }

	        public static class TodayBean {
	        	private int id;
	            public int getId() {
					return id;
				}

				public void setId(int id) {
					this.id = id;
				}
	            private int case_id;
	            private String title;
	            private String remind_time;
	            private String is_start;

	            public int getCase_id() {
	                return case_id;
	            }

	            public void setCase_id(int case_id) {
	                this.case_id = case_id;
	            }

	            public String getTitle() {
	                return title;
	            }

	            public void setTitle(String title) {
	                this.title = title;
	            }

	            public String getRemind_time() {
	                return remind_time;
	            }

	            public void setRemind_time(String remind_time) {
	                this.remind_time = remind_time;
	            }

	            public String getIs_start() {
	                return is_start;
	            }

	            public void setIs_start(String is_start) {
	                this.is_start = is_start;
	            }
	        }
	    }
	}


