package com.act.bbs.model;

import javax.persistence.*;
import java.util.Date;

/*
* 
* gen by beetlsql 2016-06-13
*/
@Entity
@Table(name="bbs_topic")
public class BbsTopic {
	@Id
	private Integer id ;
	private Integer emotion ;
	private Integer isNice ;
	private Integer isUp ;
	private Integer moduleId ;
	private Integer postCount ;
	private Integer pv ;
	private Integer replyCount ;
	private Integer userId ;
	private String content ;
	private Date createTime ;
	@OneToOne
	private BbsUser user;
	@OneToOne
	private BbsModule module;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmotion() {
		return emotion;
	}

	public void setEmotion(Integer emotion) {
		this.emotion = emotion;
	}

	public Integer getIsNice() {
		return isNice;
	}

	public void setIsNice(Integer isNice) {
		this.isNice = isNice;
	}

	public Integer getIsUp() {
		return isUp;
	}

	public void setIsUp(Integer isUp) {
		this.isUp = isUp;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getPostCount() {
		return postCount;
	}

	public void setPostCount(Integer postCount) {
		this.postCount = postCount;
	}

	public Integer getPv() {
		return pv;
	}

	public void setPv(Integer pv) {
		this.pv = pv;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
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

	public BbsModule getModule() {
		return module;
	}

	public void setModule(BbsModule module) {
		this.module = module;
	}
}
