package com.jqhee.bfastec.example.testweb.event;


import android.annotation.SuppressLint;
import android.webkit.WebView;
import android.widget.Toast;

import com.jqhee.latte.core.delegates.web.event.Event;


// js传值到app
public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), getAction(), Toast.LENGTH_LONG).show();
        if (getAction().equals("test")) {
            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @SuppressLint("NewApi")
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall()", null);
                }
            });
        }
        return null;
    }
}