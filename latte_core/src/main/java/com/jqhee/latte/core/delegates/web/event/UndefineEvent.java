package com.jqhee.latte.core.delegates.web.event;


import com.jqhee.latte.core.util.log.LatteLogger;

/**
 * @author: wuchao
 * @date: 2017/11/29 22:44
 * @desciption:
 */

public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        LatteLogger.e("UndefineEvent", params);
        return null;
    }
}
