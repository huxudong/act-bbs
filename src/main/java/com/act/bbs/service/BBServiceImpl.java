package com.act.bbs.service;

import act.db.ebean.EbeanDao;
import com.act.bbs.model.*;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by huxudong on 17/2/9.
 */
public class BBServiceImpl implements BBSService {

    @Inject
    private EbeanDao<Integer, BbsTopic> topicDao;

    @Override
    public BbsTopic getTopic(int id) {
        return null;
    }

    @Override
    public List<BbsTopic> getMyTopics(int userId) {
        return null;
    }

    @Override
    public Integer getMyTopicsCount(int userId) {
        return null;
    }

    @Override
    public void updateMyTopic(int msgId, int status) {

    }

    @Override
    public BbsMessage makeOneBbsMessage(int userId, int topicId, int statu) {
        return null;
    }

    @Override
    public void notifyParticipant(int topicId, int ownerId) {

    }

    @Override
    public void saveUser(BbsUser user) {

    }

    @Override
    public BbsUser login(BbsUser user) {
        return null;
    }

    @Override
    public void saveTopic(BbsTopic topic, BbsPost post, BbsUser user) {

    }

    @Override
    public void savePost(BbsPost post, BbsUser user) {

    }

    @Override
    public void saveReply(BbsReply reply) {

    }

    @Override
    public void deleteTopic(int id) {

    }

    @Override
    public void deletePost(int id) {

    }

    @Override
    public void updateTopic(BbsTopic topic) {

    }

    @Override
    public Date getLatestPost(int userId) {
        return null;
    }
}
