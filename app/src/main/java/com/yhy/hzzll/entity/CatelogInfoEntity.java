package com.yhy.hzzll.entity;

/**
 * 系统分类 - 获取所有分类
 * 
 * @author 一合鱼
 *
 */
public class CatelogInfoEntity {

	/**
	 * id : 89 pid : 3 name : 土地信息 type : coop_type
	 */

	private int id;
	private int pid;
	private String name;
	private String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
