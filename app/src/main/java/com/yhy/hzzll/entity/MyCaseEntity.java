package com.yhy.hzzll.entity;

/**
 * 案件管理
 * 
 * @author 一合鱼
 *
 */
public class MyCaseEntity {

	/**
	 * id : 33  title : 创建一条APP测试 ctime : 2016-07-21 finish_time : 1469030400
	 * status : 进行中
	 */

	private String id;
	private String title;
	private String ctime;
	private Long finish_time;
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Long getFinish_time() {
		return finish_time;
	}

	public void setFinish_time(Long finish_time) {
		this.finish_time = finish_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
