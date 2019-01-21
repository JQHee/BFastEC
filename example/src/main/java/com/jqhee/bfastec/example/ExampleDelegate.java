package com.jqhee.bfastec.example;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.jqhee.latte.core.app.Latte;
import com.jqhee.latte.core.delegates.LatteDelegate;
import com.jqhee.latte.core.net.RestClient;
import com.jqhee.latte.core.net.body.ProgressListener;
import com.jqhee.latte.core.net.callback.IFailure;
import com.jqhee.latte.core.net.callback.IRequest;
import com.jqhee.latte.core.net.callback.ISuccess;
import com.jqhee.latte.core.util.log.LatteLogger;

public class ExampleDelegate extends LatteDelegate implements ProgressListener {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    // 测试网络请求
    private void testRestClient() {
        RestClient.builder()
                .url("http://www.gjfenxiao.com:8017/api/upgrade/get_latest_apk")
                .params("" , "")
                .downloadSetting(null, null, "ganjie.apk")
                .onRequest(new IRequest() {
                    @Override
                    public void onRequestStart() {

                    }

                    @Override
                    public void onRequestEnd() {

                    }
                })
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .builder()
                .download();
    }

    @Override
    public void onProgress(long progress, long total, long speed, boolean done) {
        LatteLogger.i("progress", String.valueOf(progress));
    }
}
