package com.act.bbs.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
* 
* gen by beetlsql 2016-04-27
*/
@Entity
@Table(name="bbs_user")
public class BbsUser {
	@Id
	private Integer id ;
	private Integer level ;
	private Integer score ;
	private Integer balance;
	private String password;
	private String email ;
	private String userName ;
	private String corp;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public String getCorp() {
		return corp;
	}
	public void setCorp(String corp) {
		this.corp = corp;
	}
	
	
}
