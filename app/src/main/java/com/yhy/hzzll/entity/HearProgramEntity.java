package com.yhy.hzzll.entity;

/**
 * 审理程序
 * 
 * @author 一合鱼
 *
 */
public class HearProgramEntity {

	/**
	 * id : 168 pid : 99 name : 生命权、健康权、身体权纠纷 type : case_reason
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
