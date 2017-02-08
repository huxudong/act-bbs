package com.act.bbs.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *
  */
@Entity
@Table(name="bbs_reply")
public class BbsReply {
	@Id
	private Integer id ;
	private Integer postId ;
	private Integer topicId ;
	private Integer userId ;
	private String content ;
	private Date createTime ;
	private BbsUser user;
	private BbsTopic topic;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
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
