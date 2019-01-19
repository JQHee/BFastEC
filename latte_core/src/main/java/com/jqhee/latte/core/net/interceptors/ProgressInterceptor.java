package com.jqhee.latte.core.net.interceptors;

import com.jqhee.latte.core.net.body.ProgressListener;
import com.jqhee.latte.core.net.body.ProgressResponseBody;
import com.jqhee.latte.core.util.log.LatteLogger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ProgressInterceptor extends BaseInterceptor  {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Response response=chain.proceed(chain.request());
        return response.newBuilder()
                .addHeader("Accept-Encoding", "identity")
                .body(new ProgressResponseBody(response.body(),progressListener)).build();
    }
    static final ProgressListener progressListener = new ProgressListener() {
        @Override
        public void onProgress(long progress, long total, long speed, boolean done) {

            LatteLogger.i("log","progress="+progress+"total="+total);
        }
    };

}
