package com.act.bbs.model;


import javax.persistence.*;
import java.util.Date;

/**
 *
  */
@Entity
@Table(name="bbs_reply")
public class BbsReply {
	@Id
	private Integer id ;

	@ManyToOne
	private BbsPost post ;

	private String content ;
	private Date createTime ;
	@OneToOne
	private BbsUser user;
	@OneToOne
	private BbsTopic topic;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BbsPost getPost() {
		return post;
	}

	public void setPost(BbsPost post) {
		this.post = post;
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

	public BbsUser getUser() {
		return user;
	}

	public void setUser(BbsUser user) {
		this.user = user;
	}

	public BbsTopic getTopic() {
		return topic;
	}

	public void setTopic(BbsTopic topic) {
		this.topic = topic;
	}
}
