package com.act.bbs;

import act.Act;
import act.Version;
import act.controller.Controller;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.result.Result;

/**
 * Created by huxudong on 17/1/22.
 */
public class BBSConfig {

    @GetAction
    public Result sayHello(String who) {
        if ("".equals(who)) {
            who = "World";
        }
        return Controller.Util.render(who);
    }

    public static void main(String[] args) throws Exception {
        Act.start("TODO", Version.appVersion(), BBSConfig.class);
    }
}
