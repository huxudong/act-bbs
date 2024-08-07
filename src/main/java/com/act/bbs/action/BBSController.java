package com.act.bbs.action;

import act.app.conf.AutoConfig;
import act.controller.Controller;
import act.db.ebean.EbeanDao;
import com.act.bbs.common.Const;
import com.act.bbs.model.*;
import com.alibaba.fastjson.JSONObject;
import com.avaje.ebean.PagedList;
import org.osgl.http.H;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.result.Result;
import org.osgl.util.S;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by huxudong on 17/2/7.
 */
@Controller
@AutoConfig
public class BBSController extends BaseController {

    @Inject
    private EbeanDao<Integer, BbsTopic> topicDao;

    @Inject
    private EbeanDao<Integer, BbsReply> replyDao;

    @Inject
    private EbeanDao<Integer, BbsPost> postDao;

    @Inject
    private EbeanDao<Integer, BbsModule> moduleDao;

    @Inject
    private EbeanDao<Integer, BbsUser> userDao;

    private static String PAGENAME;

    @Inject
    public H.Session session;

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
    @GetAction("pagelist/{p}")
    public PagedList<BbsTopic> pagelist(Integer p,String keyword) {
        int limit = Const.BBS_PAGE_SIZE;
        int offset = (p - 1) * limit;
        if(S.isEmpty(keyword)){
            keyword = "";
        }
        PagedList<BbsTopic> page = topicDao.createQuery().offset(offset).limit(limit).where().like("content","%"+keyword+"%").findPagedList();
        return page;
    }

    /**
     * index page
     * @param p
     * @return
     */
    @GetAction("/{p}")
    public Result index(Integer p,String keyword) {
        int limit = Const.BBS_PAGE_SIZE;
        int offset = (p - 1) * limit;
        if(S.isEmpty(keyword)){
            keyword = "";
        }
        PagedList<BbsTopic> page = topicDao.createQuery().offset(offset).limit(limit).where().like("content","%"+keyword+"%").findPagedList();
        return render(page);
    }

    @GetAction("/bbs/topic/{id}/{p}")
    public Result topic( Integer id,  Integer p){
        int limit = Const.BBS_PAGE_SIZE;
        int offset = (p - 1) * limit;
        BbsTopic topic = topicDao.findById(id);
        PagedList<BbsPost> page = postDao.createQuery().offset(offset).limit(limit).where().eq("topicId",id).findPagedList();
        topic.setPv(topic.getPv() + 1);
        topicDao.save(topic);
        Integer pv = topic.getPv();
        return render(topic,page,pv);
    }

    @PostAction("/bbs/topic/save")
    public JSONObject topicSave(String title , String postContent,Integer moduleId){
        JSONObject result = new JSONObject();
        BbsTopic topic = new BbsTopic();
        BbsPost post = new BbsPost();
        result.put("err", 1);
        String userName = session.get("userName");
        Integer userId = Integer.parseInt(session.get("userId"));
        BbsUser user = userDao.findById(userId);

        if(S.isEmpty(userName)){
            result.put("msg", "请先登录后再继续！");
        }else if(title.length()<10||postContent.length()<10){
            //客户端需要完善
            result.put("msg", "标题或内容太短！");
        }else{
            BbsModule module = moduleDao.findById(moduleId);
            topic.setIsNice(0);
            topic.setIsUp(0);
            topic.setPv(1);
            topic.setPostCount(1);
            topic.setReplyCount(0);
            topic.setModule(module);
            post.setHasReply(0);
            topic.setContent(title);
            post.setContent(postContent);

            topic.setUser(user);
            topic.setCreateTime(new Date());
            topicDao.save(topic);
            post.setUser(user);
            post.setTopicId(topic.getId());
            post.setCreateTime(new Date());
            postDao.save(post);


            int score = user.getScore() + Const.BBS_TOPIC_SCORE;
            int balance = user.getBalance() + Const.BBS_TOPIC_SCORE;
            user.setScore(score);
            user.setBalance(balance);
            user.setLevel(getLevel(score));
            userDao.save(user);

            result.put("err", 0);
            result.put("msg", "/bbs/topic/"+topic.getId()+"/1");
        }
        return result;

    }

    /**
     * 分5个级别
     * @param score
     * @return
     */
    private int getLevel(int score){
        if(score>=Const.PRESIDENT_THRESHOLD){
            return 5;
        }else if(score>=Const.DIRECTOR_THRESHOLD){
            return 4;
        }else if(score>=Const.TEACHER_THRESHOLD){
            return 3;
        }if(score>=Const.OLD_THRESHOLD){
            return 2;
        }else{
            return 1 ;
        }
    }

