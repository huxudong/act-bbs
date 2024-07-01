package com.act.bbs.action;

import act.app.ActionContext;
import act.controller.Controller;
import act.db.ebean.EbeanDao;
import com.act.bbs.model.BbsModule;
import org.osgl.mvc.annotation.Before;
import org.osgl.mvc.annotation.GetAction;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by huxudong on 17/2/27.
 */
public class BaseController extends Controller.Util {


    @Inject
    private EbeanDao<Integer, BbsModule> moduleDao;

    private static String PAGENAME;

    @GetAction("/me")
    public String me(){
        return "me";
    }


    @Before
    public void initRenderArgs(ActionContext context) {
        context.renderArg("pagename",PAGENAME);
        List<BbsModule> moduleList = moduleDao.findAllAsList();
        context.renderArg("moduleList",moduleList);
    }
}
