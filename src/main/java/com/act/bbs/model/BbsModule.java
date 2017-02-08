package com.act.bbs.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
* 
* gen by beetlsql 2016-06-13
*/
@Entity
@Table(name="bbs_module")
public class BbsModule  {
	@Id
	private Integer id ;
	private Integer turn ;
	private String detail ;
	private String name ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTurn() {
		return turn;
	}

	public void setTurn(Integer turn) {
		this.turn = turn;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
