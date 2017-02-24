package com.act.bbs;

import act.Act;
import act.Version;

/**
 * Created by huxudong on 17/1/22.
 */
public class BBSConfig {


    public static void main(String[] args) throws Exception {
        Act.start("act-bbs", Version.appVersion(), BBSConfig.class);
    }
}
