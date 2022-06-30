package com.yhy.hzzll.entity;

/**
 * 财富--余额实体
 * 
 * @author Yang
 * 
 */
public class MoneyEntity extends BaseEntity {

	private String money;
	private String frozen_money;
	private String score;

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getFrozen_money() {
		return frozen_money;
	}

	public void setFrozen_money(String frozen_money) {
		this.frozen_money = frozen_money;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

}
