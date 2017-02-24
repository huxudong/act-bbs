package com.act.bbs.util;


import org.osgl.http.H;

/**
 * Created by huxudong on 17/2/24.
 */
public class PageUtils {


    /**
     * 获取url参数
     * @param request
     * @return
     */
    public static String getUrlParams(H.Request request){
        Iterable<String> params = request.paramNames();
        String urlpara = "?";
        for(String param : params){
            urlpara += param + "=" + request.paramVal(param);
        }
        return urlpara;
    }

    /**
     * 获取翻页的url
     * @param request
     * @param pageNumber
     * @return
     */
    public static String pageUrlPattern(H.Request request,int pageNumber){
        String url = request.path();

        String urlpara = "?" + request.query();

        int index = url.lastIndexOf("/");

        if (index > 0) {
            return url.substring(0, index)+"/"+pageNumber+urlpara;
        }else{
            return "/"+pageNumber+urlpara;
        }
    }

    public static String getLevelName(Integer level) {
        switch(level){
            case 1: return "幼儿宝宝";
            case 2: return "小学生";
            case 3: return "中学生";
            case 4: return "大学生";
            default: return "导师";
        }
    }
}
