package com.jqhee.latte.core.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jqhee.latte.core.delegates.IPageLoadListener;
import com.jqhee.latte.core.delegates.web.chromeclient.WebChromeClientImpl;
import com.jqhee.latte.core.delegates.web.client.WebViewClientImpl;
import com.jqhee.latte.core.delegates.web.route.RouteKeys;
import com.jqhee.latte.core.delegates.web.route.Router;


/**
 * @author: wuchao
 * @date: 2017/11/29 15:50
 * @desciption:
 */

public class WebDelegateImpl extends WebDelegate {

    private IPageLoadListener mIPageLoadListener = null;

    public static WebDelegateImpl create(String url) {
        Bundle bundle = new Bundle();
        bundle.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(bundle);
        return delegate;
    }

    public static WebDelegateImpl createContent(String content) {
        Bundle bundle = new Bundle();
        bundle.putString(RouteKeys.HTML_TEXT.name(), content);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(bundle);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    public void setPageLoadListener(IPageLoadListener listener) {
        mIPageLoadListener = listener;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if (getmHtmlText() == null) {
            //用原生方式模拟Web跳转并进行页面加载
            Router.getInstance().loadPage(this, getUrl());
        } else {
            //用原生方式模拟Web跳转并进行页面加载
            Router.getInstance().loadPageContent(this, getmHtmlText());
        }
    }

    @Override
    protected IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        client.setPageLoadListener(mIPageLoadListener);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }
}
