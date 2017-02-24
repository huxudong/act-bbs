package com.act.bbs.action;

import act.controller.Controller;
import act.db.ebean.EbeanDao;
import com.act.bbs.model.BbsPost;
import com.act.bbs.model.BbsReply;
import com.act.bbs.model.BbsTopic;
import com.avaje.ebean.PagedList;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.result.Result;
import org.osgl.util.S;

import javax.inject.Inject;

/**
 * Created by huxudong on 17/2/7.
 */
@Controller
public class BBSController extends Controller.Util {

    @Inject
    private EbeanDao<Integer, BbsTopic> topicDao;

    @Inject
    private EbeanDao<Integer, BbsReply> replyDao;

    @Inject
    private EbeanDao<Integer, BbsPost> postDao;






    /**
     * index
     * @return
     */
    @GetAction("/")
    public Result home() {
        return redirect("/1");
    }

    /**
     * index page
     * @param p
     * @return
     */
    @GetAction("/{p}")
    public Result index(Integer p,String keyword) {
        int limit = 10;
        int offset = (p - 1) * limit;
        if(S.isEmpty(keyword)){
            keyword = "";
        }
        PagedList<BbsTopic> page = topicDao.createQuery().offset(offset).limit(limit).where().like("content","%"+keyword+"%").findPagedList();
        String pagename = "首页综合";
        boolean isAdmin = true;
        return render(isAdmin,pagename,page);
    }

    @GetAction("/bbs/topic/{id}/{p}")
    public Result topic( Integer id,  Integer p){
        int limit = 10;
        int offset = (p - 1) * limit;
        boolean isAdmin = true;
        BbsTopic topic = topicDao.findById(id);
        PagedList<BbsPost> page = postDao.createQuery().setParameter("topicId",id).offset(offset).limit(limit).findPagedList();
        return render(isAdmin,topic,page);
    }


    @GetAction("/list")
    public Result list() {
        Iterable list = topicDao.findAll();

        return render(list);
    }


}
