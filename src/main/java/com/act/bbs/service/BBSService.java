package com.act.bbs.service;

import com.act.bbs.model.*;
import java.util.Date;
import java.util.List;

public interface BBSService {
    BbsTopic getTopic(int id);

//    PageQuery getTopics(PageQuery query);

    List<BbsTopic> getMyTopics(int userId);

    Integer getMyTopicsCount(int userId);

    public void updateMyTopic(int msgId,int status);

    public BbsMessage makeOneBbsMessage(int userId, int topicId, int statu);


    public void notifyParticipant(int topicId,int ownerId);

//    void getHotTopics(PageQuery query);
//
//    void getNiceTopics(PageQuery query);
//
//    void getPosts(PageQuery query);

    void saveUser(BbsUser user);

    BbsUser login(BbsUser user);

    void saveTopic(BbsTopic topic, BbsPost post, BbsUser user);

    void savePost(BbsPost post, BbsUser user);


    void saveReply(BbsReply reply);

    void deleteTopic(int id);

    void deletePost(int id);

    void updateTopic(BbsTopic topic);

    Date getLatestPost(int userId);
}
