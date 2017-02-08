package com.act.bbs.action;

import act.Act;
import act.Version;
import act.controller.Controller;
import act.db.ebean.EbeanDao;
import com.act.bbs.model.BbsTopic;
import org.osgl.mvc.annotation.GetAction;

import javax.inject.Inject;

/**
 * Created by huxudong on 17/2/7.
 */
@Controller("/bbs")
public class BBSController extends Controller.Util {

    @Inject
    private EbeanDao<Integer, BbsTopic> topicDao;


    @GetAction("/list")
    public Iterable list() {
        return topicDao.findAll();
    }

}
