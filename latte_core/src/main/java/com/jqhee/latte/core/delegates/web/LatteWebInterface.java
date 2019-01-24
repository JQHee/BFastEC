package com.jqhee.latte.core.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.jqhee.latte.core.delegates.web.event.Event;
import com.jqhee.latte.core.delegates.web.event.EventManager;

/**
 * @author: wuchao
 * @date: 2017/11/28 23:06
 * @desciption:
 */

final class LatteWebInterface {

    private final WebDelegate DELEGATE;

    private LatteWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    static LatteWebInterface create(WebDelegate delegate) {
        return new LatteWebInterface(delegate);
    }

    // 该方法供js调用 js传入的json
    @JavascriptInterface
    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        if (event != null) {
            event.setAction(action);
            event.setContext(DELEGATE.getContext());
            event.setDelegate(DELEGATE);
            event.setUrl(DELEGATE.getUrl());
            event.setHtmlText(DELEGATE.getmHtmlText());
            return event.execute(params);
        }
        return null;
    }
}
