package com.act.bbs.action;

import act.app.ActionContext;
import act.controller.Controller;
import act.db.ebean.EbeanDao;
import com.act.bbs.model.BbsUser;
import com.act.bbs.util.HashKit;
import com.act.bbs.util.PageUtils;
import com.act.bbs.util.VerifyCodeUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.osgl.http.H;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.util.IO;

import javax.inject.Inject;
import java.io.IOException;

/**
 * Created by huxudong on 17/2/24.
 */
@Controller
public class UserController extends BaseController {

    @Inject
    private EbeanDao<Integer,BbsUser> userDao;

    static final String CODE_NAME = "verCode";

    @Inject
    private H.Session session;



    @PostAction("/user/login")
    public JSONObject login(String userName , String password){
        JSONObject result = new JSONObject();
        if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(password)){
            result.put("msg","请输入正确的内容！");
        }else{
            password = HashKit.md5(password);
            BbsUser user =userDao.findOneBy("userName,password",userName,password);
            System.out.println(JSON.toJSONString(user));
            if(user==null){
                result.put("msg","用户不存在");
            }else{
                session.put("userName",user.getUserName());
                session.put("userId",user.getId());
                session.put("score",user.getScore());
                session.put("level", PageUtils.getLevelName(user.getLevel()));
                result.put("msg", "/1");
                result.put("err", 0);
            }
        }
        return result;
    }

    @PostAction("/user/logout")
    public void logout(){
        session.clear();
    }

    @PostAction("/user/doRegister")
    public JSONObject register(BbsUser user,String code,String userName,String password,String email){
        user = new BbsUser();
        JSONObject result  = new JSONObject();
        result.put("err", 1);
        String verCode = session.get(CODE_NAME);
        if(!verCode.equalsIgnoreCase(code)){
            result.put("msg", "验证码输入错误");
        }else if(userDao.findOneBy("userName",userName)!=null){
            result.put("msg", "用户已经存在");
        }else{
            password = HashKit.md5(password);
            user.setUserName(userName);
            user.setPassword(password);
            user.setEmail(email);
            user.setBalance(10);
            user.setLevel(1);
            user.setScore(10);
            user = userDao.save(user);
            session.put("userName",user.getUserName());
            session.put("userId",user.getId());
            session.put("score",user.getScore());
            session.put("level", PageUtils.getLevelName(user.getLevel()));
            result.put("err", 0);
            result.put("msg", "/");
        }
        return result;
    }

    @GetAction("/user/authImage")
    public void authImage() {
        H.Response response = ActionContext.current().resp();
        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        //删除以前的
        session.remove(CODE_NAME);
        session.put(CODE_NAME, verifyCode.toLowerCase());
        //生成图片
        int w = 100, h = 30;

        renderBinary((outputStream) -> {
            try {
                VerifyCodeUtils.outputImage(w, h, response.outputStream(), verifyCode);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IO.close(outputStream);
            }
            return null;
        });

    }
}
