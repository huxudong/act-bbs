package com.act.bbs.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/*
* 
* gen by beetlsql 2016-06-13
*/
@Entity
@Table(name="bbs_post")
public class BbsPost {
	@Id
	private Integer id ;
	private Integer hasReply ;
	private Integer topicId ;
	private Integer userId ;
	private String content ;
	private Date createTime ;
	private Date updateTime ;

	@OneToOne
	private BbsUser user;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="post_id")
	private List<BbsReply> replys ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHasReply() {
		return hasReply;
	}

	public void setHasReply(Integer hasReply) {
		this.hasReply = hasReply;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}



	public List<BbsReply> getReplys() {
		return replys;
	}

	public void setReplys(List<BbsReply> replys) {
		this.replys = replys;
	}

	public BbsUser getUser() {
		return user;
	}

	public void setUser(BbsUser user) {
		this.user = user;
	}
}