    @GetAction("/bbs/module/{id}/{p}")
    public Result module( Integer id,  Integer p , String keyword){
        int limit = 10;
        int offset = (p - 1) * limit;
        if(S.isEmpty(keyword)){
            keyword = "";
        }
        PagedList<BbsTopic> page = topicDao.createQuery().offset(offset).limit(limit).where().like("content","%"+keyword+"%").eq("module.id",id).findPagedList();
        return render("com/act/bbs/action/BBSController/index.html",page);
    }

    @PostAction("/bbs/post/save")
    public JSONObject savePost(BbsPost post){
        JSONObject result = new JSONObject();
        result.put("err", 1);
        if(post.getContent().length()<10){
            result.put("msg", "内容太短，请重新编辑！");
        }else{
            post.setHasReply(0);
            post.setCreateTime(new Date());
            String userName = session.get("userName");
            Integer userId = Integer.parseInt(session.get("userId"));
            BbsUser user = userDao.findById(userId);
            post.setUser(user);
            postDao.save(post);
            BbsTopic topic = topicDao.findById(post.getTopicId());
            int totalPost = topic.getPostCount() + 1;
            topic.setPostCount(totalPost);
            topicDao.save(topic);

            int pageSize = Const.BBS_PAGE_SIZE;
            int page = (totalPost/pageSize)+(totalPost%pageSize==0?0:1);
            result.put("msg", "/bbs/topic/"+post.getTopicId()+"-"+page+".html");
            result.put("err", 0);
        }
        return result;
    }

    /**
     * 回复评论改为Ajax方式提升体验
     * @param reply
     * @return
     */
    @PostAction("/bbs/reply/save")
    public JSONObject saveReply(BbsReply reply){
        JSONObject result = new JSONObject();
        result.put("err", 1);
        result.put("err", 1);
        String userName = session.get("userName");
        Integer userId = Integer.parseInt(session.get("userId"));
        BbsUser user = userDao.findById(userId);

        if(S.isEmpty(userName)){
            result.put("msg", "请先登录后再继续！");
        }else if(reply.getContent().length()<10){
            result.put("msg", "回复内容太短，请修改!");
        }else{
            BbsTopic topic = topicDao.findById(reply.getTopic().getId());
            reply.setCreateTime(new Date());
            reply.setUser(user);
            reply.setTopic(topic);
            replyDao.save(reply);
            result.put("msg", "评论成功！");
            result.put("err", 0);
        }
        return result;
    }



    @GetAction("/bbs/admin/post/edit/{id}")
    public Result editPost( Integer id){
        BbsPost post =  postDao.findById(id);
        BbsTopic topic = topicDao.findById(post.getTopicId());
        return render(topic , post);
    }

    @PostAction("/bbs/admin/post/update")
    public JSONObject updatePost(Integer id , String content , HttpServletRequest request, HttpServletResponse response){
        JSONObject result = new JSONObject();
        result.put("err", 1);
        if(content.length()<10){
            result.put("msg", "输入的内容太短，请重新编辑！");
        }else{
            BbsPost db = postDao.findById(id);
            if(canUpdatePost(db)){
                db.setContent(content);
                postDao.save(db);
                result.put("msg", "/bbs/topic/"+db.getTopicId()+"/1");
                result.put("err", 0);
            }else{
                result.put("msg", "不是自己发表的内容无法编辑！");
            }
        }
        return result;
    }


    /**
     * ajax方式删除内容
     * @return
     */

    @PostAction("/bbs/admin/post/delete/{id}")
    public JSONObject deletePost(Integer id){
        JSONObject result = new JSONObject();
        BbsPost post = postDao.findById(id);
        if(canUpdatePost(post)){
            postDao.delete(post);
            result.put("err", 0);
            result.put("msg", "删 除成功！");
        }else{
            result.put("err", 1);
            result.put("msg", "不是自己发表的内容无法删除！");
        }
        return result;
    }


    private boolean canUpdatePost(BbsPost post){

        Integer userId = Integer.parseInt(session.get("userId"));
        BbsUser user = userDao.findById(userId);
        if(post.getUser().getId() == user.getId()){
            return true ;
        }
        //如果是admin
        if(user.getUserName().equals("admin")){
            return true;
        }

        return false;
    }


    @GetAction("/list")
    public Result list() {
        Iterable list = topicDao.findAll();

        return render(list);
    }

    @GetAction("/bbs/topic/post")
    public Result post(){
        return render();
    }

    @GetAction("/qrcode")
    public void showQrcode(String content){
        qrcode(content);
    }



}
