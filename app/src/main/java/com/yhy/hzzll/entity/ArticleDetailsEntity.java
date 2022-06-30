package com.yhy.hzzll.entity;

/**
 * 文章详情
 * 
 * @author Yang
 * 
 */
public class ArticleDetailsEntity extends BaseEntity {


	/**
	 * code : 0
	 * message : 查询结果！
	 * data : {"id":7,"author_id":"何虎","title":"就回家回家","content":"大健康哈根达斯赶紧回家党和国家活动时间横岗街道韩国进口的规划","seo_keyword":"大概郭德纲","seo_description":"方法对方的","sort":0,"created_at":"2017年08月18日"}
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
		 * id : 7
		 * author_id : 何虎
		 * title : 就回家回家
		 * content : 大健康哈根达斯赶紧回家党和国家活动时间横岗街道韩国进口的规划
		 * seo_keyword : 大概郭德纲
		 * seo_description : 方法对方的
		 * sort : 0
		 * created_at : 2017年08月18日
		 */

		private int id;
		private String author_id;
		private String title;
		private String content;
		private String seo_keyword;
		private String seo_description;
		private int sort;
		private String created_at;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getAuthor_id() {
			return author_id;
		}

		public void setAuthor_id(String author_id) {
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

		public String getCreated_at() {
			return created_at;
		}

		public void setCreated_at(String created_at) {
			this.created_at = created_at;
		}
	}
}
