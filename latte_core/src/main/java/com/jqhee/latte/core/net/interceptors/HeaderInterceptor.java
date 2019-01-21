package com.jqhee.latte.core.net.interceptors;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;


/**
 *  添加header头
 */

public class HeaderInterceptor extends BaseInterceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder();
        //设置具体的Head内容
        builder.header("timestamp", System.currentTimeMillis() + "");
        Request.Builder requestBuilder =
                builder.method(originalRequest.method(), originalRequest.body());
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
