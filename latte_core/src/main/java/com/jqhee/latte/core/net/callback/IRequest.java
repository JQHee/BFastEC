package com.jqhee.latte.core.net.callback;

/**
 *  用于显示加载指示器
 */

public interface IRequest {

    void  onRequestStart();

    void onRequestEnd();
}
