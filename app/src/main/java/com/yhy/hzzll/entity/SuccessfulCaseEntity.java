package com.yhy.hzzll.entity;

import java.util.List;

public class SuccessfulCaseEntity {


	/**
	 * code : 0
	 * message : 查询成功!
	 * data : {"pages":1,"total":6,"list":[{"id":7,"author_id":434,"title":"就回家回家","content":"大健康哈根达斯赶紧回家党和国家活动时间横岗街道韩国进口的规划","intro":null,"seo_keyword":"大概郭德纲","seo_description":"方法对方的","sort":0,"is_display":0,"created_at":"2017-08-18 14:45:16","updated_at":"2017-08-18 14:45:16"},{"id":8,"author_id":434,"title":"就回家回家4","content":"大健康哈根达斯赶紧回家党和国家活动时间横岗街道韩国进口的规划","intro":null,"seo_keyword":"大概郭德纲","seo_description":"方法对方的","sort":0,"is_display":0,"created_at":"2017-08-18 14:45:21","updated_at":"2017-08-18 14:45:21"},{"id":9,"author_id":434,"title":"就回家回家45","content":"大健康哈根达斯赶紧回家党和国家活动时间横岗街道韩国进口的规划","intro":null,"seo_keyword":"大概郭德纲","seo_description":"方法对方的","sort":0,"is_display":0,"created_at":"2017-08-18 14:45:25","updated_at":"2017-08-18 14:45:25"},{"id":10,"author_id":434,"title":"就回家回家456","content":"大健康哈根达斯赶紧回家党和国家活动时间横岗街道韩国进口的规划","intro":null,"seo_keyword":"大概郭德纲","seo_description":"方法对方的","sort":0,"is_display":0,"created_at":"2017-08-18 14:45:28","updated_at":"2017-08-18 14:45:28"},{"id":11,"author_id":434,"title":"就回家回家4567","content":"大健康哈根达斯赶紧回家党和国家活动时间横岗街道韩国进口的规划","intro":null,"seo_keyword":"大概郭德纲","seo_description":"方法对方的","sort":0,"is_display":0,"created_at":"2017-08-18 14:45:31","updated_at":"2017-08-18 14:45:31"},{"id":12,"author_id":434,"title":"就回家回家45678","content":"大健康哈根达斯赶紧回家党和国家活动时间横岗街道韩国进口的规划","intro":null,"seo_keyword":"大概郭德纲","seo_description":"方法对方的","sort":0,"is_display":0,"created_at":"2017-08-18 14:45:34","updated_at":"2017-08-18 14:45:34"}]}
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
		 * total : 6
		 * list : [{"id":7,"author_id":434,"title":"就回家回家","content":"大健康哈根达斯赶紧回家党和国家活动时间横岗街道韩国进口的规划","intro":null,"seo_keyword":"大概郭德纲","seo_description":"方法对方的","sort":0,"is_display":0,"created_at":"2017-08-18 14:45:16","updated_at":"2017-08-18 14:45:16"},{"id":8,"author_id":434,"title":"就回家回家4","content":"大健康哈根达斯赶紧回家党和国家活动时间横岗街道韩国进口的规划","intro":null,"seo_keyword":"大概郭德纲","seo_description":"方法对方的","sort":0,"is_display":0,"created_at":"2017-08-18 14:45:21","updated_at":"2017-08-18 14:45:21"},{"id":9,"author_id":434,"title":"就回家回家45","content":"大健康哈根达斯赶紧回家党和国家活动时间横岗街道韩国进口的规划","intro":null,"seo_keyword":"大概郭德纲","seo_description":"方法对方的","sort":0,"is_display":0,"created_at":"2017-08-18 14:45:25","updated_at":"2017-08-18 14:45:25"},{"id":10,"author_id":434,"title":"就回家回家456","content":"大健康哈根达斯赶紧回家党和国家活动时间横岗街道韩国进口的规划","intro":null,"seo_keyword":"大概郭德纲","seo_description":"方法对方的","sort":0,"is_display":0,"created_at":"2017-08-18 14:45:28","updated_at":"2017-08-18 14:45:28"},{"id":11,"author_id":434,"title":"就回家回家4567","content":"大健康哈根达斯赶紧回家党和国家活动时间横岗街道韩国进口的规划","intro":null,"seo_keyword":"大概郭德纲","seo_description":"方法对方的","sort":0,"is_display":0,"created_at":"2017-08-18 14:45:31","updated_at":"2017-08-18 14:45:31"},{"id":12,"author_id":434,"title":"就回家回家45678","content":"大健康哈根达斯赶紧回家党和国家活动时间横岗街道韩国进口的规划","intro":null,"seo_keyword":"大概郭德纲","seo_description":"方法对方的","sort":0,"is_display":0,"created_at":"2017-08-18 14:45:34","updated_at":"2017-08-18 14:45:34"}]
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
			 * author_id : 434
			 * title : 就回家回家
			 * content : 大健康哈根达斯赶紧回家党和国家活动时间横岗街道韩国进口的规划
			 * intro : null
			 * seo_keyword : 大概郭德纲
			 * seo_description : 方法对方的
			 * sort : 0
			 * is_display : 0
			 * created_at : 2017-08-18 14:45:16
			 * updated_at : 2017-08-18 14:45:16
			 */

			private int id;
			private int author_id;
			private String title;
			private String content;
			private Object intro;
			private String seo_keyword;
			private String seo_description;
			private int sort;
			private int is_display;
			private String created_at;
			private String updated_at;

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public int getAuthor_id() {
				return author_id;
			}

			public void setAuthor_id(int author_id) {
				this.author_id = author_id;
			}

			public String getTitle() {
				return title;
			}

			public void setTitle(String title) {
				this.title = title;
			}

			public String getContent() {
				return content;
			}

			public void setContent(String content) {
				this.content = content;
			}

			public Object getIntro() {
				return intro;
			}

			public void setIntro(Object intro) {
				this.intro = intro;
			}

			public String getSeo_keyword() {
				return seo_keyword;
			}

			public void setSeo_keyword(String seo_keyword) {
				this.seo_keyword = seo_keyword;
			}

			public String getSeo_description() {
				return seo_description;
			}

			public void setSeo_description(String seo_description) {
				this.seo_description = seo_description;
			}

			public int getSort() {
				return sort;
			}

			public void setSort(int sort) {
				this.sort = sort;
			}

			public int getIs_display() {
				return is_display;
			}

			public void setIs_display(int is_display) {
				this.is_display = is_display;
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
	}
}
