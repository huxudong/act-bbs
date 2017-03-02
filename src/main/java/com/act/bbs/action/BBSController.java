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
    @GetAction("/{p}")
    public Result index(Integer p,String keyword) {
        int limit = 10;
        int offset = (p - 1) * limit;
        if(S.isEmpty(keyword)){
            keyword = "";
        }
        String sql = "SELECT sum(if(r.`is_sign` = '1',1,0)) sign,\n" +
                "       sum(if(r.`is_participate` = '1',1,0)) participate,\n" +
                "       sum(if(r.is_sign = '1',r.sign_numbers,0)) AS piece,\n" +
                "       sum(if(r.is_sign = '1',r.sign_cost,0)) AS cost,\n" +
                "       count(1) AS count,\n" +
                "       a.organ_name,\n" +
                "       a.area_name,\n" +
                "       a.dept_name\n" +
                "FROM t_activity_receipt r,\n" +
                "\n" +
                "  ( SELECT activity.id,\n" +
                "           activity.activity_type,\n" +
                "           activity.activity_name,\n" +
                "           userOrg.organ_name,\n" +
                "           userOrg.area_name,\n" +
                "           userOrg.dept_name\n" +
                "   FROM t_activity_release activity,\n" +
                "\n" +
                "     (SELECT user.id,\n" +
                "             user.user_name,\n" +
                "             area.organ_name,\n" +
                "             area.area_name,\n" +
                "             area.dept_name\n" +
                "      FROM t_user_basic USER,\n" +
                "                        t_area area\n" +
                "      WHERE user.group_code = area.group_code ) userOrg\n" +
                "   WHERE userOrg.id = activity.publisher_id ) a\n" +
                "WHERE r.activity_id = a.id\n" +
                "GROUP BY activity_id /* and area.organ_name ='玉溪' */";
        PagedList<BbsTopic> page = topicDao.createQuery().offset(offset).limit(limit).where().like("content","%"+keyword+"%").findPagedList();
        return render(page);
    }

    @GetAction("/bbs/topic/{id}/{p}")
    public Result topic( Integer id,  Integer p){
        int limit = 100;
        int offset = (p - 1) * limit;
        BbsTopic topic = topicDao.findById(id);
        PagedList<BbsPost> page = postDao.createQuery().offset(offset).limit(limit).where().eq("topicId",id).findPagedList();
        return render(topic,page);
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
