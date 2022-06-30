package com.yhy.hzzll.entity;

import java.util.List;

/**
 * 个人中心---学术列表
 * 
 * @author Yang
 * 
 */
public class ArticleEntity extends BaseEntity {

	private String count;
	private int pages;
	private List<list> list;

	public List<list> getList() {
		return list;
	}

	public void setList(List<list> list) {
		this.list = list;
	}

	public class list {
		private String id;
		private String uid;
		private String title;
		private String ctime;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getUid() {
			return uid;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getCtime() {
			return ctime;
		}

		public void setCtime(String ctime) {
			this.ctime = ctime;
		}

	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

}
